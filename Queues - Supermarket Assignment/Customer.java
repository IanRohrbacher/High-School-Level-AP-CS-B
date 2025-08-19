
public class Customer {
	private int serviceTime;
	private int waitedTime;
	private int timeWhenEntered;

	public Customer(int serviceTime, int timeWhenEntered) {
		this.serviceTime = serviceTime;
		waitedTime = 0;
		this.timeWhenEntered = timeWhenEntered;
	}
	
	public int getServiceTime() {
		return serviceTime;
	}
	
	public int getWaitedTime() {
		return waitedTime;
	}
	
	public void waited() {
		waitedTime++;
	}
	
	public void setTimeWhenEntered(int timeWhenEntered) {
		this.timeWhenEntered = timeWhenEntered;
	}
	
	public int getTimeWhenEntered() {
		return timeWhenEntered;
	}
	
	public boolean isLeaving() {
		return waitedTime >= serviceTime;
	}
}