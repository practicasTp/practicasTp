package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;

public class Neg extends SystemMv {
	
	
	/**
	 * Hace el opuesto de la cima de la pila y lo cambia
	 * @param cpu
	 * @return boolean
	 * @throws EmptyStackException 
	 */
	public void executeAux (Cpu cpu) throws EmptyStackException {
		int cima = cpu.pop();
		if (cima == 0) cpu.push(0);
		else {
			cima = cima * -1;
			cpu.push(cima);
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción neg
	 * @param s
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
