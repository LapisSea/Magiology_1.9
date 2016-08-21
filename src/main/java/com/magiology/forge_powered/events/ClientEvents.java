package com.magiology.forge_powered.events;

import com.magiology.util.m_extensions.ResourceLocationM;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents{

	@SubscribeEvent
	public void textureStitchPre(TextureStitchEvent.Pre e){
		e.getMap().registerSprite(new ResourceLocationM(""));
	}
	@SubscribeEvent
	public void textureStitchPost(TextureStitchEvent.Post e){
		
	}
	@SubscribeEvent
	public void modelBake(ModelBakeEvent e){
//		e.getModelLoader().
	}
	
}
