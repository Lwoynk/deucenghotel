package Project;

public class Hotel {
	public static String Name;
	public static Date date;
	public static Adress Adress;
	public static String phoneNo;
	public static int Star;
	
	
	public Adress getAdress() {
		return Adress;
	}
	public void setAdress(Adress adress) {
		Adress = adress;
	}
	public Hotel(String name, Date date, Adress adress, String phoneno, int star, Customer bestCostumer) {
		super();
		Name = name;
		this.date = date;
		Adress = adress;
		phoneNo = phoneno;
		Star = star;
		
	}
	public void display()
	{
		System.out.println(Name+" "+date.display()+" "+Adress.display()+" "+phoneNo+" "+Star);
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
	public String getphoneNo() {
		return phoneNo;
	}
	public void setphoneNo(String phoneno) {
		phoneNo = phoneno;
	}
	public int getStar() {
		return Star;
	}
	public void setStar(int star) {
		Star = star;
	}
	

	 
}
