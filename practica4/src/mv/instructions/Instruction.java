package mv.instructions;
import mv.cpu.Cpu;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;

public interface Instruction {

	
	/**
	 * Ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 * @throws DivisionByZeroException 
	 * @throws EmptyStackException 
	 * @throws IncorrectProgramCounterException 
	 * @throws IncorrectMemoryPositionException 
	 * @throws NegativeNumberIntoMemoryException 
	 */
	abstract public void execute (Cpu cpu) throws InsufficientOperandsException, EmptyStackException, DivisionByZeroException, IncorrectProgramCounterException, NegativeNumberIntoMemoryException, IncorrectMemoryPositionException;
	
	/**
	 * Recibe un string para identificar la instrucción a ejecutar
	 * @param s
	 * @return Instruction
	 */
	abstract public Instruction parse (String[] s);
}
