public class FactoryModeTest {

	
	public static void main(String[] args) {
		
		
	}
}

class concreteFactory1 implements AbstractFactoryTest {

	@Override
	public IPhone createIPhone() {
		// TODO Auto-generated method stub
		return new IphoneProduct().createIPhone("6s", "ios");
	}

	@Override
	public AndroidPhone createAndroidPhone() {
		// TODO Auto-generated method stub
		return new androidProduct().createAndroidPhone("samsung", "android os");
	}

	@Override
	public MiPhone createMiPhone() {
		// TODO Auto-generated method stub
		return new miPhoneProduct().createHMiPhone("hong mi", "mios");
	}
	
	
}

class concreteFactory2 implements AbstractFactoryTest {

	@Override
	public IPhone createIPhone() {
		// TODO Auto-generated method stub
		return new IphoneProduct().createIPhonePlus("6s plus", "ios");
	}

	@Override
	public AndroidPhone createAndroidPhone() {
		// TODO Auto-generated method stub
		return new androidProduct().createAndroidSmartPhone("samsung galaxy", "android os");
	}

	@Override
	public MiPhone createMiPhone() {
		// TODO Auto-generated method stub
		return new miPhoneProduct().createMiPhone("xiao mi", "mios");
	}
	
}

class IphoneProduct extends Phone implements IPhone {
	
	public String name;
	public String system;
	
	public IphoneProduct(){}
	
	public IphoneProduct(String name, String system){
		super(name,system);
		this.name = name;
		this.system = system;
	}

	@Override
	public IphoneProduct createIPhone(String name, String system) {
		IphoneProduct phone = new IphoneProduct(name, system);
		return phone;
	}

	@Override
	public IphoneProduct createIPhonePlus(String name, String system) {
		IphoneProduct phone = new IphoneProduct(name, system);
		return phone;
	}
	
}

class androidProduct extends Phone implements AndroidPhone {
	public String name;
	public String system;
	
	public androidProduct(){}
	
	public androidProduct(String name, String system) {
		super(name,system);
		this.name = name;
		this.system = system;
	}

	@Override
	public androidProduct createAndroidPhone(String name, String system) {
		// TODO Auto-generated method stub
		return new androidProduct(name, system);
	}

	@Override
	public androidProduct createAndroidSmartPhone(String name, String system) {
		// TODO Auto-generated method stub
		return new androidProduct(name, system);
	}
	
}

class miPhoneProduct extends Phone implements MiPhone {
	
	public String name;
	public String system;
	
	public miPhoneProduct(){}
	
	public miPhoneProduct(String name, String system) {
		super(name,system);
		this.name = name;
		this.system = system;
	}

	@Override
	public miPhoneProduct createMiPhone(String name, String system) {
		// TODO Auto-generated method stub
		return new miPhoneProduct(name,system);
	}

	@Override
	public miPhoneProduct createHMiPhone(String name, String system) {
		// TODO Auto-generated method stub
		return new miPhoneProduct(name,system);
	}
	
}
