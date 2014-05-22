package observers;

import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.instructions.Instruction;
import mv.program.ProgramMv;

public interface CPUObserver {
	public void onStartInstrExecution(Instruction instr); 
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> stack, ProgramMv program);
	public void onStartRun();
	public void onEndRun();
	public void onError(String msg);
	public void onHalt();
	public void onReset(ProgramMv program);
	public void onNewIn();
}
