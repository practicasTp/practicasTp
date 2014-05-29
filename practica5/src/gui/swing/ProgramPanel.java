package gui.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
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
		for(int i=0;i<p.getSizeProgram();i++){
			if(i == this.pc){
				allProgram += "*    "+p.lineToString(i);
			}else{
				allProgram += "      "+p.lineToString(i);
			}
		}
		
		programTextArea.setText(allProgram);
	}
	
	public void onStartInstrExecution(Instruction instr) {}
	
	/**
	 * Actualizamos el programa
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack,  final ProgramMv program) {
		this.pc = pc;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showProgram(program);
			}
		});
	}

	public void onStartRun() {}

	public void onEndRun() {}

	public void onError(String msg) {}
	
	public void onHalt() {}

	/**
	 * Repintamos el programa
	 */
	public void onReset(final ProgramMv p) {
		this.pc = 0;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showProgram(p);
			}
		});
	}
	
	public void onNewIn() {}
}