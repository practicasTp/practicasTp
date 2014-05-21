package gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.MemoryObserver;
import observers.Observable;
import observers.StackObserver;
import controllers.GUIControler;

public class StatePanel extends JPanel implements MemoryObserver<Integer>, StackObserver<Integer>, CPUObserver {
	
	private static final long serialVersionUID = 1L;
	private GUIControler guiCtrl;
	private JPanel panel;
	private int i; 
	private JLabel lblParada;
	private JLabel lblNumIns;
	private JLabel lblMem;
	private JLabel lblPila;
	private JCheckBox chMem;
	private JCheckBox chPila;
	private boolean	chMemSel = false;
	private boolean	chPilaSel = false;
	
	public StatePanel(GUIControler ctrl, Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
		memory.addObserver(this);
		cpu.addObserver(this);
		stack.addObserver(this);
		this.guiCtrl = ctrl;
		initGUI();
		
	}
	
	/**
	 * Clase que me crea un checkbox readonly
	 */
	public class ReadOnlyCheckBox extends JCheckBox {
	    public ReadOnlyCheckBox (String text, boolean selected) {
	        super(text,selected);
	    }

	    protected void processKeyEvent(KeyEvent e) {
	    }

	    protected void processMouseEvent(MouseEvent e) {

	    }
	}
	
	private void initGUI(){
		
		panel = new JPanel();
		lblParada = new JLabel("Máquina Parada");
		lblParada.setFont(new Font("Courier",Font.PLAIN,20));
		lblParada.setForeground(Color.red);
		lblParada.setVisible(false);
		lblNumIns = new JLabel("Num. instrucciones ejecutadas: " + i);
		lblNumIns.setFont(new Font("Courier",Font.PLAIN,20));
		chMem = new ReadOnlyCheckBox("",false);
		lblMem = new JLabel("Memoria modificada");
		lblMem.setFont(new Font("Courier",Font.PLAIN,20));
		chPila = new ReadOnlyCheckBox("",false);
		lblPila = new JLabel("Pila modificada");
		lblPila.setFont(new Font("Courier",Font.PLAIN,20));
		panel.add(lblParada);
		panel.add(lblNumIns);
		panel.add(chMem);
		panel.add(lblMem);
		panel.add(chPila);
		panel.add(lblPila);
		add(panel, BorderLayout.SOUTH);
	}
	
	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {
		lblNumIns.setText("Num. instrucciones ejecutadas: " + ++i);
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
		chMem.setSelected(false);
		chPila.setSelected(false);
		lblParada.setVisible(true);	
	}

	@Override
	public void onReset(ProgramMv program) {
		chMem.setSelected(false);
		chPila.setSelected(false);
		lblParada.setVisible(false);	
		lblNumIns.setText("Num. instrucciones ejecutadas: " + 0);
		this.i=0;
	}

	@Override
	public void onPush(Integer value) {
		chPila.setSelected(true);
		chMem.setSelected(false);
	}

	@Override
	public void onPop(Integer value) {
		chPila.setSelected(true);
		chMem.setSelected(false);
	}

	@Override
	public void onStackReset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWrite(int index, Integer value) {
		chMem.setSelected(true);
		chPila.setSelected(false);
	}

	@Override
	public void onMemReset() {}

	
}
