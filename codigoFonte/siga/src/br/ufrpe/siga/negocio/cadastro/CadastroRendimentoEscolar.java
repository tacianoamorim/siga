package br.ufrpe.siga.negocio.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.siga.dado.RepositorioRendimentoEscolar;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.dado.repositorio.RepositorioRendimentoEscolarArray;
import br.ufrpe.siga.negocio.Fachada;
import br.ufrpe.siga.negocio.entidade.Aluno;
import br.ufrpe.siga.negocio.entidade.RendimentoEscolar;
import br.ufrpe.siga.negocio.entidade.Turma;

public class CadastroRendimentoEscolar {
	
	private RepositorioRendimentoEscolar repRendimentoEscolar;
	
	public CadastroRendimentoEscolar() {
		this.repRendimentoEscolar= new RepositorioRendimentoEscolarArray();
	}
	
	public List<RendimentoEscolar> listar() {
		List<RendimentoEscolar> lista= new ArrayList<RendimentoEscolar>();
		Object[] lisObj= repRendimentoEscolar.listar();
		for (int i = 0; i < lisObj.length; i++) {
			if ( lisObj[i] != null ) {
				lista.add( (RendimentoEscolar) lisObj[i] );
			}
		}
		return lista;
	}

	public void inserir(RendimentoEscolar entidade) {
		repRendimentoEscolar.inserir(entidade);		
	}

	public List<RendimentoEscolar> listarRendimentoEscolarPorTurma(Turma turma) {
		List<RendimentoEscolar> rendimentos= new ArrayList<RendimentoEscolar>();
		List<RendimentoEscolar> lista= Fachada.getInstance().listarRendimentoEscolar();
		for (RendimentoEscolar rendimentoEscolar : lista) {
			if ( turma != null && turma.getId() == rendimentoEscolar.getTurma().getId() ) {
				rendimentos.add( rendimentoEscolar );
			}
		}
		return rendimentos;	
	}

	public RendimentoEscolar buscarPorId(int id) throws RegistroNaoEncontradoException {
		return repRendimentoEscolar.buscar(id);
	}

	public void alterar(RendimentoEscolar entidade) throws RegistroNaoEncontradoException {
		repRendimentoEscolar.alterar(entidade);
	}

	public boolean alunoMatriculadoTurma(int idTurma, Aluno aluno) {
		boolean alunoMatriculado= false;
		
		List<RendimentoEscolar> lista= Fachada.getInstance().listarRendimentoEscolar();
		for (RendimentoEscolar rendimentoEscolar : lista) {
			if ( rendimentoEscolar.getTurma().getId() == idTurma )
				if ( aluno != null && aluno.getId() == rendimentoEscolar.getAluno().getId() ) {
					alunoMatriculado= true;
				}
		}
		return alunoMatriculado;
	}
}
