
public class SpecialMove extends PowerAbstract{

	public SpecialMove(Character character) {
		super(character);
	}

	@Override
	public void getPower() {
		System.out.println(character.name+" has Power Special Power.");
	}
}
