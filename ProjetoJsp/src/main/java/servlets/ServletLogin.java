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

@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" }) /* Mapeamento url da tela */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginDao loginDao = new LoginDao();

	public ServletLogin() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

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

					request.getSession().setAttribute("usuario", loginModel.getLogin());

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
