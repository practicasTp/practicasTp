package gui.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import observers.CPUObserver;
import observers.Observable;
import controllers.GUIControler;

public class ProgramPanel extends JPanel implements CPUObserver { 
	private GUIControler guiCtrl;
	private JTextArea programTextArea;
	private int pc;

	public ProgramPanel(GUIControler ctrl, Observable<CPUObserver> cpu) { 
		cpu.addObserver(this);
		this.guiCtrl = ctrl; 
		initGUI();
	}
	/**
	 * Inicializa el ProgramPanel creando el programTextArea para poder mostrar la
	 * información del programa.
	 */
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Programa"));
		programTextArea = new JTextArea();
		programTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		programTextArea.setFont( new Font("Courier", Font.PLAIN, 16));
		programTextArea.setEditable(false);
		this.add(new JScrollPane(programTextArea));
		this.setPreferredSize(new Dimension(160, 0));
		this.pc=0;
	}
	
	private void showProgram(ProgramMv p){
		String allProgram = "";
		this.pc = guiCtrl.getPC();
		for(int i=0;i<p.getSizeProgram();i++){
			if(i == this.pc){
				allProgram += "*    "+p.lineToString(i);
			}else{
				allProgram += "      "+p.lineToString(i);
			}
		}
		
		programTextArea.setText(allProgram);
	}
	
	/**
	 * No se hace nada en este metodo
	 */
	public void onStartInstrExecution(Instruction instr) {}
	
	/**
	 * Actualizamos el programa
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program) {
		this.showProgram(program);
	}

	/**
	 * No se hace nada en este metodo
	 */
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * No se hace nada en este metodo
	 */
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * No se hace nada en este metodo
	 */
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * No se hace nada en este metodo
	 */
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Repintamos el programa
	 */
	public void onReset(ProgramMv p) {
		this.showProgram(p);
	}
	@Override
	public void onNewIn() {
		// TODO Auto-generated method stub
		
	}
}