package mv.instructions;

import mv.cpu.Cpu;

public class Out extends SystemMv {

	public Out () {
		super (TipoInstruction.OUT);
	}
	
	/**
	 * Metodo que devuelve el valor de la cima como caracter
	 * @return caracter resultado
	 */
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 1) {
			int cima = cpu.pop();
			System.out.println((char)cima);
			return true;
		} return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("OUT"))
			return new Out ();
		else return null;
	}
	
	public String toString () {
		return "OUT";
	}
}
