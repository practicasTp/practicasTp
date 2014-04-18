package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mv.writing.OutputMethod;

public class OutputPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUIControler guiCtrl;
	private static JTextArea outputTextArea;
	private static OutputMethod outCurr;
	private OutputMethod outNew;
	
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
		
		outCurr = guiCtrl.getOutStream();
		this.outNew = new OutStreamGUI();
		guiCtrl.setOutStream(outNew);
	}
	
	public void updateView () {
		
	}
	
	class OutStreamGUI implements OutputMethod {
		// definir los atributos necesarios
		StringBuilder content;
		
		public OutStreamGUI() {
		// inicializar los atributos
			this.content.append(outputTextArea.getText());
		}
		public void open() {
		// no hacer nada, suponemos que old ya está abierto
		}
		public void close() {
			outCurr.close();
		}
		public void writeChar(char c) {
		// 1. pasar c al OutStream original
			outCurr.writeChar(c);
		// 2. concatenar c al contenido del outputTextArea
			this.content.append(c);
			outputTextArea.setText(content.toString());
		}
	}
}
