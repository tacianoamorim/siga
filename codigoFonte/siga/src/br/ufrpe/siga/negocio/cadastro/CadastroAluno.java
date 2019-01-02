package br.ufrpe.siga.negocio.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.siga.dado.RepositorioAluno;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.dado.repositorio.RepositorioAlunoArray;
import br.ufrpe.siga.negocio.entidade.Aluno;

public class CadastroAluno {
	
	private RepositorioAluno repAluno;
	
	public CadastroAluno() {
		this.repAluno= new RepositorioAlunoArray();
	}
	
	public List<Aluno> listar() {
		List<Aluno> lista= new ArrayList<Aluno>();
		Object[] lisObj= repAluno.listar();
		for (int i = 0; i < lisObj.length; i++) {
			if ( lisObj[i] != null ) {
				lista.add( (Aluno) lisObj[i] );
			}
		}
		return lista;
	}

	public void inserir(Aluno entidade) {
		repAluno.inserir(entidade);		
	}

	public void apagar(Aluno entidade) throws RegistroNaoEncontradoException {
		repAluno.apagar(entidade.getId());	
	}

	public Aluno login(String usuario) {
		Aluno retorno = null;
		
		List<Aluno> lista= listar();
		for (Aluno aluno : lista) {
			if (usuario != null && usuario.equalsIgnoreCase(aluno.getNomeUsuario())) {
				retorno= aluno;
			}
		}
		return retorno;
	}
}
