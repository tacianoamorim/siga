package br.ufrpe.siga.negocio.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.siga.dado.RepositorioTurma;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.dado.repositorio.RepositorioTurmaArray;
import br.ufrpe.siga.negocio.entidade.Turma;

public class CadastroTurma {
	
	private RepositorioTurma repTurma;
	
	public CadastroTurma() {
		this.repTurma= new RepositorioTurmaArray();
	}
	
	public List<Turma> listar() {
		List<Turma> lista= new ArrayList<Turma>();
		Object[] lisObj= repTurma.listar();
		for (int i = 0; i < lisObj.length; i++) {
			if ( lisObj[i] != null ) {
				lista.add( (Turma) lisObj[i] );
			}
		}
		return lista;
	}

	public void inserir(Turma entidade) {
		repTurma.inserir(entidade);		
	}

	public void apagar(Turma entidade) throws RegistroNaoEncontradoException {
		repTurma.apagar(entidade.getId());	
	}
}
