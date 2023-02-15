<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang= "en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Projeto Java Jsp</title>

<style type="text/css">

h1 {
	position: absolute;
	<!--font-family: Arial, Helvetica, sans-serif;-->
	font-style:bold;
	top: 30%;
	left: 33%;
	right: 33%
}
form {
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%
}

h5 {
	position: absolute;
	top: 69%;
	left: 43%;
	right: 33%
	color: #664d03;
    background-color: #fff3cd;
    border-color: #ffecb5;


</style>
</head>
<body>

	<h1 class="topo">Login JSP</h1>

	<form action="ServletLogin" method="post" class="row g-3 row g-3 needs-validation" novalidate>
	<input type="hidden" value="<%= request.getParameter("url") %>" name="url">

		<div class="mb-6">
			<label class="form-label">Login</label> 
			<input name="login" type="text" class="form-control" aria-describedby="inputGroupPrepend" id="nome" required>
			 <div class="invalid-feedback">
       			 Campo obrigatorio!
     		 </div>
		
		</div>
		

			<div class="mb-6">
				<label class="form-label">Senha</label> 
				<input name="senha" type="password" class="form-control" aria-describedby="inputGroupPrepend" id="senha" required>
				 <div class="invalid-feedback">
       			 Campo obrigatorio!
     		 </div>
			</div>
			
			
			<div class="col-12">
			<input type="submit" class="btn btn-primary" value=Entrar >
			
			</div>
			
	</form>
		<h5>${msg}</h5>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script type="text/javascript">
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()
	
	
	</script>
</body>
</html>