@import hexlet.code.dto.RootPage
@param RootPage page

@template.layout.page(
    content = @`
        <section>
            <div class="container-fluid bg-dark p-5">
                <div class="row">
                    @if(page.getFlash() != null)
                        <div class="${page.getFlash().get("class")}" role="alert">
                            ${page.getFlash().get("message")}
                        </div>
                    @endif

                    <div class="col-md-10 col-lg-8 mx-auto text-white">
                        <h1 class="display-3 mb-0">Page Analyzer</h1>
                        <p class="lead">Free to check websites for SEO suitability</p>
                        <form action="/urls" method="post" class="rss-form text-body">
                            <div class="row">
                                <div class="col">
                                    <div class="form-floating">
                                        <input id="url-input" autofocus="" type="text" required="" name="url" aria-label="url" class="form-control w-100" placeholder="url" autocomplete="off">
                                        <label for="url-input">Your link</label>
                                    </div>
                                </div>
                                <div class="col-auto">
                                    <input type="submit" class="h-100 btn btn-lg btn-primary px-sm-5" value="Submit">
                                </div>
                            </div>
                        </form>
                        <p class="mt-2 mb-0 text-muted">Example: https://www.example.com</p>
                    </div>
                </div>
            </div>
        </section>
    `
)