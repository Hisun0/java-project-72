package gg.jte.generated.ondemand.url;
import hexlet.code.dto.UrlsPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "url/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,5,5,7,7,8,8,8,8,8,8,8,8,8,9,9,9,11,11,24,24,26,26,26,28,28,28,28,28,28,28,30,30,30,33,33,37,37,37,37,37,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container p-3\">\n        ");
				if (page.getFlash() != null) {
					jteOutput.writeContent("\n            <div");
					var __jte_html_attribute_0 = page.getFlash().get("class");
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" class=\"");
						jteOutput.setContext("div", "class");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("div", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(" role=\"alert\">\n                ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(page.getFlash().get("message"));
					jteOutput.writeContent("\n            </div>\n        ");
				}
				jteOutput.writeContent("\n        \n        <table class=\"table table-bordered table-hover mt-3\">\n            <thead>\n            <tr>\n                <th class=\"col-1\">ID</th>\n                <th>Name</th>\n                <th class=\"col-2\">Last check</th>\n                <th class=\"col-1\">Response status</th>\n            </tr>\n            </thead>\n\n            <tbody>\n            ");
				for (var url : page.getUrlList()) {
					jteOutput.writeContent("\n                <tr>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\n                    <td>\n                        <a href=\"/urls/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(url.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a>\n                    </td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getCreatedAt().toString());
					jteOutput.writeContent("</td>\n                    <td>200</td>\n                </tr>\n            ");
				}
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
