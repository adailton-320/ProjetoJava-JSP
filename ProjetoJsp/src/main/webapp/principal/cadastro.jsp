<%@page import="model.LoginModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmenu.jsp"></jsp:include>


					<div class="pcoded-content">

						<!-- Page-header start -->
						<jsp:include page="pageHeader.jsp"></jsp:include>
						<!-- Page-header end -->

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<!-- Inicia divCentral principal -->

										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">
														<h5>Cadastro Usuario</h5>
													</div>
													<div class="card-block">
														<form class="form-material"
															enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" placeholder="ID"
																	readonly="readonly" value="${loginModel.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID</label>
															</div>
															
															<div class="form-group form-default">
																<input type="text" name="dataCadastro" id="dataCadastro"
																	class="form-control" required="required"
																	value="${loginModel.dataCadastro}"> <span
																	class="form-bar"></span> <label class="float-label">Data Cadastro</label>
															</div>
															
															 

															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${loginModel.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome</label>
															</div>

															<div class="form-group form-default">
																<select class="form-control" name="perfil">
																	<option disabled="disabled">[Selecione o
																		perfil]</option>

																	<option value="AUXILIAR"
																		<%LoginModel loginModel= (LoginModel) request.getAttribute("loginModel");
																		if(loginModel != null && loginModel.getPerfil().equals("AUXILIAR")){
																			out.print("");
																			out.print("selected= \"selected\"");
																			out.print("");
																		}
																		%>>Auxiliar</option>

																	<option value="PROFISSIONAL"
																		<%	loginModel= (LoginModel) request.getAttribute("loginModel");
																			if(loginModel != null && loginModel.getPerfil().equals("PROFISSIONAL")){
																			out.print("");
																			out.print("selected= \"selected\"");
																			out.print("");
																		} %>>Profissional</option>
																		
																	<option value="ADM"
																		<%loginModel= (LoginModel) request.getAttribute("loginModel");
																			if(loginModel != null && loginModel.getPerfil().equals("ADM")){
																			out.print("");
																			out.print("selected= \"selected\"");
																			out.print("");
																			out.print("");
																			}%>>Administrador</option>
																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil</label>


															</div>

															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	value="${loginModel.login}"> <span
																	class="form-bar"></span> <label class="float-label">Login</label>
															</div>



															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	value="${loginModel.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha</label>
															</div>
															
															<div class="form-group form-default input-group mb-4">
																<div class= "input-group-prepend">
																<c:if test="${loginModel.userFoto != '' && loginModel.userFoto != null}">
																	<a href= "<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${loginModel.id}">
																		<img alt="Imagem User" id="fotoEmbase64" src=" ${loginModel.userFoto}" width="70px">
																	</a>
																</c:if>	
																<c:if test="${loginModel.userFoto == '' || loginModel.userFoto == null}">
																	
																	<img alt="Imagem User" id="fotoEmbase64" src="assets\images\user.jpeg" width="70px">
																</c:if>
																</div>
																<input type="file" accept="image/*" id="fileFoto" name="fileFoto" onchange="visualizarImg('fotoEmbase64','fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px; margin-right: 5px;">
															</div>
															
															<button type="button"
																class="btn btn-success btn-round waves-effect waves-light"
																onclick="limparForm();">Limpar</button>
															
															<button
																class="btn btn-primary btn-round waves-effect waves-light">Salvar</button>
															
															<button type="button"
																class="btn btn-primary btn-round waves-effect waves-light"
																data-toggle="modal" data-target="#pesquisarModal">
																Pesquisar</button>
															<button type="button"
																class="btn btn-danger btn-round waves-effect waves-light"
																onclick="excluirUser();">Excluir</button>

														</form>

													</div>

												</div>
											</div>
										</div>
										<span id="msg">"${msg}"</span>

										<div style="height: 300px; overflow: scroll;">
											<table class="table table-striped" id="tabelaBuscaUsuario">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>
														<th scope="col">Login</th>
														<th scope="col">Ver</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items='${listarUsuarios}' var='listarUsuarios'>
														<tr>
															<td><c:out value='${listarUsuarios.id}'></c:out></td>
															<td><c:out value='${listarUsuarios.nome}'></c:out></td>
															<td><c:out value='${listarUsuarios.login}'></c:out></td>
															<td><a
																class="btn btn-info btn-round waves-effect waves-light"
																href="<%=request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${listarUsuarios.id}">Alterar</a></td>
														</tr>
													</c:forEach>

												</tbody>
											</table>

										</div>



										<!--  Finaliza divCentral principal -->

									</div>
									<!-- Page-body end -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="javaScriptFile.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="pesquisarModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Campo de busca</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							id="buscarNomes" aria-label="nome"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button"
								onclick="buscarNome();">Buscar</button>
						</div>
					</div>

					<div style="height: 300px; overflow: scroll;">
						<table class="table table-striped" id="tabelaBuscaUsuario">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Login</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>

					<span id="totalBusca"></span>


				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn btn-secondary btn-round waves-effect waves-light"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	$( function() {
		  
		  $("#dataCadastro").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Pr�ximo',
			    prevText: 'Anterior'
			});
	} );
	
	
	function visualizarImg(fotoEmbase64 ,fileFoto) {
		
		var preview= document.getElementById(fotoEmbase64);
		var fileUser= document.getElementById(fileFoto).files[0];
		var reader= new FileReader();
		
		reader.onloadend= function(){
			preview.src= reader.result;/*Carrega a foto*/
		};
		
		if(fileUser){
			reader.readAsDataURL(fileUser);/*Preview da imagem*/
			
		}else{
			preview.src='';
		}
	}
	
		function buscarNome() {
			var buscarNome = document.getElementById('buscarNomes').value;

			if (buscarNome != null && buscarNome != '' && buscarNome.trim != '') {

				var acaoUrlForm = document.getElementById('formUser').action;

				$
						.ajax(
								{
									method : "get",
									url : acaoUrlForm,
									data : "buscarNomes=" + buscarNome
											+ '&acao=buscarUserAjax',
									success : function(response) {

										var json = JSON.parse(response);

										console.info(json);
										$('#tabelaBuscaUsuario > tbody > tr')
												.remove();

										for (var i = 0; i < json.length; i++) {
											$('#tabelaBuscaUsuario > tbody')
													.append(
															'<tr> <td>'
																	+ json[i].id
																	+ '</td> <td> '
																	+ json[i].nome
																	+ '</td><td> '
																	+ json[i].login
																	+ '</td> <td><button onclick=editarVer('
																	+ json[i].id
																	+ ') type="button" class="btn btn-info">Alterar</button></td></tr>');
										}

										document.getElementById('totalBusca').textContent = 'Resultados: '
												+ json.length;

									}

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao buscar usu�rio por nome: '
											+ xhr.responseText);
								});

			}
		}
		function editarVer(id) {
			var acaoUrlForm = document.getElementById('formUser').action;
			window.location.href = acaoUrlForm + '?acao=buscarEditar&id=' + id;

		}

		function excluirUser() {
			if (confirm('Deseja realmente excluir?')) {

				document.getElementById('formUser').method = 'get';
				document.getElementById('acao').value = 'deletar';
				document.getElementById('formUser').submit();

			}
		}

		function criarDeleteComAjax() {

			if (confirm('Deseja realmente excluir os dados?')) {

				var acaoUrlForm = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({

					method : "get",
					url : acaoUrlForm,
					data : "id=" + idUser + '&acao=deletarAjax',
					success : function(response) {

						limparForm();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar usu�rio por id: '
									+ xhr.responseText);
						});
			}
		}

		function limparForm() {

			var elementos = document.getElementById('formUser').elements;
			for (i = 0; i < elementos.length; i++) {
				elementos[i].value = '';

			}

		}
	</script>



</body>

</html>