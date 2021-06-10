import java.util.Calendar;

public class SmartLight extends SmartObject implements LocationControl, Programmable {
	private boolean hasLightTurned;
	private Calendar programTime;
	private boolean programAction;
	
	public SmartLight(String alias, String macId) {  //We use set methods to set the values.
		setAlias(alias);
		setMacId(macId);
	}
	
	public void turnOnLight() {		//We check the current status of light.If it's turned off we turn it on and we print a message.
		if(isConnectionStatus()) {
			if(hasLightTurned)		//If it is already turned on we print a message.
				System.out.println("Smart Light - " + this.getAlias() + " has been already turned on");
			else {
				hasLightTurned = true;
				programTime = Calendar.getInstance();	//We set the program time.
				System.out.println("Smart Light - " + this.getAlias() + " is turned on now (Current time: " + getHours() + ")");
			}	
		}
	}
	
	public void turnOffLight() {		//We check the current status of light. If it's turned on we turn it off and print a message.
		if(isConnectionStatus()) {
			if(!hasLightTurned)			//If it's already off we print another message.
				System.out.println("Smart Light - " + this.getAlias() + " has been already turned off");
			else {
				hasLightTurned = false;
				programTime = Calendar.getInstance();
				System.out.println("Smart Light - " + this.getAlias() + " is turned off now (Current time: " + getHours() + ")");
			}	
		}
	}
	
	public boolean testObject() {		//In this method we check if the object is working or not.
		if(isConnectionStatus()) {
			SmartObjectToString();
			System.out.println("Test is starting for " + this.getClass().getSimpleName());
			turnOnLight();
			turnOffLight();
			System.out.println("Test completed for " + this.getClass().getSimpleName());
			return true;
		}
		else
			return false;
	}
	
	public boolean shutDownObject() {
		if(isConnectionStatus()) {
			SmartObjectToString();
			if(hasLightTurned)
				hasLightTurned = false;
			return true;
		}
		else
			return false;
	}
	
	public void onLeave() {	
		if(isConnectionStatus())
			turnOffLight();
	}
	
	public void onCome() {		
		if(isConnectionStatus())
			turnOnLight();
	}
	
	public void setTimer(int seconds) {		//We set a timer to do an operation on an object.
		if(isConnectionStatus()) {
			programTime = Calendar.getInstance();	//We get the current time.
			programTime.add(Calendar.SECOND, seconds);	//We add desired seconds to current seconds.
			if(hasLightTurned) {	//We print the proper message.
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " will be turned off " + seconds + " seconds later! (Current Time: " + getHours() + ")");
			}
			else {
				System.out.println(this.getClass().getSimpleName() + " - " + this.getAlias() + " will be turned on " + seconds + " seconds later! (Current Time: " + getHours() + ")");
			}
		}
	}
	
	public void cancelTimer() {
		if(isConnectionStatus())
			programTime = null;
	}
	
	public void runProgram() {
		try {  //We did try-catch to prevent null pointer error.
		Calendar x = Calendar.getInstance(); //We get the current time.
		if(isConnectionStatus()) {
				if(programTime.get(Calendar.HOUR_OF_DAY) == x.get(Calendar.HOUR_OF_DAY) 
						&& programTime.get(Calendar.MINUTE) == x.get(Calendar.MINUTE) 
						&& programTime.get(Calendar.SECOND) == x.get(Calendar.SECOND)) {  //We check if the current time equals to program time.
					System.out.println("RunProgram -> Smart Light - " + this.getAlias());
					if(programAction) {		//We make the proper actions depending on programAction variable.
						turnOnLight();
						programTime = null;   //We reset the program time since we performed this action once.
					}	
					else {
						turnOffLight();
						programTime = null;					
					}
				}
			}
		System.out.println("--------------------------------------------------------------------------");
		}
		catch(Exception e) {
		}
	}

	public boolean isHasLightTurned() {
		return hasLightTurned;
	}

	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
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
	
	public static String getHours() {
		Calendar x = Calendar.getInstance();
		return x.getTime().toString().substring(11, 19);
	}
	
}
