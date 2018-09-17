package me.dodocarlos.kitpvp.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config extends YamlConfiguration{
		
	private File path;
	
	public Config(String configName){
		setPath(new File(Vars.main.getDataFolder(), configName + ".yml"));
		
		if(!getPath().exists()){
			try{
				Vars.main.saveResource(configName + ".yml", false);
			}catch(Exception e){
				try {
					getPath().createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		try {
			this.load(getPath());
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public Location getLocation(String path){
		
		World w = Bukkit.getWorld(getString(path + ".w"));
		Double x = getDouble(path + ".x");
		Double y = getDouble(path + ".y");
		Double z = getDouble(path + ".z");
		Float pitch = (float) getDouble(path + ".pitch");
		float yaw = (float) getDouble(path + ".yaw");
		
		return new Location(w, x, y, z, yaw, pitch);
		
	}
	
	public void setLocation(String path, Location loc){
		
		String w = loc.getWorld().getName();
		Double x = loc.getX();
		Double y = loc.getY();
		Double z = loc.getZ();
		Float pitch = loc.getPitch();
		Float yaw = loc.getYaw();
		
		set(path + ".w", w);
		set(path + ".x", x);
		set(path + ".y", y);
		set(path + ".z", z);
		set(path + ".pitch", pitch);
		set(path + ".yaw", yaw);
		
		try {
			this.save(getPath());
			this.load(getPath());
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setPath(File f){
		this.path = f;
	}
	
	public File getPath(){
		return this.path;
	}
	
	public void saveConfig(){
		try {
			this.save(getPath());
		} catch (IOException e) {
			System.out.println("Erro ao salvar a config: " + getPath().getName());
			e.printStackTrace();
		}
	}
	
}
