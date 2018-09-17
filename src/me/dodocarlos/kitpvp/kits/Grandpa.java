package me.dodocarlos.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Grandpa {
	
	public static void darGrandpa(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		ItemStack graveto = new ItemStack(Material.STICK);
		graveto.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		p.getInventory().addItem(graveto);	
		KitManager.darRecraft(p);
		Arrays.kitPlayer.put(p.getName(), "Grandpa");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "GrandPa"));
		KitManager.tpToRandomLocation(p);
	}
	
}
