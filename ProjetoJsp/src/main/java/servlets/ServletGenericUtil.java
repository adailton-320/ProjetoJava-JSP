package servlets;

import java.io.Serializable;

import dao.UsuarioDaoRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
	
	UsuarioDaoRepository daoRepository= new UsuarioDaoRepository();
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session= request.getSession();
		String usuarioLogado= (String) session.getAttribute("usuario");
		
		return daoRepository.consultarUsuarioLogin(usuarioLogado).getId();
	}
	

}
