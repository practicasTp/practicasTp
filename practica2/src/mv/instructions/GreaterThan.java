package mv.instructions;

public class GreaterThan extends Compare {

	public GreaterThan () {
		super (TipoInstruction.GT);
	}
	
	public boolean compare (int cima, int subcima) {
		if (cima > subcima) return true;
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("GT"))
			return new GreaterThan ();
		else return null;
	}
	
	public String toString () {
		return "GT";
	}
}
