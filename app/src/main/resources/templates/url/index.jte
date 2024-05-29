@import hexlet.code.dto.UrlsPage
@param UrlsPage page

@template.layout.page(
content = @`
    <div class="container p-3">
        @if(page.getFlash() != null)
            <div class="${page.getFlash().get("class")}" role="alert">
                ${page.getFlash().get("message")}
            </div>
        @endif
        
        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th class="col-1">ID</th>
                <th>Name</th>
                <th class="col-2">Last check</th>
                <th class="col-1">Response status</th>
            </tr>
            </thead>

            <tbody>
            @for(var url : page.getUrlList())
                <tr>
                    <td>${url.getId()}</td>
                    <td>
                        <a href="/urls/${url.getId()}">${url.getName()}</a>
                    </td>
                    <td>${url.getCreatedAt().toString()}</td>
                    <td>200</td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)