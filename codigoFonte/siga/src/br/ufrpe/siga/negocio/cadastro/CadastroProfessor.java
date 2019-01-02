package br.ufrpe.siga.negocio.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.siga.dado.RepositorioProfessor;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.dado.repositorio.RepositorioProfessorArray;
import br.ufrpe.siga.negocio.entidade.Professor;

public class CadastroProfessor {
	
	private RepositorioProfessor repProfessor;
	
	public CadastroProfessor() {
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

	public void inserir(Professor entidade) {
		repProfessor.inserir(entidade);		
	}
	
	public void apagar(Professor entidade) throws RegistroNaoEncontradoException {
		repProfessor.apagar(entidade.getId());	
	}	
	
	public Professor login(String usuario) {
		Professor retorno = null;
		
		List<Professor> lista= listar();
		for (Professor professor : lista) {
			if (usuario != null && usuario.equalsIgnoreCase(professor.getNomeUsuario())) {
				retorno= professor;
			}
		}
		return retorno;
	}	
}
