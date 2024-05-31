package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UrlCheckRepository extends BaseRepository {
    public static void save(UrlCheck urlCheck) {
        String sql = """
                INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) VALUES
                (?, ?, ?, ?, ?, ?)
                """;

        try (var conn = dataSource.getConnection();
                var statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, urlCheck.getUrlId());
            statement.setInt(2, urlCheck.getStatusCode());
            statement.setString(3, urlCheck.getH1());
            statement.setString(4, urlCheck.getTitle());
            statement.setString(5, urlCheck.getDescription());
            statement.setTimestamp(6, urlCheck.getCreatedAd());

            statement.executeUpdate();

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLTimeoutException("DB not returned id when save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<UrlCheck> findAll(long urlId) {
        String sql = "SELECT * FROM url_checks WHERE url_id = ?";
        var result = new ArrayList<UrlCheck>();

        try (var conn = dataSource.getConnection();
                var statement = conn.createStatement()) {
            var resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");

                var urlCheck = new UrlCheck(statusCode, h1, title, description, urlId, createdAt);
                result.add(urlCheck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
