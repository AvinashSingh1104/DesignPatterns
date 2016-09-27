
public class Main {
	
	public static void main(String[] args)
	{
		Remote toyota=new AlarmRemote(new Toyota());
		Remote ford=new AlarmRemote(new Ford());
		Remote gm=new AlarmRemote(new GM());
		
		toyota.run();
		ford.run();
		gm.run();
		
		System.out.println("\n\n");
		
		Remote toyota1=new RemoteStarter(new Toyota());
		Remote ford1=new RemoteStarter(new Ford());
		Remote gm1=new RemoteStarter(new GM());
		
		toyota1.run();
		ford1.run();
		gm1.run();
		
		System.out.println("\n\n");
		
		Remote toyota2=new DoorLockRemote(new Toyota());
		Remote ford2=new DoorLockRemote(new Ford());
		Remote gm2=new DoorLockRemote(new GM());
		
		toyota2.run();
		ford2.run();
		gm2.run();
	}

}
