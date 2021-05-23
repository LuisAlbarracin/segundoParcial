<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team</title>
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
				<a href="#" class="navbar-brand"> Gestión de Equipos </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Equipo</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${team != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${team == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${team != null}">
                                    Editar Equipo
                                </c:if>
						<c:if test="${team == null}">
                                    Agregar Nuevo Equipo
                                </c:if>
					</h2>
				</caption>

				<fieldset class="form-group">
					<label>Id del Equipo</label>
					<input type="hidden" name="id"
						value="<c:out value='${team.id}' />" class="form-control" required="required" />
				</fieldset>>

				<fieldset class="form-group">
					<label>Nombre del Equipo</label> <input type="text"
						value="<c:out value='${team.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Pais del Equipo</label> 
						<select name="pais" required class="form-control">
							<option value="<c:out value='${country.id}' />"><c:out value='${country.name}' /></option>
						</select>
				</fieldset>

				<button type="submit" class="btn btn-success">Guardar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>