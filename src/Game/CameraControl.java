package Game;

import java.awt.event.KeyEvent;

import Engine.Component;
import Engine.FlagMain;
import Engine.GameObject;
import Engine.InputHandler;
import Engine.Main;

public class CameraControl extends Component{

	public CameraControl(GameObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}
	
	double Scale = 1;
	
	@Override 
	public void logic() {
		if(InputHandler.pending.contains(KeyEvent.VK_W)) 
			super.parent.position.Translate(0, -3/Scale);
		if(InputHandler.pending.contains(KeyEvent.VK_S)) 
			super.parent.position.Translate(0, 3/Scale);
		if(InputHandler.pending.contains(KeyEvent.VK_D)) 
			super.parent.position.Translate(3/Scale, 0);
		if(InputHandler.pending.contains(KeyEvent.VK_A)) 
			super.parent.position.Translate(-3/Scale,0);
		if(InputHandler.pending.contains(KeyEvent.VK_Q)) 
			super.parent.position.rotate(.02);
		if(InputHandler.pending.contains(KeyEvent.VK_E)) 
			super.parent.position.rotate(-.02);
		if(InputHandler.pending.contains(KeyEvent.VK_X)) {
			super.parent.position.Scale(0.99, 0.99);
			Scale *= 1.01;
		}
		if(InputHandler.pending.contains(KeyEvent.VK_Z)) {
			super.parent.position.Scale(1.01, 1.01);
			Scale *= .99;
		}
	}
	
	
}
