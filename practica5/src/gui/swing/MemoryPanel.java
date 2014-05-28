package gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.MemoryObserver;
import observers.Observable;
import controllers.GUIControler;

public class MemoryPanel extends JPanel  implements MemoryObserver<Integer>, CPUObserver {
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

	public MemoryPanel(GUIControler ctrl, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
		memory.addObserver(this);
		cpu.addObserver(this);
		this.guiCtrl = ctrl;
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
				JDialog error = new JDialog();
				if (guiCtrl.validarPosicion(txtPos.getText())) {
					if (guiCtrl.validarOperando(txtValor.getText()))
						guiCtrl.memorySet(txtPos.getText(),txtValor.getText());
					else
						JOptionPane.showMessageDialog(error,"Sólo están admitidos los números entero en la memoria.");
				} else
					JOptionPane.showMessageDialog(error,"Sólo están admitidos los números enteros positivos en la posición.");
						
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
	
	private class TableModel extends AbstractTableModel {
		String[] colNames = { "Posición", "Valor" };
		TreeMap<Integer, Integer> content;
		
		public TableModel() { 
			content = new TreeMap<Integer, Integer>();
		}
		
		/**
		 * Modifica el modelo añadiendo un nuevo valor.
		 * @param index
		 * @param value
		 */
		// onWrite llama a setValue cuando hay cambios en la memoria
		public void setValue(int index, int value) {
			//- modiﬁcar la posición de index (en content) para que tenga el valor nuevo
			this.content.put(index,value);
			// - avisar al JTable que el modelo ha sido modiﬁcado
			this.fireTableDataChanged();
			
		}
		
		/**
		 * Resetea el contenido a mostrar de la memoria.
		 */
		// onMemReset (de la memoria) llama a este método 
		public void reset() {      
			this.content.clear();
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
		 * Devuelve el objeto que toca para pintarlo en la tabla
		 * @return Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {			
			Object[] objects = new Object[content.size()];
			//obtengo todas las keys
			content.keySet().toArray(objects);
			//guardo la key que toque en el objeto
			Object key 		 = objects[rowIndex];
			//guardo el valor de la key de turno
			Object value 	 = content.get(key);
			
			//en función de la columna que toca
			switch (columnIndex) {
				case 0://devuelvo la key
					return key;
				case 1://devuelvo el valor
					return value;
			}
			
			//si la columna no existe, retorno null
			return null;
		}
	}
	
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {}

	/**
	 * Desactiva los textField y el botón al ejecutar el comando run.
	 */
	public void onStartRun() {
		txtValor.setEnabled(false);
		txtPos.setEnabled(false);
		btnWrite.setEnabled(false);		
	}

	/**
	 * Activa los textField y el botón al finalizar la ejecución del comando run.
	 */
	public void onEndRun() {
		txtValor.setEnabled(true);
		txtPos.setEnabled(true);
		btnWrite.setEnabled(true);
	}

	public void onError(String msg) {}

	/**
	 * Desactiva los textField y el botón al finalizar la ejecución del programa.
	 */
	public void onHalt() {
		txtValor.setEnabled(false);
		txtPos.setEnabled(false);
		btnWrite.setEnabled(false);
	}

	/**
	 * Resetea el modelo de la memoria.
	 */
	public void onReset(ProgramMv program) {
		_modelo.reset();
	}

	/**
	 * Añade un valor nuevo al modelo de la memoria.
	 * @param index
	 * @param value
	 */
	public void onWrite(int index, Integer value) {
		_modelo.setValue(index, value);
	}

	
	/**
	 * Resetea el modelo de la memoria
	 */
	public void onMemReset() {
		_modelo.reset();
	}

	public void onNewIn() {}
}
