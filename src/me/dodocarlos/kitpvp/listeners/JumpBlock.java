package me.dodocarlos.kitpvp.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpBlock implements Listener{

	public ArrayList<String> noDamage = new ArrayList<>();
	
	@EventHandler
	public void walk(PlayerMoveEvent e){
		Block b = e.getFrom().getBlock();
		if(b.getType().equals(Material.ENDER_PORTAL_FRAME)){
			float boost = 1.1f;
			b = b.getWorld().getBlockAt(b.getLocation().add(0, -1, 0));
			while(b.getType().equals(Material.ENDER_PORTAL_FRAME)){
				boost += 0.5f;
				b = b.getWorld().getBlockAt(b.getLocation().add(0, -1, 0));
			}
			//Boost
			Vector vel = e.getPlayer().getVelocity().setY(1.8).add(new Vector(0, 1 * boost, 0));
			e.getPlayer().setVelocity(vel);
			if(!noDamage.contains(e.getPlayer().getName())){
				noDamage.add(e.getPlayer().getName());
			}
		}
	}
	
	@EventHandler
	public void damage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player && e.getCause().equals(DamageCause.FALL)){
			Player p = (Player) e.getEntity();
			if(noDamage.contains(p.getName())){
				e.setCancelled(true);
				noDamage.remove(p.getName());
			}
		}
	}
	
}
