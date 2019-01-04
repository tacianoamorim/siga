package br.ufrpe.siga.negocio;

import java.util.List;

import br.ufrpe.siga.apresentacao.FrmPrincipal;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.cadastro.CadastroAluno;
import br.ufrpe.siga.negocio.cadastro.CadastroDisciplina;
import br.ufrpe.siga.negocio.cadastro.CadastroProfessor;
import br.ufrpe.siga.negocio.cadastro.CadastroRendimentoEscolar;
import br.ufrpe.siga.negocio.cadastro.CadastroTurma;
import br.ufrpe.siga.negocio.entidade.Aluno;
import br.ufrpe.siga.negocio.entidade.Disciplina;
import br.ufrpe.siga.negocio.entidade.Professor;
import br.ufrpe.siga.negocio.entidade.RendimentoEscolar;
import br.ufrpe.siga.negocio.entidade.Turma;
import br.ufrpe.siga.negocio.excecao.LoginInvalidoException;
import br.ufrpe.siga.util.Constantes;

public class Fachada {

	private static Fachada instance;
	private CadastroProfessor cadProfessor;
	private CadastroAluno cadAluno;
	private CadastroTurma cadTurma;
	private CadastroDisciplina cadDisciplina;
	private CadastroRendimentoEscolar cadRendimentoEscolar;

	private Fachada() {
		this.cadProfessor= new CadastroProfessor();
		this.cadAluno= new CadastroAluno();
		this.cadTurma= new CadastroTurma();
		this.cadDisciplina= new CadastroDisciplina();
		this.cadRendimentoEscolar= new CadastroRendimentoEscolar();
	}
	
	public static Fachada getInstance() {
		if ( instance == null ) {
			instance= new Fachada();
		}
		return instance;
	}
	
	/**
	 * Login
	 * @throws Exception 
	 */
	public boolean login(String usuario, String senha, String TipoUsuario) 
			throws LoginInvalidoException {
		boolean logado= false;
		FrmPrincipal.alunoLogado= null;
		FrmPrincipal.professorLogado= null;
		
		try {
			if ( Constantes.PERFIL_ALUNO.equalsIgnoreCase(TipoUsuario) ) {
				Aluno aluno= Fachada.getInstance().loginAluno(usuario);
				if ( senha != null && 
						aluno.getNomeUsuario().equalsIgnoreCase(usuario.trim()) &&
						aluno.getSenha().equalsIgnoreCase(senha.trim()) ) {
					logado= true;
					FrmPrincipal.alunoLogado= aluno;
				} else 
					throw new LoginInvalidoException();
				
			} else if ( Constantes.PERFIL_PROFESSOR.equalsIgnoreCase(TipoUsuario) ) {
				Professor professor= Fachada.getInstance().loginProfessor(usuario);
				if ( senha != null && 
						professor.getNomeUsuario().equalsIgnoreCase(usuario.trim()) &&
						professor.getSenha().equalsIgnoreCase(senha.trim()) ) {
					logado= true;
					FrmPrincipal.professorLogado= professor;
				} else 
					throw new LoginInvalidoException();
				
			} else {
				if ( Constantes.PERFIL_ADM.equalsIgnoreCase(TipoUsuario) ) {
					if ( senha != null && "ADM".equalsIgnoreCase(senha.trim()) &&
							"ADM".equalsIgnoreCase(usuario) ) 
						logado= true;
					else 
						throw new LoginInvalidoException();
				}
			}
			
			FrmPrincipal.perfilLogado= TipoUsuario;
			
		} catch (LoginInvalidoException e) {
			throw e;
		}
		
		return logado;
	}

	/**
	 * Inserir professor
	 * @param professor
	 */
	public void inserir(Professor entidade) {
		cadProfessor.inserir(entidade);
	}
	
	/**
	 * Lista todos os registros
	 * @return
	 */
	public List<Professor> listarProfessores() {
		return cadProfessor.listar();
	}
	
