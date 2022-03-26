package telran.annotation.validation.constraints;
//Ilyal-HW50
import java.util.List;

public class Appl {
	public static void main(String[] args) {
		Validator vaildator = new Validator();
		System.out.println("-----------------------------");
		List<String> results = vaildator.validate(new Person());
		System.out.println("Total ammount of checks was "+results.size());
		results.forEach(str -> System.out.println(str));
	}
}

