import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

import sun.rmi.transport.LiveRef;
import b.b;


public class GenericTest {

	
	public static void main(String[] args) {
		
		
//		Node<Integer> node = new Node<Integer>("zsy",26);
//		node.setT(123123);
//		System.out.println(node.getT());
//		node.list = new ArrayList<Animal>();
		
		Cat cat = new Cat();
		Dog dog = new Dog();
		Bear bear = new Bear();
		Mammal mammal = new Mammal();
		Mammal anotherMammal = new Mammal();
		
		mammal.add(cat);
		mammal.add(dog);
		mammal.add(bear);
		anotherMammal.add(cat);
		anotherMammal.add(mammal);
		
		System.out.println(anotherMammal.getAnimalCount());
		
		
		
		
		Set set = new HashSet<Animal>();
		//set.add(anotherMammal);
		
		System.out.println(anotherMammal.isTree(set));
		
		
		God god = God.getInstance();
		
		
		
		
	}
	
}

class Cat extends Animal {
	public String name;
	public List<Cat> list = new ArrayList<Cat>();
	public Cat(){
		name = "cat";
	}
	
	public Cat(String name) {
		super(name);
	}

	@Override
	public Iterator iterator() {
		Iterator<Cat> iterator = list.iterator();
		return iterator;
	}
}
class Dog extends Animal {
	
	
	public Dog(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Dog() {
		// TODO Auto-generated constructor stub
		super();
	}}
class Bear extends Animal {

	public Bear(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Bear() {
		// TODO Auto-generated constructor stub
	}}
class Mammal extends Animal {
	
	public Mammal(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Mammal() {
		// TODO Auto-generated constructor stub
	}

	public int getAnimalCount(){
		System.out.println("mammal");
		int count = 0;
		for (Animal animal: list) {
			count = count + animal.getAnimalCount();
		}
		
		return count;
		
	}
	
	public boolean isTree(Set visited) {
		
		if (!visited.add(this))
			return false;
		for (Animal animal : list){
			if (visited.contains(animal) || !animal.isTree(visited))
				return false;
		}
		
		return true;
		
	}
}

class Node<T> extends Animal implements Creature, b {
	
	private T t;
	public Node<T> next;
	private String name;
	private int age;
	
	
	
	public Node(String name, int age){

		super(name);
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
		
	
}


class God{
	
	private God(){}
	
	private static class GodHolder{
		private static final God INSTANCE = new God();
	} 
	
	public static final God getInstance(){
		return GodHolder.INSTANCE;
	}	
}




class Habitat<T extends Animal>{	 
	
	
	
}
