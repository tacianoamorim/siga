package br.ufrpe.siga.apresentacao.rendimentoEscolar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.Fachada;
import br.ufrpe.siga.negocio.entidade.RendimentoEscolar;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRendimentoEscolar extends JDialog {

	private JLabel lblNomeAluno;
	private JLabel lblDisciplina;
	private RendimentoEscolar rendimentoEscolar;
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3195427029015322934L;
	private JTextField txtNota1VA;
	private JTextField txtNota2VA;
	private JTextField txtNota01;
	private JTextField txtNota02;
	private JTextField txtNota03;
	private JTextField txtNota04;
	private JTextArea txtArea01;
	private JTextArea txtArea02;
	private JTextArea txtArea03;
	private JTextArea txtArea04;


	/**
	 * Create the dialog.
	 */
	public FrmRendimentoEscolar( RendimentoEscolar rendimentoEscolar ) {
		setTitle("Rendimento Escolar");
		
		this.rendimentoEscolar= rendimentoEscolar;
		
		setBounds(100, 100, 459, 408);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(5, 329, 428, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							
							int nota1Va= new Integer(txtNota1VA.getText());
							int nota2Va= new Integer(txtNota2VA.getText());
							
							float nota1Tb= new Float(txtNota01.getText());
							float nota2Tb= new Float(txtNota02.getText());
							float nota3Tb= new Float(txtNota03.getText());
							float nota4Tb= new Float(txtNota04.getText());							
							
							rendimentoEscolar.setNota1VA(nota1Va);
							rendimentoEscolar.setNota2VA(nota2Va);
							
							rendimentoEscolar.getNotaTrabalhos()[0]= nota1Tb;
							rendimentoEscolar.getNotaTrabalhos()[1]= nota2Tb;
							rendimentoEscolar.getNotaTrabalhos()[2]= nota3Tb;
							rendimentoEscolar.getNotaTrabalhos()[3]= nota4Tb;
							
							rendimentoEscolar.getTrabalhos()[0]= txtArea01.getText();
							rendimentoEscolar.getTrabalhos()[1]= txtArea02.getText();
							rendimentoEscolar.getTrabalhos()[2]= txtArea03.getText();
							rendimentoEscolar.getTrabalhos()[3]= txtArea04.getText();							
							
							Fachada.getInstance().alterar(rendimentoEscolar);
							
						} catch (RegistroNaoEncontradoException e) {
							e.printStackTrace();
						}
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(10, 11, 423, 61);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JLabel lblAluno = new JLabel("Aluno:");
				lblAluno.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblAluno.setBounds(10, 37, 63, 14);
				panel.add(lblAluno);
			}
			{
				lblNomeAluno = new JLabel("lbl");
				lblNomeAluno.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNomeAluno.setBounds(81, 37, 322, 14);
				panel.add(lblNomeAluno);
			}
			{
				lblDisciplina = new JLabel("New label");
				lblDisciplina.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblDisciplina.setBounds(10, 11, 325, 14);
				panel.add(lblDisciplina);
			}
		}
		{
			JLabel lblNota = new JLabel("Nota 1 VA: ");
			lblNota.setBounds(20, 95, 72, 14);
			getContentPane().add(lblNota);
		}
		
		txtNota1VA = new JTextField();
		txtNota1VA.setBounds(82, 83, 106, 30);
		getContentPane().add(txtNota1VA);
		txtNota1VA.setColumns(10);
		
		JLabel lblNotaVa = new JLabel("Nota 2 VA:");
		lblNotaVa.setBounds(257, 95, 72, 14);
		getContentPane().add(lblNotaVa);
		
		txtNota2VA = new JTextField();
		txtNota2VA.setBounds(320, 83, 113, 30);
		getContentPane().add(txtNota2VA);
		txtNota2VA.setColumns(10);
		
		JLabel lblTrabalhos = new JLabel("Trabalhos");
		lblTrabalhos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrabalhos.setBounds(178, 124, 106, 19);
		getContentPane().add(lblTrabalhos);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 145, 423, 184);
		getContentPane().add(tabbedPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Tb 01", null, layeredPane, null);
		
		txtArea01 = new JTextArea();
		txtArea01.setBounds(10, 11, 398, 102);
		layeredPane.add(txtArea01);
		
		JLabel lblNota_1 = new JLabel("Nota");
		lblNota_1.setBounds(266, 124, 46, 14);
		layeredPane.add(lblNota_1);
		
		txtNota01 = new JTextField();
		txtNota01.setBounds(322, 121, 86, 28);
		layeredPane.add(txtNota01);
		txtNota01.setColumns(10);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Tb 02", null, layeredPane_2, null);
		
		txtArea02 = new JTextArea();
		txtArea02.setBounds(10, 11, 398, 102);
		layeredPane_2.add(txtArea02);
		
		JLabel label = new JLabel("Nota");
		label.setBounds(266, 124, 46, 14);
		layeredPane_2.add(label);
		
		txtNota02 = new JTextField();
		txtNota02.setColumns(10);
		txtNota02.setBounds(322, 121, 86, 28);
		layeredPane_2.add(txtNota02);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("Tb 03", null, layeredPane_3, null);
		
		txtArea03 = new JTextArea();
		txtArea03.setBounds(10, 11, 398, 102);
		layeredPane_3.add(txtArea03);
		
		JLabel label_1 = new JLabel("Nota");
		label_1.setBounds(266, 124, 46, 14);
		layeredPane_3.add(label_1);
		
		txtNota03 = new JTextField();
		txtNota03.setColumns(10);
		txtNota03.setBounds(322, 121, 86, 28);
		layeredPane_3.add(txtNota03);
		
		JLayeredPane layeredPane_4 = new JLayeredPane();
		tabbedPane.addTab("Tb 04", null, layeredPane_4, null);
		
		txtArea04 = new JTextArea();
		txtArea04.setBounds(10, 11, 398, 102);
		layeredPane_4.add(txtArea04);
		
		JLabel label_2 = new JLabel("Nota");
		label_2.setBounds(266, 124, 46, 14);
		layeredPane_4.add(label_2);
		
		txtNota04 = new JTextField();
		txtNota04.setColumns(10);
		txtNota04.setBounds(322, 121, 86, 28);
		layeredPane_4.add(txtNota04);
		
		carregar();
	}

	private void carregar() {
		lblDisciplina.setText(this.rendimentoEscolar.getTurma().getDisciplina().getNome());
		lblNomeAluno.setText(this.rendimentoEscolar.getAluno().getNome());
		
		txtNota01.setText( this.rendimentoEscolar.getNota1VA() + "");
		System.out.println( "NOTA v1 "+ this.rendimentoEscolar.getNota1VA() + "");
		txtNota02.setText( this.rendimentoEscolar.getNota2VA() + "");
		System.out.println( "NOTA v2 "+ this.rendimentoEscolar.getNota2VA() + "");
		
		txtArea01.setText(  this.rendimentoEscolar.getTrabalhos()[0]  );
		txtNota01.setText( this.rendimentoEscolar.getNotaTrabalhos()[0] +"");
		
		txtArea02.setText(  this.rendimentoEscolar.getTrabalhos()[1]  );
		txtNota02.setText( this.rendimentoEscolar.getNotaTrabalhos()[1] +"");
		
		txtArea03.setText(  this.rendimentoEscolar.getTrabalhos()[2]  );
		txtNota03.setText( this.rendimentoEscolar.getNotaTrabalhos()[2] +"");
		
		txtArea04.setText(  this.rendimentoEscolar.getTrabalhos()[3]  );
		txtNota04.setText( this.rendimentoEscolar.getNotaTrabalhos()[3] +"");
	}
	

}
