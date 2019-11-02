package Engine;


public class PhysicsComponent extends Component{
	
	public int state = 0;
	
	public float dragLevel =.05f;
	
	float ForceX    = 0;
	float ForceY    = 500;
	
	float VelocityX = 0;
	float VelocityY = 0;
	
	public PhysicsComponent(GameObject go) {
		super(go);
	}
	
	@Override
	public void logic() {
		
		this.state = 1;
		
		this.VelocityX += this.ForceX/FlagMain.runSpeed;
		this.VelocityY += this.ForceY/FlagMain.runSpeed;
		Position position = parent.position;
		
		/*
		if(position.myPosition.getTranslateY() >= 300 && this.VelocityY > 0 ) {
			this.VelocityY = 0;
		}*/

		this.VelocityX = VelocityX*(1 - this.dragLevel);
		this.VelocityY = VelocityY*(1 - this.dragLevel);
		
		float XDisposition = VelocityX/FlagMain.runSpeed ;
		float YDisposition = VelocityY/FlagMain.runSpeed;
		
		CollisionComponent myCollider = (CollisionComponent)this.parent.getComponent(CollisionComponent.class);
		
		double myX = position.getSX();
		double myY = position.getY();
		
		boolean standingOn;
		
		for(int i = 0; myCollider != null &&  i < myCollider.collisions.size();i++) {
			
			CollisionComponent otherCol = myCollider.collisions.get(i);
			standingOn =false;
			double otherX = otherCol.parent.position.getX();
			double otherY = otherCol.parent.position.getY();
			
			if (YDisposition < 0 && 
					myY > otherY) {
					state *= 2;
					standingOn =true;
			}
			
			if (YDisposition > 0 &&
					myY < otherY) {
					state *= 2;
					standingOn = true;
			}

			if(!standingOn) {
				if (XDisposition > 0 &&
					myX < otherX) {
					state *= 5;
				}
				if (XDisposition < 0 &&
				    myX > otherX) {
					state *= 5;
				}
			}
			
		}
		if(state % 2 == 0 ) {
			YDisposition = 0;
			VelocityY = 0;
		}
		if(state % 5 == 0 ) {
			XDisposition = 0;
			VelocityX = 0;
		}
		parent.position.Translate(XDisposition, YDisposition);
		
		//myPosition.translate(XDisposition,YDisposition);
	}
	
}
