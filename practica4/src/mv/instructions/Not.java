package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;

public class Not extends Boolean {
	
	public Not(){
		super(TipoInstruction.NOT);
	}
	
	/**
	 * Ejecuta una operación del tipo not o !
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		try {
			int n1 = cpu.pop();
			if (n1 == 1){
				return false;
			}else{
				return true;
			}
		} catch (EmptyStackException e) {
			System.err.println(e.getMessage());
			return false;
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
