package hexlet.code.controller;

import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlsRepository;
import io.javalin.http.Context;
import kong.unirest.core.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLDataException;
import java.sql.SQLTimeoutException;
import java.sql.Timestamp;
import java.util.Map;

import static io.javalin.rendering.template.TemplateUtil.model;

@Slf4j
public class UrlsController {
    public static void create(Context context) {
        try {
            var link = new URI(context.formParam("url")).toURL();
            var scheme = link.getProtocol();
            var hostname = link.getHost();

            String name = scheme + "://" + hostname;
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());

            if (UrlsRepository.findByName(name).isPresent()) {
                throw new SQLDataException("URL already exists!");
            }

            var url = new Url(name, createdAt);
            UrlsRepository.save(url);

            var flashMap = createFlashMap("URL successfully added!", "alert alert-success");
            context.sessionAttribute("flash", flashMap);
            context.redirect("/urls");
        } catch (MalformedURLException | URISyntaxException | IllegalArgumentException err) {
            // ошибка, если введена некорректная ссылка
            var flashMap = createFlashMap("Invalid URL!", "alert alert-danger");
            context.sessionAttribute("flash", flashMap);
            context.redirect("/");
        } catch (SQLTimeoutException e) {
            // ошибка, если база данных не вернула id при сохранении сущности
            log.error(e.getMessage());
        } catch (SQLDataException dataException) {
            // ошибка, если в базе данных уже есть сущность с таким же именем
            var flashMap = createFlashMap(dataException.getMessage(), "alert alert-warning");
            context.sessionAttribute("flash", flashMap);
            context.redirect("/urls");
        }
    }

    public static void showAll(Context context) {
        try {
            var urls = UrlsRepository.getEntities();
            Map<String, String> flash = context.consumeSessionAttribute("flash");
            var page = new UrlsPage(urls, flash);

            context.render("url/index.jte", model("page", page));
        } catch (RuntimeException err) {
            // TODO вывести сообщение о отсутствующих постах
            log.error("Посты не найдены");
        }
    }

    public static void show(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(id).orElseThrow(() -> new RuntimeException(""));

        var page = new UrlPage(url);
        context.render("url/show.jte", model("page", page));
    }

    public static void check(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(id).orElseThrow(RuntimeException::new);

        var response = Unirest.get(url.getName()).asString();

        var document = Jsoup.parse(response.getBody());
        var description = document.selectFirst("meta[name=description]").attr("content");

        var urlCheck = new UrlCheck(
                response.getStatus(),
                document.title(),
                document.selectFirst("h1").text(),
                description,
                url.getId(),
                new Timestamp(System.currentTimeMillis())
        );
        UrlCheckRepository.save(urlCheck);

        var urlChecks = UrlCheckRepository.getEntities();

        var page = new UrlPage(url, urlChecks);
        context.render("url/show.jte", model("page", page));
    }

    // метод для создания флеш сообщения
    private static Map<String, String> createFlashMap(String message, String className) {
        return Map.of("message", message, "class", className);
    }
}
