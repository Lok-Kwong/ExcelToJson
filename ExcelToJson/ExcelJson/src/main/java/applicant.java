import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder ({ "firstName", "lastname", "streetaddress", "city", "state", "zip", "country", "phone", "email"})
public class applicant {
	private String firstName;
	private String lastname;
	
//	private String housenumber;
//	private String streetname;
//	private String streetsuffix;
	
	private String streetaddress = "";
	
	private String city;
	private String state;
	private String zip;
	private String country;
	private String phone;
	private String email;
	
	public applicant() {
	}
	
	public applicant(String firstName, String lastname, String streetaddress, String city, String state,
			String zip, String phone, String email) {
		this.firstName = firstName;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public String getfirstName() {
		return this.firstName;
	}
	
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getlastname() {
		return this.lastname;
	}
	
	public void setlastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getstreetaddress() {
		return this.streetaddress;
	}
	
	public void setstreetaddress(String streetaddress) {
		this.streetaddress = this.streetaddress + streetaddress;
	}

	/*
	public String gethousenumber() {
		return this.housenumber;
	}
	
	public void sethousenumber(String housenumber) {
		this.housenumber = housenumber;
	}
	
	public String getstreetname() {
		return this.streetname;
	}
	
	public void setstreetname(String streetname) {
		this.streetname = streetname;
	}
	
	public String getstreetsuffix() {
		return this.streetsuffix;
	}
	
	public void setstreetsuffix(String streetsuffix) {
		this.streetsuffix = streetsuffix;
	}
	*/
	
	public String getcity() {
		return this.city;
	}
	
	public void setcity(String city) {
		this.city = city;
	}
	
	public String getstate() {
		return this.state;
	}
	
	public void setstate(String state) {
		this.state = state;
	}
	
	public String getcountry() {
		return this.country;
	}
	
	public void setcountry(String country) {
		this.country = country;
	}
	
	public String getzip() {
		return this.zip;
	}
	
	public void setzip(String zip) {
		this.zip = zip.substring(0, 5);
	}
	
	public String getphone() {
		return this.phone;
	}
	
	public void setphone(String phone) {
		this.phone = phone;
	}
	
	public String getemail() {
		return this.email;
	}
	
	public void setemail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "\"applicant\" [\"firstname\": " + firstName + ",\t"
				+ "\"lastname\": " + lastname + ",\t"
				+ "\"streetaddress\": " + streetaddress + ","
				+ "\"city\": " + city + ","
				+ "\":zip\": " + zip + ","
				+ "\":country\": " + country + ","
				+ "\":phone\": " + phone + "," 
				+ "]";

	}
}