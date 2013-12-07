package mv.instructions;

import mv.cpu.Cpu;

public class And extends Boolean{
	
	public And () {
		super (TipoInstruction.AND);
	}
	
	public boolean executeAux(Cpu cpu) {
		int n1 = cpu.pop();
		int n2 = cpu.pop();
		if (n1 == 1 && n2 == 1) return true;
		else return false;
	}
	
	public Instruction parse(String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("AND"))
			return new And ();
		else return null;
	}
	
	public String toString () {
		return "AND";
	}
}
