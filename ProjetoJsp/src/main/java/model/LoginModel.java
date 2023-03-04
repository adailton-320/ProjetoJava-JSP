package model;

import java.io.Serializable;

public class LoginModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String login;
	private String senha;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*Testa o id para cadastrar ou atualizar usuario*/
	public boolean isNovo() {
		 if(this.id == null) {   // testa para cadastrar
			return true;
			
		}else if(this.id != null && this.id > 0) { // testa para atualizar
			return false;
			
		}
		return id == null;
	}
	

}
