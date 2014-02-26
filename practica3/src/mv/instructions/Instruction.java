package mv.instructions;
import mv.cpu.Cpu;
import mv.exceptions.InsufficientOperandsException;

public interface Instruction {

	
	/**
	 * Ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 */
	abstract public boolean execute (Cpu cpu) throws InsufficientOperandsException;
	
	/**
	 * Recibe un string para identificar la instrucción a ejecutar
	 * @param s
	 * @return Instruction
	 */
	abstract public Instruction parse (String[] s);
}
