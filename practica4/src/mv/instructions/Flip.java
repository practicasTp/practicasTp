package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.InsufficientOperandsException;

public class Flip extends SystemMv {

	
	/**
	 * Metodo que intercambia el valor de la subcima de la pila por el de la cima de la pila
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 */
	public void executeAux (Cpu cpu) throws InsufficientOperandsException {
		try {	
			if (cpu.getSizeStack() >= 2) {
				int cima = cpu.pop();
				int subcima = cpu.pop();
				cpu.push(cima);
				cpu.push(subcima);
			} else
				throw new InsufficientOperandsException("Error, no hay suficientes operandos para realizar esta operación.\n");
		} catch(EmptyStackException e) {
			System.err.println(e.getMessage());
		}
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
