<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
                                                    <form class="form-material" action="<%=request.getContextPath() %>/ServletUsuarioController" method="post" >
                                                    
                                                    		<div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id= "id" class="form-control" placeholder="ID" readonly="readonly" value="${loginModel.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${loginModel.nome}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" value="${loginModel.login}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" value="${loginModel.senha}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                            
                                                            <div>
                                                           		 	<button class="btn btn-primary btn-round waves-effect waves-light">Salvar</button>
                                                                	<button class="btn btn-primary btn-round waves-effect waves-light">Alterar</button>
            														<button class="btn btn-danger btn-round waves-effect waves-light">Excluir</button>
                                                            </div>
                                                    </form>
                                                    
                                                    </div>
                                              
                                                 </div>
                                               </div>
                                          </div>
                                          <span>"${msg}"</span>




										<!--  Finaliza divCentral principal -->

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
</body>

</html>