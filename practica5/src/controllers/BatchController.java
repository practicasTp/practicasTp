package controllers;

import mv.cpu.Cpu;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;

public class BatchController extends Controller {

	public BatchController(Cpu cpu) {
		super(cpu);
	}

	public void start() {
		try {
			cpu.run();
		} 
		catch (EmptyStackException e) {} 
		catch (NegativeNumberIntoMemoryException e) {}
		catch (InsufficientOperandsException e) {} 
		catch (DivisionByZeroException e) {} 
		catch (IncorrectProgramCounterException e) {} 
		catch (IncorrectMemoryPositionException e) {}
	}

}
