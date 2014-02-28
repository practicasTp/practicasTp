package mv.instructions;
import mv.cpu.Cpu;
import mv.exceptions.InsufficientOperandsException;

abstract public class Jumps implements Instruction {
	protected int operando;
	
	/**
	 * Método abstracto que ejecutará la instrucción.
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 */
	abstract protected boolean executeAux (Cpu cpu) throws InsufficientOperandsException;
	
	/**
	 * Comprueba que la pila tiene más de 0 componentes.
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 */
	public boolean execute (Cpu cpu) throws InsufficientOperandsException {
		if(executeAux(cpu)) 
			return true;
		else 
			return false;
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
