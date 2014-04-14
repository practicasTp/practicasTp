package gui.swing;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mv.cpu.OperandStack;

class StackPanel extends JPanel {
	private GUIControler guiCtrl;
	//private JList<Integer> stackArea;
	private JTextField stackElem;

	StackPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI() {
		// ...
		//Object model = new DefaultListModel<Integer>();
		//this.stackArea = new JList<Integer>(model);
		// ...
	}

	void updateView() {
		OperandStack<Integer> operandsStack = guiCtrl.getOperandStack();
		//model.clear();
		// añadir los elementos de la pila al modelo
		// ...
	}
}