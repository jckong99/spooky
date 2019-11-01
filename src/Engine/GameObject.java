package Engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
public class GameObject {


	public String name;
	public int posX,
		   	   posY;
	public Position position;
	public static int Max = 0 ,
					  Min = 0;
	
	//public ArrayList<GameObject> children = new ArrayList<GameObject>();
	public ArrayList<Component> logics = new ArrayList<Component>();
	public ArrayList<Component> graphics = new ArrayList<Component>();
	
	ArrayList<GameObject> Children = new ArrayList();
	GameObject Parent;
	
	public GameObject(GameObject Parent) {
		this.Parent = Parent;
		if(Parent == null)
			Main.grid.elements.add(this);
		else
			Parent.Children.add(this);
		
		position = new Position(this);
	}
	
	public void logic(int P) {
		
		for(GameObject i : Children) {
			i.logic(P);
		}
		
		for (Component i : logics) {
			if(i.Priority == P)
				i.logic();
		}
	}
	
	public void graphic(int P,Graphics2D G) {
		for(GameObject i : Children) {
			i.graphic(P,G);
		}
		
		for (Component i : graphics) {
			if(i.Priority == P)
				i.graphics(G);
		}
		
	}
	
	public Component getComponent(Class C) {
		for(Component i : logics) {
			if(i.getClass().equals(C))
				return i;
			
		}
		for(Component i : graphics) {
			if(i.getClass().equals(C))
				return i;
			
		}
		return null;
	}
	
	
	public boolean removeComponent(Class C) {
		
		for(Component i : logics) {
			if(i.getClass().equals(C)){
				logics.remove(i);
				return true;
			}
		}
		for(Component i : graphics) {
			if(i.getClass().equals(C)) {
				logics.remove(i);
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public boolean addLogicComponent(Component comp) {
		boolean duplicate =true;
		
		Class C = comp.getClass();
		
		for(Component i : logics) {
			if(i.getClass().equals(C)){
				duplicate = false;
			}
		}
		
		if(duplicate) {
			logics.add(comp);
			if(Min > comp.Priority)
				Min = comp.Priority;
			if(Max < comp.Priority)
				Max = comp.Priority;
		}
		
		return duplicate;
	}
	
	public boolean addGraphicsComponent(Component comp) {
		boolean duplicate =true;
		
		Class C = comp.getClass();
		
		for(Component i : graphics) {
			if(i.getClass().equals(C)){
				duplicate = false;
			}
		}
		
		if(duplicate) {
			graphics.add(comp);
			if(Min > comp.Priority)
				Min = comp.Priority;
			if(Max < comp.Priority)
				Max = comp.Priority;
		}
		return duplicate;
	}
	
}
