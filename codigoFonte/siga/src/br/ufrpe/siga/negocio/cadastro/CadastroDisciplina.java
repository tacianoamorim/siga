package br.ufrpe.siga.negocio.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.siga.dado.RepositorioDisciplina;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.dado.repositorio.RepositorioDisciplinaArray;
import br.ufrpe.siga.negocio.entidade.Disciplina;

public class CadastroDisciplina {
	
	private RepositorioDisciplina repDisciplina;
	
	public CadastroDisciplina() {
		this.repDisciplina= new RepositorioDisciplinaArray();
	}
	
	public List<Disciplina> listar() {
		List<Disciplina> lista= new ArrayList<Disciplina>();
		Object[] lisObj= repDisciplina.listar();
		for (int i = 0; i < lisObj.length; i++) {
			if ( lisObj[i] != null ) {
				lista.add( (Disciplina) lisObj[i] );
			}
		}
		return lista;
	}

	public void inserir(Disciplina entidade) {
		repDisciplina.inserir(entidade);		
	}
	
	public void apagar(Disciplina entidade) throws RegistroNaoEncontradoException {
		repDisciplina.apagar(entidade.getId());	
	}	
}
