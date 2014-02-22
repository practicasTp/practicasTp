package mv.reading;

import java.io.IOException;

public class StdIn implements InputMethod {
	
	public int readChar() {
		int in = -1;
		try {
			in = System.in.read();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		return in;
	}
}
