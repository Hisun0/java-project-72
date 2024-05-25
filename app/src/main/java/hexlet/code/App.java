package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repository.BaseRepository;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Slf4j
public class App {
    private static int getPort() {
        var port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }

    private static String getDatabaseUrl() {
        var defaultUrl = "jdbc:h2:mem:project";
        return System.getenv().getOrDefault("JDBC_DATABASE_URL", defaultUrl);
    }

    private static String getDriverName() {
        var defaultDriver = "org.h2.Driver";
        return System.getenv().getOrDefault("DRIVER", defaultDriver);
    }

    private static String readResourceFile() throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream("schema.sql");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static Javalin getApp() throws IOException, SQLException {
        var hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(getDriverName());
        hikariConfig.setJdbcUrl(getDatabaseUrl());

        var dataSource = new HikariDataSource(hikariConfig);
        var sql = readResourceFile();

        // log.info(sql);
        try (var conn = dataSource.getConnection();
                var statement = conn.createStatement()) {
            statement.execute(sql);
        }

        BaseRepository.dataSource = dataSource;

        return Javalin.create(javalinConfig -> javalinConfig.bundledPlugins.enableDevLogging())
                .get("/", context -> context.result("Hello World"));
    }

    public static void main(String[] args) throws SQLException, IOException {
        var app = getApp();
        app.start(getPort());
    }
}
