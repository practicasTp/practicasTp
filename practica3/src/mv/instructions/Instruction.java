package mv.instructions;
import mv.cpu.Cpu;

public interface Instruction {

	
	/**
	 * Ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 */
	abstract public boolean execute (Cpu cpu);
	
	/**
	 * Recibe un string para identificar la instrucción a ejecutar
	 * @param s
	 * @return Instruction
	 */
	abstract public Instruction parse (String[] s);
}
