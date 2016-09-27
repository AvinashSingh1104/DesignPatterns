
public class DoorLockRemote extends Remote {
	
	public DoorLockRemote(Car car) {
	      super(car);
	     
	   }

	public void run()
	{
		car.lock();
	}

}
