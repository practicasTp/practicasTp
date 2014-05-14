package controllers;

import mv.cpu.Cpu;

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
