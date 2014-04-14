package gui.swing;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import mv.cpu.Memory;

class MemoryPanel extends JPanel {
	private GUIControler guiCtrl;
	private TableModel model;
	private JTextField memPos;
	private JTextField memValue;

	MemoryPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI() {
		// ...
		model = new TableModel();
		JTable table = new JTable(model);
		// ...
	}

	void updateView() {
		model.refresh();
	}
	
	private class TableModel extends AbstractTableModel {
		String[] colNames = { "Address", "Value" };
		int[][] memTable; // tiene dos columnas

		TableModel() {
			refresh();
		}

		public void refresh() {
			Memory<Integer> memory = guiCtrl.getMemory();
			// - reconstruir memTable a partir de memory
			// - avisar cambios a JTable
			// ...
		}
		// ...

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
