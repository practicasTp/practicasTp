package mv.instructions;

public class Equals extends Compare {

	public Equals () {
		super (TipoInstruction.EQ);
	}
	
	public boolean compare (int cima, int subcima) {
		if (cima == subcima) return true;
		else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("EQ"))
			return new Equals ();
		else return null;
	}
	
	public String toString () {
		return "EQ";
	}
}
