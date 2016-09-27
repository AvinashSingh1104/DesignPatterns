
public class PowerAbstract implements Power{

	protected Character character;

	public PowerAbstract(Character character) {
		this.character = character;
	}

	@Override
	public void getPower() {
		System.out.println(character.name+" has Power");
	}
}
