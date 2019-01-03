package br.ufrpe.siga.apresentacao;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.ufrpe.siga.apresentacao.aluno.FrmAluno;
import br.ufrpe.siga.apresentacao.disciplina.FrmDisciplina;
import br.ufrpe.siga.apresentacao.professor.FrmProfessor;
import br.ufrpe.siga.apresentacao.turma.FrmTurma;
import br.ufrpe.siga.negocio.Fachada;
import br.ufrpe.siga.negocio.entidade.Aluno;
import br.ufrpe.siga.negocio.entidade.Professor;
import br.ufrpe.siga.negocio.entidade.RendimentoEscolar;
import br.ufrpe.siga.negocio.entidade.Turma;
import br.ufrpe.siga.util.Constantes;

public class FrmPrincipal {

	JFrame frame;
	private JDesktopPane desktop;
	
	public static String acaoInicializacao;
	public static String perfilLogado;
	
	public static Professor professorLogado;
	public static Aluno alunoLogado;

	private final JToolBar tbProfessor = new JToolBar();
	private JButton tbBtnAluno;
	private JButton tbBtnProfessor;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton tbBtnDisciplina;
	private JSeparator separator_2;
	private JButton tbBtnRendimentoEscolar;
	private JSeparator separator_3;
	private JButton tbBtnTurma;
	private JSeparator separator_4;
	private JButton tbBtnSair;
	private Panel pnlTree;
	private JTree tree;
	
	private DefaultMutableTreeNode root;
	private int idRendimentoSelecionado;


	/**
	 * Create the application.
	 */
	public FrmPrincipal() {
		initialize();
		
		carregar();
	}

	/**
	 * Carregar a permissoes de cada perfil
	 */
	private void carregar() {
		if (Constantes.PERFIL_ALUNO.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {
			tbBtnAluno.setEnabled(true);
			tbBtnRendimentoEscolar.setEnabled(false);			
		} else if (Constantes.PERFIL_PROFESSOR.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {
			tbBtnProfessor.setEnabled(true);
			tbBtnAluno.setEnabled(true);
			tbBtnRendimentoEscolar.setEnabled(true);			

		} else {
			tbBtnProfessor.setEnabled(true);
			tbBtnAluno.setEnabled(true);
    		tbBtnDisciplina.setEnabled(true);
    		tbBtnTurma.setEnabled(true);
			tbBtnRendimentoEscolar.setEnabled(true);				
		}
		tbBtnSair.setEnabled(true);
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 828, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SIGA");
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		desktop = new JDesktopPane();
		frame.setContentPane(desktop);

		//Make dragging a little faster but perhaps uglier.
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    desktop.setLayout(null);
	    
	    JPanel pnlBarraTarefa = new JPanel();
	    pnlBarraTarefa.setBounds(0, 0, 822, 51);
	    desktop.add(pnlBarraTarefa);
	    pnlBarraTarefa.add(tbProfessor);
	    tbProfessor.setFloatable(false);
	    
	    tbBtnAluno = new JButton(" Aluno   ");
	    tbBtnAluno.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		FrmAluno frmCadastroAluno= new FrmAluno();
	    		frmCadastroAluno.setVisible(true);
	    	}
	    });
	    tbBtnAluno.setEnabled(false);
	    tbBtnAluno.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/aluno_32.png")));
	    tbBtnAluno.setBackground(Color.WHITE);
	    tbProfessor.add(tbBtnAluno);
	    
	    separator = new JSeparator();
	    separator.setOrientation(SwingConstants.VERTICAL);
	    tbProfessor.add(separator);
	    
