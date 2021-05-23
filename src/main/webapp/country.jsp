<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Country</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand"> Gestión de Paises </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Pais</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${country != null}">
					<form action="/update" method="post">
				</c:if>
				<c:if test="${country == null}">
					<form action="/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${country != null}">
                                    Editar Equipo
                                </c:if>
						<c:if test="${country == null}">
                                    Agregar Nuevo Equipo
                                </c:if>
					</h2>
				</caption>

				<fieldset class="form-group">
					<label for="id">Id del Pais</label>
					<input type="text" name="id" value="<c:out value='${country.id}' />" class="form-control" required="required" />
				</fieldset>>

				<fieldset class="form-group">
					<label>Nombre del Pais</label> <input type="text"
						value="<c:out value='${country.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Guardar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>