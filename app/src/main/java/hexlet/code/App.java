package hexlet.code;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    private static int getPort() {
        var port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }

    public static Javalin getApp() {
        return Javalin.create(javalinConfig -> javalinConfig.bundledPlugins.enableDevLogging())
                .get("/", context -> context.result("Hello World"));
    }

    public static void main(String[] args) {
        var app = getApp();
        app.start(getPort());
    }
}
