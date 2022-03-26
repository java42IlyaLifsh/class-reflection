package telran.annotation.validation.constraints;
//Ilyal-HW50

public class Person {

	@Patern(value="[A-Z][a-z]+")
	String name = "haim";
	@Min(value=0)
	@Max(value=120)
	double age = -18d;
	@Patern(value="[0-9]{9}")	
	String id = "12345678X";
	@Valid
	Address address = new Address("Ierusalime", "sokolov", 555555d);
	public Person(String name, double age, String id, Address address) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
		this.address = address;
	}
	Person() {}
	

}
