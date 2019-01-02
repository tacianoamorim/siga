package br.ufrpe.siga.apresentacao.professor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufrpe.siga.apresentacao.FrmPrincipal;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.Fachada;
import br.ufrpe.siga.negocio.entidade.Professor;
import br.ufrpe.siga.util.Constantes;

public class FrmProfessor extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3362196800794746166L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtCargo;
	private JTextField txtNomeUsuario;
	private JTextField txtSenha;
	private JComboBox<Integer> jcbxDia;
	private JComboBox<Integer> jcbxMes;
	private JComboBox<Integer> jcbxAno;
	private JButton btnSalvar;
	private JButton btnApagar;
	
	private TableModelProfessor tbModelProfessor;
	private JTable jTableProfessor;
	

	/**
	 * Create the dialog.
	 */
	public FrmProfessor() {
		setModal(true);
		setTitle("Cadastro de Professor");
		setBounds(100, 100, 683, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 647, 134);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(54, 6, 457, 30);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 14, 46, 14);
		panel.add(lblNome);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento: ");
		lblDataDeNascimento.setBounds(10, 59, 114, 14);
		panel.add(lblDataDeNascimento);
		
		JLabel lblPeriodo = new JLabel("Periodo: ");
		lblPeriodo.setBounds(342, 59, 59, 14);
		panel.add(lblPeriodo);
		
		txtCargo = new JTextField();
		txtCargo.setBounds(404, 51, 105, 30);
		panel.add(txtCargo);
		txtCargo.setColumns(10);
		
		JLabel lblNomeDoUsuario = new JLabel("Nome do usuario:");
		lblNomeDoUsuario.setBounds(10, 103, 105, 14);
		panel.add(lblNomeDoUsuario);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBounds(112, 95, 170, 30);
		panel.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(293, 103, 46, 14);
		panel.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(339, 95, 170, 30);
		panel.add(txtSenha);
		txtSenha.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(FrmProfessor.class.getResource("/image/iconfinder_Download_728930 (1).png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					int dia= Integer.parseInt(jcbxDia.getSelectedItem().toString());
					int mes= Integer.parseInt(jcbxMes.getSelectedItem().toString());
					int ano= Integer.parseInt(jcbxAno.getSelectedItem().toString());
					
					@SuppressWarnings("deprecation")
					Professor professor= new Professor(0, txtNome.getText(), 
							new Date(dia, mes, ano), txtNomeUsuario.getText(), 
							txtSenha.getText(), txtCargo.getText());
					
					Fachada.getInstance().inserir(professor);
					
					carregarLista();
					
					JOptionPane.showMessageDialog(null,"Cadastro realizado.");
					
					if (Constantes.LOGIN_NOVO_CADASTRO_ALUNO.equalsIgnoreCase(FrmPrincipal.acaoInicializacao)) {
						FrmPrincipal.acaoInicializacao= null;
						setVisible(false);
					} 		
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Existe um erro no preenchimento dos dados");
				}
			}
		});
		btnSalvar.setBounds(532, 11, 105, 41);
		panel.add(btnSalvar);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setHorizontalAlignment(SwingConstants.LEFT);
		btnApagar.setIcon(new ImageIcon(FrmProfessor.class.getResource("/image/apagar_32.png")));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	try {
		    		int idx= jTableProfessor.getSelectedRow();
		    		Professor professor= tbModelProfessor.getProfessor(idx);
					Fachada.getInstance().apagar(professor);
					
					carregarLista();
					
				} catch (RegistroNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null,"Foi n�o encontrado o professor selecionado");
					e.printStackTrace();
				}
			}
		});
		btnApagar.setBounds(532, 76, 105, 41);
		panel.add(btnApagar);
		
		jcbxDia = new JComboBox<Integer>();
		jcbxDia.setBounds(120, 52, 59, 30);
		panel.add(jcbxDia);
		
		jcbxMes = new JComboBox<Integer>();
		jcbxMes.setBounds(186, 51, 57, 30);
		panel.add(jcbxMes);
		
		jcbxAno = new JComboBox<Integer>();
		jcbxAno.setBounds(250, 51, 79, 30);
		panel.add(jcbxAno);
		
		JPanel pnlLista = new JPanel();
		pnlLista.setBounds(10, 156, 647, 293);
		contentPanel.add(pnlLista);
		
		tbModelProfessor = new TableModelProfessor();
		pnlLista.setLayout(new BoxLayout(pnlLista, BoxLayout.X_AXIS));
		jTableProfessor = new JTable(tbModelProfessor);
		formatarTabela(jTableProfessor);	
		
		JScrollPane scrollPane = new JScrollPane(jTableProfessor);
		jTableProfessor.setFillsViewportHeight(true);
		
		pnlLista.add(scrollPane);	
		
		
		jTableProfessor.getSelectionModel().addListSelectionListener(  
		  new ListSelectionListener() {  
		    @SuppressWarnings("deprecation")
			public void valueChanged(ListSelectionEvent e) {  
		    	int idx= jTableProfessor.getSelectedRow();
		    	Professor professor= tbModelProfessor.getProfessor(idx);
		    	
		    	txtNome.setText(professor.getNome());
		    	txtNomeUsuario.setText(professor.getNomeUsuario());
		    	txtSenha.setText(professor.getSenha());
		    	txtCargo.setText(professor.getCargo());
		    	
		    	jcbxDia.setSelectedItem(professor.getDataNascimento().getDate());
		    	jcbxMes.setSelectedItem(professor.getDataNascimento().getMonth()+1);
		    	jcbxAno.setSelectedItem(professor.getDataNascimento().getYear());
		    	
		    }});
		
		carregarDados();
	}
	
	private void carregarDados() {
		
		for (int i = 1; i <= 31; i++) {
			jcbxDia.addItem(i);
		}
		for (int i = 1; i <= 12; i++) {
			jcbxMes.addItem(i);
		}
		for (int i = 2019; i > 1930; i--) {
			jcbxAno.addItem(i);
		}		
		
		if (Constantes.LOGIN_NOVO_CADASTRO_ALUNO.equalsIgnoreCase(FrmPrincipal.acaoInicializacao)) {
			btnApagar.setEnabled(false);
			
			
		} else {
			btnApagar.setEnabled(true);
			
			carregarLista();
			
		}
		
	}

	private void carregarLista() {
		// Carrega a lista de jogadores do time
		List<Professor> professors= Fachada.getInstance().listarProfessores();
		
		tbModelProfessor.limpar();
		
		// Adiciona os professors
		for (Professor professor : professors ) {
			tbModelProfessor.addProfessor(professor);
		}
	}

	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
	}
}
