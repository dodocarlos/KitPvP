package me.dodocarlos.kitpvp.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.dodocarlos.kitpvp.feast.FeastRunnable;

public class Vars {
	
	public static Plugin main;
	
	public static String Motd;
	public static String tag;
	public static String infoColor = "§f";
	public static String defaultColor = "§a";
	public static String scoreTitle;
	public static String server;
	public static String db;
	public static String user;
	public static String pass;
	
	public static Config feastConfig;
	
	public static HashMap<ItemStack, Integer> feastItems = new HashMap<>();
	
	public static int timeToSpawnFeast = 300;
	
	public static Location spawn;
	public static Location feastLocation;
	
	public static int feastTimer;
	
	public static void setup(){
		Motd = Methods.toColoredString(main.getConfig().getString("Motd"));
		infoColor = Methods.toColoredString(main.getConfig().getString("InfoColor"));
		defaultColor = Methods.toColoredString(main.getConfig().getString("DefaultColor"));
		tag = Methods.toColoredString(main.getConfig().getString("Tag"));
		scoreTitle = Methods.toColoredString(main.getConfig().getString("ScoreTitle"));
		
		server = main.getConfig().getString("MySQL.host");
		db = main.getConfig().getString("MySQL.db");
		user = main.getConfig().getString("MySQL.user");
		pass = main.getConfig().getString("MySQL.pass");
		
		for(String line : main.getConfig().getStringList("ScoreBoard")){
			Arrays.scoreBoard.add(line);
		}
		
		try{
			World w = Bukkit.getWorld(main.getConfig().getString("Spawn.w"));
			Double x = main.getConfig().getDouble("Spawn.x");
			Double y = main.getConfig().getDouble("Spawn.y");
			Double z = main.getConfig().getDouble("Spawn.z");
			
			spawn = new Location(w, x, y, z);
			
		}catch(Exception e){
			spawn = Bukkit.getWorlds().get(0).getSpawnLocation();
		}
		
		for(String line : main.getConfig().getStringList("SpawnLocations")){
			try{
				String[] data = line.split(":");
				World w = Bukkit.getWorld(data[0]);
				double x = Double.valueOf(data[1]);
				double y = Double.valueOf(data[2]);
				double z = Double.valueOf(data[3]);
				
				Location l = new Location(w, x, y, z);
				
				Arrays.spawnLocations.add(l);
			}catch(Exception e){
				System.out.println("§cErro ao carregar os locais de spawn");
				e.printStackTrace();
			}
		}
		
		try{
				for(String itemstring : feastConfig.getStringList("Items")){
					String materialName = itemstring.split(", ")[0].split(":")[0];
					String dataValue = itemstring.split(", ")[0].split(":")[1];
					int chance = Integer.valueOf(itemstring.split(", ")[1]);
					int amount = Integer.valueOf(itemstring.split(", ")[2]);
					String enchants = null;
					
					try{
						enchants = itemstring.split(", ")[3];
					}catch(Exception e){
						
					}
					
					final ItemStack item = new ItemStack(Material.getMaterial(materialName), amount, (short) Double.parseDouble(dataValue));
					if(enchants != null){
						for(String enc : enchants.split("/")){
							try{
								String encName = enc.split(":")[0];
								int encLevel = Integer.parseInt(enc.split(":")[1]);
								item.addUnsafeEnchantment(Enchantment.getByName(encName), encLevel);
							}catch(Exception e){
								Bukkit.getConsoleSender().sendMessage("§cErro: ");
								e.printStackTrace();
							}
						}
					}
					feastItems.put(item, chance);
				}
		}catch(Exception e){
			Bukkit.getConsoleSender().sendMessage("§4Erro");
			e.printStackTrace();
		}
	
		//Load feast location
		try{
			feastLocation = feastConfig.getLocation("FeastEnchantLocation");
			feastTimer = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new FeastRunnable(), 0l, 20l);
		}catch(Exception e){
			System.out.println(Methods.toColoredString("&4Erro ao definir local do spawn do feast! Feast cancelado!"));
		}
		
	}
	
}
