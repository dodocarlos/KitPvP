package me.dodocarlos.kitpvp.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Stats {
	
	public static File f = new File("/plugins/dKitPvP/stats.yml");
	public static YamlConfiguration statsfile = YamlConfiguration.loadConfiguration(f);
	
	public static void setup(){
		if(!f.exists()){
			try {
				statsfile.save(f);
			} catch (IOException e) {
				System.out.println("§cErro ao salvar o arquivo stats.yml");
				e.printStackTrace();
			}
		}
	}
	
	public static void save(){
		try {
			statsfile.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addKills(Player p, int value){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".kills", statsfile.getInt(uuid + ".kills") + value);
		save();
	}
	
	public static void addDeaths(Player p, int value){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".deaths", statsfile.getInt(uuid + ".deaths") + value);
		save();
	}
	
	public static void addXP(Player p, int value){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".xp", statsfile.getInt(uuid + ".xp") + value);
		save();
	}
	
	public static void takeKills(Player p, int value){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".kills", statsfile.getInt(uuid + ".kills") - value);
		save();
	}
	
	public static void takeDeaths(Player p, int value){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".deaths", statsfile.getInt(uuid + ".deaths") - value);
		save();
	}
	
	public static void takeXP(Player p, int value){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".xp", statsfile.getInt(uuid + ".xp") - value);
		save();
	}
	
	public static void resetKills(Player p){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".kills", 0);
		save();
	}
	
	public static void resetDeaths(Player p){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".deaths", 0);
		save();
	}
	
	public static void resetXP(Player p){
		String uuid = p.getUniqueId().toString();
		statsfile.set(uuid + ".xp", 0);
		save();
	}
	
	public static int getKills(Player p){
		String uuid = p.getUniqueId().toString();
		return statsfile.getInt(uuid + ".kills");
	}
	
	public static int getDeaths(Player p){
		String uuid = p.getUniqueId().toString();
		return statsfile.getInt(uuid + ".deaths");
	}
	
	public static int getXP(Player p){
		String uuid = p.getUniqueId().toString();
		return statsfile.getInt(uuid + ".xp");
	}
	
}
