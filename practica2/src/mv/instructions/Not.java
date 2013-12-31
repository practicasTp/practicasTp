package mv.instructions;

import mv.cpu.Cpu;

public class Not extends Boolean {
	
	public Not () {
		super (TipoInstruction.NOT);
	}
	
	public boolean executeAux (Cpu cpu) {
		int n1 = cpu.pop();
		if (n1 == 1){
			return false;
		}else{
			return true;
		}
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("NOT"))
			return new Not ();
		else return null;
	}
	
	public String toString () {
		return "NOT";
	}
}
