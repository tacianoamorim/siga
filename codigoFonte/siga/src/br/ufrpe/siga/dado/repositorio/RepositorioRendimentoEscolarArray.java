package br.ufrpe.siga.dado.repositorio;

import br.ufrpe.siga.dado.RepositorioRendimentoEscolar;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.RendimentoEscolar;

public class RepositorioRendimentoEscolarArray extends RepositorioArray<RendimentoEscolar> 
	implements RepositorioRendimentoEscolar {

	@Override
	public RendimentoEscolar buscarPorAluno(int idAluno) 
			throws RegistroNaoEncontradoException {
		
		RendimentoEscolar elemento= null;
		for (int i = 0; i < super.elementos.length; i++) {
			if ( ((RendimentoEscolar)elementos[i]).getAluno().getId() == idAluno ) {
				elemento= (RendimentoEscolar) elementos[i];
			}
		}
		return elemento;
		
	}

}
