package br.ufrpe.siga.negocio.entidade;

import java.io.Serializable;
import java.util.Date;

public class Pessoa extends Entidade implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5225395330498526926L;

	private String nome;
	private Date dataNascimento;
	private String nomeUsuario;
	private String senha;
	
	public Pessoa() {
		super();
	}
	
	/**
	 * @param id
	 * @param nome
	 * @param dataNascimento
	 * @param nomeUsuario
	 * @param senha
	 */
	public Pessoa(int id, String nome, Date dataNascimento, 
			String nomeUsuario, String senha) {
		super();
		super.setId(id);
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	public int getId() {
		return super.getId();
	}
	public void setId(int id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
