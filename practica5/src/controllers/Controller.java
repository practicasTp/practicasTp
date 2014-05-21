package controllers;

import gui.swing.MainWindow;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mv.cpu.Cpu;
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

public abstract class Controller {

	protected Cpu cpu;
	private MainWindow gui;
	protected String errorTitle = "Error en la máquina virtual";
	
	public Controller(Cpu cpu){
		this.cpu = cpu;
	}
	
	/**
	 * Método que se encarga de presentar una pantalla indicando el error producido.
	 * @param msg
	 * @param title
	 */
	protected void reportError(String msg, String title) { 
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
        
        JLabel textArea = new JLabel();
        textArea.setText(msg);
        textArea.setSize(640, 80);
        textArea.setLocation(25, 80);
        textArea.setHorizontalAlignment(JLabel.CENTER);
        textArea.setFont(new Font("Courier", Font.BOLD, 16));
        textArea.setBackground(null);
        warning.add(textArea);
        
		dialogo.add(warning);

		
		dialogo.setLocationRelativeTo(gui);
        dialogo.setVisible(true);
        //Definimos el tipo de estructura general de nuestra ventana
      	dialogo.setLayout(new FlowLayout(FlowLayout.LEADING, 40, 50));
        
	}
	
	/**
	 * Intenta realizar el step de la cpu y en caso de error 
	 * se encarga de capturar y mostrar todas las excepciones.
	 */
	public void step() { 
		try {
			this.cpu.step();
		} catch (InsufficientOperandsException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (EmptyStackException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (DivisionByZeroException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (IncorrectProgramCounterException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (NegativeNumberIntoMemoryException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (IncorrectMemoryPositionException e) {
			reportError(e.getMessage(),this.errorTitle);
		}
	}
	
	/**
	 * Ejecuta el run de la cpu y en caso de error se encarga de capturar y mostrar
	 * todas las excepciones.
	 */
	public void run() { 
		try {
			this.cpu.run();
		} catch (InsufficientOperandsException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (EmptyStackException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (DivisionByZeroException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (IncorrectProgramCounterException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (NegativeNumberIntoMemoryException e) {
			reportError(e.getMessage(),this.errorTitle);
		} catch (IncorrectMemoryPositionException e) {
			reportError(e.getMessage(),this.errorTitle);
		}
	}
	
	/**
	 * Comprueba que la cpu no haya finalizado.
	 * @return boolean
	 */
	public boolean finished(){
		return this.cpu.finished();
	}
	
	/**
	 * Ejecuta el pop de la cpu y captura las excepciones que se puedan producir,
	 * mostrando el error. Después actualiza la interfaz.
	 */
	public void pop() { 
		try {
			this.cpu.pop();
		} catch (EmptyStackException e) {
			reportError(e.getMessage(),this.errorTitle);
		}
		//this.gui.updateView();
	}
	
	/**
	 * Valida el operando que se le pasa.
	 * @param operando
	 * @return boolean
	 */
	private boolean validarOperando(String operando){
		return operando.matches("[-+]?\\d*\\.?\\d+");
	}
	
	/**
	 * Ejecuta el método push de la cpu comprobando que el operando cumpla las
	 * condiciones, en caso negativo lanza un mensaje de error. Después actualiza
	 * la interfaz.
	 * @param s
	 */
	public void push(String s) {
		if(this.validarOperando(s)){
			this.cpu.push(Integer.parseInt(s));
		}else{
			reportError("Solo están admitidos los números a la hora de insertar elementos", this.errorTitle);
		}
	}
	
	/**
	 * Ejecuta el store de la cpu realizando las correspondientes validaciones de los
	 * operandos a utilizar. Después actualiza la interfaz.
	 * @param pos
	 * @param dato
	 */
	public void memorySet(String pos, String dato) { 
		if(this.validarOperando(pos)){
			if(this.validarOperando(dato)){
				this.cpu.store(Integer.parseInt(pos), Integer.parseInt(dato));
			}else{
				reportError("Solo están admitidos los números en memoria", this.errorTitle);
			}
		}else{
			reportError("Solo están admitidos los números en la posición de memoria", this.errorTitle);
		}
	}
	
	public void pause() {
		
	} // ejecuta el pause del cpu 
	
	public abstract void start(); // un método abstracto, depende del modo ! 
	

	
}
