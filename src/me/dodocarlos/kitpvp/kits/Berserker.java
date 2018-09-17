package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Berserker implements Listener{
	
	public static ArrayList<Player> berserker = new ArrayList<Player> ();
		
	public static void darBerserker(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		berserker.add(p);
		Arrays.kitPlayer.put(p.getName(), "Berserker");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Berserker"));
		KitManager.tpToRandomLocation(p);
	}
	
	
	@EventHandler
	public void matar(PlayerDeathEvent e){
			if(e.getEntity().getKiller() instanceof Player){
				Player matou = (Player) e.getEntity().getKiller();
				if(berserker.contains(matou)){
					matou.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30, 1));
				}
			}
	}
}
