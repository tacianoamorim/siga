package br.ufrpe.siga.negocio.entidade;

import java.io.Serializable;

public class Disciplina extends Entidade implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5931253855713793056L;

	private String nome;
	private String ementa;

	/**
	 * 
	 */
	public Disciplina() {
		super();
	}

	/**
	 * @param id
	 * @param nome
	 * @param ementa
	 */
	public Disciplina(int id, String nome, String ementa) {
		super();
		super.setId(id);
		this.nome = nome;
		this.ementa = ementa;
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

	public String getEmenta() {
		return ementa;
	}
	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

}
