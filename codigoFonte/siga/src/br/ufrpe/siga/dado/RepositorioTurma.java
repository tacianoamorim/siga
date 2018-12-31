package br.ufrpe.siga.dado;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.Turma;

public interface RepositorioTurma {
	
	public Turma buscar(int id) throws RegistroNaoEncontradoException;
	public Object[] listar();
	public void inserir(Turma entidade);
	public void alterar(Turma entidade) throws RegistroNaoEncontradoException;
	public void apagar(int id) throws RegistroNaoEncontradoException;
}
