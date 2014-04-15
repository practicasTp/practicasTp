package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mv.reading.InputMethod;

class InputPanel extends JPanel{
	private GUIControler guiCtrl;
	private static JTextArea inputTextArea;
	protected static InputMethod inCurr;
	
	InputPanel(GUIControler guiCtrl){
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Input"));
		inputTextArea = new JTextArea(5, 5);
		inputTextArea.setAlignmentX(CENTER_ALIGNMENT);
		inputTextArea.setFont( new Font("Courier", Font.PLAIN, 16));
		inputTextArea.setEditable(false);		
		this.add(new JScrollPane(inputTextArea));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		this.inCurr = guiCtrl.getInStream();
		InputMethod inNew = new InStreamGUI();
		guiCtrl.setInStream(inNew);
	}
	
	class InStreamGUI implements InputMethod {
		 JTextArea inputTextArea;
		 InputMethod old;
		 StringBuilder content;
		 int pos;
		 public InStreamGUI() {
		  this.old = inCurr;
		  this.inputTextArea = inputTextArea;
		  pos = 0;
		// 1. leer toda la entrada del old, y construir el StringBuilder content
		// 2. mosrar el contenido de content en el inputTextArea
		 }
		 public void open() {} // suponemos que old ya está abierto
		 public void close() { //old.close(); 
			  // cerrar old también
		 }
		 public int readChar() {
			 return 1;
		// 1. si pos == content.length() entonce ya no hay más caracteres
		// 2. consultar el carácter c el la posición pos del content
		// 3. si c no es salto de linea (c=10 y c=13) lo cambiamos con * en content
		// 4. actualizar el inputTextArea;
		 // 5. actualizar pos
		 // 6. devolver c;
		 }
	}
}