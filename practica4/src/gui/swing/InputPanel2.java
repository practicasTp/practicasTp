package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mv.reading.InputMethod;
/* FUck */
public class InputPanel2 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUIControler guiCtrl; 
	private static JTextArea inputTextArea;
	private static InputMethod inCurr;
	
	InputPanel2 (GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Input"));
		inputTextArea = new JTextArea(5, 5);
		inputTextArea.setAlignmentX(CENTER_ALIGNMENT);
		inputTextArea.setFont( new Font("Courier", Font.PLAIN, 16));
		inputTextArea.setEditable(false);		
		this.add(new JScrollPane(inputTextArea));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		inCurr = guiCtrl.getInStream();
		InputMethod inNew = new InStreamGUI();
		guiCtrl.setInStream( inNew );
	}
	
	static class InStreamGUI implements InputMethod {
		StringBuilder content;
		int pos;
		
		public InStreamGUI() {
			pos = 0;
			// 1. leer toda la entrada del old, y construir el StringBuilder content
			int character = inCurr.readChar();
			while (character != -1) {
				content.append((char)character);
				character = inCurr.readChar();
			}
			// 2. mostrar el contenido de content en el inputTextArea
			inputTextArea.setText(content.toString());
		}
		
		public void open() {} // suponemos que old ya está abierto
		
		public void close() { inCurr.close(); } // cerrar old también
		
		public int readChar() {
			int c = -1;
			// 1. si pos == content.length() entonce ya no hay más caracteres
			if (this.pos != this.content.length()) {
				// 2. consultar el carácter c el la posición pos del content
				c = this.content.codePointAt(this.pos);
				// 3. si c no es salto de linea (c!=10 y c!=13) lo cambiamos con * en content!
				if (c != 10 && c != 13) 
					this.content.replace(this.pos, this.pos + 1, "*");
			}
			// 4. actualizar el inputTextArea;
			inputTextArea.setText(content.toString());
			// 5. actualizar pos
			this.pos++;
			// 6. devolver c;
			return c;
		}
	}
}
