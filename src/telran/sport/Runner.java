package telran.sport;

public class Runner implements Sportsman {
private int speed;
public Runner(String speed) {
	this.speed = Integer.parseInt(speed);
}
	@Override
	public void sportAction() {
		System.out.printf("I'm running with speed: %d km/h\n", speed);

	}

}
