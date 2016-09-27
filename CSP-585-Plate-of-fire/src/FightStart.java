
public class FightStart implements FightStateInterface{
	@Override
	public void  doAction(FightContext context) {
		System.out.println("********Start the Game********");
		context.setState(this);
	}

}
