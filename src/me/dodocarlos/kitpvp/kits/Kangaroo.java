package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Kangaroo implements Listener{

	public static ArrayList<String> kangaroo = new ArrayList<>();
	public static ArrayList<String> kanga = new ArrayList<>();
	
	public static void darKangaroo(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		p.getInventory().addItem(new ItemStack(Material.FIREWORK));
		KitManager.darRecraft(p);
		kangaroo.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "Kangaroo");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Kangaroo"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void kanga(PlayerInteractEvent e){
		Player p = e.getPlayer();		
		if(e.getItem() != null && e.getItem().getType() == Material.FIREWORK){
			e.setCancelled(true);
			if(kangaroo.contains(p.getName())){
				if(!kanga.contains(p.getName())){								
					if(p.isSneaking()){
						p.setVelocity(p.getLocation().getDirection().multiply(1.2D));
				          p.setVelocity(new Vector(p.getVelocity().getX(), 0.5D, p.getVelocity().getZ()));
					}else{
						p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
					}	
					kanga.add(p.getName());
				}
			}
		}
	}
	
	@EventHandler
	public void andar(PlayerMoveEvent e){
		if(kanga.contains(e.getPlayer().getName())){
			if(((CraftPlayer)e.getPlayer()).getHandle().onGround){
				kanga.remove(e.getPlayer().getName());
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL && kangaroo.contains(p.getName())){
				if(e.getDamage() > 7.0){
					e.setDamage(7.0);
				}
			}
		}
	}
}
