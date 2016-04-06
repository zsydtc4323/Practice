
public class CallBackTest {

	public static void  main(String[] args) {
		
		Questioner questioner = new Questioner("Shuyuan");
		Solver solver = new Solver("Liky");
		
		questioner.setsolver(solver);
		
		questioner.ask("1+1=?");
		
		
	}
	
	
}



interface CallBack {
	
	public void call(String result);
}


class Questioner implements CallBack {
	
	private String name;
	private Solver solver;
	
	public Questioner(String name) {
		this.name = name;
	}
	
	public void ask(String question){
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					solver.solveProblem(Questioner.this, question);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();;
		
		play();
	}
	
	public void play(){
		
		System.out.println("I'm going to play basketball!");
	}

	@Override
	public void call(String result) {
		// TODO Auto-generated method stub
		System.out.println("The answer is: " + result);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Solver getsolver() {
		return solver;
	}

	public void setsolver(Solver solver) {
		this.solver = solver;
	}
	
	
}

class Solver {
	
	private String name;
	
	public Solver(String name) {
		
		this.name = name;
	}
	
	public void solveProblem(CallBack callBack, String question) throws InterruptedException {
		
		int answer = 0;
		for (int i=0; i<1000000; i++) {
			
			answer +=i;
		}
		
		Thread.sleep(5000);
		callBack.call(String.valueOf(answer));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}