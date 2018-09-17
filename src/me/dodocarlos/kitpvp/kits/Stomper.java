package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Stomper implements Listener {

	public static ArrayList<Player> stomper = new ArrayList<Player>();

	public static void darStomper(Player p) {
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		stomper.add(p);
		Arrays.kitPlayer.put(p.getName(), "Stomper");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Stomper"));
		KitManager.tpToRandomLocation(p);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void dano(EntityDamageEvent e){
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
				if(stomper.contains(p)) {
					if (e.getCause() == DamageCause.FALL) {
						e.setDamage(4);
						List<Entity> stompados = p.getNearbyEntities(5D, 5D, 5D);

						for (Entity en : stompados) {
							if (en instanceof Player) {
								((CraftPlayer)en).getHandle().lastDamager = ((CraftPlayer)p).getHandle();
								if (((Player) en).isSneaking()) {
									((Player) en)
											.damage((int) p.getFallDistance() / 7);
									e.setDamage((int) p.getFallDistance() / 8);
								} else {
									((Player) en)
											.damage((int) p.getFallDistance() / 2);
									e.setDamage((int) p.getFallDistance() / 8);
								}
							}
						}


				}
			}
		}
	}

}
