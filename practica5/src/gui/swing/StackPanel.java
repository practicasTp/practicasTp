package gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.Observable;
import observers.StackObserver;
import controllers.GUIControler;

public class StackPanel extends JPanel implements StackObserver<Integer>, CPUObserver{
	private GUIControler guiCtrl;
	private JScrollPane _scroll;
	private JList _lstPila;
	private DefaultListModel _modeloLista;
	private JLabel lblValor; 
	private JTextField txtValor;
	private JButton btnPush;
	private JButton btnPop;

	public StackPanel(GUIControler ctrl, Observable<StackObserver<Integer>> stack, Observable<CPUObserver> cpu) {
		stack.addObserver(this);
		cpu.addObserver(this);
		this.guiCtrl = ctrl;
		initGUI();
		
	}
	
	/**
	 * Inicializa el StackPanel creando el panel y una lista para mostrar la
	 * información de la pila. También crea los botones que permitirán interactuar
	 * con la pila desde la interfaz.
	 */
	private void initGUI() {
		// Establecer un borde para el panel
		setBorder(BorderFactory.createTitledBorder("Pila de Operandos"));
		setLayout(new BorderLayout());
		// Crear un componente de tipo JList con un modelo personalizado
		_modeloLista = new DefaultListModel();
		_lstPila = new JList(_modeloLista);
		_lstPila.setFont(new Font("Courier", Font.BOLD, 16));
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)_lstPila.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  
		//alineo al centro el contenido de la lista
		_scroll = new JScrollPane(_lstPila);
		_scroll.setPreferredSize(new Dimension(560, 260));
		add(_scroll,BorderLayout.CENTER);
		
		//Botones
		JPanel panel = new JPanel();
		lblValor = new JLabel("Valor", JLabel.CENTER);
		txtValor = new JTextField(5);
		btnPush = new JButton("Push");
		
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (guiCtrl.validarOperando(txtValor.getText())) {
					guiCtrl.push(txtValor.getText());
				} else {
					JDialog error = new JDialog();
					JOptionPane.showMessageDialog(error,"Sólo están admitidos los números en la pila");
				}
				txtValor.setText("");
			}
		});
		
		btnPop = new JButton("Pop");
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.pop();
			}
		});
		
		
		panel.add(lblValor);
		panel.add(txtValor);
		panel.add(btnPush);
		panel.add(btnPop);
		add(panel, BorderLayout.SOUTH);	
	}
	
	@Override
	public void onStartInstrExecution(Instruction instr) {}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {}

	/**
	 * Desactiva los botones y el textField al ejecutar el comando run.
	 */
	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				txtValor.setEnabled(false);
				btnPush.setEnabled(false);
				btnPop.setEnabled(false);
			}
		});
	}

	/**
	 * Activa los botones y el textField al terminar la ejecución del comando run.
	 */
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				txtValor.setEnabled(true);
				btnPush.setEnabled(true);
				btnPop.setEnabled(true);
			}
		});
	}

	@Override
	public void onError(String msg) {}

	/**
	 * Desactiva los botones y el textField al terminar la ejecución del programa.
	 */
	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				txtValor.setEnabled(false);
				btnPush.setEnabled(false);
				btnPop.setEnabled(false);
			}
		});
	}

	/**
	 * Limpia la ventana donde se muestran los valores de la pila al resetear la
	 * máquina.
	 */
	public void onReset(ProgramMv program) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_modeloLista.clear();
				txtValor.setEnabled(true);
				btnPush.setEnabled(true);
				btnPop.setEnabled(true);
			}
		});
	}

	/**
	 * Añade un elemento a la ventana donde se muestran los valores de la pila.
	 */
	public void onPush(final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_modeloLista.addElement(value);
			}
		});
	}

	/**
	 * Elimina un elemento de la ventana donde se muestran los valores de la pila.
	 */
	public void onPop(final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_modeloLista.removeElement(value);
			}
		});
	}

	/**
	 * Limpia el contenido de la pila, la vacía.
	 */
	public void onStackReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_modeloLista.clear();
			}
		});
	}

	@Override
	public void onNewIn() {}
}