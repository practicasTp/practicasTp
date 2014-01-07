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
	
	/**
	 * método que parsea un string para identificarse como una instrucción out
	 * @return new Out o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("OUT"))
			return new Out ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción out
	 * @return "OUT"
	 */
	public String toString () {
		return "OUT";
	}
}
