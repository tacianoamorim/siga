package br.ufrpe.siga.apresentacao.disciplina;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.ufrpe.siga.negocio.entidade.Disciplina;

public class TableModelDisciplina extends AbstractTableModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633858949677031416L;
	
	private List<Disciplina> disciplinas;
	private String[] colunas = new String[] { 
		"Cod", "Nome", "Nome usuario" 
	};

	/** Creates a new instance of TableModel */
	public TableModelDisciplina(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public TableModelDisciplina() {
		this.disciplinas = new ArrayList<Disciplina>();
	}

	public int getRowCount() {
		return disciplinas.size();
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

	public void setValueAt(Disciplina aValue, int rowIndex) {
		Disciplina disciplina = disciplinas.get(rowIndex);
		
		disciplina.setId( aValue.getId() );
		disciplina.setNome( aValue.getNome() );
		disciplina.setEmenta( aValue.getEmenta() );
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Disciplina disciplina = disciplinas.get(rowIndex);

		switch (columnIndex) {
		case 0:
			disciplina.setId( Integer.parseInt(aValue.toString()) );
		case 1:
			disciplina.setNome(aValue.toString());
		case 2:
			disciplina.setEmenta( aValue.toString() );
					
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Disciplina disciplinaSelecionado = disciplinas.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = String.valueOf(disciplinaSelecionado.getId());
			break;
		case 1:
			valueObject = disciplinaSelecionado.getNome();
			break;
		case 2:
			valueObject = disciplinaSelecionado.getEmenta();
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Disciplina.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Disciplina getDisciplina(int indiceLinha) {
		return disciplinas.get(indiceLinha);
	}

	public void addDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeDisciplina(int indiceLinha) {
		disciplinas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeDisciplinas(List<Disciplina> novosDisciplinas) {
		int tamanhoAntigo = getRowCount();
		disciplinas.addAll(novosDisciplinas);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		disciplinas.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return disciplinas.isEmpty();
	}

}