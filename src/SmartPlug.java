import java.util.Calendar;

public class SmartPlug extends SmartObject implements Programmable {
	private boolean status;
	private Calendar programTime;
	private boolean programAction;
	
	public SmartPlug(String alias, String macId) {
		setAlias(alias);
		setMacId(macId);
	}
	
	public void turnOn() {		//We make proper actions depending on the status value and print a message.
		if(isConnectionStatus()) {
			if(!status) {
			status = true;
			System.out.println(this.getClass().getSimpleName() + " - " + getAlias() + " is turned on now (Current Time: " + SmartLight.getHours() + ")");
			}
			else {
				System.out.println(this.getClass().getSimpleName() + " - " + getAlias() + " has been already turned on.");
			}
		}
	}
	
	public void turnOff() {			//We make proper actions depending on the status value and print a message.
		if(isConnectionStatus()) {
			if(status) {
				status = false;
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " is turned off now (Current Time: " + SmartLight.getHours() + ")");
			}
			else {
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " has been already turned off.");
			}
		}
	}
	
	public boolean testObject() {		//We make some operations to see if the object is working or not.
		if(isConnectionStatus()) {
			SmartObjectToString();
			System.out.println("Test is starting for " + this.getClass().getSimpleName());
			turnOn();
			turnOff();
			System.out.println("The test completed for " + this.getClass().getSimpleName());
			return true;
		}
		return false;
	}
	
	public boolean shutDownObject() {
		if(isConnectionStatus()) {
			if(status) {
				status = false;
				SmartObjectToString();
				return true;
			}
		}
		return false;
	}
	
	public void setTimer(int seconds) {		//We set a timer to make an operation.
		if(isConnectionStatus()) {
			programTime = Calendar.getInstance();	//We get the current time.
			programTime.add(Calendar.SECOND, seconds);	//We add seconds to current time.
			if(status) {
				programAction = false;	
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " will be turned off in " + seconds + " seconds later! (Current Time: " + SmartLight.getHours() + ")");
			}
			else {
				programAction = true;
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " will be turned on in " + seconds + " seconds later! (Current Time: " + SmartLight.getHours() + ")");
			}
		}
	}
	
	public void cancelTimer() {
		if(isConnectionStatus())
			programTime = null;
	}
	
	public void runProgram() {	
		try {
		Calendar x = Calendar.getInstance(); //We get current time.
		if(isConnectionStatus()) {
			if(programTime.get(Calendar.HOUR_OF_DAY) == x.get(Calendar.HOUR_OF_DAY) 
					&& programTime.get(Calendar.MINUTE) == x.get(Calendar.MINUTE) 
					&& programTime.get(Calendar.SECOND) == x.get(Calendar.SECOND)) { //We compare current time with program time.
					System.out.println("RunProgram -> Smart Plug - " + this.getAlias());
					if(programAction) {		//We perform some operations depending on the programAction value.
						turnOn();
						programTime = null;
					}	
					else {
						turnOff();
						programTime = null;					
					}
				}
			}
		System.out.println("--------------------------------------------------------------------------");
		}
		catch(Exception e) {
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Calendar getProgramTime() {
		return programTime;
	}

	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}

	public boolean isProgramAction() {
		return programAction;
	}

	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	
}
