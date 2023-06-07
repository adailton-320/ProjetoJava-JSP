package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoginModel;

import java.io.IOException;

import dao.LoginDao;
import dao.UsuarioDaoRepository;

@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" }) /* Mapeamento url da tela */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginDao loginDao = new LoginDao();
	UsuarioDaoRepository daoRepository= new UsuarioDaoRepository();

	public ServletLogin() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao= request.getParameter("acao");
		if(acao!=null && !acao.isEmpty() && !acao.equalsIgnoreCase("logout") ) {
			request.getSession().invalidate();//invalida a seção.
			
			RequestDispatcher direcionar= request.getRequestDispatcher("index.jsp");
			direcionar.forward(request, response);
			
			
		}else {
			
			doPost(request, response);
			
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				String servLogin = request.getParameter("login");
				String servSenha = request.getParameter("senha");
				String url = request.getParameter("url");

		try {

			if (servLogin != null && !servLogin.isEmpty() && servSenha != null && !servSenha.isEmpty()) {

				LoginModel loginModel = new LoginModel();
				loginModel.setLogin(servLogin);
				loginModel.setSenha(servSenha);

				if (loginDao.autenticarUser(loginModel)) {
					
					loginModel= daoRepository.consultarUsuarioLogin(servLogin);

					request.getSession().setAttribute("usuario", loginModel.getLogin());
					request.getSession().setAttribute("perfil", loginModel.getPerfil());
					
					request.getSession().setAttribute("imagemUser", loginModel.getUserFoto());
					
					

					if (url == null || url.equalsIgnoreCase("null")) {
						url = "principal/principal.jsp";

					}

					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);

				} else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Login ou senha inválidos");
					redirecionar.forward(request, response);
				}

			} else {

				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Campo login ou senha não pode está vazio");
				redirecionar.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}

	}
}
