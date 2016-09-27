
public abstract class Remote {
	
	protected Car car;
	
	protected Remote(Car car)
	{
		this.car=car;
	}
	
	public abstract void run();

}
