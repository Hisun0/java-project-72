package hexlet.code.controller;

import hexlet.code.dto.RootPage;
import io.javalin.http.Context;

import java.util.Map;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void index(Context context) {
        Map<String, String> flash = context.consumeSessionAttribute("flash");
        context.render("index.jte", model("page", new RootPage(flash)));
    }
}
