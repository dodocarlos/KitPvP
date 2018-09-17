package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;

public class MLG {
	
	public static ArrayList<String> mlg = new ArrayList<> ();
	
	public static void darKit(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
		mlg.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "MLG");
		Methods.updatePvPScore(p);
	}
	
}
