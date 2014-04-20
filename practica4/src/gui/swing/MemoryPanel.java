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
import javax.swing.table.DefaultTableCellRenderer;

import mv.cpu.Memory;

class MemoryPanel extends JPanel {
	private GUIControler guiCtrl;
	// Componentes visuales
	private JScrollPane _scroll;
	protected TableModel _modelo;
	protected JTable _tbMemoria;
	private JTextField txtPos;
	private JTextField txtValor;
	private JButton btnWrite;
	private JLabel lblPosicion;
	private JLabel lblValor;

	MemoryPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	/**
	 * Inicializa la memoria creando la tabla para mostrar los valores y creando
	 * los paneles correspondientes. También crea el botón que permitirá interactuar
	 * con la memoria.
	 */
	private void initGUI() {
		this.setLayout(new BorderLayout());
		// Establecer un borde para el panel
		setBorder(BorderFactory.createTitledBorder("Memoria"));
		
		setLayout(new BorderLayout());
		
		// Crear la tabla con un modelo personalizado
		_modelo = new TableModel();
		_tbMemoria = new JTable(_modelo);
		_tbMemoria.setFillsViewportHeight(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		_tbMemoria.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		_tbMemoria.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		_scroll = new JScrollPane(_tbMemoria);
		_scroll.setPreferredSize(new Dimension(100,100));
		add(_scroll, BorderLayout.CENTER);
		lblPosicion = new JLabel("Posicion", JLabel.CENTER);
		lblValor = new JLabel("Valor", JLabel.CENTER);
		
		// Crear el panel con los textfields y el botón
		JPanel panel = new JPanel();
		txtPos = new JTextField(5);
		txtValor = new JTextField(5);
		btnWrite = new JButton("Write");
		
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.memorySet(txtPos.getText(),txtValor.getText());
				txtPos.setText("");
				txtValor.setText("");
			}
		});
		
		panel.add(lblPosicion);
		panel.add(txtPos);
		panel.add(lblValor);
		panel.add(txtValor);
		panel.add(btnWrite);
		add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Actualiza la información mostrada de la memoria.
	 */
	void updateView() {
		_modelo.refresh();
		if(this.guiCtrl.finished()){
			txtValor.setEnabled(false);
			txtPos.setEnabled(false);
			btnWrite.setEnabled(false);
		}
	}
	
	private class TableModel extends AbstractTableModel {
		String[] colNames = { "Posición", "Valor" };
		int[][] memTable; // tiene dos columnas
		TableModel() {
			refresh();
		}
		
		/**
		 * Refresca el contenido de la tabla de la memoria.
		 */
		public void refresh() {
			Memory<Integer> memory = guiCtrl.getMemory();
			memTable = memory.getMemory();
			this.fireTableDataChanged();
			
		}
		
		/**
		 * Devuelve el nombre de la columna
		 */
		public String getColumnName(int column){
			return colNames[column];
		}
		
		/**
		 * Devuelve 2
		 * @return int
		 */
		@Override
		public int getColumnCount() {
			return 2;
		}
		
		/**
		 * Devuelve la longitud máxima de la memoria.
		 * @return int
		 */
		@Override
		public int getRowCount() {
			Memory<Integer> memory = guiCtrl.getMemory();
			return memory.getMaxLength();
		}
		
		/**
		 * Devuelve el valor de la posición indicada en la tabla.
		 * @return Object
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return memTable[rowIndex][columnIndex];
		}
	}
}
