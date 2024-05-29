package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.model.Url;
import hexlet.code.repository.BaseRepository;
import hexlet.code.repository.UrlsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static hexlet.code.util.Util.readResourceFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public final class RepositoryTest {
    private HikariDataSource testDataSource;
    private HikariDataSource originalDataSource;

    @BeforeEach
    void setUp() throws SQLException, IOException {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:test");
        var dataSource = new HikariDataSource(hikariConfig);

        originalDataSource = BaseRepository.dataSource;
        BaseRepository.dataSource = dataSource;

        testDataSource = BaseRepository.dataSource;

        var startSql = readResourceFile("test.sql");
        var sql = """
                INSERT INTO urls (name, created_at) VALUES
                ('example', '2024-05-27 10:42:04'),
                ('another', '2024-01-26 16:00:34'),
                ('onemore', '2023-03-25 21:21:04')""";

        try (var conn = dataSource.getConnection();
                var statement = conn.createStatement()) {
            statement.execute(startSql);
            statement.executeUpdate(sql);
        }
    }

    @Test
    void saveTest() throws SQLException {
        var startUrl = new Url("https://ru.hexlet.io", new Timestamp(System.currentTimeMillis()));
        startUrl.setId(4);

        UrlsRepository.save(startUrl);

        var sql = "SELECT * FROM urls WHERE id = 4";

        try (var connection = testDataSource.getConnection();
                var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            log.info("Получаю resultSet: {}", resultSet.next());

            var name = resultSet.getString("name");
            var id = resultSet.getLong("id");
            var createdAt = resultSet.getTimestamp("created_at");
            var endUrl = new Url(name, createdAt);
            endUrl.setId(id);

            log.info("Стартовый URL: {}", startUrl);
            log.info("Конечный URL: {}", endUrl);
            assertEquals(startUrl, endUrl);
        }
    }

    @Test
    void findTest() throws SQLException {
        String dateTimeString = "2024-05-27 10:42:04";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        long id = 1;
        var url = UrlsRepository.find(id).orElseThrow(() -> new SQLException("Не нашел"));
        var expected = new Url("example", timestamp);
        expected.setId(id);

        assertEquals(expected, url);
    }

    @Test
    void findByNameTest() throws SQLException {
        String dateTimeString = "2024-05-27 10:42:04";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        String name = "example";
        long id = 1;
        var url = UrlsRepository.findByName(name).orElseThrow(() -> new SQLException("Не нашел"));
        var expected = new Url(name, timestamp);
        expected.setId(id);

        assertEquals(expected, url);
    }

    @AfterEach
    void backUp() {
        BaseRepository.dataSource = originalDataSource;
    }
}
