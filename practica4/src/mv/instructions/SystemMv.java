package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;

abstract public class SystemMv implements Instruction {
	protected int operando;

	
	/**
	 * Método que ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 * @throws NegativeNumberIntoMemoryException 
	 * @throws EmptyStackException 
	 * @throws InsufficientOperandsException 
	 * @throws IncorrectMemoryPositionException 
	 * @throws Exception 
	 */
	abstract protected void executeAux(Cpu cpu) throws NegativeNumberIntoMemoryException, EmptyStackException, InsufficientOperandsException, IncorrectMemoryPositionException ;
	
	/**
	 * Se encarga de ejecutar las instrucciones propias del sistema, incrementando el 
	 * contador de programa en caso de que se ejecuten correctamente.
	 * @param cpu
	 * @return boolean
	 * @throws IncorrectMemoryPositionException 
	 * @throws InsufficientOperandsException 
	 * @throws EmptyStackException 
	 * @throws NegativeNumberIntoMemoryException 
	 */
	public void execute(Cpu cpu) throws NegativeNumberIntoMemoryException, EmptyStackException, InsufficientOperandsException, IncorrectMemoryPositionException{
		this.executeAux(cpu);
		cpu.increaseProgramCounter();
	}
	
	/**
	 * Parsea un string para identificar la instrucción.
	 * @param s
	 * @return instruction.
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Pasa a string la instrucción.
	 * @return string
	 */
	abstract public String toString ();
}
