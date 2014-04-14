package gui.swing;

import java.awt.BorderLayout;
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

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Programa"));
		programTextArea = new JTextArea(20, 15);
		programTextArea.setAlignmentX(CENTER_ALIGNMENT);
		programTextArea.setFont( new Font("Courier", Font.PLAIN, 16));
		programTextArea.setEditable(false);		
		this.add(new JScrollPane(programTextArea));
		this.setAlignmentX(CENTER_ALIGNMENT);
	}

	void updateView() {
		ProgramMv program = guiCtrl.getProgram();
		String allProgram = "";
		for(int i=0;i<program.getSizeProgram();i++){
			if(i == guiCtrl.getPC() && guiCtrl.getPC()!=0){
				allProgram += "*"+program.lineToString(i);
			}else{
				allProgram += program.lineToString(i);
			}
		}
		
		programTextArea.setText(allProgram);
	}
}