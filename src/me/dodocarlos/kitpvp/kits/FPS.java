package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;

public class FPS {
	
	public static ArrayList<String> fps = new ArrayList<> ();
	
	public static void darKit(Player p){
		KitManager.preparePlayer(p);
		ItemStack espada = new ItemStack(Material.STONE_SWORD);
		espada.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		p.getInventory().addItem(espada);
		p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		KitManager.darRecraft(p);
		fps.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "FPS");
		Methods.updatePvPScore(p);
	}
	
}
