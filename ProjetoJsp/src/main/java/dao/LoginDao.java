package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;
import model.LoginModel;

public class LoginDao {
	
	//LoginModel loginModel= new LoginModel();
	 Connection connection;
	 
	 public LoginDao() {
		 connection= SingleConnection.getConection();
	 }
	
	public boolean autenticarUser(LoginModel usuario) {
		
		String sql= "select * from loginModel where upper(login) = upper(?) and upper(senha)= upper(?) ";
		try {
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			
			ResultSet resultSet= statement.executeQuery();
			
			if(resultSet.next()) {
				return true;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
