

public class CharacterFactory implements Cloneable {
	
	JohnCena objJohnCena = null;
	Kane objKane = null;
	BigShow objBigShow = null;
	UnderTaker objUnderTaker = null;
	
	public Character getCharacter(String character) {
		if(character == null){
	         return null;
	      }	
		if (character.equalsIgnoreCase("JohnCena") && null == objJohnCena) {
			return new JohnCena();
		}
		else if (character.equalsIgnoreCase("Kane") && null== objKane) {
			return new Kane();
		}
		else if (character.equalsIgnoreCase("BigShow") && null== objBigShow) {
			return new BigShow();
		}
		else if (character.equalsIgnoreCase("UnderTaker") && null== objUnderTaker) {
			return new UnderTaker();
		}
		return null;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
