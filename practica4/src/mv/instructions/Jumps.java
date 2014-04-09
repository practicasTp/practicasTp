package mv.instructions;
import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;

abstract public class Jumps implements Instruction {
	protected int operando;
	
	/**
	 * Método abstracto que ejecutará la instrucción.
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 * @throws EmptyStackException 
	 * @throws IncorrectProgramCounterException 
	 */
	abstract protected void executeAux (Cpu cpu) throws InsufficientOperandsException, EmptyStackException, IncorrectProgramCounterException;
	
	/**
	 * Comprueba que la pila tiene más de 0 componentes.
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 * @throws EmptyStackException 
	 * @throws IncorrectProgramCounterException 
	 */
	public void execute (Cpu cpu) throws InsufficientOperandsException, EmptyStackException, IncorrectProgramCounterException {
		executeAux(cpu);
	}
	
	/**
	 * Método que parseará un string para identificar la instrucción.
	 * @param s
	 * @return Instruction
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Pasa a string la instrucción.
	 * @return string
	 */
	abstract public String toString ();
}
