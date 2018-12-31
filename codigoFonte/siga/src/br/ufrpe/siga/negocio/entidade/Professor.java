package br.ufrpe.siga.negocio.entidade;

import java.util.Date;

public class Professor extends Pessoa {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4275188235166886454L;
	
	private String cargo;

	public Professor() {
		super();
	}

	/**
	 * @param id
	 * @param nome
	 * @param dataNascimento
	 * @param nomeUsuario
	 * @param senha
	 * @param cargo
	 */
	public Professor(int id, String nome, Date dataNascimento, 
			String nomeUsuario, String senha, String cargo) {
		super(id, nome, dataNascimento, nomeUsuario, senha);
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
