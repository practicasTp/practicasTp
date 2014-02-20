package mv.instructions;

import mv.cpu.Cpu;

public class StoreInd extends Instruction{

	public StoreInd () {
		super (TipoInstruction.STOREIND);
	}
	
	/**
	 * Modifica el contador de programa de la cpu cambiando el valor por el indicado en la cima de la pila
	 * @param cpu
	 * @return boolean
	 */
	public boolean execute (Cpu cpu) {
		
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción jump
	 * @param s
	 * @return new InconditionalJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("STOREIND")) {
			return new StoreInd ();
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción jump
	 * @return "JUMP"
	 */
	public String toString () {
		return "STOREIND ";
	}
}
