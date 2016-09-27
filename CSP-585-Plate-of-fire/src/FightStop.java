
public class FightStop implements FightStateInterface{

	@Override
	public void  doAction(FightContext context){
		System.out.println("********Sopping the Game********");
		context.setState(this);
	}
	
}
