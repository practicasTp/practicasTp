package mv.instructions;

import mv.cpu.Cpu;

public class In extends SystemMv {

	public In () {
		super (TipoInstruction.IN);
	}
	
	/**
	 * Metodo que devuelve el valor de la cima como caracter
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		int caracter = cpu.readChar();
		cpu.push(caracter);
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción out
	 * @param s
	 * @return new Out o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("IN"))
			return new In ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción out
	 * @return "OUT"
	 */
	public String toString () {
		return "IN";
	}
}
