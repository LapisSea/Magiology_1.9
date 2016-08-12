package com.magiology.handlers.particle;

import java.util.List;

import com.magiology.util.objs.ColorF;
import com.magiology.util.objs.Vec2i;
import com.magiology.util.objs.Vec3M;
import com.magiology.util.statics.OpenGLM;
import com.magiology.util.statics.UtilC;
import com.magiology.util.statics.math.MathUtil;
import com.magiology.util.statics.math.PartialTicksUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class IParticle{
	
	
	public IParticle(Vec3M pos){
		this(pos,new Vec3M());
	}
	
	public IParticle(Vec3M pos,Vec3M speed){
		setPosTo(pos);
		setSpeed(speed);
		setBoundingBoxFromPos();
	}
	
	public abstract boolean isDead();
	
	public abstract void kill();
	
	public abstract void onDeath();
	
	public abstract Vec3M getPos();
	
	public abstract Vec3M getPrevPos();
	
	public abstract Vec3M getSpeed();
	
	public abstract void setPos(Vec3M pos);
	
	public abstract void setPosX(double x);
	
	public abstract void setPosY(double y);
	
	public abstract void setPosZ(double z);
	
	public abstract void setPrevPos(Vec3M prevPos);
	
	public abstract void setSpeed(Vec3M speed);
	
	public abstract ColorF getColor();
	
	public abstract void setColor(ColorF color);
	
	public abstract boolean isCollided();
	
	public abstract void setCollided(boolean isColided);
	
	public abstract boolean noClip();
	
	public abstract void setNoClip(boolean noClip);
	
	public abstract float getSize();
	
	public abstract void setSize(float size);
	
	public abstract float getPrevSize();
	
	public abstract void setPrevSize(float prevSize);
	
	public abstract void onCollided(Vec3i direction);
	
	public abstract AxisAlignedBB getBoundingBox();
	
	public abstract void setBoundingBox(AxisAlignedBB box);
	
	public abstract int getParticleAge();
	
	public abstract void setParticleAge(int age);
	
	public abstract void addParticleAge(int ageAdd);
	
	public abstract void addParticleAge();
	
	protected World getWorld(){
		return UtilC.getTheWorld();
	}
	
	public abstract void update();
	
	public void moveParticle(double xSpeed, double ySpeed, double zSpeed){
		moveParticle(new Vec3M(xSpeed,ySpeed,zSpeed));
	}
	
	public void moveParticle(Vec3M speed){
		if(noClip()){
			setBoundingBox(getBoundingBox().offset(speed.x,speed.y,speed.z));
			setPosFromBoundingBox();
			return;
		}
		Vec3M originalSpeed=speed.copy();
		List<AxisAlignedBB> boundingBoxes=getWorld().getCollisionBoxes(getBoundingBox().addCoord(speed.x,speed.y,speed.z));
		boundingBoxes.forEach(box->speed.x=box.calculateXOffset(getBoundingBox(),speed.x));
		setBoundingBox(getBoundingBox().offset(speed.x,0.0D,0.0D));
		boundingBoxes.forEach(box->speed.y=box.calculateYOffset(getBoundingBox(),speed.y));
		setBoundingBox(getBoundingBox().offset(0.0D,speed.y,0.0D));
		boundingBoxes.forEach(box->speed.z=box.calculateZOffset(getBoundingBox(),speed.z));
		setBoundingBox(getBoundingBox().offset(0.0D,0.0D,speed.z));
		setPosFromBoundingBox();
		final boolean xColided=originalSpeed.x!=speed.x,yColided=originalSpeed.y!=speed.y,zColided=originalSpeed.z!=speed.z;
		setCollided(xColided||yColided||zColided);
		if(isCollided()){
			int x=0,y=0,z=0;
			if(xColided&&getSpeed().x!=0)x=MathUtil.getNumPrefix(getSpeed().x);
			if(yColided&&getSpeed().y!=0)y=MathUtil.getNumPrefix(getSpeed().y);
			if(zColided&&getSpeed().z!=0)z=MathUtil.getNumPrefix(getSpeed().z);
			onCollided(new Vec3i(x,y,z));
		}
	}
	
	protected void setPosFromBoundingBox(){
		AxisAlignedBB box=getBoundingBox();
		setPos(new Vec3M((box.minX+box.maxX)/2,(box.minY+box.maxY)/2,(box.minZ+box.maxZ)/2));
	}
	
	public void setBoundingBoxFromPos(){
		Vec3M pos=getPos();
		float size=getSize()/2.0F;
		setBoundingBox(new AxisAlignedBB(pos.x-size,pos.y-size,pos.z-size,pos.x+size,pos.y+size,pos.z+size));
	}
	
	public void setPosTo(Vec3M pos){
		setPrevPos(pos);
		setPos(pos);
	}
	
	public void setSizeTo(float size){
		setSize(size);
		setPrevSize(size);
	}
	
	public void updatePrev(){
		setPrevPos(getPos());
		setPrevSize(getSize());
	}
	
	//REDERING
	public abstract Vec2i getLightPos(Vec3M pos);
	
	public abstract void renderModel(float xRotation, float zRotation, float yzRotation, float xyRotation, float xzRotation);
	
	public abstract void setUpOpenGl();

	public abstract int[] getModelIds();
	public abstract int getModelId();
	
	@Override
	public String toString(){
		return getClass().getSimpleName();
	}
	
	public void pushOutOfBlocks(){
		float growth=getSize()-getPrevSize();
		if(growth<=0)return;
		growth/=2;
		
		//get world intersection
		List<AxisAlignedBB> boundingBoxes=getWorld().getCollisionBoxes(getBoundingBox());
		//exit if nothing to process
		if(boundingBoxes.isEmpty())return;
		
		Vec3M pos=getPos();
		
		//pos = position of the entity and center position of bounding box 
		
		AxisAlignedBB bb=getBoundingBox();
		
		//how much the bounding box has grown on a side
		
		//total movement for bounding box to exit any colliding boxes
		Vec3M push=new Vec3M();
		
		if(boundingBoxes.size()==1){
			
			AxisAlignedBB box=boundingBoxes.get(0);
			
			if((box.minX+box.maxX)/2<pos.x)push.x=box.maxX-bb.minX+growth;
			else push.x=box.maxX-bb.minX-growth;
			
			if((box.minY+box.maxY)/2<pos.y)push.y=box.maxY-bb.minY+growth;
			else push.y=box.minY-bb.maxY-growth;
			
			if((box.minZ+box.maxZ)/2<pos.z)push.z=box.maxZ-bb.minZ+growth;
			else push.z=box.minZ-bb.maxZ-growth;
			
		}else for(AxisAlignedBB box:boundingBoxes){
			
			if((box.minX+box.maxX)/2<pos.x)push.x=Math.max(push.x, box.maxX-bb.minX+growth);
			else push.x=Math.min(push.x, box.maxX-bb.minX-growth);
			
			if((box.minY+box.maxY)/2<pos.y)push.y=Math.max(push.y, box.maxY-bb.minY+growth);
			else push.y=Math.min(push.y, box.minY-bb.maxY-growth);
			
			if((box.minZ+box.maxZ)/2<pos.z)push.z=Math.max(push.z, box.maxZ-bb.minZ+growth);
			else push.z=Math.min(push.z, box.minZ-bb.maxZ-growth);
		}
		
		//absolute vector of push used to determine that plane should be used to minimize distance pushed
		Vec3M absPush=push.abs();
		
		if(absPush.x<absPush.y&&absPush.x<absPush.z){
			setPosX(pos.x+push.x*1.01);
			return;
		}
		if(absPush.y<absPush.x&&absPush.y<absPush.z){
			setPosY(pos.y+push.y*1.01);
			return;
		}
		if(absPush.z<absPush.x&&absPush.z<absPush.x){
			setPosZ(pos.z+push.z*1.01);
		}
	}

	protected void transformSimpleParticleColored(){
		transformSimpleParticle();
		OpenGLM.color(getColor());
	}
	protected void transformSimpleParticle(){
		EntityPlayer player=UtilC.getThePlayer();
		
		OpenGLM.translate(PartialTicksUtil.calculate(this));
		OpenGLM.rotate(-player.rotationYaw+90, 0, 1, 0);
		OpenGLM.rotate( player.rotationPitch,  0, 0, 1);
		OpenGLM.scale(PartialTicksUtil.calculate(getPrevSize(), getSize()));
	}
}
