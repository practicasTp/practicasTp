package mv.instructions;

import mv.cpu.Cpu;

public class Flip extends SystemMv {

	public Flip () {
		super (TipoInstruction.DUP);
	}
	
	/**
	 * Metodo que intercambia el valor de la subcima de la pila por el de la cima de la pila
	 * @return true/false
	 */
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 2) {
			int cima = cpu.pop();
			int subcima = cpu.pop();
			cpu.push(cima);
			cpu.push(subcima);
			return true;
		} else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("FLIP"))
			return new Flip ();
		else return null;
	}
	
	public String toString () {
		return "FLIP";
	}
}
