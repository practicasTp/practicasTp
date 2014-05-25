package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import observers.CPUObserver;
import observers.Observable;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import mv.reading.InputMethod;
import controllers.GUIControler;

public class InputPanel extends JPanel implements CPUObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUIControler guiCtrl; 
	private static JTextArea inputTextArea;
	private static InputMethod inCurr;
	private InputMethod inNew;
	
	public InputPanel (GUIControler guiCtrl, Observable<CPUObserver> cpu){
		cpu.addObserver(this);
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	/**
	 * Inicializa el InputPanel incluyendo el inputTextArea.
	 */
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
		this.inNew = new InStreamGUI();
		guiCtrl.setInStream( inNew );
	}
	
	static class InStreamGUI implements InputMethod {
		StringBuilder content;
		int pos;
		
		public InStreamGUI() {
			init();
		}
		
		/**
		 * Se encarga de leer el input, cargarlo en el StringBuilder y mostrarlo en el
		 * textArea.
		 */
		private void init(){ 
			this.content = new StringBuilder();
			this.pos = 0;
			// 1. leer toda la entrada del old, y construir el StringBuilder content
			int character = inCurr.readChar();
			while (character != -1) {
				this.content.append((char)character);
				character = inCurr.readChar();
			}
			// 2. mostrar el contenido de content en el inputTextArea
			inputTextArea.setText(content.toString());
		}
		
		public void open() {} // suponemos que old ya está abierto
		
		/**
		 * Se encarga de cerrar el archivo de entrada abierto.
		 */
		public void close() { inCurr.close(); } // cerrar old también
		
		/**
		 * Lee el siguiente caracter guardado y lo devueve. Además actualiza el 
		 * inputTextArea para que muestre un * en lugar del caracter obtenido.
		 * @return int
		 */
		public int readChar() {
			int c = -1;
			// 1. si pos == content.length() entonce ya no hay más caracteres
			if (this.pos != this.content.length()) {
				// 2. consultar el carácter c el la posición pos del content
				c = this.content.codePointAt(this.pos);
				// 3. si c no es salto de linea (c!=10 y c!=13) lo cambiamos con * en content!
				if (c != 10 && c != 13) 
					this.content.replace(this.pos, this.pos+1, "*");
			}
			// 4. actualizar el inputTextArea;
			inputTextArea.setText(content.toString());
			// 5. actualizar pos
			this.pos++;
			// 6. devolver c;
			return c;
		}

		/**
		 * Resetea el input y lo reinicia.
		 */
		public void reset() {
			inCurr.reset();
			init();
		}
	}
	
	public void onStartInstrExecution(Instruction instr) {}

	public void onEndInstrExecution(int pc, Memory<Integer> memory,OperandStack<Integer> stack, ProgramMv program) {}

	public void onStartRun() {}

	public void onEndRun() {}

	public void onError(String msg) {}

	public void onHalt() {}

	public void onReset(ProgramMv program) {}

	/**
	 * Se carga un nuevo input en inCurr, se genera un nuevo objeto de la clase
	 * InStreamGui para reiniciar el text area y se establece en la cpu.
	 */
	public void onNewIn() {
		inCurr = guiCtrl.getInStream();
		this.inNew = new InStreamGUI();
		guiCtrl.setInStream( inNew );
	}
}
