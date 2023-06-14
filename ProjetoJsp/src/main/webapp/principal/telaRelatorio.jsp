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

										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">
														<h5>Relatório Usuário</h5>
													</div>
													<div class="card-block">

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formUser">
															
															<input type="hidden" id="acaoRelatorio" name="acao" value="impriRelatorioUser">

															<!-- Inicio campo data-filtro -->
															
															<div class="form-row align-items-center">
																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="dataInicial">Data Inicial</label>
																	<input type="text" value="${dataInicial}" class="form-control" placeholder="Data Inicial"
																		id="dataInicial" name="dataInicial">
																</div>
																
																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="dataFinal">Data Final</label>
																	<input type="text" value="${dataFinal}" class="form-control" placeholder="Data Final"
																		id="dataFinal" name="dataFinal">
																</div>
																
																<div class="col-auto my-1">
																	<button type="button" onclick="ImprimirTela();" class="btn btn-primary btn-round waves-effect waves-light">Relatorio Tela</button>
																	<button type="button" onclick="ImprimirPdf();" class="btn btn-primary btn-round waves-effect waves-light">Relatorio PDF</button>
																</div>
															</div>

															<!-- Fim campo data-filtro -->
															</form>
															<!-- Inicio tabela dados -->

														<div style="height: 300px; overflow: scroll;">
															<table class="table table-striped"
																id="tabelaBuscaUsuario">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Nome</th>
																		<th scope="col">Login</th>

																	</tr>
																</thead>
																<tbody>
																	<c:forEach items='${listTela}' var='listarUsuarios'>
																		<tr>
																			<td><c:out value='${listarUsuarios.id}'></c:out></td>
																			<td><c:out value='${listarUsuarios.nome}'></c:out></td>
																			<td><c:out value='${listarUsuarios.login}'></c:out></td>
																	</c:forEach>

																</tbody>
															</table>

														</div>



														<!-- Final tabela dados -->



													</div>
												</div>
											</div>
										</div>

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="javaScriptFile.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	$( function() {
		  
		  $("#dataInicial").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	} );
	
	$( function() {
		  
		  $("#dataFinal").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	} );
	
	function ImprimirTela() {
		document.getElementById("acaoRelatorio").value='impriRelatorioUser';
		 $("#formUser").submit();
		
	}
	
	function ImprimirPdf(){
		document.getElementById("acaoRelatorio").value='impriRelatorioPDF';
		 $("#formUser").submit();
		
	}
	
	</script>
	
</body>

</html>