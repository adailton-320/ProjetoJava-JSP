package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoginModel;

import java.io.IOException;

import dao.UsuarioDaoRepository;

@WebServlet(urlPatterns = { "/principal/ServletUsuarioController", "/ServletUsuarioController" })
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDaoRepository usuarioDaoRepository= new UsuarioDaoRepository();
	String msg= "Usuario salvo com sucesso!";
       
   
    public ServletUsuarioController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id= request.getParameter("id");
			String nome= request.getParameter("nome");
			String login= request.getParameter("login");
			String senha= request.getParameter("senha");
			
			LoginModel loginModel= new LoginModel();
			
			loginModel.setId(id != null && !id.isEmpty() ? Long.parseLong(id): null);
			loginModel.setNome(nome);
			loginModel.setLogin(login);
			loginModel.setSenha(senha);
			
			if(usuarioDaoRepository.ValidarUsuario(loginModel.getLogin()) && loginModel.getId()== null ){
				
				msg= "Usuario já existe, Informe outro login!";
				
			}else {
				
				loginModel =usuarioDaoRepository.salvarUsuario(loginModel);
				loginModel =usuarioDaoRepository.salvarUsuario(loginModel);
				
				
				
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loginModel",loginModel);
			request.getRequestDispatcher("principal/cadastro.jsp").forward(request, response);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
		
	
	}

}
