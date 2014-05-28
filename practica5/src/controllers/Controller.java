package controllers;

import observers.CPUObserver;
import mv.cpu.Cpu;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;
import views.MainWindow;

public abstract class Controller {

	protected Cpu cpu;
	private MainWindow gui;
	protected String errorTitle = "Error en la máquina virtual";

	public Controller(Cpu cpu) {
		this.cpu = cpu;
	}

	/**
	 * Intenta realizar el step de la cpu y en caso de error se encarga de
	 * capturar y mostrar todas las excepciones.
	 */
	public void step() {
		try {
			this.cpu.step();
		} catch (InsufficientOperandsException e) {} 
		catch (EmptyStackException e) {} 
		catch (DivisionByZeroException e) {} 
		catch (IncorrectProgramCounterException e) {} 
		catch (NegativeNumberIntoMemoryException e) {} 
		catch (IncorrectMemoryPositionException e) {}
	}

	/**
	 * Ejecuta el run de la cpu y en caso de error se encarga de capturar y
	 * mostrar todas las excepciones.
	 */
	public void run() {
		try {
			this.cpu.run();
		} catch (InsufficientOperandsException e) {} 
		catch (EmptyStackException e) {} 
		catch (DivisionByZeroException e) {} 
		catch (IncorrectProgramCounterException e) {} 
		catch (NegativeNumberIntoMemoryException e) {} 
		catch (IncorrectMemoryPositionException e) {}
	}

	/**
	 * Comprueba que la cpu no haya finalizado.
	 * 
	 * @return boolean
	 */
	public boolean finished() {
		return this.cpu.finished();
	}

	/**
	 * Ejecuta el pop de la cpu y captura las excepciones que se puedan
	 * producir, mostrando el error. Despu�s actualiza la interfaz.
	 */
	public void pop() {
		try {
			this.cpu.pop();
		} catch (EmptyStackException e) {}
		// this.gui.updateView();
	}

	/**
	 * Valida el operando que se le pasa.
	 * 
	 * @param operando
	 * @return boolean
	 */
	public boolean validarOperando(String operando) {
		return operando.matches("[-+]?\\d*\\.?\\d+");
	}

	public boolean validarPosicion(String posicion) {
		return posicion.matches("[+]?\\d*\\.?\\d+");
	}

	/**
	 * Ejecuta el método push de la cpu comprobando que el operando cumpla las
	 * condiciones, en caso negativo lanza un mensaje de error. Después
	 * actualiza la interfaz.
	 * 
	 * @param s
	 */
	public void push(String s) {
		this.cpu.push(Integer.parseInt(s));	
	}

	/**
	 * Ejecuta el store de la cpu realizando las correspondientes validaciones
	 * de los operandos a utilizar. Después actualiza la interfaz.
	 * 
	 * @param pos
	 * @param dato
	 */
	public void memorySet(String pos, String dato) {
		this.cpu.store(Integer.parseInt(pos), Integer.parseInt(dato));	
	}

	public void pause() {
	} // ejecuta el pause del cpu

	public abstract void start(); // un m�todo abstracto, depende del modo !

}