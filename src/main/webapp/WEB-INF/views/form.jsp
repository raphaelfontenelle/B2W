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
  		
  		<style type="text/css">
	        .errormsg {
	            color: red;
	        }
	    </style>
	    
	</head>
	
	<body>
	
		<nav class="navbar navbar-inverse">
		
  			<div class="container-fluid">
  			
		    <div class="navbar-header">
		    
		     	<a class="navbar-brand" href="#">B2W</a>
		     	
		    </div>

		    <ul class="nav navbar-nav">
		    
		    	<li><a href="list">Home</a></li>
		    	
		     	<li class="active"><a href="add">Adicionar Planeta</a></li>
		     	
		      	<li><a href="findformid">Buscar por ID</a></li>
		      	
		     	<li><a href="findformnome">Buscar por nome</a></li>
		     	
		    </ul>
		    
		  </div>
		  
		</nav>
	
	    <div class="container">
	    
	    	<h3 id="form_header" class="text-warning" align="center">Adiciona Planeta</h3>
	    	
	        <div> </div>
	
			<!-- Planet input form to add a new planet or update the existing planet-->
			
	        <c:url var="saveUrl" value="/planeta/save" />
	        
	        <form:form id="planeta_form" modelAttribute="planetAttr" method="POST" action="${saveUrl}">
	        
	        	<form:hidden path="id" />
   	
	            <label for="planet_name">Nome: </label>
	            
	            <small><form:errors path="nome" cssClass="errormsg" /></small>
	            
	            <form:input id="planet_name" cssClass="form-control" path="nome" />     
	    
	            <label for="planet_clima">Clima: </label>
	         	
	         	<small><form:errors path="clima" cssClass="errormsg" /></small>
	         	
	            <form:input id="planet_clima" cssClass="form-control" path="clima" />
	            
	            <label for="planet_terreno">Terreno: </label>
	            
	            <small><form:errors path="terreno" cssClass="errormsg" /></small>
	            
	            <form:input id="planet_terreno" cssClass="form-control" path="terreno" />
	      
	            <form:hidden id="planet_filmes" cssClass="form-control" path="filmes" />
	           
	            <div> </div>

	            <button id="saveBtn" type="submit" class="btn btn-primary">Salvar</button>
	            
	        </form:form>
	        
	    </div>
	    
	</body>
	
</html>