package mv.instructions;

import mv.cpu.Cpu;

public class Neg extends SystemMv {
	
	public Neg () {
		super (TipoInstruction.NEG);
	}
	
	/**
	 * Hace el opuesto de la cima de la pila y lo cambia
	 * @param cpu
	 */
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 1) {
			int cima = cpu.pop();
			if (cima == 0) cpu.push(0);
			else {
				cima = cima * -1;
				cpu.push(cima);
			}
			
			return true;
		} else return false;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción neg
	 * @return new Neg o null
	 */
	public Instruction parse (String[] s) {
		if (s.length >= 1 && s[0].equalsIgnoreCase("NEG"))
			return new Neg ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción neg
	 * @return "NEG"
	 */
	public String toString () {
		return "NEG";
	}
}
