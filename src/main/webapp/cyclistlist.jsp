<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ciclistas</title>
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
	<br />

	<div class="container mt-2">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="row">
			<h3 class="text-center">Listado de Ciclistas</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Agregar
					Nuevo Ciclista</a>
			</div>
			<br>
			<table class="table table-bordered mt-3">
				<thead>
					<tr>
						<th class="text-center">Id</th>
						<th class="text-center">Nombre</th>
						<th class="text-center">Fecha Nacimiento</th>
						<th class="text-center">Pais</th>
						<th class="text-center">Equipo</th>
						<th class="text-center">&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="ciclista" items="${listCyclist}">

						<tr>
							<td><c:out value="${ciclista.id}" /></td>
							<td><c:out value="${ciclista.name}" /></td>
							<td><c:out value="${ciclista.birthday}" /></td>
							<td><c:out value="${ciclista.country}" /></td>
							<td><c:out value="${ciclista.team}" /></td>
							
							<td class="d-flex justify-content-center"><button class="p-1 mb-1 bg-warning btn-sm text-dark"><a href="edit?id=<c:out value='${ciclista.id}' />" class="text-decoration-none">Editar</a></button>
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="p-1 mb-1 bg-danger btn-sm text-white"><a
								href="delete?id=<c:out value='${ciclista.id}' />" class="text-decoration-none">Eliminar</a></button></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>