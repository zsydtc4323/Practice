import java.util.ArrayList;
import java.util.List;




public class CommandPatternTest {
	
	public static void main(String[] args) {
		
		Player Liky = new Player();
		
		AudioPlayer audioPlayer = new AudioPlayer();
		
		PlayCommand playCommand = new PlayCommand(audioPlayer);
		RewindCommand rewindCommand = new RewindCommand(audioPlayer);
		StopCommand stopCommand = new StopCommand(audioPlayer);
		
		Liky.action(playCommand);
		Liky.action(rewindCommand);
		Liky.action(stopCommand);
		
		Liky.setPlayCommand(playCommand);
		Liky.setRewindCommand(rewindCommand);
		Liky.setStopCommand(stopCommand);
		
		
		Liky.play();
		Liky.rewind();
		Liky.stop();
		
		
		CommandCollection commandCollection = new CommandCollection();
		
		commandCollection.add(playCommand);
		commandCollection.add(rewindCommand);
		commandCollection.add(stopCommand);
		
		commandCollection.execute();
	}

}


interface Command {
	
	public void execute();
}

class AudioPlayer {
	
	public void play(){
		System.out.println("Playing....");
	}
	
	public void rewind(){
		System.out.println("Rewinding....");
		
	}
	
	public void stop(){
		System.out.println("Stoping....");
		
	}
	
}

interface MacroCommand extends Command {
	
	public void add(Command command);
	public void remove(Command command);
	
}

class CommandCollection implements MacroCommand {
	
	private List<Command> commandList = new ArrayList<Command>();

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		for (Command command : commandList) {
			command.execute();
		}
		
	}

	@Override
	public void add(Command command) {
		// TODO Auto-generated method stub
		commandList.add(command);
		
	}

	@Override
	public void remove(Command command) {
		// TODO Auto-generated method stub
		commandList.remove(command);
		
	}
	
	
}

class PlayCommand implements Command {

	private AudioPlayer audioPlayer;
	
	public PlayCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		audioPlayer.play();
		
	}
}

class RewindCommand implements Command {

	private AudioPlayer audioPlayer;
	
	public RewindCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		audioPlayer.rewind();		
	}
}

class StopCommand implements Command {

	private AudioPlayer audioPlayer;
	
	public StopCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		audioPlayer.stop();
		
	}
}


class Player{
	
	private PlayCommand playCommand;
	private RewindCommand rewindCommand;
	private StopCommand stopCommand;
	
	
	public void action(Command command){
		if (command instanceof PlayCommand) {
			this.playCommand = (PlayCommand)command;
			play();
		}
		else if (command instanceof RewindCommand){
			this.rewindCommand = (RewindCommand)command;
			rewind();
					
		}
		else {
			this.stopCommand = (StopCommand)command;
			stop();
		}
	}
	
	public void play(){
		playCommand.execute();
	}
	public void rewind(){
		rewindCommand.execute();
	}
	public void stop(){
		stopCommand.execute();
	}
	
	public PlayCommand getPlayCommand() {
		return playCommand;
	}
	public void setPlayCommand(PlayCommand playCommand) {
		this.playCommand = playCommand;
	}
	public RewindCommand getRewindCommand() {
		return rewindCommand;
	}
	public void setRewindCommand(RewindCommand rewindCommand) {
		this.rewindCommand = rewindCommand;
	}
	public StopCommand getStopCommand() {
		return stopCommand;
	}
	public void setStopCommand(StopCommand stopCommand) {
		this.stopCommand = stopCommand;
	}
	
	
	
}
