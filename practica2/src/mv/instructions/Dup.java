package mv.instructions;

import mv.cpu.Cpu;

public class Dup extends SystemMv{

	public Dup () {
		super (TipoInstruction.DUP);
	}
	
	/**
	 * Metodo que duplica el valor de la cima
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 1) {
			int cima = cpu.pop();
			cpu.push(cima);
			cpu.push(cima);
			return true;
		} else return false;
	}
	
	
	/**
	 * método que parsea un string para identificarse como una instrucción dup
	 * @param s
	 * @return new dup o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("DUP"))
			return new Dup ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción add
	 * @return "ADD"
	 */
	public String toString () {
		return "DUP";
	}
}
