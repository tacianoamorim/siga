package br.ufrpe.siga.dado;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.Professor;

public interface RepositorioProfessor {
	
	public Professor buscar(int id) throws RegistroNaoEncontradoException;
	public Object[] listar();
	public void inserir(Professor professor);
	public void alterar(Professor professor) throws RegistroNaoEncontradoException;
	
}
