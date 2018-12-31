package br.ufrpe.siga.negocio.entidade;

import java.util.Date;

public class Aluno extends Pessoa {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3997405159502870880L;
	
	private int periodo;

	public Aluno() {
		super();
	}
	
	/**
	 * @param id
	 * @param nome
	 * @param dataNascimento
	 * @param nomeUsuario
	 * @param senha
	 * @param periodo
	 */
	public Aluno(int id, String nome, Date dataNascimento, 
			String nomeUsuario, String senha, int periodo) {
		super(id, nome, dataNascimento, nomeUsuario, senha);
		this.periodo = periodo;
	}

	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

}
