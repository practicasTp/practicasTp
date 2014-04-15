package gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class ToolBarPanel extends JPanel {
	private GUIControler guiCtrl;
	private JButton stepButton;
	private JButton runButton;
	
	ToolBarPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI() {
		stepButton = new JButton();
		stepButton.setIcon(createImageIcon("step.png"));
		stepButton.setToolTipText("Step");
		this.add(stepButton);
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.step();
			}
		});
		runButton = new JButton();
		runButton.setIcon(createImageIcon("run.png"));
		runButton.setToolTipText("Run");
		this.add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.run();
			}
		});
		JButton quitButton = new JButton();
		quitButton.setIcon(createImageIcon("exit.png"));
		quitButton.setToolTipText("Exit");
		this.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.quit();
			}
		});
	}

	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainWindow.class.getResource(path);
		if (imgURL != null)
			return new ImageIcon(imgURL);
		return null;
	}
	
	public void updateview(){
		if(this.guiCtrl.finished()){
			stepButton.setEnabled(false);
			runButton.setEnabled(false);
		}
	}
	
}
