package Game;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Engine.*;

public class Main {

	public static FlagMain grid;
	public static int size = 300;
	public static InputHandler IH;
	public static ArrayList<GameObject> elements = new ArrayList<GameObject>();
	public static int fraimes = 0;
	
	
	public static void main(String[] args) throws InterruptedException {
		
		grid  = new FlagMain(size, 2, 3, "Spooky", "Assets//");
		
		
		GameObject testPlayer = new GameObject(null);
		Component controller = new PlayerController(testPlayer, "Cat");
		testPlayer.addGraphicsComponent(controller);
		testPlayer.addLogicComponent(controller);
		
		testPlayer.addLogicComponent(new CollisionComponent(testPlayer,64,64));
		//testPlayer.addLogicComponent(new PhysicsComponent(testPlayer));
		
		GameObject ground;
		Component tileComp;
		for(int i = 0; i < 1 ; i++) {
			ground = new GameObject(null);

			tileComp = new TileComponent(ground,i*32,0);
			ground.addGraphicsComponent(tileComp);
			
			ground.addLogicComponent(new CollisionComponent(testPlayer,64,64));
			ground.addLogicComponent(new PhysicsComponent(testPlayer));
			
		}
				
		
		//FlagMain.Camera = testPlayer.position;
		
		
		grid.start();
		
		
	}
	
	
}
