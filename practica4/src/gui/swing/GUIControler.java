package gui.swing;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import mv.cpu.Cpu;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.MvError;
import mv.exceptions.NegativeNumberIntoMemoryException;
import mv.program.ProgramMv;
import mv.reading.InputMethod;
import mv.writing.OutputMethod;

public class GUIControler {
	private Cpu cpu;
	private MainWindow gui;
	JDialog dialogo = null;
	
	GUIControler(Cpu cpu, MainWindow gui) {
		this.cpu = cpu;
		this.gui = gui;
	}

	private void reportError(String msg, String title) { 
		JDialog dialogo = new JDialog(gui);
		dialogo.setTitle(title);
		dialogo.setModal(true);
        dialogo.setSize(700, 250);
        
        
		Image img = new ImageIcon(MainWindow.class.getResource("error.png")).getImage();
		dialogo.setIconImage(img);
		
		JPanel warning = new JPanel();
		warning.setSize(680, 230);
		warning.setLocation(10, 10);
		warning.setLayout(null);
		
		JLabel iconLabel = new JLabel();  
        iconLabel.setIcon(new ImageIcon(MainWindow.class.getResource("error.png")));
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        iconLabel.setLocation(300, 20); 
        iconLabel.setSize(70, 70);
        warning.add(iconLabel);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(msg);
        textArea.setSize(640, 80);
        textArea.setLocation(20, 120);
        textArea.setFont(new Font("Courier", Font.BOLD, 16));
        textArea.setBackground(null);
        warning.add(textArea);
        
		dialogo.add(warning);

		
		dialogo.setLocationRelativeTo(gui);
        dialogo.setVisible(true);
        //Definimos el tipo de estructura general de nuestra ventana
      	dialogo.setLayout(new FlowLayout(FlowLayout.LEADING, 40, 50));
        
	}

	void step() { 
		try {
			this.cpu.step();
		} catch (InsufficientOperandsException e) {
			reportError(e.getMessage(),"Error en la máquina virtual");
		} catch (EmptyStackException e) {
			reportError(e.getMessage(),"Error en la máquina virtual");
		} catch (DivisionByZeroException e) {
			reportError(e.getMessage(),"Error en la máquina virtual");
		} catch (IncorrectProgramCounterException e) {
			reportError("No ha sido posible saltar a la instrucción deseada.","Error en la máquina virtual");
		} catch (NegativeNumberIntoMemoryException e) {
			reportError("No se puede introducir valores en posiciones negativas de memoria.","Error en la máquina virtual");
		} catch (IncorrectMemoryPositionException e) {
			reportError("No se puede acceder a esa posición de memoria.","Error en la máquina virtual");
		}
		
		this.gui.updateView();
	}

	void run() { 
		try {
			this.cpu.run();
		} catch (InsufficientOperandsException e) {
			reportError("No hay suficientes operandos en la pila para ejecutar la instrucción.","Error en la máquina virtual");
		} catch (EmptyStackException e) {
			reportError("No hay suficientes operandos en la pila para ejecutar la instrucción.","Error en la máquina virtual");
		} catch (DivisionByZeroException e) {
			reportError("No se puede realizar una división con denominador 0.","Error en la máquina virtual");
		} catch (IncorrectProgramCounterException e) {
			reportError("No ha sido posible saltar a la instrucción deseada.","Error en la máquina virtual");
		} catch (NegativeNumberIntoMemoryException e) {
			reportError("No se puede introducir valores en posiciones negativas de memoria.","Error en la máquina virtual");
		} catch (IncorrectMemoryPositionException e) {
			reportError("No se puede acceder a esa posición de memoria.","Error en la máquina virtual");
		}
		
		this.gui.updateView();
	}

	boolean finished(){
		return this.cpu.finished();
	}
	
	void pop() { 
		try {
			this.cpu.pop();
		} catch (EmptyStackException e) {
			reportError(e.getMessage(),"Error en la máquina virtual");
		}
		this.gui.updateView();
	}

	private boolean validarOperando(String operando){
		return operando.matches("[-+]?\\d*\\.?\\d+");
	}
	
	void push(String s) {
		if(this.validarOperando(s)){
			this.cpu.push(Integer.parseInt(s));
		}else{
			reportError("Solo están admitidos los números a la hora de insertar elementos", "Error en la máquina virtual");
		}
		this.gui.updateView();
	}

	void quit() { 
		 String ObjButtons[] = {"Aceptar","Cancelar"};
		 int PromptResult = JOptionPane.showOptionDialog(null,"¿Estás seguro de que deseas salir?","Cerrar máquina virtual",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		 if(PromptResult==JOptionPane.YES_OPTION) {
			 
			 System.exit(0);
		 }
	}

	void memorySet(String pos, String dato) { 
		if(this.validarOperando(pos)){
			if(this.validarOperando(dato)){
				this.cpu.store(Integer.parseInt(pos), Integer.parseInt(dato));
			}else{
				reportError("Solo están admitidos los números en memoria", "Error en la máquina virtual");
			}
		}else{
			reportError("Solo están admitidos los números en la posición de memoria", "Error en la máquina virtual");
		}

		this.gui.updateView();
	}

	int getPC() {
		return cpu.getPC();
	}

	void setInStream(InputMethod in) { 
		/*try {
			this.cpu.setInStream(in);
		} catch (MvError e) {
			reportError(e.getMessage(), "Error en la máquina virtual");
		}*/
	}

	void setOutStream(OutputMethod out) { 
		/*try {
			this.cpu.setOutStream(out);
		} catch (MvError e) {
			reportError(e.getMessage(), "Error en la máquina virtual");
		}*/
	}

	InputMethod getInStream() {
		return this.cpu.getInStream();
	}

	OutputMethod getOutStream() { 
		return this.cpu.getOutStream();
	}

	ProgramMv getProgram() { 
		return this.cpu.getProgram();
	}

	OperandStack<Integer> getOperandStack() { 
		return this.cpu.getOperandStack();
	}

	Memory<Integer> getMemory() { 
		return this.cpu.getMemory();
	}
}
