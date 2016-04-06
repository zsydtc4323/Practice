
public class DecoratorPatternTest {

	
	public static void main(String[] args) {
		
		
		TheGreatestSage monkey = new Monkey();
		
		Change bird = new Bird(monkey);
		Change fish = new Fish(bird);
		
		fish.move();
		fish.getSage().move();
		bird.getSage().move();
		
	}
}


interface TheGreatestSage{
	
	public void move();
}

class Monkey implements TheGreatestSage{

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("Monkey move!");
	}
}

class Change implements TheGreatestSage {

	private TheGreatestSage sage;
	
	public Change(TheGreatestSage sage) {
		this.sage = sage;
	}
	
	
	public TheGreatestSage getSage() {
		return sage;
	}


	public void setSage(TheGreatestSage sage) {
		this.sage = sage;
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub
		sage.move();
		
	}
}

class Bird extends Change{
	
	
	public Bird(TheGreatestSage sage) {
		super(sage);
	}
	
	@Override
	public void move(){
		
		System.out.println("Bird!!!");
	}
}

class Fish extends Change{
	public Fish(TheGreatestSage sage) {
		super(sage);
	}
	
	@Override
	public void move(){
		
		System.out.println("Fish!!!");
	}
}