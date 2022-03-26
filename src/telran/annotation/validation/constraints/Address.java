package telran.annotation.validation.constraints;
//Ilyal-HW50
public class Address {
	
	@Patern(value="[A-Z][a-z]+")
	String city;
	@Patern(value="[A-Z][\s[a-z]]+")
	String street;
	@Max(value=9999)
	@Min(value=1)
	double houseNumber;
	Address(String city, String street, Double number) {
		this.city = city;
		this.street = street;
		this.houseNumber = number;
	}
	Address() {}
}
