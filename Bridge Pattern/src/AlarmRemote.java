
public class AlarmRemote extends Remote {
	
	public AlarmRemote(Car car) {
	      super(car);
	     
	   }

	public void run()
	{
		car.alarm();
	}
}
