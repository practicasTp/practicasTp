package mv.instructions;

public class Mult extends Arithmetics {
	
	public Mult () {
		super(TipoInstruction.MUL);
	}
	
	public boolean execute (int n1, int n2) {
		this.result = n1 * n2;
		return true;
	}
	
	public Instruction parse (String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("MULT")) 
			return new Mult();
		else return null;
	}
	
	public String toString () {
		return "MULT";
	}
}
