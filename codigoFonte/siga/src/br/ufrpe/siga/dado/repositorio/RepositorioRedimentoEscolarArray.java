package br.ufrpe.siga.dado.repositorio;

import br.ufrpe.siga.dado.RepositorioRedimentoEscolar;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.RedimentoEscolar;

public class RepositorioRedimentoEscolarArray extends RepositorioArray<RedimentoEscolar> 
	implements RepositorioRedimentoEscolar {

	@Override
	public RedimentoEscolar buscarPorAluno(int idAluno) 
			throws RegistroNaoEncontradoException {
		
		RedimentoEscolar elemento= null;
		for (int i = 0; i < super.elementos.length; i++) {
			if ( ((RedimentoEscolar)elementos[i]).getAluno().getId() == idAluno ) {
				elemento= (RedimentoEscolar) elementos[i];
			}
		}
		return elemento;
		
	}

}
