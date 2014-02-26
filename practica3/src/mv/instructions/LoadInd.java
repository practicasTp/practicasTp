package mv.instructions;

import mv.cpu.Cpu;

public class LoadInd extends SystemMv {

	
	/**
	 * Cargo de memoria una posición que está almacenada en la pila y apilo el valor extraido
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		try {
			int positionToLoad = cpu.pop();
			int MemoryValue = cpu.getMemoryValue(positionToLoad);
			cpu.push(MemoryValue);
			return true; 
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción jump
	 * @param s
	 * @return new InconditionalJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LOADIND")) {
			return new LoadInd ();
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción jump
	 * @return "JUMP"
	 */
	public String toString () {
		return "LOADIND ";
	}
}
