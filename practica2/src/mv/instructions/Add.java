package mv.instructions;

public class Add extends Arithmetics{
	
	public Add () {
		super(TipoInstruction.ADD);
	}
	
	public boolean execute (int n1, int n2) {
		this.result = n1 + n2;
		return true;
	}
	
	public Instruction parse (String[] s) {
		if ((s.length == 1) && s[0].equalsIgnoreCase("ADD")) 
			return new Add();
		else return null;
	}
	
	public String toString () {
		return "ADD";
	}
}
