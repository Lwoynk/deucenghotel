package Project;
public class Reservation 
{
	public static int count=0;
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getRoomID() {
		return RoomID;
	}
	public void setRoomID(int roomID) {
		RoomID = roomID;
	}
	public Date getArrivaldate() {
		return arrivaldate;
	}
	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}
	public Date getDeparturedate() {
		return departuredate;
	}
	public void setDeparturedate(Date departuredate) {
		this.departuredate = departuredate;
	}
	int CustomerID;
	int RoomID;
	Date arrivaldate;
	Date departuredate;
	public Reservation(int customerID, int roomID, Date arrivaldate, Date departuredate) {
		super();
		count++;
		CustomerID = customerID;
		RoomID = roomID;
		this.arrivaldate = arrivaldate;
		this.departuredate = departuredate;
	}
	public void display()
	{
		
	}
	
	
	
}
