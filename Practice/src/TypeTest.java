import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TypeTest {
	
	public static void main(String[] args) {
		
		Human manHuman = new Man();
		Human womanHuman = new Woman();
		
		//System.out.println(Eat.EATMEAT);
		
		System.out.println(QuickEat.QuickEatMeat);
		Eat eat = new Eat();
		eat.eat(manHuman);
		eat.eat(womanHuman);
		
		System.out.println(manHuman instanceof Man);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String s = bufferedReader.readLine();
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

abstract class Human{
	
}

class Man extends Human{
	
}

class Woman extends Human {
	
}

class Eat{
	
	public void eat(Human human) {
		System.out.println("Human eat!!");
	}
	
	public void eat(Man man) {
		System.out.println("Man eat!!");
	}
	
	public void eat(Woman woman) {
		System.out.println("Woman eat!!");
	}
	
	static void eat(){
		
		System.out.println("who eats?");
	}
	
	
	static {
		EATMEAT = "?????????????";
	}
	
	public static String EATMEAT = "eat meat!";
}

class QuickEat extends Eat {
	
	public static String QuickEatMeat = EATMEAT;
}






