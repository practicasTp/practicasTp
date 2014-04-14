package gui.swing;

import javax.swing.JTextArea;

import mv.writing.OutputMethod;

public class OutputPanel {
	private GUIControler guiCtrl;
	private JTextArea outputTextArea;
	
	OutputPanel(GUIControler guiCtrl){
		this.guiCtrl = guiCtrl;
		initGui();
	}
	
	private void initGui(){
	//...
	OutputMethod outCurr = guiCtrl.getOutStream();
	OutputMethod outNew = new OutStreamGUI(outCurr, outputTextArea);
	guiCtrl.setOutStream(outNew);
	}
	
	class OutStreamGUI implements OutputMethod {
		// definir los atributos necesarios
		//...
		 public OutStreamGUI(OutputMethod old, JTextArea outputTextArea) {
		// inicializar los atributos
		 }
		 public void open() {
		// no hacer nada, suponemos que old ya está abierto
		}
		 public void close() {
	//	old.close();
		}
		public void writeChar(int c) {
		 // 1. pasar c al OutStream original
		// 2. concatenar c al contenido del outputTextArea
		}
		@Override
		public void writeChar(char c) {
			// TODO Auto-generated method stub
			
		}
	}
}