	    tbBtnProfessor = new JButton(" Professor    ");
	    tbBtnProfessor.setEnabled(false);
	    tbBtnProfessor.setBackground(Color.WHITE);
	    tbBtnProfessor.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		FrmProfessor frmProfessor= new FrmProfessor();
	    		frmProfessor.setVisible(true);
	    	}
	    });
	    tbBtnProfessor.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/professor_32.png")));
	    tbProfessor.add(tbBtnProfessor);
	    
	    separator_1 = new JSeparator();
	    separator_1.setOrientation(SwingConstants.VERTICAL);
	    tbProfessor.add(separator_1);
	    
	    tbBtnDisciplina = new JButton(" Disciplina   ");
	    tbBtnDisciplina.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		FrmDisciplina frmDisciplina= new FrmDisciplina();
	    		frmDisciplina.setVisible(true);
	    	}
	    });
	    tbBtnDisciplina.setEnabled(false);
	    tbBtnDisciplina.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/disciplina_32.png")));
	    tbBtnDisciplina.setBackground(Color.WHITE);
	    tbProfessor.add(tbBtnDisciplina);
	    
	    separator_3 = new JSeparator();
	    separator_3.setOrientation(SwingConstants.VERTICAL);
	    tbProfessor.add(separator_3);
	    
	    tbBtnTurma = new JButton(" Turma   ");
	    tbBtnTurma.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		FrmTurma frmTurma= new FrmTurma();
	    		frmTurma.setVisible(true);
	    	}
	    });
	    tbBtnTurma.setEnabled(false);
	    tbBtnTurma.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/classe_32.png")));
	    tbBtnTurma.setBackground(Color.WHITE);
	    tbProfessor.add(tbBtnTurma);
	    
	    separator_2 = new JSeparator();
	    separator_2.setOrientation(SwingConstants.VERTICAL);
	    tbProfessor.add(separator_2);
	    
	    tbBtnRendimentoEscolar = new JButton(" Rendimento escolar   ");
	    tbBtnRendimentoEscolar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	    	}
	    });
	    tbBtnRendimentoEscolar.setEnabled(false);
	    tbBtnRendimentoEscolar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/redimentoEscolar_32.png")));
	    tbBtnRendimentoEscolar.setBackground(Color.WHITE);
	    tbProfessor.add(tbBtnRendimentoEscolar);
	    
	    separator_4 = new JSeparator();
	    separator_4.setOrientation(SwingConstants.VERTICAL);
	    tbProfessor.add(separator_4);
	    
	    tbBtnSair = new JButton(" Sair   ");
	    tbBtnSair.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		FrmPrincipal.perfilLogado= null;
	    		tbBtnProfessor.setEnabled(false);
	    		tbBtnAluno.setEnabled(false);
	    		tbBtnDisciplina.setEnabled(false);
	    		tbBtnTurma.setEnabled(false);
	    		tbBtnRendimentoEscolar.setEnabled(false);
	    		frame.setVisible(false);
	    		
	    		FrmLogin login= new FrmLogin();
	    		login.setVisible(true);
	    	}
	    	
	    });
	    tbBtnSair.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/sair_32.png")));
	    tbBtnSair.setEnabled(false);
	    tbBtnSair.setBackground(Color.WHITE);
	    tbProfessor.add(tbBtnSair);
	    
	    JPanel pnlCorpo = new JPanel();
	    pnlCorpo.setBounds(0, 51, 822, 410);
	    pnlCorpo.setBackground(new Color(255, 255, 255));
	    desktop.add(pnlCorpo);
	    pnlCorpo.setLayout(null);
        
	    //create the tree by passing in the root node
	    root= new DefaultMutableTreeNode("Turmas");
	    tree = new JTree(root);
	    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	    tree.setBounds(10, 10, 250, 380);
	    tree.addTreeSelectionListener(new TreeSelectionListener() {
	        public void valueChanged(TreeSelectionEvent e) {
			    TreePath[] paths = tree.getSelectionPaths();
		        for (TreePath path : paths) {
		        	
		        	String texto= path.getLastPathComponent().toString();
		        	String numero= texto.substring(7,9);
		        	try {
						int id= new Integer(numero);
						RendimentoEscolar rendEsc= Fachada.getInstance()
								.buscarRendimentoEscolarPorId(id);
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
		        }
	        }
	    });    
	    
	    carregar( root );

	    pnlTree = new Panel();
	    pnlTree.setBackground(new Color(255, 255, 255));
	    pnlTree.setBounds(0, 0, 300, 410);
	    pnlCorpo.add(pnlTree);
	    pnlTree.setLayout(null);
	    pnlTree.add(tree);

	}

	private void carregar(DefaultMutableTreeNode root) {
		
		// Carregar as turmas
		List<Turma> turmas= new ArrayList<Turma>();
		if (Constantes.PERFIL_ALUNO.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {
			turmas= Fachada.getInstance().listarTurmasAluno(alunoLogado);
			
		} else if (Constantes.PERFIL_PROFESSOR.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {
			turmas= Fachada.getInstance().listarTurmasProfessor(professorLogado);
		} 		
		
		for (Turma turma : turmas) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(turma.getDisciplina().getNome());
			root.add( node );
			
			// buscar os alunos da turma
			List<RendimentoEscolar> rendEscolarres = Fachada.getInstance().listarRendimentoEscolarPorTurma( turma ); 
			for (RendimentoEscolar rendimentoEscolar : rendEscolarres) {
				DefaultMutableTreeNode nodeRend = new DefaultMutableTreeNode(
						"Aluno: "+ FrmPrincipal.completeToLeft(
								rendimentoEscolar.getAluno().getId() + "",'0' ,2)
						 +" " + rendimentoEscolar.getAluno().getNome());
				node.add( nodeRend );
			}
		}
		
		// Carregar os rendimentos
		
	}
	
	public static String completeToLeft(String value, char c, int size) {
		String result = value;
		while (result.length() < size) {
			result = c + result;
		}
		return result;
	}
}
