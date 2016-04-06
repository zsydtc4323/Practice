import java.util.HashMap;
import java.util.Map;



public class StatePatternTest {
	
	
	public static void main(String[] args) {
		
		VoteManager voteManager = new VoteManager();
		
		voteManager.vote("zsy", "one piece");
		voteManager.vote("zsy", "naruto");
		voteManager.vote("zsy", "naruto");
		voteManager.vote("zsy", "naruto");
		voteManager.vote("zsy", "naruto");
		voteManager.vote("zsy", "naruto");
		voteManager.vote("zsy", "naruto");
		voteManager.vote("zsy", "naruto");
		voteManager.vote("zsy", "naruto");
		
	}

	
}

interface VoteState {
	
	public void vote(String userName, String voteItem, VoteManager voteManager);
}


class NormalState implements VoteState {

	@Override
	public void vote(String userName, String voteItem, VoteManager voteManager) {
		// TODO Auto-generated method stub
		voteManager.getVoteMap().put(userName, voteItem);
		System.out.println("Vote Successfully!");
		
	}
	
	
}

class RepeatState implements VoteState {

	@Override
	public void vote(String userName, String voteItem, VoteManager voteManager) {
		// TODO Auto-generated method stub
		
		String user = voteManager.getVoteMap().get(userName);
		
		if (user != null) {
			voteManager.getVoteMap().remove(userName);
		}
		
		System.out.println("user: " +userName + " is repeated!");
		
	}
	
}

class SpiteState implements VoteState {

	@Override
	public void vote(String userName, String voteItem, VoteManager voteManager) {
		// TODO Auto-generated method stub
		System.out.println("user: " + userName + " must stop voting spitely!");
		
	}
	
}

class BlackState implements VoteState {

	@Override
	public void vote(String userName, String voteItem, VoteManager voteManager) {
		// TODO Auto-generated method stub
		System.out.println(userName + "is in Black list!");
		
	}
	
}

class VoteManager {
	
	private VoteState voteState = null;
	private Map<String, String> voteMap = new HashMap<String,String>();
	private Map<String, Integer> voteCountMap = new HashMap<String, Integer>();
	
	
	public void vote(String userName, String voteItem){
		
		Integer voteCount = voteCountMap.get(userName);
		
		if (voteCount == null) {
			voteCount = 0;
		}
		voteCount +=1;
		
		voteCountMap.put(userName, voteCount);
		
		if (voteCount == 1) {
			voteState = new NormalState();
		}
		else if (voteCount >1 && voteCount <5) {
			voteState = new RepeatState();
		}
		else if (voteCount >=5 && voteCount <8){
			voteState = new SpiteState();
		}
		else if (voteCount >= 8) {
			voteState = new BlackState();
			
		}
		
		voteState.vote(userName, voteItem, this);
	}
	
	
	public VoteState getVoteState() {
		return voteState;
	}
	public void setVoteState(VoteState voteState) {
		this.voteState = voteState;
	}
	public Map<String, String> getVoteMap() {
		return voteMap;
	}
	public void setVoteMap(Map<String, String> voteMap) {
		this.voteMap = voteMap;
	}
	public Map<String, Integer> getVoteCountMap() {
		return voteCountMap;
	}
	public void setVoteCountMap(Map<String, Integer> voteCountMap) {
		this.voteCountMap = voteCountMap;
	}
	
	
	
}
