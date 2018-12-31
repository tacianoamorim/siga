package br.ufrpe.siga.dado;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.Aluno;

public interface RepositorioAluno {
	
	public Aluno buscar(int id) throws RegistroNaoEncontradoException;
	public Object[] listar();
	public void inserir(Aluno entidade);
	public void alterar(Aluno entidade) throws RegistroNaoEncontradoException;
	public void apagar(int id) throws RegistroNaoEncontradoException;
}
