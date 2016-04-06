import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;


public class DynamicProxyTest {

	public static void main(String[] args) {
		IHello hello1 = new Hello();
		IHello hello2 = new NewHello();
		
		
		IHello iHello1 = (IHello) new HelloProxyHandler().bind(hello1, new LoggerOperation());
		
		IHello iHello2 = (IHello) new HelloProxyHandler().bind(hello2, new LoggerOperation());
		
		iHello1.sayHello("zsy");
		System.out.println("---------------------------------------------------");
		iHello1.sayGoodBye("zsy");
		
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println();
		
		
		iHello2.sayHello("wch");
		System.out.println("---------------------------------------------------");
		iHello2.sayGoodBye("wch");
	}
}

interface IHello {
	
	public void sayHello(String name);
	public void sayGoodBye(String name);
}

class Hello implements IHello {

	private String name;
	
	public Hello(){
		
	}
	
	public Hello(String name) {
		this.name = name;
	}
	
	
	@Override
	public void sayHello(String name) {
		System.out.println("Hello, " + name + "!");
	}

	@Override
	public void sayGoodBye(String name) {
		System.out.println(name + " goodbye!");
	}
}

class NewHello implements IHello{

	@Override
	public void sayHello(String name) {

		System.out.println(name + " is coming!");
		
	}

	@Override
	public void sayGoodBye(String name) {
		System.out.println(name + " is leaving!");
		
	}
	
}


interface ILogger {
	public void start(Method method);
	
	public void end(Method method);
	
}

class LoggerOperation implements ILogger {

	public LoggerOperation() {
		
	}
	
	@Override
	public void start(Method method) {

		System.out.println("Method " + method.getName() + " starts at " + new Date());
		
	}

	@Override
	public void end(Method method) {
		System.out.println("Method " + method.getName() + " ends at " + new Date());
		
	}
}

class HelloProxyHandler implements InvocationHandler {

	private Object proxy;
	
	private Object delegate;
	
	public Object bind (Object delegate, Object proxy) {
		this.delegate = delegate;
		this.proxy = proxy;
		
		return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(), this.delegate.getClass().getInterfaces(), this);
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object result = null;
		try {
			beforeInvocaction(this.proxy, method);
			
			result = method.invoke(delegate, args);
			
			afterInvocation(this.proxy, method);
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
			return result;
		}
		
	}
	
	public void beforeInvocaction(Object proxy, Method method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Method startMethod = proxy.getClass().getDeclaredMethod("start", new Class[]{Method.class});
		
		startMethod.invoke(proxy, new Object[]{method});
		
	}
	
	public void afterInvocation(Object proxy, Method method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method endMethod = proxy.getClass().getDeclaredMethod("end", new Class[]{Method.class});
		
		endMethod.invoke(proxy, new Object[]{method});
	}
	
}

