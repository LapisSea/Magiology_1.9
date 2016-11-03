package com.magiology.client.rendering.item;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.magiology.util.interf.ObjectReturn;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.IModelPart;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public class ItemRendererModelDummy implements IModel{

	private static final IModelState NO_STATE=(t)->Optional.absent();
	public static ModelBlock GEN_MODEL;
	static{
		
	}
	
	private ResourceLocation parent;
	
	public ItemRendererModelDummy(ResourceLocation parent){
		this.parent=parent;
	}
	
	
	@Override
	public Collection<ResourceLocation> getDependencies(){
		return Collections.emptyList();
	}
	
	@Override
	public Collection<ResourceLocation> getTextures(){
		return Collections.emptyList();
	}
	
	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter){
		GEN_MODEL=ModelBlock.deserialize(((ObjectReturn<String>)()->{
			StringBuilder s=new StringBuilder();
			s.append("{");
			s.append("		'display': {");
			s.append("	        'ground': {");
			s.append("	            'rotation': [ 0, 0, 0 ],");
			s.append("	            'translation': [ 0, 2, 0],");
			s.append("	            'scale':[ 0.5, 0.5, 0.5 ]");
			s.append("	        },");
			s.append("	        'head': {");
			s.append("	            'rotation': [ 0, 180, 0 ],");
			s.append("	            'translation': [ 0, 13, 7],");
			s.append("	            'scale':[ 1, 1, 1]");
			s.append("	        },");
			s.append("	        'thirdperson_righthand': {");
			s.append("	            'rotation': [ 0, 0, 0 ],");
			s.append("	            'translation': [ 0, 3, 1 ],");
			s.append("	            'scale': [ 0.55, 0.55, 0.55 ]");
			s.append("	        },");
			s.append("	        'firstperson_righthand': {");
			s.append("	            'rotation': [ 0, -90, 25 ],");
			s.append("	            'translation': [ 1.13, 3.2, 1.13],");
			s.append("	            'scale': [ 0.68, 0.68, 0.68 ]");
			s.append("	        }");
			s.append("	    }");
			s.append("}");
			return s.toString().replaceAll("'", "\"");
		}).process());
		
		GEN_MODEL.name="GreneratedDummyModel_MAGI";
		
		return new DummyVanillaBakedModel(GEN_MODEL.getAllTransforms());
	}
	
	
	@Override
	public IModelState getDefaultState(){
		return NO_STATE;
	}
	
	
}