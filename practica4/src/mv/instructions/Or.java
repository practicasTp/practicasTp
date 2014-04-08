package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;

public class Or extends Boolean {
	
	public Or(){
		super(TipoInstruction.OR);
	}
	/**
	 * Ejecuta una operación del tipo or o ||
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		try {	
			int n1 = cpu.pop();
			int n2 = cpu.pop();
			if (n1 == 1 || n2 == 1) return true;
			else return false;
		} catch (EmptyStackException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción or
	 * @param s
	 * @return new Or o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("OR"))
			return new Or ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción or
	 * @return "OR"
	 */
	public String toString () {
		return "OR";
	}
}
