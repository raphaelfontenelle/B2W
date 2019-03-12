<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
		    	
		      		<li><a href="list">Home</a></li>
		      		
		      		<li><a href="add">Adicionar Planeta</a></li>
		      		
		      		<li><a href="findformid">Buscar por ID</a></li>
		      		
		      		<li class="active"><a href="findformnome">Buscar por nome</a></li>
		      		
		    	</ul>
		    	
		  	</div>
		  	
		</nav>

		<div class="container">
		
			<h3 id="form_header" class="text-warning" align="center">Busca por nome</h3>
			
		    <div> </div>
		        
			<div class="form-group">
			
		    	<form action = "findname" method = "GET">
		        
		            <label for="planet_name">Nome: </label>
		            
		            <input type = "text" name = "nome" class="form-control">
		           
		            <div> </div>
	
		            <button id="saveBtn" type="submit" class="btn btn-primary">Buscar</button>
		            
		        </form>
		        
		     </div>
		     
		  </div>
		  
	</body>
	
</html>