@import hexlet.code.dto.UrlPage
@param UrlPage page

@template.layout.page(
    content = @`
    <div class="container-lg mt-5">
        <h1>Сайт: ${page.getUrl().getName()}</h1>

        <table class="table table-bordered table-hover mt-3">
            <tbody>
            <tr>
                <td>ID</td>
                <td>${page.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${page.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Дата создания</td>
                <td>${page.getUrl().getCreatedAt().toString()}</td>
            </tr>
            </tbody>
        </table>

        <h2 class="mt-5">Проверки</h2>
        <form method="post" action="/urls/${page.getUrl().getId()}/checks">
            <button type="submit" class="btn btn-primary">Запустить проверку</button>
        </form>

        <table class="table table-bordered table-hover mt-3">
            <thead>
                <tr>
                    <th class="col-1">ID</th>
                    <th class="col-1">Код ответа</th>
                    <th>Название страницы</th>
                    <th>Заголовок h1</th>
                    <th>Описание</th>
                    <th class="col-2">Дата проверки</th>
                </tr>
            </thead>
            <tbody>

            <tr>
                <td>
                    1
                </td>
                <td>
                    200
                </td>
                <td>
                    Анализатор страниц
                </td>
                <td>
                    Анализатор страниц
                </td>
                <td>

                </td>
                <td>
                    29/05/2024 18:37
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    `
)
