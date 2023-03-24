package Project;

public class Room {
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public boolean isAirConditioning() {
		return AirConditioning;
	}

	public void setAirConditioning(boolean airConditioning) {
		AirConditioning = airConditioning;
	}

	public boolean isBalcony() {
		return Balcony;
	}

	public void setBalcony(boolean balcony) {
		Balcony = balcony;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int ID;
	public String Type;
	public boolean AirConditioning;
	public boolean Balcony;
	public static int count=0;
	public int Price; //per day
	public Room(int iD, String type, boolean airConditioning, boolean balcony, int price) {
		super();
		ID = iD;
		Type = type;
		AirConditioning = airConditioning;
		Balcony = balcony;
		Price = price;
		count++;
	}

	public void display()
	{ String message=" ";
	  if(AirConditioning==true && Balcony==true)
	    message="Room #"+ID+"\t"+Type+ "\t aircondition    balcony\t" +Price+"TL";
	  else if(AirConditioning==true && Balcony==false)
		 message="Room #"+ID+"\t"+Type+ "\t aircondition    no-balcony\t" +Price+"TL";
	  else if(AirConditioning==false&& Balcony==false)
		 message="Room #"+ID+"\t"+Type+ "\t no-aircondition    no-balcony\t" +Price+"TL";
	  else if(AirConditioning==false && Balcony==true)
		 message="Room #"+ID+"\t"+Type+ "\t no-aircondition   balcony\t" +Price+"TL";
		System.out.println(message);
	 }
	
}
