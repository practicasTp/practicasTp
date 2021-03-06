package gui.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import mv.program.ProgramMv;

class ProgramPanel extends JPanel {
	private GUIControler guiCtrl;
	private JTextArea programTextArea;

	ProgramPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
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
}