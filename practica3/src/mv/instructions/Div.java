package mv.instructions;

import mv.exceptions.DivisionByZeroException;

public class Div extends Arithmetics {
	
	/**
	 * Ejecuta una división.
	 * @param n1
	 * @param n2
	 * @return boolean
	 * @throws DivisionByZeroException 
	 */
	public boolean execute (int n1, int n2) throws DivisionByZeroException {
		boolean execute = false;
		if (n2 != 0) {
			this.result = n1 / n2;
			execute = true;
		} else {
			throw new DivisionByZeroException("Error: No se puede realizar una división con denominador 0.");
		}
		
		return execute;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción div
	 * @param s
	 * @return new div o null
	 */
	public Instruction parse (String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("DIV")) 
			return new Div();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción div
	 * @return "DIV"
	 */
	public String toString () {
		return "DIV";
	}
}
