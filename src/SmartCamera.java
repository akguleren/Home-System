
public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera> {
	private boolean status;
	private int batteryLife;
	private boolean nightVision;
	
	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		setAlias(alias);
		setMacId(macId);
		this.nightVision = nightVision;
		this.batteryLife = batteryLife;
	}
	
	public void recordOn(boolean isDay) {  //We turn on the recording depending on isDay parameter.
		if(isConnectionStatus()) {
			if(!isDay && !nightVision)	//If isDay is false we check the night vision of the camera. If the camera doesn't have a night vision option we print a message.
				System.out.println("Sorry! " + this.getClass().getSimpleName() + " - " + this.getAlias() + " does not have night vision feature.");
			else if(status)		
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " has been already turned on.");
			else {
				status = true;
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " is turned on now.");
			}
		}
	}
	
	public void recordOff() {	//We turn off the recording.
		if(isConnectionStatus()) {
			if(status) {
				status = false;
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " is turned off now.");
			}
			else
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " has been already turned off.");
		}
	}
	
	public boolean testObject() {	//We test the functionality of the object with performing some operations.
		if(isConnectionStatus()) {
			SmartObjectToString();
			System.out.println("Test is starting for " + this.getClass().getSimpleName() + " day time");
			recordOn(true);
			recordOff();
			System.out.println("Test Completed for " + this.getClass().getSimpleName());
			System.out.println("Test is starting for " + this.getClass().getSimpleName() + " night time");
			recordOn(false);
			recordOff();
			System.out.println("Test Completed for " + this.getClass().getSimpleName());
			return true;
		}
		return false;
	}
	
	public boolean shutDownObject() {
		if(isConnectionStatus()) {
			if(status) {
				SmartObjectToString();
				status = false;
				return true;
			}
		}
		return false;
	}
	
	public boolean controlMotion(boolean hasMotion, boolean isDay) {	//We check if there is a motion or not. If there is we start recording.
		if(hasMotion) {
			System.out.println("Motion detected!");
			if(isDay)
				recordOn(isDay);
			return true;
		}
		else
			System.out.println("Motion not detected!");
		return false;
	}

	public int compareTo(SmartCamera smartCamera) {		//We implement compareTo method from comparable interface.
		if(batteryLife > smartCamera.getBatteryLife())		//It compares cameras by checking the batteryLife values.
			return 1;
		else if(batteryLife == smartCamera.getBatteryLife())
			return 0;
		else
			return -1;
	}
	
	public String toString() {
		if(status)
			return this.getClass().getSimpleName() + " -> " + this.getAlias() + "'s battery life is " + batteryLife + " status is recording";
		else
			return this.getClass().getSimpleName() + " -> " + this.getAlias() + "'s battery life is " + batteryLife + " status is not recording";
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public boolean isNightVision() {
		return nightVision;
	}

	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
}
