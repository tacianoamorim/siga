package br.ufrpe.siga.negocio;

import java.util.List;

import br.ufrpe.siga.apresentacao.FrmPrincipal;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.cadastro.CadastroAluno;
import br.ufrpe.siga.negocio.cadastro.CadastroDisciplina;
import br.ufrpe.siga.negocio.cadastro.CadastroProfessor;
import br.ufrpe.siga.negocio.cadastro.CadastroRedimentoEscolar;
import br.ufrpe.siga.negocio.cadastro.CadastroTurma;
import br.ufrpe.siga.negocio.entidade.Aluno;
import br.ufrpe.siga.negocio.entidade.Disciplina;
import br.ufrpe.siga.negocio.entidade.Professor;
import br.ufrpe.siga.negocio.entidade.Turma;
import br.ufrpe.siga.negocio.excecao.LoginInvalidoException;
import br.ufrpe.siga.util.Constantes;

public class Fachada {

	private static Fachada instance;
	private CadastroProfessor cadProfessor;
	private CadastroAluno cadAluno;
	private CadastroTurma cadTurma;
	private CadastroDisciplina cadDisciplina;
	private CadastroRedimentoEscolar cadRedimentoEscolar;

	private Fachada() {
		this.cadProfessor= new CadastroProfessor();
		this.cadAluno= new CadastroAluno();
		this.cadTurma= new CadastroTurma();
		this.cadDisciplina= new CadastroDisciplina();
		this.cadRedimentoEscolar= new CadastroRedimentoEscolar();
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
		
		try {
			if ( Constantes.PERFIL_ALUNO.equalsIgnoreCase(TipoUsuario) ) {
				if ( Constantes.PERFIL_ALUNO.equalsIgnoreCase(TipoUsuario) ) {
					if ( senha != null && "ADM".equalsIgnoreCase(senha.trim()) &&
							"ADM".equalsIgnoreCase(usuario) )
						logado= true;
					else 
						throw new LoginInvalidoException();
				}
				
			} else if ( Constantes.LOGIN_NOVO_CADASTRO_PROFESSOR.equalsIgnoreCase(TipoUsuario) ) {
				if ( Constantes.PERFIL_PROFESSOR.equalsIgnoreCase(TipoUsuario) ) {
					if ( senha != null && "ADM".equalsIgnoreCase(senha.trim()) &&
							"ADM".equalsIgnoreCase(usuario) )
						logado= true;
					else 
						throw new LoginInvalidoException();
				}
				
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
}
