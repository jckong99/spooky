	package Engine;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Game.*;

public class Main {

	public static FlagMain grid;
	public static int size = 300;
	public static InputHandler IH;
	public static ArrayList<GameObject> elements = new ArrayList<GameObject>();
	public static int fraimes = 0;
	
	
	public static void main(String[] args) throws InterruptedException {
		
		grid  = new FlagMain(size, 2, 3, "Solar-System", "Assets//");
		
		
		
		grid.start();
		
		
	}
	
	
}
