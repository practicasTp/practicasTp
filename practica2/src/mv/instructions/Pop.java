package mv.instructions;

import mv.cpu.Cpu;

public class Pop extends SystemMv {

	public Pop () {
		super (TipoInstruction.POP);
	}
	
	/**
	 * Extrae la cima de la pila
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 1) {
			cpu.pop();
			return true;
		} else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción pop
	 * @param s
	 * @return new Pop o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("POP"))
			return new Pop ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción pop
	 * @return "POP"
	 */
	public String toString () {
		return "POP";
	}
}
