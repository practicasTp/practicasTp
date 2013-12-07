package mv.instructions;

public class LessOrEqual extends Compare {

	public LessOrEqual () {
		super (TipoInstruction.LE);
	}
	
	public boolean compare (int cima, int subcima) {
		if (cima <= subcima) return true;
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("LE"))
			return new LessOrEqual ();
		else return null;
	}
	
	public String toString () {
		return "LE";
	}
}
