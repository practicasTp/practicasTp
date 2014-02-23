package mv.writing;

public class StdOut implements OutputMethod{
	
	public void writeChar(char c) {
		System.out.print(c);
	}
}
