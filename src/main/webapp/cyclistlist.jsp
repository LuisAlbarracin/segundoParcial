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
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
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

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Listado de Ciclistas</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/cyclist/new" class="btn btn-success">Agregar
					Nuevo Ciclista</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Email</th>
						<th>Cumpleaños</th>
						<th>Pais</th>
						<th>Equipo</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="ciclista" items="${listCyclist}">

						<tr>
							<td><c:out value="${ciclista.id}" /></td>
							<td><c:out value="${ciclista.name}" /></td>
							<td><c:out value="${ciclista.email}" /></td>
							<td><c:out value="${ciclista.birthdate}" /></td>
							<td><c:out value="${ciclista.country}" /></td>
							<td><c:out value="${ciclista.team}" /></td>
							<td><a href="edit?id=<c:out value='${ciclista.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${ciclista.id}' />">Eliminar</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>