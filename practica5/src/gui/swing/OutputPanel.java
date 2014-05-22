package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import observers.CPUObserver;
import observers.Observable;
import observers.StackObserver;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import mv.writing.OutputMethod;
import controllers.GUIControler;

public class OutputPanel extends JPanel implements CPUObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUIControler guiCtrl;
	private static JTextArea outputTextArea;
	private static OutputMethod outCurr;
	private OutputMethod outNew;
	
	public OutputPanel(GUIControler guiCtrl, Observable<CPUObserver> cpu){
		cpu.addObserver(this);
		this.guiCtrl = guiCtrl;
		initGui();
	}
	
	/**
	 * Inicializa el OutputPanel creando el outputTextArea para mostrar la 
	 * información de salida.
	 */
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Output"));
		outputTextArea = new JTextArea(5,5);
		outputTextArea.setAlignmentX(CENTER_ALIGNMENT);
		outputTextArea.setFont( new Font("Courier", Font.PLAIN, 16));
		outputTextArea.setEditable(false);		
		this.add(new JScrollPane(outputTextArea));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		outCurr = guiCtrl.getOutStream();
		this.outNew = new OutStreamGUI();
		guiCtrl.setOutStream(outNew);
	}
	
	class OutStreamGUI implements OutputMethod {
		// definir los atributos necesarios
		StringBuilder content;
		
		public OutStreamGUI() {
			init();
		}
		
		private void init(){
			// inicializar los atributos
			this.content = new StringBuilder();
			this.content.append(outputTextArea.getText());
		}
		
		/**
		 * No hace nada ya que se encarga otro método de abrir el archivo.
		 */
		public void open() {}
		
		/**
		 * Cierra el archivo de salida abierto.
		 */
		public void close() {
			outCurr.close();
		}
		
		/**
		 * Escribe un caracter tanto en el archivo de salida indicado, como en el
		 * outputTextArea de salida.
		 * @param char c
		 */
		public void writeChar(char c) {
		// 1. pasar c al OutStream original
			outCurr.writeChar(c);
		// 2. concatenar c al contenido del outputTextArea
			this.content.append(c);
			outputTextArea.setText(content.toString());
		}

		@Override
		public void reset() {
			outCurr.reset();
			outputTextArea.setText("");
			init();
		}
	}

	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memory,
			OperandStack<Integer> stack, ProgramMv program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMv program) {}

	@Override
	public void onNewIn() {
		// TODO Auto-generated method stub
		
	}
}
