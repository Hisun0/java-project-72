package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.BaseRepository;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static hexlet.code.Util.convertStringToTimestamp;
import static hexlet.code.util.Util.readResourceFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class UrlCheckRepositoryTest {
    private static HikariDataSource testDataSource;
    private static final List<Url> URLS = new ArrayList<>();
    private static final List<UrlCheck> URL_CHECKS = new ArrayList<>();

    @BeforeAll
    static void fillURLS() throws IOException, SQLException {
        var urls = new ArrayList<>(List.of(
                new Url(1, "example", convertStringToTimestamp("2024-05-27 10:42:04")),
                new Url(2, "another", convertStringToTimestamp("2024-01-26 16:00:34")),
                new Url(3, "onemore", convertStringToTimestamp("2023-03-25 21:21:04"))
        ));

        var urlChecks = new ArrayList<>(List.of(
                new UrlCheck(1, 200, "Title", "Header 1", "Description", 1, convertStringToTimestamp("2023-03-25 23:21:04")),
                new UrlCheck(2, 200, "Title", "Header 1", "Description", 1, convertStringToTimestamp("2023-03-25 23:25:04")),
                new UrlCheck(3, 200, "Another Title", "Another Header 1", "Another Description", 2, convertStringToTimestamp("2024-01-29 15:25:04"))
        ));

        URLS.addAll(urls);
        URL_CHECKS.addAll(urlChecks);

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:test");

        BaseRepository.dataSource = new HikariDataSource(hikariConfig);
        testDataSource = BaseRepository.dataSource;

        log.info("testDataSource: " + testDataSource);

        var startSql = readResourceFile("test.sql");

        try (var conn = testDataSource.getConnection();
             var statement = conn.createStatement()) {
            statement.execute(startSql);
        }
    }

    @Test
    void saveTest() throws SQLException {
        var startUrl = new UrlCheck(4, 200, "New title", "new Header 1", "new Description", 3, convertStringToTimestamp("2023-03-29 11:25:04"));

        UrlCheckRepository.save(startUrl);

        var sql = "SELECT * FROM urls WHERE id = 4";

        try (var connection = testDataSource.getConnection();
             var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);

            var id = resultSet.getLong("id");
            var urlId = resultSet.getLong("url_id");
            var statusCode = resultSet.getInt("status_code");
            var h1 = resultSet.getString("h1");
            var title = resultSet.getString("title");
            var description = resultSet.getString("description");
            var createdAt = resultSet.getTimestamp("created_at");

            var endUrl = new UrlCheck(id, statusCode, title, h1, description, urlId, createdAt);

            assertEquals(startUrl, endUrl);
        }
    }

    @Test
    void findAllTest() {
        var expected = URL_CHECKS.subList(0, 2);
        var actual = UrlCheckRepository.findAll(1);

        assertEquals(expected, actual);
    }

    @AfterEach
    void clearDatabase() throws IOException, SQLException {
        var startSql = readResourceFile("test.sql");

        try (var conn = testDataSource.getConnection();
             var statement = conn.createStatement()) {
            statement.execute(startSql);
        }
    }
}
