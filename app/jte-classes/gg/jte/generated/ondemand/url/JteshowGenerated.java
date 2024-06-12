package gg.jte.generated.ondemand.url;
import hexlet.code.dto.UrlPage;
public final class JteshowGenerated {
	public static final String JTE_NAME = "url/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,5,5,7,7,7,13,13,13,17,17,17,21,21,21,27,27,27,27,43,43,44,44,47,47,47,50,50,50,53,53,53,56,56,56,59,59,59,62,62,62,65,65,66,66,70,70,70,71,71,71,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\n\n        <table class=\"table table-bordered table-hover mt-3\">\n            <tbody>\n            <tr>\n                <td>ID</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <td>Имя</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\n            </tr>\n            <tr>\n                <td>Дата создания</td>\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().toString());
				jteOutput.writeContent("</td>\n            </tr>\n            </tbody>\n        </table>\n\n        <h2 class=\"mt-5\">Проверки</h2>\n        <form method=\"post\" action=\"/urls/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/checks\">\n            <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\n        </form>\n\n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n                <tr>\n                    <th class=\"col-1\">ID</th>\n                    <th class=\"col-1\">Код ответа</th>\n                    <th>Название страницы</th>\n                    <th>Заголовок h1</th>\n                    <th>Описание</th>\n                    <th class=\"col-2\">Дата проверки</th>\n                </tr>\n            </thead>\n            <tbody>\n                ");
				if (page.getUrlCheck() != null) {
					jteOutput.writeContent("\n                    ");
					for (var urlCheck : page.getUrlCheck()) {
						jteOutput.writeContent("\n                        <tr>\n                            <td>\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getUrlId());
						jteOutput.writeContent("\n                            </td>\n                            <td>\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getStatusCode());
						jteOutput.writeContent("\n                            </td>\n                            <td>\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getTitle());
						jteOutput.writeContent("\n                            </td>\n                            <td>\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getH1());
						jteOutput.writeContent("\n                            </td>\n                            <td>\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getDescription());
						jteOutput.writeContent("\n                            </td>\n                            <td>\n                                ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getCreatedAd().toString());
						jteOutput.writeContent("\n                            </td>\n                        </tr>\n                    ");
					}
					jteOutput.writeContent("\n                ");
				}
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n    ");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
