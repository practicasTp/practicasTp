package controllers;

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
	
	public Controller(Cpu cpu){
		this.cpu = cpu;
	}
	
	public void step(){
		try {
			cpu.step();
		} catch (InsufficientOperandsException e) {
		} catch (EmptyStackException e) {
		} catch (DivisionByZeroException e) {
		} catch (IncorrectProgramCounterException e) {
		} catch (NegativeNumberIntoMemoryException e) {
		} catch (IncorrectMemoryPositionException e) {
		}
	}
	
	// ejecuta el run del cpu !
	public void run() {
		try {
			cpu.run();
		} catch (InsufficientOperandsException e) {
		} catch (EmptyStackException e) {
		} catch (DivisionByZeroException e) {
		} catch (IncorrectProgramCounterException e) {
		} catch (NegativeNumberIntoMemoryException e) {
		} catch (IncorrectMemoryPositionException e) {
		}
	}  
	
	public void pop() {
		try {
			cpu.pop();
		} catch (EmptyStackException e) {}
	} // ejecuta el pop del cpu ! 
	
	public void push(int v) {
		cpu.push(v);
	} // ejecuta el push del cpu !
	
	public void memorySet(int i, int v) {
		cpu.store(i, v);
	} // ejecuta el setMem del cpu ! 
	
	public ProgramMv getProgram() {
		return cpu.getProgram();
	} // devuelve el programa actual ! 
	
	public void setInStream(InputMethod in) {
		try {
			cpu.setInStream(in);
		} catch (MvError e) {}
	} // cambia el inStream ! 
	
	public InputMethod getInStream() {
		return cpu.getInStream();
	} // devuelve el inStream actual ! 
	
	public void setOutStream(OutputMethod out) {
		try {
			cpu.setOutStream(out);
		} catch (MvError e) {}
	} // cambia el outStream ! 
	
	public OutputMethod getOutStream() {
		return cpu.getOutStream();	
	} //devuelve el outStream actual ! 
	
	public void pause() {
		
	} // ejecuta el pause del cpu 
	
	public abstract void start(); // un método abstracto, depende del modo ! 
	
	public void quit() {
		this.getOutStream().close(); 
		this.getInStream().close(); 
		System.exit(0);
	} 
	
}
