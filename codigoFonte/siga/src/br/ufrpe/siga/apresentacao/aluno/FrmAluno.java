package br.ufrpe.siga.apresentacao.aluno;

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
import br.ufrpe.siga.negocio.entidade.Aluno;
import br.ufrpe.siga.util.Constantes;

public class FrmAluno extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3362196800794746166L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtPeriodo;
	private JTextField txtNomeUsuario;
	private JTextField txtSenha;
	private JComboBox<Integer> jcbxDia;
	private JComboBox<Integer> jcbxMes;
	private JComboBox<Integer> jcbxAno;
	private JButton btnSalvar;
	private JButton btnApagar;
	
	private TableModelAluno tbModelAluno;
	private JTable jTableAluno;
	

	/**
	 * Create the dialog.
	 */
	public FrmAluno() {
		setModal(true);
		setTitle("Cadastro de Aluno");
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
		
		txtPeriodo = new JTextField();
		txtPeriodo.setBounds(404, 51, 105, 30);
		panel.add(txtPeriodo);
		txtPeriodo.setColumns(10);
		
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
		btnSalvar.setIcon(new ImageIcon(FrmAluno.class.getResource("/image/iconfinder_Download_728930 (1).png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					int periodo= Integer.parseInt( txtPeriodo.getText() );
					
					int dia= Integer.parseInt(jcbxDia.getSelectedItem().toString());
					int mes= Integer.parseInt(jcbxMes.getSelectedItem().toString());
					int ano= Integer.parseInt(jcbxAno.getSelectedItem().toString());
					
					@SuppressWarnings("deprecation")
					Aluno aluno= new Aluno(0, txtNome.getText(), 
							new Date(dia, mes, ano), txtNomeUsuario.getText(), 
							txtSenha.getText(), periodo);
					
					Fachada.getInstance().inserir(aluno);
					
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
		btnApagar.setIcon(new ImageIcon(FrmAluno.class.getResource("/image/apagar_32.png")));
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	try {
		    		int idx= jTableAluno.getSelectedRow();
		    		Aluno aluno= tbModelAluno.getAluno(idx);
					Fachada.getInstance().apagar(aluno);
					
					carregarLista();
					
				} catch (RegistroNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null,"Foi não encontrado o aluno selecionado");
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
		
		tbModelAluno = new TableModelAluno();
		pnlLista.setLayout(new BoxLayout(pnlLista, BoxLayout.X_AXIS));
		jTableAluno = new JTable(tbModelAluno);
		formatarTabela(jTableAluno);	
		
		JScrollPane scrollPane = new JScrollPane(jTableAluno);
		jTableAluno.setFillsViewportHeight(true);
		
		pnlLista.add(scrollPane);	
		
		
		jTableAluno.getSelectionModel().addListSelectionListener(  
		  new ListSelectionListener() {  
		    @SuppressWarnings("deprecation")
			public void valueChanged(ListSelectionEvent e) {  
		    	int idx= jTableAluno.getSelectedRow();
		    	Aluno aluno= tbModelAluno.getAluno(idx);
		    	
		    	txtNome.setText(aluno.getNome());
		    	txtNomeUsuario.setText(aluno.getNomeUsuario());
		    	txtPeriodo.setText(aluno.getPeriodo()+"");
		    	txtSenha.setText(aluno.getSenha());
		    	
		    	jcbxDia.setSelectedItem(aluno.getDataNascimento().getDate());
		    	jcbxMes.setSelectedItem(aluno.getDataNascimento().getMonth()+1);
		    	jcbxAno.setSelectedItem(aluno.getDataNascimento().getYear());
		    	
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
		List<Aluno> alunos= Fachada.getInstance().listarAlunos();
		
		tbModelAluno.limpar();
		
		// Adiciona os alunos
		for (Aluno aluno : alunos ) {
			tbModelAluno.addAluno(aluno);
		}
	}

	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
	}
}
