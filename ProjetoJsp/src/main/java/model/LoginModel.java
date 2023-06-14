package model;

import java.io.Serializable;
import java.sql.Date;


public class LoginModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String perfil;
	private String login;
	private String senha;
	private Boolean useradmin;
	private Date dataCadastro;
	private String userFoto;
	private String extencaoFoto;

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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
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

	public Boolean getUseradmin() {
		return useradmin;
	}

	public void setUseradmin(Boolean useradmin) {
		this.useradmin = useradmin;
	}

	public String getUserFoto() {
		return userFoto;
	}

	public void setUserFoto(String userFoto) {
		this.userFoto = userFoto;
	}

	public String getExtencaoFoto() {
		return extencaoFoto;
	}

	public void setExtencaoFoto(String extencaoFoto) {
		this.extencaoFoto = extencaoFoto;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//Pega data do sistema
	
	/*public String pegarData() {
		DateFormat dateFormat= new SimpleDateFormat("dd/mm/yyyy");
		Date data= new Date();
		
		return dateFormat.format(data);
	}*/

	/* Testa o id para cadastrar ou atualizar usuario */
	public boolean isNovo() {
		if (this.id == null) { // testa para cadastrar
			return true;

		} else if (this.id != null && this.id > 0) { // testa para atualizar
			return false;

		}
		return id == null;
	}

	
	

}
