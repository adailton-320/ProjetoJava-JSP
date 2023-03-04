package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.LoginModel;

public class UsuarioDaoRepository {
	
	private Connection connection;
	
	public UsuarioDaoRepository()  {
		connection= SingleConnection.getConection();
	}
	
	public LoginModel salvarUsuario(LoginModel usuario) throws Exception {
		
		if(usuario.isNovo()) {
			
			String sql="insert into loginmodel (nome, login, senha) values (?, ?, ?)";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getLogin());
			preparedStatement.setString(3, usuario.getSenha());
			
			preparedStatement.execute();
			connection.commit();
			
			}else{
			
			alterarUsuario(usuario);
			
		}
		return this.consultarUsuario(usuario.getLogin());
	}
	
		public void alterarUsuario(LoginModel usuario) throws Exception {
		String sql="update loginmodel set nome=?, login=?, senha=? where id= "+usuario.getId()+";";
		
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1,usuario.getNome());
		preparedStatement.setString(2,usuario.getLogin());
		preparedStatement.setString(3, usuario.getSenha());
		
		preparedStatement.executeUpdate();
		connection.commit();
	}
		
		public List<LoginModel> consultarUsuarioList(String nome) throws Exception{
			List<LoginModel> listaUsuario = new ArrayList<LoginModel>();
			String sql= "select * from loginmodel where upper (nome) like upper (?) ";
			
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1,("%"+nome+"%"));
			ResultSet resultado= preparedStatement.executeQuery();
			while(resultado.next()) {
				LoginModel loginModel= new LoginModel();
				
				loginModel.setId(resultado.getLong("id"));
				loginModel.setNome(resultado.getString("nome"));
				loginModel.setLogin(resultado.getString("login"));
				//loginModel.setSenha(resultado.getString("senha"));
				
				listaUsuario.add(loginModel);
				
			}
			
			
			return listaUsuario;
		}
	
	public LoginModel consultarUsuario(String usuario) throws Exception {
		LoginModel loginModel= new LoginModel();
		String sql="SELECT * FROM loginmodel where upper (login)= upper (?)";
		
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, usuario);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setLogin(resultSet.getString("login"));
			loginModel.setNome(resultSet.getString("nome"));
			loginModel.setSenha(resultSet.getString("senha"));
		}
		return loginModel;
	}
	 
	public LoginModel consultarUsuarioId(String id) throws Exception {
		LoginModel loginModel= new LoginModel();
		String sql= "select * from loginmodel where id= ?";
		
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setLong(1, Long.parseLong(id));
		
		ResultSet resultSet= preparedStatement.executeQuery();
		while(resultSet.next()) {
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setLogin(resultSet.getString("login"));
			loginModel.setNome(resultSet.getString("nome"));
			loginModel.setSenha(resultSet.getString("senha"));
			
		}
		return loginModel;
	}
	
	public void deletarUsuario(String id) throws Exception {
		String sql="delete from loginmodel where id= ?";
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, Long.parseLong(id));
		preparedStatement.executeUpdate();
		connection.commit();
	}
	
	public Boolean ValidarUsuario(String login) throws Exception{
		
		String sql="SELECT count(1) > 0 as existe from loginmodel where upper (login)= upper ('"+ login +"')";
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		resultSet.next();
		
		return resultSet.getBoolean("existe");
	}

}
