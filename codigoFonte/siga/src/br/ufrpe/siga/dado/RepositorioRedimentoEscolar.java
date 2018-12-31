package br.ufrpe.siga.dado;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.RedimentoEscolar;

public interface RepositorioRedimentoEscolar {

	public RedimentoEscolar buscarPorAluno(int idAluno) 
			throws RegistroNaoEncontradoException;

	public void inserir(RedimentoEscolar entidade);

	public void alterar(RedimentoEscolar entidade) 
			throws RegistroNaoEncontradoException;
}
