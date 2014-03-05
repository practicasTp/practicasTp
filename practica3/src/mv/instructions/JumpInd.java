package mv.instructions;

import mv.cpu.Cpu;

public class JumpInd extends Jumps{
	
	/**
	 * Modifica el contador de programa de la cpu cambiando el valor por el indicado en la cima de la pila
	 * @param cpu
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		int newProgramPosition = cpu.pop();
		try{
			cpu.jumpProgramCounter(newProgramPosition);
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			cpu.push(newProgramPosition);
			return false;
		}
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción jump
	 * @param s
	 * @return new InconditionalJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("JUMPIND")) {
			return new JumpInd ();
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción jump
	 * @return "JUMP"
	 */
	public String toString () {
		return "JUMPIND ";
	}
}
