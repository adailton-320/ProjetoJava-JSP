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

	public UsuarioDaoRepository() {
		connection = SingleConnection.getConection();
	}

	public LoginModel salvarUsuario(LoginModel usuario, Long userLogado) throws Exception {

		if (usuario.isNovo()) {

			String sql = "insert into loginmodel (nome, login, senha, usuario_id, perfil) values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getLogin());
			preparedStatement.setString(3, usuario.getSenha());
			preparedStatement.setLong(4, userLogado);
			preparedStatement.setString(5, usuario.getPerfil());

			preparedStatement.execute();
			connection.commit();
			
			if(usuario.getUserFoto() != null && !usuario.getUserFoto().isEmpty()) {
				sql="update loginModel set userfoto =?, extencaofoto =? where login=?";
				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setString(1, usuario.getUserFoto());
				preparedStatement.setString(2, usuario.getExtencaoFoto());
				preparedStatement.setString(3,usuario.getLogin());
				
				preparedStatement.execute();
				connection.commit();
				
			}

		} else {

			alterarUsuario(usuario);

		}
		return this.consultarUsuario(usuario.getLogin(), userLogado);
	}

	public void alterarUsuario(LoginModel usuario) throws Exception {
		String sql = "update loginmodel set nome=?, login=?, senha=?, perfil=? where id= " + usuario.getId() + ";";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, usuario.getNome());
		preparedStatement.setString(2, usuario.getLogin());
		preparedStatement.setString(3, usuario.getSenha());
		preparedStatement.setString(4, usuario.getPerfil());

		preparedStatement.executeUpdate();
		connection.commit();
		
		if(usuario.getUserFoto() != null && !usuario.getUserFoto().isEmpty()) {
			sql="update loginModel set userfoto =?, extencaofoto =? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, usuario.getUserFoto());
			preparedStatement.setString(2, usuario.getExtencaoFoto());
			preparedStatement.setLong(3,usuario.getId());
			
			preparedStatement.execute();
			connection.commit();
			
		}
	}

	public List<LoginModel> consultarUsuarioList(String nome, Long userLogado) throws Exception {
		List<LoginModel> listaUsuario = new ArrayList<LoginModel>();
		String sql = "select * from loginmodel where upper (nome) like upper (?) and useradmin is false and usuario_id="
				+ userLogado;

		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, ("%" + nome + "%"));

		ResultSet resultado = preparedStatement.executeQuery();
		while (resultado.next()) {
			LoginModel loginModel = new LoginModel();

			loginModel.setId(resultado.getLong("id"));
			loginModel.setNome(resultado.getString("nome"));
			loginModel.setLogin(resultado.getString("login"));
			// loginModel.setSenha(resultado.getString("senha"));
			loginModel.setPerfil(resultado.getString("perfil"));

			listaUsuario.add(loginModel);

		}

		return listaUsuario;
	}

	public List<LoginModel> listarUsuarios(Long userLogado) throws Exception {
		List<LoginModel> listaUsuarios = new ArrayList<LoginModel>();
		String sql = "select * from loginmodel where useradmin is false and usuario_id= " + userLogado;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LoginModel loginModel = new LoginModel();
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setNome(resultSet.getString("nome"));
			loginModel.setLogin(resultSet.getString("login"));
			// loginModel.setSenha(resultSet.getString("senha"));
			loginModel.setPerfil(resultSet.getString("perfil"));

			listaUsuarios.add(loginModel);
		}

		return listaUsuarios;
	}

	public LoginModel consultarUsuarioLogin(String login) throws Exception {
		LoginModel loginModel = new LoginModel();

		String sql = "select * from loginmodel where upper (login) = upper('" + login + "')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setNome(resultSet.getString("nome"));
			loginModel.setLogin(resultSet.getString("login"));
			loginModel.setSenha(resultSet.getString("senha"));
			loginModel.setUseradmin(resultSet.getBoolean("useradmin"));
			loginModel.setPerfil(resultSet.getString("perfil"));
			loginModel.setUserFoto(resultSet.getString("userFoto"));

		}

		return loginModel;
	}

	public LoginModel consultarUsuarioLogin(String login, Long userLogado) throws Exception {
		LoginModel loginModel = new LoginModel();

		String sql = "select * from loginmodel where upper (login) = upper('" + login
				+ "') and useradmin is false and usuario_id= " + userLogado;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setNome(resultSet.getString("nome"));
			loginModel.setLogin(resultSet.getString("login"));
			loginModel.setSenha(resultSet.getString("senha"));
			loginModel.setPerfil(resultSet.getString("perfil"));
			loginModel.setUserFoto(resultSet.getString("userFoto"));

		}

		return loginModel;
	}

	public LoginModel consultarUsuario(String usuario, Long userLogado) throws Exception {
		LoginModel loginModel = new LoginModel();
		String sql = "SELECT * FROM loginmodel where upper (login)= upper (?) and useradmin is false and usuario_id= "
				+ userLogado;

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, usuario);
		// preparedStatement.setLong(2, userLogado);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setLogin(resultSet.getString("login"));
			loginModel.setNome(resultSet.getString("nome"));
			loginModel.setSenha(resultSet.getString("senha"));
			loginModel.setPerfil(resultSet.getString("perfil"));
			loginModel.setUserFoto(resultSet.getString("userFoto"));
		}
		return loginModel;
	}

	public LoginModel consultarUsuarioId(String id, Long userLogado) throws Exception {
		LoginModel loginModel = new LoginModel();
		String sql = "select * from loginmodel where id= ? and useradmin is false and usuario_id= ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, Long.parseLong(id));
		preparedStatement.setLong(2, userLogado);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			loginModel.setId(resultSet.getLong("id"));
			loginModel.setLogin(resultSet.getString("login"));
			loginModel.setNome(resultSet.getString("nome"));
			loginModel.setSenha(resultSet.getString("senha"));
			loginModel.setPerfil(resultSet.getString("perfil"));
			loginModel.setUserFoto(resultSet.getString("userFoto"));
			loginModel.setExtencaoFoto(resultSet.getString("extencaofoto"));

		}
		return loginModel;
	}

	public void deletarUsuario(String id) throws Exception {
		String sql = "delete from loginmodel where id= ? and useradmin is false";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setLong(1, Long.parseLong(id));
		preparedStatement.executeUpdate();
		connection.commit();
	}

	public Boolean ValidarUsuario(String login) throws Exception {

		String sql = "SELECT count(1) > 0 as existe from loginmodel where upper (login)= upper ('" + login + "')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();

		return resultSet.getBoolean("existe");
	}

}
