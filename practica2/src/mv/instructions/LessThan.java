package mv.instructions;

public class LessThan extends Compare{
	
	public LessThan () {
		super (TipoInstruction.LT);
	}
	
	public boolean compare (int cima, int subcima) {
		if (cima < subcima) return true;
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LT"))
			return new LessThan ();
		else return null;
	}
	
	public String toString () {
		return "LT";
	}
}
