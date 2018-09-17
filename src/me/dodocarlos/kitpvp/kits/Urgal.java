package me.dodocarlos.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Urgal {
	
	public static void darUrgal(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		Potion potion = new Potion(PotionType.STRENGTH, 1);
		p.getInventory().addItem(potion.toItemStack(3));
		KitManager.darRecraft(p);
		Arrays.kitPlayer.put(p.getName(), "Urgal");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Urgal"));
		KitManager.tpToRandomLocation(p);
	}
	
}
