package observers;

public interface MemoryObserver<T> {
	public void onWrite(int index, T value); 
	public void onMemReset(); // OPCIONAL -- en caso que tenéis el método reset
}
