package Project;

public class Staff 
{
	private int ID;
	public String Task;  //there is 3 tasks and staff should be at least 1 of each
	public String Name;
	public String BirthDate;
	public String Gender;
	public String Phone;
	public int Salary;
	public static int totalCount=0;
	Adress adress;
	public void display()
	{
		System.out.println("Staff#"+(ID+1)+" "+Name+" "+Gender+" "+BirthDate+" "+Task);
	}

	public Staff(String task, String name, String birthDate, String gender, String phone, int salary,Adress adress) {
		super();
		this.adress=adress;
		ID=totalCount;
		Task = task;
		Name = name;
		BirthDate = birthDate;
		Gender = gender;
		Phone = phone;
		Salary = salary;
		totalCount=totalCount+1;
	}
	
	
	
}
