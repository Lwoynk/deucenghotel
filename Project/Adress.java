package Project;

public class Adress {
	String Apartment;
	String District;
	String Province;
	public Adress(String apartment, String district, String province) {
		super();
		Apartment = apartment;
		District = district;
		Province = province;
	}
	public String display()
	{
		return Apartment+"/"+District+"/"+Province;
	}
	public String getApartment() {
		return Apartment;
	}
	public void setApartment(String apartment) {
		Apartment = apartment;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	
	
	
	
	
	
}
