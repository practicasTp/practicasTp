package observers;

public interface StackObserver<T> {
	public void onPush(T value); 
	public void onPop(T value); 
	public void onStackReset(); // OPCIONAL -- en caso que tenéis el método reset
}
