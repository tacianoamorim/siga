package br.ufrpe.siga.negocio.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.siga.dado.RepositorioProfessor;
import br.ufrpe.siga.dado.repositorio.RepositorioProfessorArray;
import br.ufrpe.siga.negocio.entidade.Professor;

public class CadastroRedimentoEscolar {
	
	private RepositorioProfessor repProfessor;
	
	public CadastroRedimentoEscolar() {
		this.repProfessor= new RepositorioProfessorArray();
	}
	
	public List<Professor> listar() {
		List<Professor> lista= new ArrayList<Professor>();
		Object[] lisObj= repProfessor.listar();
		for (int i = 0; i < lisObj.length; i++) {
			if ( lisObj[i] != null ) {
				lista.add( (Professor) lisObj[i] );
			}
		}
		return lista;
	}

	public void inserir(Professor professor) {
		inserir(professor);		
	}
}
