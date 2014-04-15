package gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import mv.cpu.Memory;

class MemoryPanel extends JPanel {
	private GUIControler guiCtrl;
	// Componentes visuales
	private JScrollPane _scroll;
	private TableModel _modelo;
	private JTable _tbMemoria;
	private JTextField txtPos;
	private JTextField txtValor;
	private JButton btnWrite;
	private JLabel lblPosicion;
	private JLabel lblValor;

	MemoryPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		// Establecer un borde para el panel
		setBorder(BorderFactory.createTitledBorder("Memoria"));
		
		setLayout(new BorderLayout());
		
		// Crear la tabla con un modelo personalizado
		_modelo = new TableModel();
		_tbMemoria = new JTable(_modelo);
		_scroll = new JScrollPane(_tbMemoria);
		_scroll.setPreferredSize(new Dimension(100,100));
		add(_scroll, BorderLayout.CENTER);
		lblPosicion = new JLabel("Posicion", JLabel.CENTER);
		lblValor = new JLabel("Valor", JLabel.CENTER);
		
		// Crear el panel con los textfields y el botÃƒÂ³n
		JPanel panel = new JPanel();
		txtPos = new JTextField(5);
		txtValor = new JTextField(5);
		btnWrite = new JButton("Write");
		
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.memorySet("","");
			}
		});
		
		panel.add(lblPosicion);
		panel.add(txtPos);
		panel.add(lblValor);
		panel.add(txtValor);
		panel.add(btnWrite);
		add(panel, BorderLayout.SOUTH);
	}

	void updateView() {
		_modelo.refresh();
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
