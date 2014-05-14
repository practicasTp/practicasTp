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

	public ProgramPanel(GUIControler ctrl, Observable<CPUObserver> cpu) { 
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
	}
	
	/**
	 * Actualiza la información del programPanel
	 */
	void updateView() {
		ProgramMv program = guiCtrl.getProgram();
		String allProgram = "";
		for(int i=0;i<program.getSizeProgram();i++){
			if(i == guiCtrl.getPC()){
				allProgram += "*    "+program.lineToString(i);
			}else{
				allProgram += "      "+program.lineToString(i);
			}
		}
		
		programTextArea.setText(allProgram);
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
}