package com.magiology.core;

import static com.magiology.core.MReference.*;

import java.io.File;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.*;

import com.google.common.eventbus.EventBus;
import com.magiology.forge_powered.networking.SimpleNetworkWrapperM;
import com.magiology.forge_powered.proxy.CommonProxy;
import com.magiology.io.IOManager;
import com.magiology.util.statics.LogUtil;
import com.magiology.util.statics.class_manager.ClassList;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.versioning.*;
import scala.actors.threadpool.Arrays;

@Mod(modid=MODID, version=VERSION, name=NAME, acceptedMinecraftVersions=ACCEPTED_MC_VERSION)
public class Magiology implements ModContainer{
	
	private static final boolean IS_DEV;
	static{
		CompatibilityChecker.checkJava8();
		IS_DEV=SOURCE_FILE==null;
		if(IS_DEV)LogUtil.printWrapped(NAME+" is running in development environment! Work Lapis! Work! NO! CLOSE THAT YOUTUBE VIDEO! ");
	}
	/***//** variables *//***/
	public static SimpleNetworkWrapperM NETWORK_CHANNEL;
	@Instance(value=MODID)
	private static Magiology instance;
	@SidedProxy(modId=MODID, clientSide=ClIENT_PROXY_LOCATION, serverSide=SERVER_PROXY_LOCATION)
	public static CommonProxy sideProxy;
	public static CommonProxy commonProxy=new CommonProxy();
	private static String marker=NAME+"_"+MC_VERSION+"-"+VERSION;
	
	public static IOManager extraFiles=new IOManager();
	
	public Magiology(){
		instance=this;
		ClassList.getImplementations();
	}
	
	/***//** forge events *//***/
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		commonProxy.loadModFiles();
		sideProxy.loadModFiles();
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			commonProxy.onExit();
			sideProxy.onExit();
		}));
		
		LogUtil.printWrapped(marker+" -> Pre initialization started!");
		commonProxy.preInit();
		sideProxy.preInit();
		LogUtil.printWrapped(marker+" -> Pre initialization compleate!");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		LogUtil.printWrapped(marker+" -> Initialization started!");
		commonProxy.init();
		sideProxy.init();
		LogUtil.printWrapped(marker+" -> Initialization compleate!");
		
	}
	
	@EventHandler
	public void postInitStarter(FMLPostInitializationEvent event){
		
		LogUtil.printWrapped(marker+" -> Post initialization started!");
		commonProxy.postInit();
		sideProxy.postInit();
		LogUtil.printWrapped(marker+" -> Post initialization compleate!");
		
		marker=null;
	}
	
	/***/
	/** getters */
	/***/
	public static Magiology getMagiology(){
		return instance;
	}
	
	public static boolean isDev(){
		return IS_DEV;
	}
	
	/***/
	/** interfaces */
	/***/
	@Override
	public VersionRange acceptableMinecraftVersionRange(){
		try{
			return VersionRange.createFromVersionSpec(MC_VERSION);
		}catch(Exception e){}
		return null;
	}
	
	@Override
	public void bindMetadata(MetadataCollection mc){}
	
	@Override
	public Disableable canBeDisabled(){
		return Disableable.NEVER;
	}
	
	@Override
	public Map<String, String> getCustomModProperties(){
		return null;
	}
	
	@Override
	public Class<?> getCustomResourcePackClass(){
		return null;
	}
	
	@Override
	public List<ArtifactVersion> getDependants(){
		return null;
	}
	
	@Override
	public List<ArtifactVersion> getDependencies(){
		return null;
	}
	
	@Override
	public String getDisplayVersion(){
		return VERSION;
	}
	
	@Override
	public String getGuiClassName(){
		return null;
	}
	
	@Override
	public ModMetadata getMetadata(){
		return getModMetadata();
	}
	
	@Override
	public Object getMod(){
		return this;
	}
	
	@Override
	public String getModId(){
		return MODID;
	}
	
	@Override
	public String getName(){
		return NAME;
	}
	
	@Override
	public List<String> getOwnedPackages(){
		return Arrays.asList(new String[]{BASE_PATH.substring(0, BASE_PATH.length()-1)});
	}
	
	@Override
	public ArtifactVersion getProcessedVersion(){
		return new DefaultArtifactVersion(VERSION);
	}
	
	@Override
	public Set<ArtifactVersion> getRequirements(){
		return null;
	}
	
	@Override
	public Map<String, String> getSharedModDescriptor(){
		return null;
	}
	
	@Override
	public Certificate getSigningCertificate(){
		return null;
	}
	
	@Override
	public String getSortingRules(){
		return null;
	}
	
	@Override
	public File getSource(){
		return null;
	}
	
	@Override
	public URL getUpdateUrl(){
		return null;
	}
	
	@Override
	public String getVersion(){
		return VERSION;
	}
	
	@Override
	public boolean isImmutable(){
		return true;
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller){
		bus.register(getClass());
		return true;
	}
	
	@Override
	public void setEnabledState(boolean enabled){
		String keemstar="Alex is a stupid niger";
		keemstar.equals("Gnome");
	}
	
	@Override
	public boolean shouldLoadInEnvironment(){
		return true;
	}
	
	@Override
	public boolean matches(Object mod){
		return mod==this;
	}
	
	@Override
	public void setClassVersion(int classVersion){}
	
	@Override
	public int getClassVersion(){
		return -1;
	}
}
