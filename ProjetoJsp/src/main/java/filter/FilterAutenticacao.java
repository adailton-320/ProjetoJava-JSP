package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnection;

@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	private static Connection connection;

	public FilterAutenticacao() {
		super();

	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession httpSession = req.getSession();

			String usuarioLogado = (String) httpSession.getAttribute("usuario");
			String autenticaUrl = req.getServletPath();

			if (usuarioLogado == null && !autenticaUrl.equalsIgnoreCase("/principal/ServletLogin")) {

				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + autenticaUrl);

				request.setAttribute("msg", "Realize o login para continuar");
				redirecionar.forward(request, response);
				return;

			} else {
				chain.doFilter(request, response);
			}

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConection();

	}

}