	/**
	 * Apagar registro
	 * @throws RegistroNaoEncontradoException 
	 */
	public void apagar(Professor entidade) throws RegistroNaoEncontradoException {
		cadProfessor.apagar(entidade);
	}	
	
	private Professor loginProfessor(String usuario) {
		return cadProfessor.login(usuario);
	}

	/**
	 * Inserir aluno
	 * @param aluno
	 */
	public void inserir(Aluno entidade) {
		cadAluno.inserir(entidade);
	}
	
	/**
	 * Lista todos os registros
	 * @return
	 */
	public List<Aluno> listarAlunos() {
		return cadAluno.listar();
	}	
	
	/**
	 * Apagar registro
	 * @throws RegistroNaoEncontradoException 
	 */
	public void apagar(Aluno entidade) throws RegistroNaoEncontradoException {
		cadAluno.apagar(entidade);
	}
	
	private Aluno loginAluno(String usuario) {
		return cadAluno.login(usuario);
	}
	
	/**
	 * Inserir turma
	 * @param Turma
	 */
	public void inserir(Turma entidade) {
		cadTurma.inserir(entidade);
	}
	
	/**
	 * Lista todos os registros
	 * @return
	 */
	public List<Turma> listarTurmas() {
		return cadTurma.listar();
	}	
	
	/**
	 * Apagar registro
	 * @throws RegistroNaoEncontradoException 
	 */
	public void apagar(Turma entidade) throws RegistroNaoEncontradoException {
		cadTurma.apagar(entidade);
	}	
	
	public List<Turma> listarTurmasProfessor(Professor professor) {
		return cadTurma.listarTurmasProfessor(professor);
	}
	
	public List<Turma> listarTurmasAluno(Aluno aluno) {
		return cadTurma.listarTurmasAluno(aluno);
	}
	
	public Turma buscarTurma(int id) throws RegistroNaoEncontradoException {
		return cadTurma.buscarTurma(id);
	}
	
	/**
	 * Inserir disciplina
	 * @param Disciplina
	 */
	public void inserir(Disciplina entidade) {
		cadDisciplina.inserir(entidade);
	}
	
	/**
	 * Lista todos os registros
	 * @return
	 */
	public List<Disciplina> listarDisciplinas() {
		return cadDisciplina.listar();
	}	
	
	/**
	 * Apagar registro
	 * @throws RegistroNaoEncontradoException 
	 */
	public void apagar(Disciplina entidade) throws RegistroNaoEncontradoException {
		cadDisciplina.apagar(entidade);
	}

	/**
	 * Lista todos os registros
	 * @return
	 */
	public List<RendimentoEscolar> listarRendimentoEscolar() {
		return cadRendimentoEscolar.listar();
	}	
	
	/**
	 * Inserir rendimentoEscolar
	 * @param RendimentoEscolar
	 */
	public void inserir(RendimentoEscolar entidade) {
		cadRendimentoEscolar.inserir(entidade);
	}

	/**
	 * Listar todos os rendimentos escolares por turma
	 * @param turma
	 * @return List<RendimentoEscolar>
	 */
	public List<RendimentoEscolar> listarRendimentoEscolarPorTurma(Turma turma) {
		return cadRendimentoEscolar.listarRendimentoEscolarPorTurma(turma);
	}	
	
	public RendimentoEscolar buscarRendimentoEscolarPorId(int id) 
			throws RegistroNaoEncontradoException {
		return cadRendimentoEscolar.buscarPorId(id);
	}	
	
	public void alterar(RendimentoEscolar rendimentoEscolar) throws RegistroNaoEncontradoException {
		cadRendimentoEscolar.alterar(rendimentoEscolar) ;
	}

	public boolean alunoMatriculadoTurma(int idTurma, Aluno aluno) {
		return cadRendimentoEscolar.alunoMatriculadoTurma(idTurma, aluno) ;
	}

}
