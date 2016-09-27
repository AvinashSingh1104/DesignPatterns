import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class PlateOfFire {
	
	private static CharacterFactory objCharacterFactory;
	
	static ArrayList<Character> lstcharacter = new ArrayList<Character>();
	private static final String[] characters = { "JohnCena", "Kane", "BigShow" ,"UnderTaker"};
	
	public static void main(String[] args) throws IOException {
		System.out.println("*********** THE PLATE OF FIRE ***********");
		System.out.println();
		getobjCharacterFactoryInstance();
		
		System.out.println("Introducing the Characters");
		//FlyWeight Implementation
		for(String character : characters){
			lstcharacter.add(objCharacterFactory.getCharacter(character));
			objCharacterFactory.getCharacter(character).draw();
		}
		
		//Get 2 random Character.
		Character character1 = getRandomCharacter();
		Character character2 = getRandomCharacter();
		
		System.out.println();
		//Logic to Do Fight
		if(character1.onGamelevel == character2.onGamelevel){
			System.out.println("Fight can be done as both at same level.");
			System.out.println("First Character : "+character1.name);
			System.out.println("second Character : "+character2.name);
			System.out.println();
			
			//State Design Pattern
			FightContext context = new FightContext();
			FightStart startState = new FightStart();
			startState.doAction(context);
			
			System.out.println();
			//Decorator DP
			SpecialMove objSpecialMove = new SpecialMove(character1);
			ChairInHandPower objChairInHandPower = new ChairInHandPower(character2);
			objSpecialMove.getPower();
			objChairInHandPower.getPower();
			
			System.out.println();
			//Strategy
			StrategyContext runandPunchFight = new StrategyContext(new StrategyRunandPunch());
			runandPunchFight.doOperation(character1);
			
			StrategyContext liftandFight = new StrategyContext(new StrategyLiftAndDrop());
			liftandFight.doOperation(character2);
			System.out.println();
			FightStop stopFight = new FightStop();
			stopFight.doAction(context);
			
		}else{
			System.out.println("Fight can not be done as both are not at same level.");
		}
	
	}
	
	public static synchronized CharacterFactory getobjCharacterFactoryInstance() throws IOException {
		if (objCharacterFactory == null) {
			objCharacterFactory = new CharacterFactory();
		}
		return objCharacterFactory;
	}

	//logic to get a random character.
	public static Character getRandomCharacter(){
		Random objrandom = new Random();
		Character character = lstcharacter.get(objrandom.nextInt(4));
		return character;
	}
}
