package Engine;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.io.EOFException;
import java.util.ArrayList;

public class Position {
	
	GameObject Parent;
	
	//When choosing the sprite the gameObject updates the Size
	public double SizeX = 100, SizeY = 100;
	
	AffineTransform Rot   = new AffineTransform(),
				    Scale = new AffineTransform(),
				    Trans = new AffineTransform(),
				    ToCenter, FromCenter;	
	
	static AffineTransform SCToCenter, SCFromCenter;
	
	public Position(GameObject object) {
		Parent = object;
		Rot.setToIdentity();
		Scale.setToIdentity();
		Trans.setToIdentity();
		ToCenter = AffineTransform.getTranslateInstance(SizeX*Scale.getScaleX()/2 ,SizeY*Scale.getScaleY()/2);
		FromCenter = AffineTransform.getTranslateInstance( -1 * SizeX*Scale.getScaleX()/2 , - 1 * SizeY*Scale.getScaleY()/2);
	}
	
	public void setSize (double x, double y) {
		SizeX = x;
		SizeY = y;
		ToCenter = AffineTransform.getTranslateInstance(SizeX*Scale.getScaleX()/2 ,SizeY*Scale.getScaleY()/2);
		FromCenter = AffineTransform.getTranslateInstance( -1 * SizeX*Scale.getScaleX()/2 , - 1 * SizeY*Scale.getScaleY()/2);
	}
	
	public void SetPosition(double dx, double dy) {
		Trans = AffineTransform.getTranslateInstance(dx, dy);
	}
	
	public void SetRotation(Double R) {
		Rot = AffineTransform.getRotateInstance(R,SizeX*Scale.getScaleX()/2 ,SizeY*Scale.getScaleY()/2 );
	}
	
	public void SetScale(double Sx, double Sy) {
		Scale = AffineTransform.getScaleInstance(Sx, Sy);
		ToCenter = AffineTransform.getTranslateInstance(SizeX*Scale.getScaleX()/2 ,SizeY*Scale.getScaleY()/2);
		FromCenter = AffineTransform.getTranslateInstance( -1 * SizeX*Scale.getScaleX()/2 , - 1 * SizeY*Scale.getScaleY()/2);
	}
	
	public void Translate(double dx, double dy) {
		Trans.concatenate(AffineTransform.getTranslateInstance(dx, dy));
	}
	
	public void rotate(double R) {		
		Rot.concatenate(AffineTransform.getRotateInstance(R));
	}
	
	public void Scale(double dx, double dy) {
		Scale.concatenate(AffineTransform.getScaleInstance(dx,dy));	
		ToCenter = AffineTransform.getTranslateInstance(SizeX*Scale.getScaleX()/2 ,SizeY*Scale.getScaleY()/2);
		FromCenter = AffineTransform.getTranslateInstance( -1 * SizeX*Scale.getScaleX()/2 , - 1 * SizeY*Scale.getScaleY()/2);
	}
	
	
	
	public AffineTransform GetAffine() {
		AffineTransform result = new AffineTransform();
		result.setToIdentity();
		
		if(Parent.Parent != null) {
			result.concatenate(Parent.Parent.position.GetAffineCenter());
			
		}else if(Main.grid.Camera != null) {
			result.concatenate(Main.grid.Camera.GetAffineCamera());
		}
		
		result.translate( -1 * SizeX*Scale.getScaleX()/2 ,  -1 * SizeY*Scale.getScaleY()/2);
		
		result.concatenate(Trans);
		
		result.concatenate(ToCenter);
		result.concatenate(Rot);
		result.concatenate(FromCenter);
		
		result.concatenate(Scale);
		
		
		return result;
	}
	
	public AffineTransform GetAffineCenter() {
		
		AffineTransform result = new AffineTransform();
		result.setToIdentity();
		
		if(Parent.Parent != null) {
			result.concatenate(Parent.Parent.position.GetAffineCenter());	
		}else if(Main.grid.Camera != null){
			result.concatenate(Main.grid.Camera.GetAffineCamera());
		}
		
		result.concatenate(Trans);
		
		result.concatenate(Rot);
		
		result.concatenate(Scale);
		
		
		
		return result;
	}
	
	public AffineTransform GetAffineCamera() {
		
		AffineTransform result = new AffineTransform();
		result.setToIdentity();
		
		if(Parent.Parent != null) {
			result.concatenate(Parent.Parent.position.GetAffineCenterCam());	
		}
		
		result.concatenate(Trans);
		
		result.concatenate(Rot);
		result.translate( -1 *Main.grid.Width*Scale.getScaleX()*3/2 ,  -1 *Main.grid.Height*Scale.getScaleY()*3/2);
		result.concatenate(Scale);
		
		
		
		
		try {
			result= result.createInverse();
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			System.out.println("Cam not working");
			e.printStackTrace();
		}
		
		
		
		//System.out.println(result);
		return result;
	}
	
	public AffineTransform GetAffineCenterCam() {
		
		AffineTransform result = new AffineTransform();
		result.setToIdentity();
		
		if(Parent.Parent != null) {
			result.concatenate(Parent.Parent.position.GetAffineCenterCam());	
		}
		
		result.concatenate(Trans);
		
		result.concatenate(Rot);
		
		result.concatenate(Scale);
		
		
		
		return result;
	}
	
	
	public double getX(){
		return GetAffine().getTranslateX();
	}
	
	public double getY(){
		return GetAffine().getTranslateY();
	}
	
	public double getSX(){
		return GetAffine().getScaleX();
	}
	
	public double getSY(){
		return GetAffine().getScaleY();
	}
}
