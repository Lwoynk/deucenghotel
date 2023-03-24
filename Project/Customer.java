package Project;

import java.util.Formatter;

public class Customer {
	private String name;
	private String surname;
	private String gender;
	private String birthdate;
	private String addresstext;
	private String district;
	private String city;
	private String phone;
	public static int count=0;
	
	
	public Customer(String name, String surname, String gender, String birthdate, String addresstext, String district,
			String city,String phone ) {
		
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.addresstext = addresstext;
		this.district = district;
		this.city = city;
		this.phone=phone;
		count++;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddresstext() {
		return addresstext;
	}
	public void setAddresstext(String addresstext) {
		this.addresstext = addresstext;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}
	public Formatter display()
	
	{  Formatter fmt = new Formatter(); 
	return fmt.format("%-12s%-12s %-12s %-12s %-12s %-1s%-2s %-1s%3s%-1s %7s  \n",name,surname,gender,birthdate,city,"+",phone.substring(1,3),"(",phone.substring(3,6),")",phone.substring(6));

	}
	
	
	
	
}
