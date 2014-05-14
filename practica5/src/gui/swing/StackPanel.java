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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
				guiCtrl.push(txtValor.getText());
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
	
	/**
	 * Actualiza la información mostrada en la interfaz sobre la pila.
	 */
	void updateView() {
		//obtengo la pila actual
		OperandStack<Integer> operandStack = guiCtrl.getOperandStack();
		//limpio el modelo
		_modeloLista.clear();
		//voy insertando cada elemento de la pila dentro del modelo
		for (int i=0; i<= operandStack.getCima(); i++){
			_modeloLista.addElement(operandStack.operandToString(i));
		}
		
		if(this.guiCtrl.finished()){
			txtValor.setEnabled(false);
			btnPush.setEnabled(false);
			btnPop.setEnabled(false);
		}
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMv program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPush(Integer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPop(Integer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStackReset() {
		// TODO Auto-generated method stub
		
	}
}