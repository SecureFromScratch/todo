package tasks;

public class MutableState {
	private boolean m_isCompleted = false;
	
	public final boolean isCompleted() {
		return m_isCompleted;
	}
	
	public final void setCompleted(boolean isCompleted) {
		m_isCompleted = isCompleted;
	}
}
