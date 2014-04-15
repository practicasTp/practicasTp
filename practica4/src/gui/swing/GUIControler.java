package gui.swing;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import mv.cpu.Cpu;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
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
		Image img = new ImageIcon(MainWindow.class.getResource("error.png")).getImage();
		dialogo.setIconImage(img);		
		
		JLabel redLabel = new JLabel(msg); 
		redLabel.setLocation(0, 0); 
		redLabel.setSize(80, 40); 
		redLabel.setHorizontalAlignment(JLabel.CENTER);
		redLabel.setFont(new Font("Courier", Font.BOLD, 16));

		
		//Definimos el tipo de estructura general de nuestra ventana
		dialogo.setLayout(new FlowLayout(FlowLayout.LEADING, 40, 50));
		//Dividimos la ventana en secciones
		dialogo.add(redLabel); 
		
		JLabel label = new JLabel();  
        label.setIcon(new ImageIcon(MainWindow.class.getResource("error.png")));
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setLocation(0, 0); 
        dialogo.add(label);
		
		dialogo.setTitle(title);
		dialogo.setModal(true);
        dialogo.setSize(700, 200);
        dialogo.setLocationRelativeTo(gui);
        dialogo.setVisible(true);
        
	}

	void step() { 
		try {
			this.cpu.step();
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
			reportError("No hay suficientes operandos en la pila para ejecutar el pop.","Error en la máquina virtual");
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
		 if(PromptResult==JOptionPane.YES_OPTION) System.exit(0);
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
		//... }
	}

	void setOutStream(OutputMethod out) { 
		//... }
	}

	InputMethod getInStream() {
		return null; 
		//... 
		
	}

	OutputMethod getOutStream() { 
		//... }
		return null;
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
