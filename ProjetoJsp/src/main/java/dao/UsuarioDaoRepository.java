package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.LoginModel;

public class UsuarioDaoRepository {
	
	private Connection connection;
	
	public UsuarioDaoRepository()  {
		connection= SingleConnection.getConection();
	}
	
	public LoginModel salvarUsuario(LoginModel usuario) throws Exception {
		
		
			String sql="insert into loginmodel (nome, login, senha) values (?, ?, ?) ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getLogin());
			preparedStatement.setString(3, usuario.getSenha());
			
			preparedStatement.execute();
			connection.commit();
			
		return this.consultarUsuario(usuario);
		
	}
	
	public LoginModel consultarUsuario(LoginModel usuario) throws Exception {
		LoginModel loginModel= new LoginModel();
		String sql="SELECT * FROM loginmodel where upper (login)= upper (?)";
		
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1, usuario.getLogin());
		
		ResultSet resultSet= preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setLogin(resultSet.getString("login"));
			loginModel.setNome(resultSet.getString("nome"));
		}
		return loginModel;
	}
	
	public Boolean ValidarUsuario(String login) throws Exception{
		
		String sql="SELECT count(1) > 0 as existe from loginmodel where upper (login)= upper ('"+ login +"')";
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		
		ResultSet resultSet= preparedStatement.executeQuery();
		resultSet.next();
		
		return resultSet.getBoolean("existe");
	}

}
