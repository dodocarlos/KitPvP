package me.dodocarlos.kitpvp.kits;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class KitManager {
	
	public static void preparePlayer(Player p){
		KitManager.removeKit(p);
		if(Arrays.admin.contains(p.getName())){
			Arrays.admin.remove(p.getName());
		}
		if(Arrays.build.contains(p.getName())){
			Arrays.build.remove(p.getName());
		}
		p.setHealth(20d);
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		if(Arrays.spawnProtection.contains(p.getName())){
			Arrays.spawnProtection.remove(p.getName());
			p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Você perdeu a proteção do spawn!"));
		}
		
		
	}
	
	public static void tpToRandomLocation(Player p){
		p.teleport(Arrays.spawnLocations.get(new Random().nextInt(Arrays.spawnLocations.size())));
	}
	
	public static void darRecraft(Player p){
		for(int i = 0; i < 36; i++){
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		p.getInventory().setItem(13, new ItemStack(Material.RED_MUSHROOM, 64));
		p.getInventory().setItem(14, new ItemStack(Material.BOWL, 64));
		p.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 64));
	}
	
	public static void removeKit(Player p){
		Methods.updatePvPScore(p);
		
		Arrays.kitPlayer.remove(p.getName());
		PvP.pvp.remove(p.getName());
		Stomper.stomper.remove(p);
		Viper.viper.remove(p);
		Gladiator.gladiator.remove(p);
		Berserker.berserker.remove(p);
		Thor.thor.remove(p);
		Anchor.anchor.remove(p);
		Archer.archer.remove(p);
		Cannibal.cannibal.remove(p);
		Boxer.boxer.remove(p);
		Camel.camel.remove(p);
		CheckPoint.checkpoint.remove(p);
		CookieMonster.cookiemonster.remove(p);
		Fireman.fireman.remove(p);
		Fisherman.fisherman.remove(p);
		ForceField.forcefield.remove(p.getName());
		C4.c4.remove(p.getName());
		Turtle.turtle.remove(p.getName());
		Ninja.ninja.remove(p.getName());
		Kangaroo.kangaroo.remove(p.getName());
		Grappler.grappler.remove(p.getName());
		Hulk.hulk.remove(p.getName());
		Lava.lava.remove(p.getName());
		FPS.fps.remove(p.getName());
		MLG.mlg.remove(p.getName());
	}
	
}
