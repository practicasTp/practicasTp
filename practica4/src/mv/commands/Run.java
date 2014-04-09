package mv.commands;

import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;

public class Run extends Step {
	public Run(){
		super();
	}
	
	/**
	 * Metodo que se encarga de ejecutar todas las instrucciones
	 * @return resultado
	 * @throws IncorrectMemoryPositionException 
	 * @throws IncorrectProgramCounterException 
	 * @throws DivisionByZeroException 
	 * @throws NegativeNumberIntoMemoryException 
	 * @throws EmptyStackException 
	 * @throws InsufficientOperandsException 
	 */
	public boolean executeCommand() throws EmptyStackException, NegativeNumberIntoMemoryException, InsufficientOperandsException, DivisionByZeroException, IncorrectProgramCounterException, IncorrectMemoryPositionException{
		//cuando hago un run, reseteo la máquina
		CommandInterpreter.cpu.resetCpu();
		boolean resultado = false;
		
		CommandInterpreter.cpu.run();
			
		//si la cpu me dice que ha terminado, paro la máquina (del bucle se puede salir por fallo de instrucción)
		if(CommandInterpreter.cpu.finished()  || CommandInterpreter.cpu.abortComputation()){
			this.isFinished = true;
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return RUN
	 */
	public String toString () {
		return "RUN ";
	}
}
