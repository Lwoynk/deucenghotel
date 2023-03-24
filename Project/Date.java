package Project;

public class Date {
public Date(int day, int month, int year) {
		super();
		Day = day;
		Month = month;
		Year = year;
	}
public Date(String date)
{
	String[] dateArrStr=date.split(".");
	Day=(Integer)(Object)dateArrStr[0];
	Month=(Integer)(Object)dateArrStr[1];
	Year=(Integer)(Object)dateArrStr[2];
}
public String display()
{
	return (String.valueOf(Day)+" "+String.valueOf(Month)+"."+String.valueOf(Year));
}
public int getDay() {
		return Day;
	}
	public void setDay(int day) {
		Day = day;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
public int Day;
public int Month;
public int Year;
}
