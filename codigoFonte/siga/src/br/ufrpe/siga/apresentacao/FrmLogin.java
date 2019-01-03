package br.ufrpe.siga.apresentacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrpe.siga.apresentacao.aluno.FrmAluno;
import br.ufrpe.siga.negocio.Fachada;
import br.ufrpe.siga.negocio.entidade.Aluno;
import br.ufrpe.siga.negocio.entidade.Disciplina;
import br.ufrpe.siga.negocio.entidade.Professor;
import br.ufrpe.siga.negocio.entidade.RendimentoEscolar;
import br.ufrpe.siga.negocio.entidade.Turma;
import br.ufrpe.siga.util.Constantes;

public class FrmLogin extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1864210157235754121L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtSenha;
	
	private JRadioButton jrbProfessor, jrbAluno, jrbAdministrador;
	private RadioButtonHandler handler;
	private ButtonGroup grupo;
	
	private JButton btnCadastro, tbnLogar;
	

	/**
	 * Create the dialog.
	 */
	public FrmLogin() {
		setTitle("Login");
		setType(Type.POPUP);
		setBounds(100, 100, 398, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		cargaInicial();
		
		{
			JLabel lblNomeUsuario = new JLabel("Nome usuario:");
			lblNomeUsuario.setBounds(10, 19, 102, 14);
			contentPanel.add(lblNomeUsuario);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setText("paulo");
			txtCodigo.setBounds(108, 11, 131, 30);
			txtCodigo.setColumns(10);
			contentPanel.add(txtCodigo);
		}
		{
			txtSenha = new JTextField();
			txtSenha.setText("123");
			txtSenha.setBounds(108, 50, 131, 30);
			txtSenha.setColumns(10);
			contentPanel.add(txtSenha);
		}
		{
			jrbProfessor = new JRadioButton("Professor");
			jrbProfessor.setBounds(259, 7, 117, 23);
			jrbProfessor.setSelected(true);
			jrbProfessor.setBackground(Color.WHITE);
			contentPanel.add(jrbProfessor);
		}
		{
			jrbAluno = new JRadioButton("Aluno");
			jrbAluno.setBounds(259, 33, 117, 23);
			jrbAluno.setBackground(Color.WHITE);
			contentPanel.add(jrbAluno);
		}
		{
			JLabel label = new JLabel("Senha:");
			label.setBounds(10, 58, 88, 14);
			contentPanel.add(label);
		}
		{
			jrbAdministrador = new JRadioButton("Administrador");
			jrbAdministrador.setBounds(259, 57, 117, 23);
			jrbAdministrador.setBackground(Color.WHITE);
			contentPanel.add(jrbAdministrador);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCadastro = new JButton("");
				btnCadastro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						FrmPrincipal.perfilLogado= null;
						
						// Cadastrar professor
						if ( jrbProfessor.isSelected() ) {
							FrmPrincipal.acaoInicializacao= Constantes.LOGIN_NOVO_CADASTRO_PROFESSOR;
							
						}
						
						// Cadastrar aluno
						if ( jrbAluno.isSelected() ) {
							FrmPrincipal.acaoInicializacao= Constantes.LOGIN_NOVO_CADASTRO_ALUNO;
							FrmAluno window = new FrmAluno();
							window.setVisible(true);
						}
						
					}
				});
				btnCadastro.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/novo_32.png")));
				buttonPane.add(btnCadastro);
				getRootPane().setDefaultButton(btnCadastro);
			}
			{
				tbnLogar = new JButton("");
				tbnLogar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// Limpa a variavel
							FrmPrincipal.acaoInicializacao= null;
							
							if ( txtCodigo.getText() != null && 
									txtCodigo.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(null,"Informe o usuario de login");
								
							} else if ( txtSenha.getText() != null && 
									txtSenha.getText().trim().length() == 0 ) {
								JOptionPane.showMessageDialog(null,"Informe a senha");
								
							} else {
								
								String tipo= "";
								if(jrbAluno.isSelected())
									tipo= Constantes.PERFIL_ALUNO;
								if(jrbProfessor.isSelected())
									tipo= Constantes.PERFIL_PROFESSOR;
								if(jrbAdministrador.isSelected())
									tipo= Constantes.PERFIL_ADM;
								
								boolean loginLiberado= 	Fachada.getInstance().login( txtCodigo.getText(), 
										txtSenha.getText(), tipo );
								
								if ( loginLiberado ) {
									setVisible(false);
									FrmPrincipal.perfilLogado= tipo;
									FrmPrincipal window = new FrmPrincipal();
									window.frame.setVisible(true);
								};
							}
							
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null,"Verifique o usuario e a senha informada.");
						}
						
						
					}
				});
				tbnLogar.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/login_32.png")));
				buttonPane.add(tbnLogar);
			}
		}
		
		grupo = new ButtonGroup();
		grupo.add(jrbAdministrador);
		grupo.add(jrbAluno);
		grupo.add(jrbProfessor);
		
		handler = new RadioButtonHandler();
		jrbAdministrador.addItemListener(handler);
		jrbAluno.addItemListener(handler);
		jrbProfessor.addItemListener(handler);
	}
	
	private class RadioButtonHandler implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			if(jrbAdministrador.isSelected()) {
				btnCadastro.setEnabled(false);
				
			} else {
				btnCadastro.setEnabled(true);
				//if(jrbAluno.isSelected())
					//JOptionPane.showMessageDialog(null,"Aluno!");
				//if(jrbProfessor.isSelected())
					//JOptionPane.showMessageDialog(null,"Prof!");				
			}
		}
		
	}
	
	private static void cargaInicial() {
		
		// Professor
		Professor professor01= new Professor(0, "Marx Paulo", new Date(), "marx", "123","1 Prof");
		Fachada.getInstance().inserir(professor01);
		Professor professor02= new Professor(0, "Maria Eduarda", new Date(), "maria", "123","2 Prof");
		Fachada.getInstance().inserir(professor02);
		Professor professor03= new Professor(0, "Paulo Mello", new Date(), "paulo", "123","3 Prof");
		Fachada.getInstance().inserir(professor03);
		
		// Disciplina
		Disciplina disciplina01= new Disciplina(0, "Matemática", "1+2+3+4");
		Fachada.getInstance().inserir(disciplina01);
		Disciplina disciplina02= new Disciplina(0, "Portugues", "blablabla");
		Fachada.getInstance().inserir(disciplina02);
		Disciplina disciplina03= new Disciplina(0, "Ciencias", "Lua e sol");
		Fachada.getInstance().inserir(disciplina03);
		Disciplina disciplina04= new Disciplina(0, "Geografia", "Norte e sul");
		Fachada.getInstance().inserir(disciplina04);
		Disciplina disciplina05= new Disciplina(0, "Fisica", "Velocidade");
		Fachada.getInstance().inserir(disciplina05);
		Disciplina disciplina06= new Disciplina(0, "Quimica", "Materia");
		Fachada.getInstance().inserir(disciplina06);
		
		// Turmas
		Turma turma01= new Turma(0, disciplina01, professor03, 40);
		Fachada.getInstance().inserir(turma01);
		Turma turma02= new Turma(0, disciplina02, professor03, 40);
		Fachada.getInstance().inserir(turma02);
		Turma turma03= new Turma(0, disciplina03, professor02, 40);
		Fachada.getInstance().inserir(turma03);
		Turma turma04= new Turma(0, disciplina04, professor03, 40);
		Fachada.getInstance().inserir(turma04);	
		Turma turma05= new Turma(0, disciplina05, professor01, 40);
		Fachada.getInstance().inserir(turma05);		
		Turma turma06= new Turma(0, disciplina06, professor03, 40);
		Fachada.getInstance().inserir(turma06);	
		Turma turma07= new Turma(0, disciplina05, professor03, 40);
		Fachada.getInstance().inserir(turma07);	
		
		// Aluno
		Aluno aluno01= new Aluno(0, "Andre da Silva", new Date(), "andre", "123", 1);
		Fachada.getInstance().inserir(aluno01);
		Aluno aluno02= new Aluno(0, "Maria Clara", new Date(), "maria", "123", 2);
		Fachada.getInstance().inserir(aluno02);	
		Aluno aluno03= new Aluno(0, "Paulo Marques", new Date(), "paulo", "123", 3);
		Fachada.getInstance().inserir(aluno03);			
		Aluno aluno04= new Aluno(0, "Juliana Dias", new Date(), "dias", "123", 4);
		Fachada.getInstance().inserir(aluno04);	
		Aluno aluno05= new Aluno(0, "Hugo alexandre", new Date(), "hugo", "123", 4);
		Fachada.getInstance().inserir(aluno05);	
		
		// RendimentoEscolar
		RendimentoEscolar rendEscolar01= new RendimentoEscolar(0, turma01, aluno01);
		Fachada.getInstance().inserir(rendEscolar01);
		RendimentoEscolar rendEscolar02= new RendimentoEscolar(0, turma01, aluno02);
		Fachada.getInstance().inserir(rendEscolar02);
		RendimentoEscolar rendEscolar03= new RendimentoEscolar(0, turma02, aluno01);
		Fachada.getInstance().inserir(rendEscolar03);
		RendimentoEscolar rendEscolar04= new RendimentoEscolar(0, turma03, aluno01);
		Fachada.getInstance().inserir(rendEscolar04);
		RendimentoEscolar rendEscolar05= new RendimentoEscolar(0, turma03, aluno02);
		Fachada.getInstance().inserir(rendEscolar05);
		RendimentoEscolar rendEscolar06= new RendimentoEscolar(0, turma03, aluno03);
		Fachada.getInstance().inserir(rendEscolar06);		
		RendimentoEscolar rendEscolar07= new RendimentoEscolar(0, turma03, aluno04);
		Fachada.getInstance().inserir(rendEscolar07);		
		RendimentoEscolar rendEscolar08= new RendimentoEscolar(0, turma01, aluno04);
		Fachada.getInstance().inserir(rendEscolar08);	
		RendimentoEscolar rendEscolar09= new RendimentoEscolar(0, turma01, aluno03);
		Fachada.getInstance().inserir(rendEscolar09);			
	}	

}
