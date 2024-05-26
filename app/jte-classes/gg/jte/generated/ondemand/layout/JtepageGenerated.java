package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,27,27,27,27,36,36,36,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\"\n          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n    <title>Document</title>\n</head>\n<body class=\"d-flex flex-column min-vh-100\">\n    <header class=\"mb-auto bg-dark\">\n        <nav class=\"navbar navbar-expand-lg navbar-dark\">\n            <div class=\"container-fluid\">\n                <a class=\"navbar-brand\" href=\"/\">Page analyzer</a>\n                <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n                    <div class=\"navbar-nav\">\n                        <a class=\"nav-link\" aria-current=\"page\" href=\"/\">Home</a>\n                        <a class=\"nav-link\" href=\"/pages\">Pages</a>\n                    </div>\n                </div>\n            </div>\n        </nav>\n    </header>\n    <main class=\"flex-grow-1\">");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("</main>\n    <footer class=\"footer bg-dark py-3 mt-5\">\n        <div class=\"container-xl\">\n            <div class=\"text-center text-white\">\n                Created by <a href=\"https://github.com/Hisun0\" class=\"link-light\">Rodion</a>\n            </div>\n        </div>\n    </footer>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, content);
	}
}
