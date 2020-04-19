package slot_details_table;

public class slotlayoutBean {

	String slotss;
	String floors;
	String vehicle;
	String type;
	
	public slotlayoutBean(){}

	public slotlayoutBean(String slotss, String floors, String vehicle, String type) {
		super();
		this.slotss = slotss;
		this.floors = floors;
		this.vehicle = vehicle;
		this.type = type;
	}

	public String getSlotss() {
		return slotss;
	}

	public void setSlotss(String slotss) {
		this.slotss = slotss;
	}

	public String getFloors() {
		return floors;
	}

	public void setFloors(String floors) {
		this.floors = floors;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
	
}
