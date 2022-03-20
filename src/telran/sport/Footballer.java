package telran.sport;

public class Footballer implements Sportsman {
String team;

	public Footballer(String team) {
	this.team = team;
}

	@Override
	public void sportAction() {
		System.out.printf("I'm footboller in the team %s\n",team);
		

	}

}
