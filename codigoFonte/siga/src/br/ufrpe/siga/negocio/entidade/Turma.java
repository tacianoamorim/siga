package br.ufrpe.siga.negocio.entidade;

import java.io.Serializable;

public class Turma extends Entidade implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3959402308718356469L;
	
	private Disciplina disciplina;
	private Professor professor;
	private int capacidadeTurma;

	public Turma() {
		super();
	}

	/**
	 * @param id
	 * @param disciplina
	 * @param professor
	 * @param capacidadeTurma
	 */
	public Turma(int id, Disciplina disciplina, 
			Professor professor, int capacidadeTurma) {
		super();
		super.setId(id);
		this.disciplina = disciplina;
		this.professor = professor;
		this.capacidadeTurma = capacidadeTurma;
	}

	public int getId() {
		return super.getId();
	}
	public void setId(int id) {
		super.setId(id);
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public int getCapacidadeTurma() {
		return capacidadeTurma;
	}
	public void setCapacidadeTurma(int capacidadeTurma) {
		this.capacidadeTurma = capacidadeTurma;
	}

}
