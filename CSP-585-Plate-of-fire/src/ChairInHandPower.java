
public class ChairInHandPower extends PowerAbstract{

	public ChairInHandPower(Character character)  {
		super(character);
	}

	@Override
	public void getPower() {
		System.out.println(character.name+" has Power to Hit by Chair");
	}
}
