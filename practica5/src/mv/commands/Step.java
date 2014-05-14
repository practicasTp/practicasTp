package mv.commands;

import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;
import mv.instructions.Instruction;

public class Step extends CommandInterpreter {
	
	public Step () {
		super();
	}
	
	/**
	 * Método que ejecuta una sola instrucción
	 * @return resultado
	 * @throws IncorrectMemoryPositionException 
	 * @throws NegativeNumberIntoMemoryException 
	 * @throws IncorrectProgramCounterException 
	 * @throws DivisionByZeroException 
	 * @throws EmptyStackException 
	 * @throws InsufficientOperandsException 
	 */
	public boolean executeCommand() throws InsufficientOperandsException, EmptyStackException, DivisionByZeroException, IncorrectProgramCounterException, NegativeNumberIntoMemoryException, IncorrectMemoryPositionException {
		boolean resultado = false;
		
		//si la ejecución ha ido correctamente, muestro el estado de la máquina
		CommandInterpreter.cpu.step();
		
		//si la cpu me dice que no hay más instrucciones entonces paro la máquina
		if(CommandInterpreter.cpu.finished() || CommandInterpreter.cpu.abortComputation()){
			this.isFinished = true;
		//si no, compruebo si esa ha sido la ultima instrucción para parar la máquina
		}else{
			Instruction nextInstruction = CommandInterpreter.cpu.getCurrentInstruction();
			if(nextInstruction == null){
				this.isFinished = true;
			}
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return step
	 */
	public String toString () {
		return "STEP";
	}
}
