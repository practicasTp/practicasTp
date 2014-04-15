package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mv.writing.OutputMethod;

public class OutputPanel extends JPanel{
	private GUIControler guiCtrl;
	private static JTextArea outputTextArea;
	private static OutputMethod outCurr;
	
	OutputPanel(GUIControler guiCtrl){
		this.guiCtrl = guiCtrl;
		initGui();
	}
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Output"));
		outputTextArea = new JTextArea(5,5);
		outputTextArea.setAlignmentX(CENTER_ALIGNMENT);
		outputTextArea.setFont( new Font("Courier", Font.PLAIN, 16));
		outputTextArea.setEditable(false);		
		this.add(new JScrollPane(outputTextArea));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		OutputMethod outCurr = guiCtrl.getOutStream();
		OutputMethod outNew = new OutStreamGUI();
		guiCtrl.setOutStream(outNew);
	}
	
	class OutStreamGUI implements OutputMethod {
		// definir los atributos necesarios
		//...
		 public OutStreamGUI() {
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
