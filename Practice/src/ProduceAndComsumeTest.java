import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


public class ProduceAndComsumeTest {

	
	public static void main(String[] args) {
		
		final Warehouse warehouse = new Warehouse("Treasure", 100);
		
		Producer p1 = new Producer("p1", warehouse, 15);
		Producer p2 = new Producer("p2", warehouse, 19);
		Producer p3 = new Producer("p3", warehouse, 66);
		Producer p4 = new Producer("p4", warehouse, 48);
		Producer p5 = new Producer("p5", warehouse, 76);
		Producer p6 = new Producer("p6", warehouse, 150);
		
		
		Consumer c1 = new Consumer("c1", warehouse, 89);
		Consumer c2 = new Consumer("c2", warehouse, 190);
		Consumer c3 = new Consumer("c3", warehouse, 45);
		Consumer c4 = new Consumer("c4", warehouse, 41);
		Consumer c5 = new Consumer("c5", warehouse, 35);
		Consumer c6 = new Consumer("c6", warehouse, 50);
		
		try {
			fillHeap(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static class OOMObject {
		public byte[] placeholder = new byte[64*1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException {
		
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i=0; i<num; i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	} 
}

class Consumer implements Runnable {
	
	private String name;
	private Warehouse warehouse;
	private Thread thread;
	private Integer quantity;
	
public Consumer(String name, final Warehouse warehouse, Integer quantity) {
		
		this.name = name;
		this.warehouse = warehouse;
		this.thread = new Thread(this,name);
		this.thread.start();
		this.quantity = quantity;
	}

@Override
public void run() {
	// TODO Auto-generated method stub
	consume(quantity);
	
}

public void consume(Integer quantity){
	warehouse.lockConsume(name, quantity);
}

public Integer getQuantity() {
	return quantity;
}

public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}


}

class Producer implements Runnable {

	private String name;
	private Warehouse warehouse;
	private Thread thread;
	private Integer quantity;
	
	public Producer(String name, final Warehouse warehouse, Integer quantity) {
		
		this.name = name;
		this.warehouse = warehouse;
		this.thread = new Thread(this,name);
		this.thread.start();
		this.quantity = quantity;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Integer produceQuantity = getQuantity();
		produce(quantity);
		
	}
	
	
	public void produce(Integer quantity){
		warehouse.lockProduce(name, quantity);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}


class Warehouse{
	
	private String name;
	private Integer size;
	private Integer currentSize;
	private Lock lock;
	private Condition fullCondition;
	private Condition emptyCondition;
	
	public Warehouse (String name, Integer size) {
		
		this.name = name;
		this.size = size;
		this.currentSize = 0;
		this.lock = new ReentrantLock();
		fullCondition = lock.newCondition();
		emptyCondition = lock.newCondition();
	}
	
	public synchronized void produce(String name, Integer quantity) throws InterruptedException{
		
		if (quantity > size) {
			System.out.println(name + " has too much to produce!");
			return;
		}
		
		
		while (currentSize + quantity > size) {
			wait();
		}
		currentSize += quantity;
		System.out.println(name + " produced " + quantity);
		notify();
	}
	
	public void lockProduce(String name, Integer quantity) {
		
		lock.lock();
		try{
			if (quantity > size) {
				System.out.println(name + " has too much to produce!" );
				return;
			}
		
			//Lock wirteLock = lock.writeLock();
			while (currentSize == size || currentSize + quantity > size) {
				fullCondition.await();
			}
			//wirteLock.lock();
			currentSize += quantity;
			//wirteLock.unlock();
			emptyCondition.signal();
			System.out.println(name + " produced " + quantity + "   Current size is: " +currentSize);
		} 
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		
	}
	
	public void lockConsume(String name, Integer quantity) {
		
		lock.lock();
		try {
	
			if (quantity > size) {
				System.out.println(name + " has too much to consume!");
				return;
			}
			//Lock wirteLock = lock.writeLock();
			while (currentSize == 0 || currentSize < quantity) {
				emptyCondition.await();
			}
	
			//wirteLock.lock();
			currentSize -= quantity;
			//wirteLock.unlock();
			fullCondition.signal();
			System.out.println(name + " consumed " + quantity + "   Current size is: " +currentSize);
		} 
		catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		finally {
			lock.unlock();
		}
		
	}
	
	public synchronized void consume(String name, Integer quantity) throws InterruptedException {
		
		if (quantity > size) {
			System.out.println(name + " has too much to consume!");
			return;
		}
		
		while (currentSize < quantity) {
			wait();
		}
		currentSize -= quantity;
		System.out.println(name + " consumed " + quantity);
		notify();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(Integer currentSize) {
		this.currentSize = currentSize;
	}
	
	
}