package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.model.Url;
import hexlet.code.repository.BaseRepository;
import hexlet.code.repository.UrlsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static hexlet.code.util.Util.readResourceFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public final class RepositoryTest {
    private static HikariDataSource testDataSource;
    private static HikariDataSource originalDataSource;
    private static final List<Url> URLS = new ArrayList<>();

    @BeforeAll
    static void fillURLS() throws IOException, SQLException {
        var url1 = new Url("example", convertStringToTimestamp("2024-05-27 10:42:04"));
        var url2 = new Url("another", convertStringToTimestamp("2024-01-26 16:00:34"));
        var url3 = new Url("onemore", convertStringToTimestamp("2023-03-25 21:21:04"));

        URLS.add(url1);
        URLS.add(url2);
        URLS.add(url3);

        int index = 0;
        for (var url : URLS) {
            url.setId(++index);
        }

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:test");
        var dataSource = new HikariDataSource(hikariConfig);

        originalDataSource = BaseRepository.dataSource;
        BaseRepository.dataSource = dataSource;

        testDataSource = BaseRepository.dataSource;

        log.info("Value of originalDataSource: " + originalDataSource);
        log.info("Value of testDataSource: " + testDataSource);

        var startSql = readResourceFile("test.sql");
        var sql = """
                INSERT INTO URLS (name, created_at) VALUES
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

        var sql = "SELECT * FROM URLS WHERE id = 4";

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
        var url = UrlsRepository.find(URLS.getFirst().getId()).orElseThrow(() -> new SQLException("Не нашел"));
        var expected = URLS.getFirst();

        log.info("expected: {}", expected);
        log.info("actual: {}", url);
        assertEquals(expected, url);
    }

    @Test
    void findByNameTest() throws SQLException {
        var url = UrlsRepository.findByName(URLS.getFirst().getName()).orElseThrow(() -> new SQLException("Не нашел"));
        var expected = URLS.getFirst();

        assertEquals(expected, url);
    }

    @Test
    void getEntitiesTest() throws SQLException {
        var repositoryURLS = UrlsRepository.getEntities();

        assertEquals(URLS, repositoryURLS);
    }

    @AfterEach
    void clearDatabase() throws IOException, SQLException {
        var startSql = readResourceFile("test.sql");
        var sql = """
                INSERT INTO URLS (name, created_at) VALUES
                ('example', '2024-05-27 10:42:04'),
                ('another', '2024-01-26 16:00:34'),
                ('onemore', '2023-03-25 21:21:04')""";

        try (var conn = testDataSource.getConnection();
             var statement = conn.createStatement()) {
            statement.execute(startSql);
            statement.executeUpdate(sql);
        }
    }

    @AfterAll
    static void backUp() {
        BaseRepository.dataSource = originalDataSource;
    }

    private static Timestamp convertStringToTimestamp(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
        return Timestamp.valueOf(localDateTime);
    }
}
