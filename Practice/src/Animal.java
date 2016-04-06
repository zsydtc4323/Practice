import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sun.javafx.geom.AreaOp.AddOp;
import com.sun.org.apache.regexp.internal.recompile;


public class Animal implements Iterable<Animal>{

	public List<Animal> list = new ArrayList<Animal>();
	
	//public Animal(){}
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Animal(){
		
	}

	public Animal (String name) {
		this.name = name;
	}
	
	public void add(Animal animal) {
		
		list.add(animal);
	}
	
	public int getAnimalCount(){
		
		return 1;
	}
	
	public boolean isTree(Set visited) {
		
		return (visited.add(this));
		
		
	}

	@Override
	public Iterator<Animal> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}
}
