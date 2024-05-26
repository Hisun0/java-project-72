package gg.jte.generated.ondemand;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,2,2,27,27,27,27,27,27,27,27};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <section>\n            <div class=\"container-fluid bg-dark p-5\">\n                <div class=\"row\">\n                    <div class=\"col-md-10 col-lg-8 mx-auto text-white\">\n                        <h1 class=\"display-3 mb-0\">Page Analyzer</h1>\n                        <p class=\"lead\">Free to check websites for SEO suitability</p>\n                        <form action=\"/urls\" method=\"post\" class=\"rss-form text-body\">\n                            <div class=\"row\">\n                                <div class=\"col\">\n                                    <div class=\"form-floating\">\n                                        <input id=\"url-input\" autofocus=\"\" type=\"text\" required=\"\" name=\"url\" aria-label=\"url\" class=\"form-control w-100\" placeholder=\"url\" autocomplete=\"off\">\n                                        <label for=\"url-input\">Your link</label>\n                                    </div>\n                                </div>\n                                <div class=\"col-auto\">\n                                    <input type=\"submit\" class=\"h-100 btn btn-lg btn-primary px-sm-5\" value=\"Submit\">\n                                </div>\n                            </div>\n                        </form>\n                        <p class=\"mt-2 mb-0 text-muted\">Example: https://www.example.com</p>\n                    </div>\n                </div>\n            </div>\n        </section>\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
