package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;

public class And extends Boolean{
	
	public And(){
		super(TipoInstruction.AND);
	}
	
	/**
	 * Método que ejecuta una operación de tipo and entre 2 números
	 * @return resultado
	 * @param cpu
	 */
	public boolean executeAux(Cpu cpu) {
		try {	
			int n1 = cpu.pop();
			int n2 = cpu.pop();
			if (n1 == 1 && n2 == 1) 
				return true;
			else 
				return false;
		} catch (EmptyStackException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción and
	 * @return new and o null
	 * @param s
	 */
	public Instruction parse(String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("AND"))
			return new And ();
		else return null;
	}
	
	/**
	 * Método que pasa a string la instrucción AND
	 * @return "And"
	 */
	public String toString () {
		return "AND";
	}
}
