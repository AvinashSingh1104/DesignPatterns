
public class StrategyContext {
	private StrategyInterface objStrategyInterface;

	public StrategyContext(StrategyInterface objStrategyInterface) {
		this.objStrategyInterface = objStrategyInterface;
	}

	public void doOperation(Character character) {
		objStrategyInterface.doOperation(character);;
	}
}
