package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDaoRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.LoginModel;
import util.ReportUtil;
@MultipartConfig
@WebServlet(urlPatterns = { "/principal/ServletUsuarioController", "/ServletUsuarioController" })
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private UsuarioDaoRepository usuarioDaoRepository = new UsuarioDaoRepository();
	String msg = "Usuario salvo com sucesso!";

	public ServletUsuarioController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");

				usuarioDaoRepository.deletarUsuario(idUser);
				
				List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios(super.getUserLogado(request));
				request.setAttribute("listarUsuarios", listarUsuarios);

				request.setAttribute("msg", "Excluido com sucesso!");
				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);

			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {

				String idUser = request.getParameter("id");
				usuarioDaoRepository.deletarUsuario(idUser);
				response.getWriter().write("Excluido com sucesso!");

			} 
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("buscarNomes");
				List<LoginModel> dadosJsonUser = usuarioDaoRepository.consultarUsuarioList(nomeBusca, super.getUserLogado(request));

				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);

			}
			
			else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id= request.getParameter("id");
				LoginModel redirecionarUser= usuarioDaoRepository.consultarUsuarioId(id, super.getUserLogado(request));
				
				List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios(super.getUserLogado(request));
				request.setAttribute("listarUsuarios", listarUsuarios);
				
				request.setAttribute("msg", "Usuario em edição");
				request.setAttribute("loginModel", redirecionarUser);
				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);

				

			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuarios")) {
				
				List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuarios carregados");
				request.setAttribute("listarUsuarios", listarUsuarios);
				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
				
				
			
				}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")){
					String idUser = request.getParameter("id");
					LoginModel loginModel= usuarioDaoRepository.consultarUsuarioId(idUser, super.getUserLogado(request));
						if(loginModel.getUserFoto()!= null && !loginModel.getUserFoto().isEmpty()) {
							response.setHeader("Content-Disposition", "attachment;filename=arquivo."+ loginModel.getExtencaoFoto());
							response.getOutputStream().write(new Base64().decodeBase64(loginModel.getUserFoto().split("\\,")[1]));
							
						}
				
				
				
				}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("impriRelatorioUser")){

						String dataInicial= request.getParameter("dataInicial");
						String dataFinal= request.getParameter("dataFinal");
						
						if(dataInicial== null || dataInicial.isEmpty() 
								&& dataFinal== null || dataFinal.isEmpty()) {
							
							request.setAttribute("listTela", usuarioDaoRepository.listarUsuariosRelatorio(super.getUserLogado(request)));
						}else {
							request.setAttribute("listTela", usuarioDaoRepository
									.listarUsuariosRelatorio(super.getUserLogado(request), dataInicial, dataFinal));
							
						}
						
						request.setAttribute("dataInicial", dataInicial);
						request.setAttribute("dataFinal", dataFinal);

						request.getRequestDispatcher("principal/telaRelatorio.jsp").forward(request, response);
				
				
		}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("impriRelatorioPDF")){
			
			String dataInicial= request.getParameter("dataInicial");
			String dataFinal= request.getParameter("dataFinal");
			
			List<LoginModel> loginModels=null;
			
			if(dataInicial== null || dataInicial.isEmpty() 
					&& dataFinal== null || dataFinal.isEmpty()) {
				
				loginModels= usuarioDaoRepository.listarUsuariosRelatorio(super.getUserLogado(request));
			}else {
				
				loginModels= usuarioDaoRepository.listarUsuariosRelatorio(super.getUserLogado(request), dataInicial, dataFinal);
				
			}
			
			byte[] relatorioPDF= new ReportUtil().geraRelatorioPDF(loginModels, "relatorioUserJsp", request.getServletContext());
			response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
			response.getOutputStream().write(relatorioPDF);
			
		}else{
				
					List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios(super.getUserLogado(request));
					request.setAttribute("listarUsuarios", listarUsuarios);
				    request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String perfil= request.getParameter("perfil");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String dataCadastro= request.getParameter("dataCadastro");

			LoginModel loginModel = new LoginModel();

			loginModel.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			loginModel.setNome(nome);
			loginModel.setPerfil(perfil);
			loginModel.setLogin(login);
			loginModel.setSenha(senha);
			loginModel.setDataCadastro(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataCadastro))));
			
			if(ServletFileUpload.isMultipartContent(request)) {  /*Testa foto enviada pelo usuario*/
				Part part= request.getPart("fileFoto");
				
				if(part.getSize() > 0){
					
					byte[]foto= IOUtils.toByteArray(part.getInputStream());
					String imagemBase64= "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
					
					loginModel.setUserFoto(imagemBase64);
					loginModel.setExtencaoFoto(part.getContentType().split("\\/")[1]);
					
				}
			}

			if (usuarioDaoRepository.ValidarUsuario(loginModel.getLogin()) && loginModel.getId() == null) {

				msg = "Usuario já existe, Informe outro login!";

			} else {
				if (loginModel.isNovo()) {
					msg = "Cadastrado com sucesso!";
				} else {
					msg = "Alterado com sucesso!";
				}

				loginModel = usuarioDaoRepository.salvarUsuario(loginModel, super.getUserLogado(request));

			}
			List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios(super.getUserLogado(request));
			request.setAttribute("listarUsuarios", listarUsuarios);
			
			request.setAttribute("msg", msg);
			request.setAttribute("loginModel", loginModel);
			request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
