@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Document</title>
</head>
<body class="d-flex flex-column min-vh-100">
    <header class="mb-auto bg-dark">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Page analyzer</a>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <div class="navbar-nav">
                        <a class="nav-link" aria-current="page" href="/">Home</a>
                        <a class="nav-link" href="/pages">Pages</a>
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <main class="flex-grow-1">${content}</main>
    <footer class="footer bg-dark py-3 mt-5">
        <div class="container-xl">
            <div class="text-center text-white">
                Created by <a href="https://github.com/Hisun0" class="link-light">Rodion</a>
            </div>
        </div>
    </footer>
</body>
</html>