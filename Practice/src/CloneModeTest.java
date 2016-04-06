import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;


public class CloneModeTest {
	
	public static void main(String[] args) {
		
		compareTest();
	}
	
	public static void test1(){
		
		
		ProtoType1 one = new ProtoType1();
		one.setName("test1");
		
		ProtoType1 two = (ProtoType1)one.clone();
		two.setName("test2");
		
		System.out.println("one: " + one.getName());
		System.out.println("two: " + two.getName());
		
	}

	public static void test2(){
		
		ProtoType1 one = new ProtoType1();
		one.setName("original prototype");
		
		NewProtoType1 newProtoType1 = new NewProtoType1();
		
		newProtoType1.setName("one");
		newProtoType1.setProtoType1(one);
		
		
		NewProtoType1 next = (NewProtoType1)newProtoType1.clone();
		next.setName("two");
		next.getProtoType1().setName("changed prototype");
		
		
		System.out.println("first name: " + newProtoType1.getName());
		System.out.println("first: " + newProtoType1.getProtoType1().getName());
		System.out.println("next name: " + next.getName());
		System.out.println("next: " + next.getProtoType1().getName());
		
		System.out.println(newProtoType1.getProtoType1() == next.getProtoType1());
	
		
	}
	
	public static void test3() {
		
		ProtoType1 one = new ProtoType1();
		one.setName("original prototype");
		
		NewProtoType2 newProtoType2 = new NewProtoType2();
		
		newProtoType2.setName("one");
		newProtoType2.setProtoType1(one);
		
		
		NewProtoType2 next = (NewProtoType2)newProtoType2.clone();
		next.setName("two");
		next.getProtoType1().setName("changed prototype");
		
		
		System.out.println("first name: " + newProtoType2.getName());
		System.out.println("first: " + newProtoType2.getProtoType1().getName());
		System.out.println("next name: " + next.getName());
		System.out.println("next: " + next.getProtoType1().getName());
		
		System.out.println(newProtoType2.getProtoType1() == next.getProtoType1());
	}
	
	public static void compareTest(){
		
		Rocket rocket1 = new Rocket("apolo", 10.1, 4.2, 1000, 1000);
		Rocket rocket2 = new Rocket("shenzhou5", 5.2, 2.6, 800, 880);
		Rocket rocket3 = new Rocket("saturn", 56.2, 100.2, 2000, 1500);
		Rocket rocket4 = new Rocket("alpha", 22.3, 15.7, 320, 410);
		Rocket rocket5 = new Rocket("japan ruozhi", 1.3, 86.2, 45, 12);
		
		Rocket[] rockets = new Rocket[]{rocket1,rocket2,rocket3,rocket4,rocket5};
		
		
		Arrays.sort(rockets, new NameComparator());
		System.out.println("Sorted by name: ");
		for(int i=0; i<rockets.length; i++) {
			System.err.println(rockets[i].getName());
		}
		
		Arrays.sort(rockets, new ApogeeComparator());
		System.out.println("Sorted by apogee: ");
		for(int i=0; i<rockets.length; i++) {
			System.err.println(rockets[i].getName() + ":　"+ rockets[i].getApogee());
		}
		
	}

}


class ProtoType1 implements Cloneable{
	
	private String name;
	
	public ProtoType1 (){};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone(){
		
		try {
//			Stack<ProtoType1> stack = new Stack<ProtoType1>();
			return super.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
			return null;
		}
	}
	
}

class NewProtoType1 implements Cloneable {
	
	private ProtoType1 protoType1;
	
	private String name;

	public ProtoType1 getProtoType1() {
		return protoType1;
	}

	public void setProtoType1(ProtoType1 protoType1) {
		this.protoType1 = protoType1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone(){
		
		try {
			
			return super.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
			return null;
		}
	}
	
}

class NewProtoType2 implements Cloneable {
	
	private ProtoType1 protoType1;
	
	private String name;

	public ProtoType1 getProtoType1() {
		return protoType1;
	}

	public void setProtoType1(ProtoType1 protoType1) {
		this.protoType1 = protoType1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone(){
		NewProtoType2 newProtoType2 = null;
		try {
				newProtoType2 = (NewProtoType2)super.clone();
				ProtoType1 pro = (ProtoType1)protoType1.clone();
				newProtoType2.setProtoType1(pro);
				return newProtoType2;
				
			
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
			return null;
		}
	}
	
}

class NameComparator implements Comparator<Object> {
	
	

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub);
		Rocket rocket1 = (Rocket)o1;
		Rocket rocket2 = (Rocket)o2;
		
		return rocket1.getName().toString().compareTo(rocket2.getName().toString());
	
	}
	
}

class ApogeeComparator implements Comparator<Object> {


	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Rocket rocket1 = (Rocket)o1;
		Rocket rocket2 = (Rocket)o2;
		
		return Double.compare(rocket1.getApogee(), rocket2.getApogee());
	}
	
}


class Rocket {
	
	private String name;
	private Integer apogee;
	private Double weight;
	private Double price;
	private Integer thrust;
	
	public Rocket(String name, Double weight, Double price, Integer apogee, Integer thrust) {
		
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.apogee = apogee;
		this.thrust = thrust;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getApogee() {
		return apogee;
	}

	public void setApogee(Integer apogee) {
		this.apogee = apogee;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getThrust() {
		return thrust;
	}

	public void setThrust(Integer thrust) {
		this.thrust = thrust;
	}
	
	
}

