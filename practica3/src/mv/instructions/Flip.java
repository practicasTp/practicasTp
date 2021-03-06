package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;

public class Flip extends SystemMv {

	
	/**
	 * Metodo que intercambia el valor de la subcima de la pila por el de la cima de la pila
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		boolean resultado = false;
		
		try {	
			if (cpu.getSizeStack() >= 2) {
				int cima = cpu.pop();
				int subcima = cpu.pop();
				cpu.push(cima);
				cpu.push(subcima);
				resultado = true;
			}
		} catch(EmptyStackException e) {
			System.err.println(e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción flip
	 * @param s
	 * @return new flip o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("FLIP"))
			return new Flip ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción flip
	 * @return "FLIP"
	 */
	public String toString () {
		return "FLIP";
	}
}
