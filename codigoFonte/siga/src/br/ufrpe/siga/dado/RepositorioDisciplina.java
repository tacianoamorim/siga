package br.ufrpe.siga.dado;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.Disciplina;

public interface RepositorioDisciplina {
	
	public Disciplina buscar(int id) throws RegistroNaoEncontradoException;
	public Object[] listar();
	public void inserir(Disciplina entidade);
	public void alterar(Disciplina entidade) throws RegistroNaoEncontradoException;
	public void apagar(int id) throws RegistroNaoEncontradoException;
}
