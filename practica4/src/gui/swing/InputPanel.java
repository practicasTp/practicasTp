package gui.swing;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import mv.reading.InputMethod;

class InputPanel extends JPanel{
	private GUIControler guiCtrl;
	private JTextArea inputTextArea;
	
	InputPanel(GUIControler guiCtrl){
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	private void initGUI(){
		//...
		InputMethod inCurr = guiCtrl.getInStream();
		InputMethod inNew = new InStreamGUI(inCurr, inputTextArea);
		guiCtrl.setInStream(inNew);
	}
	
	class InStreamGUI implements InputMethod {
		 JTextArea inputTextArea;
		 InputMethod old;
		 StringBuilder content;
		 int pos;
		 public InStreamGUI(InputMethod old, JTextArea inputTextArea) {
		  this.old = old;
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