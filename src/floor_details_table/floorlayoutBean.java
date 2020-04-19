package floor_details_table;

public class floorlayoutBean {

	String slotss;
	String floors;
	String type;
	String slots_occupied;
	
	public floorlayoutBean(){}
	public floorlayoutBean(String floors, String slotss, String slots_occupy, String type) {
		super();
		this.slotss = slotss;
		this.floors = floors;
		this.slots_occupied = slots_occupy;
		this.type = type;
	}
	public String getSlots_occupied() {
		return slots_occupied;
	}
	public void setSlots_occupied(String slots_occupied) {
		this.slots_occupied = slots_occupied;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
