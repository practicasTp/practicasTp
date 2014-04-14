package gui.swing;

import java.awt.BorderLayout;

import javax.swing.*;

import mv.cpu.Cpu;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Cpu cpu;
	private ToolBarPanel toolBar;
	private ProgramPanel program;
	private StackPanel stack;

	public MainWindow(Cpu cpu) {
		super("Virtual Machine");
		this.cpu = cpu;
		initGUI();
		updateView();
	}
	private void initGUI() {
		GUIControler guiCtrl = new GUIControler(cpu, this);
		this.toolBar = new ToolBarPanel(guiCtrl);
		this.program = new ProgramPanel(guiCtrl);
		//this.stack = new StackPanel(guiCtrl);
		
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		mainPanel.add(toolBar, BorderLayout.PAGE_START );
		mainPanel.add(program, BorderLayout.LINE_START);
		
		this.setSize(1000,1000);
		this.setVisible(true);
	}
	void updateView() {
		program.updateView();
		//stack.updateView();
	}
}