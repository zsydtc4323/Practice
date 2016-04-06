import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import b.b;


public class ObservorPatternTest {

	
	public static void main(String[] args) {
		
		ConcreteSubject onepiece = new ConcreteSubject();
		
		onepiece.attach(new ConcreteObserver("Luffy"));
		onepiece.attach(new ConcreteObserver("zoro"));
		onepiece.attach(new ConcreteObserver("Sanji"));
		
		onepiece.change("One piece has been found!!");
	}
}


interface Subject {
	
	public void notifyAllMembers(String newState);
	
}

class ConcreteSubject implements Subject{
	
	private List<NewObserver> memberList = new ArrayList<NewObserver>();
	private String state;
	
	public ConcreteSubject(){
		
	}
	
	public void attach(NewObserver o) {
		memberList.add(o);
	}
	
	public boolean detach(NewObserver o){
		
		return memberList.remove(o);
	}
	
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public void notifyAllMembers(String newState) {
		// TODO Auto-generated method stub
		
		for (NewObserver observer: memberList) {
			observer.updateState(newState);
		}
		
	}
	
	public void change(String newState){
		
		setState(newState);
		System.out.println("Explosive news: " + newState + "!");
		notifyAllMembers(newState);
	}

}

interface NewObserver{
	public void updateState(String state);
}


class ConcreteObserver implements NewObserver {
	
	private String name;
	private String state;
	
	public ConcreteObserver(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public void updateState(String state) {
		// TODO Auto-generated method stub
		this.state = state;
		System.out.println(this.name + " knows " + state);
		
	}



	
	
}
