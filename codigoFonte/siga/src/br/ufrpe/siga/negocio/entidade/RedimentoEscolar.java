package br.ufrpe.siga.negocio.entidade;

import java.io.Serializable;

public class RedimentoEscolar extends Entidade implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6767877261545391405L;
	
	private Turma turma;
	private Aluno aluno;
	private int nota1VA;
	private int nota2VA;
	private String[] trabalhos = new String[4];
	private String[] notaTrabalhos = new String[4];
	
	public RedimentoEscolar() {
		super();
	}

	/**
	 * @param turma
	 * @param aluno
	 */
	public RedimentoEscolar(int id, Turma turma, Aluno aluno) {
		super();
		super.setId(id);
		this.turma = turma;
		this.aluno = aluno;
	}
	
	/**
	 * @param turma
	 * @param aluno
	 * @param nota1va
	 * @param nota2va
	 */
	public RedimentoEscolar(int id, Turma turma, Aluno aluno, int nota1va, int nota2va) {
		super();
		super.setId(id);
		this.turma = turma;
		this.aluno = aluno;
		nota1VA = nota1va;
		nota2VA = nota2va;
	}

	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public int getNota1VA() {
		return nota1VA;
	}
	public void setNota1VA(int nota1va) {
		nota1VA = nota1va;
	}

	public int getNota2VA() {
		return nota2VA;
	}
	public void setNota2VA(int nota2va) {
		nota2VA = nota2va;
	}

	public String[] getTrabalhos() {
		return trabalhos;
	}
	public void setTrabalhos(String[] trabalhos) {
		this.trabalhos = trabalhos;
	}

	public String[] getNotaTrabalhos() {
		return notaTrabalhos;
	}
	public void setNotaTrabalhos(String[] notaTrabalhos) {
		this.notaTrabalhos = notaTrabalhos;
	}

}
