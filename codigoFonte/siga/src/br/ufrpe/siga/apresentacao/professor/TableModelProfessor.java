package br.ufrpe.siga.apresentacao.professor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.siga.negocio.entidade.Professor;

public class TableModelProfessor extends AbstractTableModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633858949677031416L;
	
	private List<Professor> professors;
	private String[] colunas = new String[] { 
		"Cod", "Nome", "Nome usuario" 
	};

	/** Creates a new instance of TableModel */
	public TableModelProfessor(List<Professor> professors) {
		this.professors = professors;
	}

	public TableModelProfessor() {
		this.professors = new ArrayList<Professor>();
	}

	public int getRowCount() {
		return professors.size();
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

	public void setValueAt(Professor aValue, int rowIndex) {
		Professor professor = professors.get(rowIndex);
		
		professor.setId( aValue.getId() );
		professor.setNome( aValue.getNome() );
		professor.setNomeUsuario( aValue.getNomeUsuario() );
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Professor professor = professors.get(rowIndex);

		switch (columnIndex) {
		case 0:
			professor.setId( Integer.parseInt(aValue.toString()) );
		case 1:
			professor.setNome(aValue.toString());
		case 2:
			professor.setNomeUsuario( aValue.toString() );
					
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Professor professorSelecionado = professors.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = String.valueOf(professorSelecionado.getId());
			break;
		case 1:
			valueObject = professorSelecionado.getNome();
			break;
		case 2:
			valueObject = professorSelecionado.getNomeUsuario();
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Professor.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Professor getProfessor(int indiceLinha) {
		return professors.get(indiceLinha);
	}

	public void addProfessor(Professor professor) {
		professors.add(professor);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeProfessor(int indiceLinha) {
		professors.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeProfessors(List<Professor> novosProfessors) {
		int tamanhoAntigo = getRowCount();
		professors.addAll(novosProfessors);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		professors.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return professors.isEmpty();
	}

}