package br.ufrpe.siga.apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import br.ufrpe.siga.apresentacao.aluno.FrmAluno;
import br.ufrpe.siga.apresentacao.disciplina.FrmDisciplina;
import br.ufrpe.siga.apresentacao.professor.FrmProfessor;
import br.ufrpe.siga.apresentacao.turma.FrmTurma;
import br.ufrpe.siga.util.Constantes;

public class FrmPrincipal {

	JFrame frame;
	JDesktopPane desktop;
	
	public static String acaoInicializacao;
	public static String perfilLogado;
	private final JToolBar tbProfessor = new JToolBar();
	private JButton tbBtnAluno;
	private JButton tbBtnProfessor;
	private JPanel panel;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton tbBtnDisciplina;
	private JSeparator separator_2;
	private JButton tbBtnRendimentoEscolar;
	private JSeparator separator_3;
	private JButton tbBtnTurma;
	private JSeparator separator_4;
	private JButton tbBtnSair;

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
		
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 912, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SIGA");
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		desktop = new JDesktopPane();
		frame.setContentPane(desktop);

		//Make dragging a little faster but perhaps uglier.
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    desktop.setLayout(new BoxLayout(desktop, BoxLayout.X_AXIS));
	    
	    panel = new JPanel();
	    desktop.add(panel);
	    panel.add(tbProfessor);
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
	    	}
	    	
	    });
	    tbBtnSair.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/image/sair_32.png")));
	    tbBtnSair.setEnabled(false);
	    tbBtnSair.setBackground(Color.WHITE);
	    tbProfessor.add(tbBtnSair);
		
	}

}
