import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class SmartHome {
	private ArrayList<SmartObject> smartObjectList = new ArrayList<SmartObject>();
	private int k = 100;	//This value holds the last part of IP value.
	
	public SmartHome() {
		
	}
	
	public boolean addSmartObject(SmartObject smartObject) {
		smartObject.connect("10.0.0." + k);   //We connect the object with proper IP.
		k++;	//We increment the value to change IP for each object.
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Adding new SmartObject");
		System.out.println("--------------------------------------------------------------------------");
		smartObject.testObject();
		smartObjectList.add(smartObject);
		return true;
	}
	
	public boolean removeSmartObject(SmartObject smartObject) {		
		smartObjectList.remove(smartObject);
		return true;
	}
	
	public void controlLocation(boolean onCome) {
		if(onCome) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("LocationControl: OnCome");
			System.out.println("--------------------------------------------------------------------------");
		}
		else {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("LocationControl: OnLeave");
			System.out.println("--------------------------------------------------------------------------");
		}
			
		for(int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof SmartLight) {   //We search for smart lights
				SmartLight temp = (SmartLight)(smartObjectList.get(i));  
				if(onCome)
					temp.onCome();
				else
					temp.onLeave();
				
			}
		}
	}
	
	public void controlMotion(boolean hasMotion, boolean isDay) { //We control the motion.
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("MotionControl: HasMotion, isDay");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof SmartCamera) {		//We search for smart cameras.
				SmartCamera temp = (SmartCamera)(smartObjectList.get(i));
				temp.controlMotion(hasMotion, isDay);
			}
		}
	}
	
	public void controlProgrammable() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: runProgram");
		System.out.println("--------------------------------------------------------------------------");
		for(int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Programmable) { 	//We search for programmable objects.
				((Programmable)smartObjectList.get(i)).runProgram();	//We invoke runProgram method.
			}
		}
	}
	
	public void controlTimer(int seconds) {		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = " + seconds + " seconds");
		System.out.println("--------------------------------------------------------------------------");
		for(int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Programmable) {	//We search for the object.
				Programmable temp = (Programmable)(smartObjectList.get(i));		//We downcast the object from the list.
				if(seconds < 0) {
					System.out.println("Invalid value for seconds");
					System.exit(0);
				}
				else if(seconds > 0)
					temp.setTimer(seconds);
				else
					temp.cancelTimer();
			}
		}
	}
	
	public void controlTimerRandomly() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = 5 or 10 seconds randomly");
		System.out.println("--------------------------------------------------------------------------");
		for(int i = 0; i < smartObjectList.size(); i++) {		//We search for the objects.
			int a = ((int)(Math.random() * 3)) * 5;
			if(smartObjectList.get(i) instanceof Programmable) {	
				if(!(a == 0))		//If a is equal to 0 we cancel the timer otherwise we invoke set timer method.
					((Programmable)smartObjectList.get(i)).setTimer(a);
				else
					((Programmable)smartObjectList.get(i)).cancelTimer();
			}
		}
		System.out.println("--------------------------------------------------------------------------");
	}
	
	public void sortCameras() {
		int j = 0;
		for(int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Comparable)
				j++;
		}
		SmartCamera[] tempArray = new SmartCamera[j];
		j = 0;
		for(int i = 0; i < smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Comparable) {
				tempArray[j] = (SmartCamera)(smartObjectList.get(i));	//We add the objects to smart camera array.
				j++;
 			}
		}
		Arrays.sort(tempArray);		//We sort the arrays.
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Sort Smart Cameras");
		System.out.println("--------------------------------------------------------------------------");
		for(int i = 0; i < tempArray.length; i++)
			System.out.println(tempArray[i].toString());	//We print the sorted objects.
		System.out.println("--------------------------------------------------------------------------");
	}

	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjectList;
	}

	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}
	
}
