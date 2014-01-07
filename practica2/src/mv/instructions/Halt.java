package mv.instructions;

import mv.cpu.Cpu;

public class Halt extends SystemMv {

	public Halt () {
		super (TipoInstruction.HALT);
	}
	
	/**
	 * Finaliza la ejecución de la cpu.
	 */
	public boolean executeAux (Cpu cpu) {
		cpu.exit();
		
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción add
	 * @return new add o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("HALT"))
			return new Halt ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción add
	 * @return "ADD"
	 */
	public String toString () {
		return "HALT";
	}
}
