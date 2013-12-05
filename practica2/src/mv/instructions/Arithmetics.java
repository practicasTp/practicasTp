package mv.instructions;
import mv.cpu.Cpu;

abstract public class Arithmetics extends Instruction {
	protected int result;
	
	public Arithmetics(TipoInstruction tipo) {
		super(tipo);
	}
	
	abstract protected boolean execute (int n1, int n2);
	
	public boolean execute (Cpu cpu) {
		if (cpu.getSizeStack () >= 2){
			int n1 = cpu.pop();
			int n2 = cpu.pop();
			if (this.execute (n1, n2)) {
				cpu.push (this.result);
				cpu.increaseProgramCounter ();
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	abstract public Instruction parse (String[] s);
		//Si tiene éxito (sólo un extring y la componente 0 es igual a A) return new Add ();
}
