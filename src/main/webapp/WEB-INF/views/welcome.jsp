<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<title>StarWars</title>
		
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	   
 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 		
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  		
	</head>
	
	<body>
	
		<nav class="navbar navbar-inverse">
		
			<div class="container-fluid">
			
		    	<div class="navbar-header">
		    	
	        		<a class="navbar-brand" href="#">B2W</a>
	        		
				</div>
				
			    <ul class="nav navbar-nav">
			    
			      <li class="active"><a href="list">Home</a></li>
			      
			      <li><a href="add">Adicionar Planeta</a></li>
			      
			      <li><a href="findformid">Buscar por ID</a></li>
			      
			      <li><a href="findformnome">Buscar por nome</a></li>
			      
			    </ul>
			    
			  </div>
			  
			</nav>
			
		<div class="container">
		
			<h2 id="article_header" class="text-warning" align="center">B2W Stars Wars</h2>
			
	    	<div> </div>
	    	
	    	<!-- Table to display the planet list from the mongo database -->
	    	<table id="planet_table" class="table">
	    	
	        	<thead>
	        	
	            	<tr align="center">
	            	
	            		<th>Id</th><th>Nome</th><th>Clima</th><th>Terreno</th><th>Filmes</th><th colspan="2"></th>
	            		
	            	</tr>
	            	
	        	</thead>
	        	
	        	<tbody>
	        	
	            	<c:forEach items="${planetas}" var="planeta">
	            	
	                	<tr align="center">
	                	
	                    	<td><c:out value="${planeta.id}" /></td>
	                    	
	                    	<td><c:out value="${planeta.nome}" /></td>
	                    	
	                    	<td><c:out value="${planeta.clima}" /></td>
	                    	
	                    	<td><c:out value="${planeta.terreno}" /></td>
	                    	
	                    	<td><c:out value="${planeta.filmes}" /></td>
	                    	
	                    	<td>
	                        	<c:url var="editUrl" value="/planeta/edit?id=${planeta.id}" /><a id="update" href="${editUrl}" class="btn btn-warning">Atualizar</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="/planeta/delete?id=${planeta.id}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Deletar</a>
	                    	</td>
	                	</tr>

	            	</c:forEach>
	            	
	        	</tbody>
	        	
	    	</table>
	    	
		</div>
			    
	</body>
	
</html>