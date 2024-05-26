package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.model.Url;
import hexlet.code.repository.BaseRepository;
import hexlet.code.repository.UrlsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import static hexlet.code.util.Util.readResourceFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class RepositoryTest {
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

        var sql = readResourceFile();

        try (var conn = dataSource.getConnection();
                var statement = conn.createStatement()) {
            statement.execute(sql);
        }
    }

    @Test
    void saveTest() throws SQLException {
        var startUrl = new Url("https://ru.hexlet.io", new Timestamp(System.currentTimeMillis()));
        startUrl.setId(1);

        UrlsRepository.save(startUrl);

        var sql = "SELECT * FROM urls";
        var connection = testDataSource.getConnection();
        var statement = connection.createStatement();
        var resultSet = statement.executeQuery(sql);
        log.info("Получаю resultSet: {}", resultSet.next());

        var name = resultSet.getString("name");
        var createdAt = resultSet.getTimestamp("created_at");
        var endUrl = new Url(name, createdAt);
        endUrl.setId(1);

        log.info("Стартовый URL: {}", startUrl);
        log.info("Конечный URL: {}", endUrl);
        assertEquals(startUrl, endUrl);
    }

    @AfterEach
    void backUp() {
        BaseRepository.dataSource = originalDataSource;
    }
}
