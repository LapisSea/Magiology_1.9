package com.magiology.util.objs.vec;

import com.magiology.util.interf.Calculable;
import com.magiology.util.interf.Locateable.LocateableVec3D;
import com.magiology.util.interf.Locateable.LocateableVec3M;
import com.magiology.util.statics.math.MathUtil;
import com.magiology.util.statics.math.MatrixUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.WritableVector3f;

import java.io.Serializable;
import java.util.function.DoubleUnaryOperator;

public class Vec3M extends Vec3MRead implements Serializable, WritableVector3f, Calculable<Vec3M>{
	
	public Vec3M(){
		super();
	}
	
	public Vec3M(double x, double y, double z){
		super(x, y, z);
	}
	
	public Vec3M(Vec3d vec){
		super(vec);
	}
	
	public Vec3M(Vec3i vec){
		super(vec);
	}
	
	public Vec3M(IVec3M vec){
		super(vec);
	}
	
	public Vec3M(LocateableVec3M h){
		super(h.getPos());
	}
	
	public Vec3M(LocateableVec3D h){
		super(h.getPos());
	}
	
	public Vec3M(RayTraceResult h){
		super(h);
	}
	
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}
	
	public void setZ(double z){
		this.z=z;
	}
	
	public Vec3M abs(){
		return new Vec3M(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public Vec3M absSelf(){
		x=Math.abs(x);
		y=Math.abs(y);
		z=Math.abs(z);
		return this;
	}
	
	public Vec3M add(BlockPos pos){
		return new Vec3M(x()+pos.getX(), y()+pos.getY(), z()+pos.getZ());
	}
	
	public Vec3M add(double x, double y, double z){
		return new Vec3M(this.x+x, this.y+y, this.z+z);
	}
	
	public Vec3M add(double var){
		return this.add(var, var, var);
	}
	
	@Override
	public Vec3M add(float var){
		return this.add(var, var, var);
	}
	
	public Vec3M add(Vec3i vec){
		return add(vec.getX(), vec.getY(), vec.getZ());
	}
	
	@Override
	public Vec3M add(Vec3M vec){
		return this.add(vec.x, vec.y, vec.z);
	}
	
	public Vec3M add(IVec3M vec){
		return this.add(vec.x(), vec.y(), vec.z());
	}
	
	public Vec3M addSelf(double var){
		return addSelf(var,var,var);
	}
	public Vec3M addSelf(double x, double y, double z){
		this.x+=x;
		this.y+=y;
		this.z+=z;
		return this;
	}
	
	public Vec3M addSelf(float var){
		x+=var;
		y+=var;
		z+=var;
		return this;
	}
	
	public Vec3M addSelf(Vec3i vec){
		x+=vec.getX();
		y+=vec.getY();
		z+=vec.getZ();
		return this;
	}
	
	public Vec3M addSelf(IVec3M vec){
		x+=vec.x();
		y+=vec.y();
		z+=vec.z();
		return this;
	}
	
	public Vec3M addX(double var){
		return new Vec3M(x+var, y, z);
	}
	
	public Vec3M addY(double var){
		return new Vec3M(x, y+var, z);
	}
	
	public Vec3M addZ(double var){
		return new Vec3M(x, y, z+var);
	}
	
	public Vec3M addSelfX(double var){
		x+=var;
		return this;
	}
	
	public Vec3M addSelfY(double var){
		y+=var;
		return this;
	}
	
	public Vec3M addSelfZ(double var){
		z+=var;
		return this;
	}
	
	public Vec3M mulX(double var){
		return new Vec3M(x*var, y, z);
	}
	
	public Vec3M mulY(double var){
		return new Vec3M(x, y*var, z);
	}
	
	public Vec3M mulZ(double var){
		return new Vec3M(x, y, z*var);
	}
	
	public Vec3M mulSelfX(double var){
		x*=var;
		return this;
	}
	
	public Vec3M mulSelfY(double var){
		y*=var;
		return this;
	}
	
	public Vec3M mulSelfZ(double var){
		z*=var;
		return this;
	}
	
	public Vec3M divX(double var){
		return new Vec3M(x/var, y, z);
	}
	
	public Vec3M divY(double var){
		return new Vec3M(x, y/var, z);
	}
	
	public Vec3M divZ(double var){
		return new Vec3M(x, y, z/var);
	}
	
	public Vec3M divSelfX(double var){
		x/=var;
		return this;
	}
	
	public Vec3M divSelfY(double var){
		y/=var;
		return this;
	}
	
	public Vec3M divSelfZ(double var){
		z/=var;
		return this;
	}
	
	public Vec3M subX(double var){
		return new Vec3M(x-var, y, z);
	}
	
	public Vec3M subY(double var){
		return new Vec3M(x, y-var, z);
	}
	
	public Vec3M subZ(double var){
		return new Vec3M(x, y, z-var);
	}
	
	public Vec3M subSelfX(double var){
		x-=var;
		return this;
	}
	
	public Vec3M subSelfY(double var){
		y-=var;
		return this;
	}
	
	public Vec3M subSelfZ(double var){
		z-=var;
		return this;
	}
	
	public Vec3d conv(){
		return new Vec3d(x(), y(), z());
	}
	
	@Override
	public Vec3M copy(){
		return new Vec3M(x, y, z);
	}
	
	public Vec3M crossProduct(IVec3M vec){
		return new Vec3M(y*vec.z()-z*vec.y(), z*vec.x()-x*vec.z(), x*vec.y()-y*vec.x());
	}
	
	public double distanceTo(double x, double y, double z){
		double d0=x-this.x;
		double d1=y-this.y;
		double d2=z-this.z;
		return MathHelper.sqrt(d0*d0+d1*d1+d2*d2);
	}
	
	public float distanceTo(Vec3d vec){
		double d0=vec.x-x;
		double d1=vec.y-y;
		double d2=vec.z-z;
		return MathHelper.sqrt(d0*d0+d1*d1+d2*d2);
	}
	
	public double distanceTo(Vec3i pos){
		double d0=pos.getX()-x;
		double d1=pos.getY()-y;
		double d2=pos.getZ()-z;
		return MathHelper.sqrt(d0*d0+d1*d1+d2*d2);
	}
	
	public double distanceTo(IVec3M vec){
		double d0=vec.x()-x;
		double d1=vec.y()-y;
		double d2=vec.z()-z;
		return MathHelper.sqrt(d0*d0+d1*d1+d2*d2);
	}
	
	public double distanceTo(ReadableVector3f vec){
		double d0=vec.getX()-x;
		double d1=vec.getY()-y;
		double d2=vec.getZ()-z;
		return MathHelper.sqrt(d0*d0+d1*d1+d2*d2);
	}
	
	@Override
	public Vec3M div(float var){
		return this.div(new Vec3M(var, var, var));
	}
	
	@Override
	public Vec3M div(Vec3M var){
		return new Vec3M(x/var.x, y/var.y, z/var.z);
	}
	
	public Vec3M div(IVec3M var){
		return new Vec3M(x/var.x(), y/var.y(), z/var.z());
	}
	
	public double dotProduct(IVec3M vec){
		return x*vec.x()+y*vec.x()+z*vec.x();
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Vec3M)) return false;
		Vec3M vec=(Vec3M)obj;
		if(vec==this) return true;
		return vec.x==x&&vec.y==y&&vec.z==z;
	}
	
	public Vec3M getIntermediateWithXValue(IVec3M vec, double x){
		double d1=vec.x()-this.x;
		if(d1*d1<1.0000000116860974E-7D) return null;
		double d2=vec.x()-y, d3=vec.x()-z, d4=(x-this.x)/d1;
		return d4>=0.0D&&d4<=1.0D?new Vec3M(this.x+d1*d4, y+d2*d4, z+d3*d4):null;
		
	}
	
	public Vec3M getIntermediateWithYValue(IVec3M vec, double y){
		double d2=vec.x()-this.y;
		if(d2*d2<1.0000000116860974E-7D) return null;
		double d1=vec.x()-x, d3=vec.x()-z, d4=(y-this.y)/d2;
		return d4>=0.0D&&d4<=1.0D?new Vec3M(x+d1*d4, this.y+d2*d4, z+d3*d4):null;
		
	}
	
	public Vec3M getIntermediateWithZValue(Vec3M vec, double z){
		double d3=vec.x-this.z;
		if(d3*d3<1.0000000116860974E-7D) return null;
		double d1=vec.x-x, d2=vec.x-y, d4=(z-this.z)/d3;
		return d4>=0.0D&&d4<=1.0D?new Vec3M(x+d1*d4, y+d2*d4, this.z+d3*d4):null;
		
	}
	
	public double lengthVector(){
		return MathHelper.sqrt(x*x+y*y+z*z);
	}
	
	public double lightProduct(Vec3MRead vec){
		return 1-MathUtil.snap(normalize().distanceTo(vec.normalize()), 0, 1);
	}
	
	public Vec3M max(IVec3M other){
		return new Vec3M(Math.max(x, other.x()), Math.max(y, other.y()), Math.max(z, other.z()));
	}
	
	public Vec3M min(IVec3M other){
		return new Vec3M(Math.min(x, other.x()), Math.min(y, other.y()), Math.min(z, other.z()));
	}
	
	public Vec3M mul(double x, double y, double z){
		return new Vec3M(x()*x, y()*y, z()*z);
	}
	
	public Vec3M mul(double value){
		return mul(value, value, value);
	}
	
	@Override
	public Vec3M mul(float var){
		return mul(var, var, var);
	}
	
	@Override
	public Vec3M mul(Vec3M vec){
		return mul(vec.x(), vec.y(), vec.z());
	}
	
	public Vec3M mul(IVec3M vec){
		return mul(vec.x(), vec.y(), vec.z());
	}

	public Vec3M mulSelf(double x, double y, double z){
		this.x*=x;
		this.y*=y;
		this.z*=z;
		return this;
	}
	
	public Vec3M mulSelf(double value){
		return mulSelf(value, value, value);
	}
	
	public Vec3M mulSelf(float var){
		return mulSelf(var, var, var);
	}
	
	public Vec3M mulSelf(IVec3M vec){
		return mul(vec.x(), vec.y(), vec.z());
	}
	
	public Vec3M offset(EnumFacing facing){
		return offset(facing, 1);
	}
	
	public Vec3M offset(EnumFacing facing, float mul){
		return new Vec3M(x+facing.getFrontOffsetX()*mul, y+facing.getFrontOffsetY()*mul, z+facing.getFrontOffsetZ()*mul);
	}
	
	public Vec3M reflect(Vec3MRead normal){
		Vec3M norm=normal.normalize();
		Vec3M base=normalize();
		Vec3M difference=base.sub(norm);
		Matrix4f rot=new Matrix4f();
		rot.rotate((float)Math.PI, norm.toLWJGLVec());
		difference=MatrixUtil.transformVector(difference, rot);
		return norm.add(difference);
	}
	
	public void rotateAroundX(float par1){
		float f1=MathHelper.cos(par1);
		float f2=MathHelper.sin(par1);
		double d0=x;
		double d1=y*f1+z*f2;
		double d2=z*f1-y*f2;
		x=d0;
		y=d1;
		z=d2;
	}
	
	/**
	 * Rotates the vector around the y axis by the specified angle.
	 */
	public void rotateAroundY(float par1){
		float f1=MathHelper.cos(par1);
		float f2=MathHelper.sin(par1);
		double d0=x*f1+z*f2;
		double d1=y;
		double d2=z*f1-x*f2;
		x=d0;
		y=d1;
		z=d2;
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * Rotates the vector around the z axis by the specified angle.
	 */ public void rotateAroundZ(float par1){
		float f1=MathHelper.cos(par1);
		float f2=MathHelper.sin(par1);
		double d0=x*f1+y*f2;
		double d1=y*f1-x*f2;
		double d2=z;
		x=d0;
		y=d1;
		z=d2;
	}
	
	public Vec3M rotatePitch(float pitch){
		float f1=MathHelper.cos(pitch);
		float f2=MathHelper.sin(pitch);
		double d0=x;
		double d1=y*f1+z*f2;
		double d2=z*f1-y*f2;
		return new Vec3M(d0, d1, d2);
	}
	
	public Vec3M rotateYaw(float yaw){
		float f1=MathHelper.cos(yaw);
		float f2=MathHelper.sin(yaw);
		double d0=x*f1+z*f2;
		double d1=y;
		double d2=z*f1-x*f2;
		return new Vec3M(d0, d1, d2);
	}
	
	@Override
	public void set(float x, float y){
		setX(x);
		setY(y);
	}
	
	public void set(double x, double y){
		setX(x);
		setY(y);
	}
	
	public Vec3M set(IVec3M vec){
		set(vec.x(), vec.y(), vec.z());
		return this;
	}
	
	public Vec3M set(Vec3i vec){
		set(vec.getX(), vec.getY(), vec.getZ());
		return this;
	}
	
	public Vec3M set(ReadableVector3f vec){
		set(vec.getX(), vec.getY(), vec.getZ());
		return this;
	}
	
	@Override
	public void set(float x, float y, float z){
		set(x, y);
		setZ(z);
	}
	
	public Vec3M set(IVec3M vec, DoubleUnaryOperator operator){
		return set(operator.applyAsDouble(vec.x()), operator.applyAsDouble(vec.y()), operator.applyAsDouble(vec.z()));
	}
	
	public Vec3M set(Vec3i vec, DoubleUnaryOperator operator){
		return set(operator.applyAsDouble(vec.getX()), operator.applyAsDouble(vec.getY()), operator.applyAsDouble(vec.getZ()));
	}
	
	public Vec3M set(ReadableVector3f vec, DoubleUnaryOperator operator){
		return set(operator.applyAsDouble(vec.getX()), operator.applyAsDouble(vec.getY()), operator.applyAsDouble(vec.getZ()));
	}
	
	public Vec3M set(float x, float y, float z, DoubleUnaryOperator operator){
		return set(operator.applyAsDouble(x), operator.applyAsDouble(y), operator.applyAsDouble(z));
	}
	
	@Override
	public void setX(float x){
		this.x=x;
	}
	
	@Override
	public void setY(float y){
		this.y=y;
	}
	
	@Override
	public void setZ(float z){
		this.z=z;
	}
	
	public Vec3M set(double x, double y, double z){
		set(x, y);
		setZ(z);
		return this;
	}
	
	public Vec3M sqrt(){
		int x1=MathUtil.getNumPrefix(x), y1=MathUtil.getNumPrefix(y), z1=MathUtil.getNumPrefix(z);
		return new Vec3M(Math.sqrt(x*x1)*x1, Math.sqrt(y*y1)*y1, Math.sqrt(z*z1)*z1);
	}
	
	public double squareDistanceTo(IVec3M vec){
		double d0=vec.x()-x;
		double d1=vec.y()-y;
		double d2=vec.z()-z;
		return d0*d0+d1*d1+d2*d2;
	}
	
	public Vec3M sub(double x, double y, double z){
		return this.add(-x, -y, -z);
	}
	
	@Override
	public Vec3M sub(float var){
		return this.sub(var, var, var);
	}
	
	public Vec3M sub(Vec3i vec){
		return sub(vec.getX(), vec.getY(), vec.getZ());
	}
	
	public Vec3M subSelf(double x, double y, double z){
		return add(-x, -y, -z);
	}
	
	public Vec3M subSelf(float var){
		return this.sub(var, var, var);
	}
	
	public Vec3M subSelf(Vec3i vec){
		x-=vec.getX();
		y-=vec.getY();
		z-=vec.getZ();
		return this;
	}
	
	@Override
	public Vec3M sub(Vec3M vec){
		return this.sub(vec.x, vec.y, vec.z);
	}
	
	public Vec3M sub(IVec3M vec){
		return this.sub(vec.x(), vec.y(), vec.z());
	}
	
	public Vec3M subtractReverse(double x, double y, double z){
		return new Vec3M(x-this.x, x-this.y, x-this.z);
	}
	
	public Vec3M subtractReverse(IVec3M vec){
		return new Vec3M(vec.x()-x, vec.y()-y, vec.z()-z);
	}
	
	public Vec3M subtractReverseSelf(double x, double y, double z){
		set(x-this.x, x-this.y, x-this.z);
		return this;
	}
	
	public Vec3M subtractReverseSelf(IVec3M vec){
		set(vec.x()-x, vec.y()-y, vec.z()-z);
		return this;
	}
	
	@Override
	public Vector3f toLWJGLVec(){
		return new Vector3f(getX(), getY(), getZ());
	}
	
	@Override
	public String toString(){
		return "M("+x+", "+y+", "+z+")";
	}
	
	public Vec3M transform(Matrix4f matrix){
		return MatrixUtil.transformVector(this, matrix);
	}
	
	public Vec3M transformSelf(Matrix4f matrix){
		Vec3M src=transform(matrix);
		x=src.x;
		y=src.y;
		z=src.z;
		return this;
	}

	public Vec3M add(EnumFacing fac){
		return add(fac,1);
	}
	public Vec3M add(EnumFacing fac, double size){
		Vec3i vec=fac.getDirectionVec();
		return new Vec3M(x+vec.getX()*size,y+vec.getY()*size,z+vec.getZ()*size);
	}
	public Vec3M addSelf(EnumFacing fac){
		return addSelf(fac,1);
	}
	public Vec3M addSelf(EnumFacing fac, double size){
		Vec3i vec=fac.getDirectionVec();
		x+=vec.getX()*size;
		y+=vec.getY()*size;
		z+=vec.getZ()*size;
		return this;
	}
}
