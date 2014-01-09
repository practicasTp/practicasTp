package mv.instructions;

import mv.cpu.Cpu;

public class RelativeJump extends Jumps {
	
	public RelativeJump (int operando) {
		super (TipoInstruction.RJUMP, operando);
		this.operando = operando;
	}
	
	/**
	 * Incrementa el contador de programa en función del atributo operando.
	 * @param cpu.
	 * @return boolean
	 */
	public boolean executeAux (Cpu cpu) {
		cpu.increaseProgramCounter(this.operando);
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción rjump
	 * @param s
	 * @return new RelativeJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("RJUMP")) {
			int operando = Integer.parseInt(s[1]);
			return new RelativeJump (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción rjump
	 * @return "RJUMP"
	 */
	public String toString () {
		return "RJUMP " + this.operando;
	}
}
