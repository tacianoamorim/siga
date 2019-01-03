package br.ufrpe.siga.dado;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.RendimentoEscolar;

public interface RepositorioRendimentoEscolar {

	public Object[] listar();
	
	public RendimentoEscolar buscar(int id) throws RegistroNaoEncontradoException;
	
	public RendimentoEscolar buscarPorAluno(int idAluno) 
			throws RegistroNaoEncontradoException;

	public void inserir(RendimentoEscolar entidade);

	public void alterar(RendimentoEscolar entidade) 
			throws RegistroNaoEncontradoException;
}
