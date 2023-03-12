package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoginModel;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDaoRepository;

@WebServlet(urlPatterns = { "/principal/ServletUsuarioController", "/ServletUsuarioController" })
public class ServletUsuarioController extends HttpServlet {
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
				
				List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios();
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
				List<LoginModel> dadosJsonUser = usuarioDaoRepository.consultarUsuarioList(nomeBusca);

				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);

			}
			
			else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id= request.getParameter("id");
				LoginModel redirecionarUser= usuarioDaoRepository.consultarUsuarioId(id);
				
				List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios();
				request.setAttribute("listarUsuarios", listarUsuarios);
				
				request.setAttribute("msg", "Usuario em edição");
				request.setAttribute("loginModel", redirecionarUser);
				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);

				

			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuarios")) {
				
				List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios();
				
				request.setAttribute("msg", "Usuarios carregados");
				request.setAttribute("listarUsuarios", listarUsuarios);
				request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
				
				
			
				}else {
					
					List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios();
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
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			LoginModel loginModel = new LoginModel();

			loginModel.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			loginModel.setNome(nome);
			loginModel.setLogin(login);
			loginModel.setSenha(senha);

			if (usuarioDaoRepository.ValidarUsuario(loginModel.getLogin()) && loginModel.getId() == null) {

				msg = "Usuario já existe, Informe outro login!";

			} else {
				if (loginModel.isNovo()) {
					msg = "Cadastrado com sucesso!";
				} else {
					msg = "Alterado com sucesso!";
				}

				loginModel = usuarioDaoRepository.salvarUsuario(loginModel);

			}
			List<LoginModel> listarUsuarios= usuarioDaoRepository.listarUsuarios();
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
