import java.util.Iterator;


public class IteratorTest {
	
public static void main(String[] args) {
	
	Animal[] animals = new Animal[100];
	
	Animal bird = new Animal("Bird");
	Animal cat = new Animal("Cat");
	Animal dog = new Animal("Dog");
	
	Animal cow = new Animal("Cow");
	Animal lion = new Animal("Lion");
	
	animals[0] = bird;
	animals[1] = cat;
	animals[2] = dog;
	animals[3] = cow;
	animals[4] = lion;
	
	AnimalAggregate animalAggregate = new AnimalAggregate(animals);
	
	Iterator<Animal> iterator = animalAggregate.iterator();
	
	while(iterator.hasNext()){
		
		System.out.println(iterator.next().getName());
		
	}
}
	

}



class AnimalIterator implements Iterator<Animal>{
	
	private Animal animalArray[];
	private int currentIndex;
	 
	
	public  AnimalIterator() {
		// TODO Auto-generated constructor stub
		this.animalArray = new Animal[100];
		this.currentIndex = 0;
	}
	
	public AnimalIterator(Animal[] animals){
		
		this.animalArray = animals;
		this.currentIndex = 0;
	}
	
	

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !(currentIndex == animalArray.length);
	}

	@Override
	public Animal next() {
		// TODO Auto-generated method stub
		Animal animal = null;
		if (hasNext()){
			animal =  animalArray[currentIndex++];
		}

		return animal;
	}
}

class AnimalAggregate implements Iterable<Animal>{

	private Animal[] animals; 
	
	public AnimalAggregate(Animal[] animals) {
		this.animals = animals;
	}
	
	@Override
	public Iterator<Animal> iterator() {
		// TODO Auto-generated method stub
		return new AnimalIterator(animals);
	}

	
	
}
