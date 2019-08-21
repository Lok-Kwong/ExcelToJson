import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder ({ "firstName", "lastname", "streetaddress", "city", "state", "zip", "country", "phone", "email"})
public class Applicant {
	private String firstname;
	private String lastname;
	private String streetaddress = "";
	private String city;
	private String state;
	private String zip;
	private String country;
	private String phone;
	private String email;
	
	public Applicant() {
	}
	
	public Applicant(String firstname, String lastname, String streetaddress, 
			String city, String state, String zip, String phone, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getStreetAddress() {
		return this.streetaddress;
	}
	
	public void setStreetAddress(String streetaddress) {
		this.streetaddress = this.streetaddress + streetaddress;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getZip() {
		return this.zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip.substring(0, 5);
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "\"applicant\" [\"firstname\": " + firstname + ",\t"
				+ "\"lastname\": " + lastname + ",\t"
				+ "\"streetaddress\": " + streetaddress + ","
				+ "\"city\": " + city + ","
				+ "\":zip\": " + zip + ","
				+ "\":country\": " + country + ","
				+ "\":phone\": " + phone + "," 
				+ "]";

	}
}