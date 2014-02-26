package mv.instructions;

import mv.cpu.Cpu;

public class Not extends Boolean {
	
	
	/**
	 * Ejecuta una operación del tipo not o !
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		int n1 = cpu.pop();
		if (n1 == 1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción not
	 * @param s
	 * @return new Not o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("NOT"))
			return new Not ();
		else return null;
	}
	
	/**
	 * método que pasa a string la instrucción not
	 * @return "NOT"
	 */
	public String toString () {
		return "NOT";
	}
}
