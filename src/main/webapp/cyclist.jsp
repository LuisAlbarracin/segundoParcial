<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ciclista</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-info text-white">
			<div>
				<a href="#" class="navbar-brand"> Gestión de Ciclistas </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Ciclista</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${cyclist != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${cyclist == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${cyclist != null}">
                                    Editar Ciclista
                                </c:if>
						<c:if test="${cyclist == null}">
                                    Agregar Nuevo Ciclista
                                </c:if>
					</h2>
				</caption>

				<c:if test="${cyclist != null}">
					<input type="hidden" name="id"
						value="<c:out value='${cyclist.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nombre</label> <input type="text"
						value="<c:out value='${cyclist.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${cyclist.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Fecha Nacimiento</label> <input type="date"
						value="<c:out value='${cyclist.birthday}' />"
						class="form-control" name="birthday" />
				</fieldset>

				<fieldset class="form-group">
					<label>Pais</label> <input type="text"
						value="<c:out value='${cyclist.country}' />" class="form-control"
						name="country" />
				</fieldset>

				<fieldset class="form-group">
					<label>Equipo</label> <input type="text"
						value="<c:out value='${cyclist.team}' />" class="form-control"
						name="team" />
				</fieldset>

				<div class="d-flex justify-content-end">
					<button type="submit" class="btn btn-success mt-3 float-right">Guardar</button>
				</div>
				
				</form>
			</div>
		</div>
	</div>
</body>
</html>