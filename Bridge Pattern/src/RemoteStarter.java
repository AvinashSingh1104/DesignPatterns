
public class RemoteStarter extends Remote {

	public RemoteStarter(Car car) {
	      super(car);
	     
	   }

	public void run()
	{
		car.remote();
	}
}
