package gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import mv.cpu.OperandStack;

class StackPanel extends JPanel {
	private GUIControler guiCtrl;
	private JScrollPane _scroll;
	private JList _lstPila;
	private DefaultListModel _modeloLista;
	private JLabel lblValor; 
	private JTextField txtValor;
	private JButton btnPush;
	private JButton btnPop;

	StackPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

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
}