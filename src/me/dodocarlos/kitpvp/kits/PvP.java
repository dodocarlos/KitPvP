package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class PvP {
	
	public static ArrayList<String> pvp = new ArrayList<> ();
	
	public static void darKit(Player p){
		KitManager.preparePlayer(p);
		ItemStack espada = new ItemStack(Material.STONE_SWORD);
		espada.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		p.getInventory().addItem(espada);
		p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		KitManager.darRecraft(p);
		pvp.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "PvP");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "PvP"));
		KitManager.tpToRandomLocation(p);
	}
	
}
