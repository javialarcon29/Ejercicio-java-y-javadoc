<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div style="text-align:center; width: 30%; margin: 0 auto ;">
<br/>
<h2>Formulario de Login</h2>
<br/>
<form action='servletProcesado' method='post'>
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Usuario</label>
    <input type="text" class="form-control"  name="username">
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Contraseña</label>
    <input type="password" class="form-control"  name="password">
  </div>
  <button type="submit" class="btn btn-primary">Entrar</button>
</form>
<div>
<br/>
<label>${requestScope.error }</label>
</div>

</div>
</body>
</html>