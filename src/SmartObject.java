
public abstract class SmartObject {
	private String alias;
	private String macId;
	private String IP;
	private boolean connectionStatus;
	
	public SmartObject() {
	}
	
	public boolean connect(String IP) {   //In this method we insert the IP, change the connectionStatus value and print a message.
		this.IP = IP;
		System.out.println(alias + " connection established");
		connectionStatus = true;
		return connectionStatus;
	}
	
	public boolean disconnect() {		//In this method we change the connectionStatus from true to false.
		connectionStatus = false;
		return connectionStatus;
	}
	
	public void SmartObjectToString() {		
		System.out.println("This is " + this.getClass().getSimpleName() + " device " + this.getAlias() + "\n\t MacId: " + this.getMacId() + "\n\t IP: " + this.getIP());
	}
	
	public boolean controlConnection() {	//We check the connection status and print a message that indicates the connection status of device.
		if(connectionStatus) {
			System.out.println("This device is connected. " + this.getClass().getSimpleName() + " -> " + this.getAlias());
			return true;
		}	
		else {
			System.out.println("This device is not connected. " + this.getClass().getSimpleName() + " -> " + this.getAlias());
			return false;
		}	
	}
	
	public abstract boolean testObject();		//We declare the abstract methods to implement later.
	public abstract boolean shutDownObject();

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public boolean isConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
}
