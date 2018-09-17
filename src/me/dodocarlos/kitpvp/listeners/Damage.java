package me.dodocarlos.kitpvp.listeners;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.dodocarlos.kitpvp.kits.Lava;
import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Damage implements Listener{

	@EventHandler
	public void dano(EntityDamageEvent e){
		if(e.getCause() != DamageCause.LAVA || e.getCause() != DamageCause.FALL || e.getCause() != DamageCause.LIGHTNING){
			e.setDamage(e.getDamage() * 0.5);
		}
		//Spawn protect
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Arrays.spawnProtection.contains(p.getName())){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void damage2(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			Player damager = (Player) e.getDamager();
			if(e.getEntity() instanceof Player){
				if(Arrays.kitPlayer.get(damager) != "Nenhum" || !Arrays.kitMaterial.containsKey(damager)){
					if(Arrays.spawnProtection.contains(damager.getName())){
						Arrays.spawnProtection.remove(damager.getName());
						damager.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Você perdeu a proteção do spawn!"));
					}
				}
				if(Lava.lava.contains(damager.getName())){
					e.setCancelled(true);
				}
			}
			if(e.getEntity() instanceof ItemFrame){
				if(!Arrays.build.contains(damager.getName())){
					e.setCancelled(true);
				}
			}
		}
	}
	
}
