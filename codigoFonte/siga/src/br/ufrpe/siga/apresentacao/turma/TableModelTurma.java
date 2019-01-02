package br.ufrpe.siga.apresentacao.turma;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.ufrpe.siga.negocio.entidade.Turma;

public class TableModelTurma extends AbstractTableModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633858949677031416L;
	
	private List<Turma> turmas;
	private String[] colunas = new String[] { 
		"Cod", "Nome", "Nome usuario" 
	};

	/** Creates a new instance of TableModel */
	public TableModelTurma(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public TableModelTurma() {
		this.turmas = new ArrayList<Turma>();
	}

	public int getRowCount() {
		return turmas.size();
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

	public void setValueAt(Turma aValue, int rowIndex) {
		Turma turma = turmas.get(rowIndex);
		
		turma.setId( aValue.getId() );
		turma.setCapacidadeTurma( aValue.getCapacidadeTurma() );
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Turma turma = turmas.get(rowIndex);

		switch (columnIndex) {
		case 0:
			turma.setId( Integer.parseInt(aValue.toString()) );
		case 1:
			turma.setCapacidadeTurma( Integer.parseInt(aValue.toString()) );
					
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Turma turmaSelecionado = turmas.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = String.valueOf(turmaSelecionado.getId());
			break;
		case 1:
			valueObject = String.valueOf(turmaSelecionado.getCapacidadeTurma());
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Turma.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Turma getTurma(int indiceLinha) {
		return turmas.get(indiceLinha);
	}

	public void addTurma(Turma turma) {
		turmas.add(turma);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeTurma(int indiceLinha) {
		turmas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeTurmas(List<Turma> novosTurmas) {
		int tamanhoAntigo = getRowCount();
		turmas.addAll(novosTurmas);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		turmas.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return turmas.isEmpty();
	}

}