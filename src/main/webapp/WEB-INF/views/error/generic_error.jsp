<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
	    <title>Exception</title>
	    
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	    
	</head>
	
	<body>
	
	    <div class="container">
	    
	        <h2 align="center" class="text-primary">B2W Desafio Starwars</h2>
	        
	        <hr />
	
	        <!-- Error Code -->
	        
	        <div id="errorcode">
	        
	            <c:if test="${not empty errCode}">
	            
	                <h3 class="text-info">Erro: ${errCode}</h3>
	                
	            </c:if>

	            <c:if test="${empty errCode}">
	            
	                <h3 class="text-info">Ocorreu um erro.</h3>
	                
	            </c:if>
	            
	        </div>
	
	        <!-- Error Message -->

	        <div id="errormessage">
	        
	            <c:if test="${not empty errMsg}">
	            
	                <h4 class="text-danger">${errMsg}</h4>
	                
	            </c:if>
	            
	        </div>
	        
	    </div>
	    
	</body>
	
</html>