package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;

public class Pop extends SystemMv {

	
	/**
	 * Extrae la cima de la pila
	 * @param cpu
	 * @return boolean
	 * @throws EmptyStackException 
	 */
	public void executeAux (Cpu cpu) throws EmptyStackException {
		cpu.pop();
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
