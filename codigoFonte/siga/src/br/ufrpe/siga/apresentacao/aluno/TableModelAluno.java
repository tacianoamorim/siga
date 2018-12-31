package br.ufrpe.siga.apresentacao.aluno;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.siga.negocio.entidade.Aluno;

public class TableModelAluno extends AbstractTableModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633858949677031416L;
	
	private List<Aluno> alunos;
	private String[] colunas = new String[] { 
		"Cod", "Nome", "Nome usuario" 
	};

	/** Creates a new instance of TableModel */
	public TableModelAluno(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public TableModelAluno() {
		this.alunos = new ArrayList<Aluno>();
	}

	public int getRowCount() {
		return alunos.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setValueAt(Aluno aValue, int rowIndex) {
		Aluno aluno = alunos.get(rowIndex);
		
		aluno.setId( aValue.getId() );
		aluno.setNome( aValue.getNome() );
		aluno.setNomeUsuario( aValue.getNomeUsuario() );
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Aluno aluno = alunos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			aluno.setId( Integer.parseInt(aValue.toString()) );
		case 1:
			aluno.setNome(aValue.toString());
		case 2:
			aluno.setNomeUsuario( aValue.toString() );
					
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Aluno alunoSelecionado = alunos.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = String.valueOf(alunoSelecionado.getId());
			break;
		case 1:
			valueObject = alunoSelecionado.getNome();
			break;
		case 2:
			valueObject = alunoSelecionado.getNomeUsuario();
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Aluno.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Aluno getAluno(int indiceLinha) {
		return alunos.get(indiceLinha);
	}

	public void addAluno(Aluno aluno) {
		alunos.add(aluno);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeAluno(int indiceLinha) {
		alunos.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeAlunos(List<Aluno> novosAlunos) {
		int tamanhoAntigo = getRowCount();
		alunos.addAll(novosAlunos);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		alunos.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return alunos.isEmpty();
	}

}