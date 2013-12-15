package mv.commands;

public class Step extends CommandInterpreter {
	
	public Step () {
		super();
	}
	
	public boolean executeCommand() {
		if (CommandInterpreter.cpu.step(CommandInterpreter.cpu))
			return true;
		else return false;
	}
	
	public String toString () {
		return "STEP";
	}
}
