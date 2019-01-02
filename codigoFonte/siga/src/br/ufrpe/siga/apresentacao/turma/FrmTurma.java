package br.ufrpe.siga.apresentacao.turma;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import br.ufrpe.siga.apresentacao.aluno.TableModelAluno;
import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.Fachada;
import br.ufrpe.siga.negocio.entidade.Aluno;
import br.ufrpe.siga.negocio.entidade.Disciplina;
import br.ufrpe.siga.util.Constantes;

public class FrmTurma extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3362196800794746166L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtEmenta;
	private JButton btnSalvar;
	private JButton btnApagar;
	
	private TableModelAluno tbModelAluno;
	private JTable jTableAluno;
	

	/**
	 * Create the dialog.
	 */
	public FrmTurma() {
		setModal(true);
		setTitle("Cadastro de Turma");
		setBounds(100, 100, 683, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 647, 113);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(54, 6, 457, 30);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 14, 46, 14);
		panel.add(lblNome);
		
		JLabel lblNomeDoUsuario = new JLabel("Ementa:");
		lblNomeDoUsuario.setBounds(10, 62, 105, 14);
		panel.add(lblNomeDoUsuario);
		
		txtEmenta = new JTextField();
		txtEmenta.setBounds(64, 54, 438, 30);
		panel.add(txtEmenta);
		txtEmenta.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(FrmTurma.class.getResource("/image/iconfinder_Download_728930 (1).png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					Disciplina disciplina= new Disciplina(0, txtNome.getText(), txtEmenta.getText());
					
					Fachada.getInstance().inserir(disciplina);
					
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
		btnApagar.setIcon(new ImageIcon(FrmTurma.class.getResource("/image/apagar_32.png")));
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
		btnApagar.setBounds(532, 62, 105, 41);
		panel.add(btnApagar);
		
		JPanel pnlLista = new JPanel();
		pnlLista.setBounds(10, 135, 647, 314);
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
		    public void valueChanged(ListSelectionEvent e) {  
		    	int idx= jTableAluno.getSelectedRow();
		    	Aluno aluno= tbModelAluno.getAluno(idx);
		    	
		    	txtNome.setText(aluno.getNome());
		    	txtEmenta.setText(aluno.getNomeUsuario());
		    	
		    }});
		carregarLista();
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
