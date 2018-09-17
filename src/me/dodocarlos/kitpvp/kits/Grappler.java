package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.CordaGrappler;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Grappler implements Listener{
	
	  HashMap<Player, CordaGrappler> hooks = new HashMap<>();
	  public static ArrayList<String> grappler = new ArrayList<> ();
	  
	  public static void darGrappler(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		p.getInventory().addItem(new ItemStack(Material.LEASH));
		KitManager.darRecraft(p);
		grappler.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "Grappler");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Grappler"));
		KitManager.tpToRandomLocation(p);
	  }
	  
	  @EventHandler
	  public void onSlot(PlayerItemHeldEvent e)
	  {
	    if (this.hooks.containsKey(e.getPlayer()))
	    {
	      ((CordaGrappler)this.hooks.get(e.getPlayer())).remove();
	      this.hooks.remove(e.getPlayer());
	    }
	  }
	  
	  @EventHandler
	  public void grapplerDamageNoLeash(EntityDamageEvent event)
	  {
	    if (!(event.getEntity() instanceof Player)) {
	      return;
	    }
	    Player player = (Player)event.getEntity();
	    if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
	      return;
	    }
	    if ((this.hooks.containsKey(player)) && 
	      (((CordaGrappler)this.hooks.get(player)).isHooked()) && 
	      (event.getDamage() > 6.0D)) {
	      event.setDamage(6.0D);
	    }
	  }
	  
	  @EventHandler
	  public void onMove(PlayerMoveEvent e)
	  {
	    if ((this.hooks.containsKey(e.getPlayer())) && 
	      (!e.getPlayer().getItemInHand().getType().equals(Material.LEASH)))
	    {
	      ((CordaGrappler)this.hooks.get(e.getPlayer())).remove();
	      this.hooks.remove(e.getPlayer());
	    }
	  }
	  
	@EventHandler
	  public void onLeash(PlayerLeashEntityEvent e)
	  {
	    Player p = e.getPlayer();
	    if (e.getPlayer().getItemInHand().getType().equals(Material.LEASH))
	    {
	      e.setCancelled(true);
	      e.getPlayer().updateInventory();
	      e.setCancelled(true);
	      if(!grappler.contains(p.getName())){
	    	  return;
	      }
	      if (!this.hooks.containsKey(p)) {
	        return;
	      }
	      if (!((CordaGrappler)this.hooks.get(p)).isHooked()) {
	        return;
	      }
	      double d = ((CordaGrappler)this.hooks.get(p)).getBukkitEntity()
	        .getLocation().distance(p.getLocation());
	      double t = d;
	      double v_x = (1.0D + 0.06000000000000001D * t) * (
	        ((CordaGrappler)this.hooks.get(p)).getBukkitEntity()
	        .getLocation().getX() - p.getLocation().getX()) / t;
	      double v_y = (1.0D + 0.03D * t) * (
	        ((CordaGrappler)this.hooks.get(p)).getBukkitEntity()
	        .getLocation().getY() - p.getLocation().getY()) / t;
	      double v_z = (1.0D + 0.06000000000000001D * t) * (
	        ((CordaGrappler)this.hooks.get(p)).getBukkitEntity()
	        .getLocation().getZ() - p.getLocation().getZ()) / t;
	      
	      org.bukkit.util.Vector v = p.getVelocity();
	      v.setX(v_x);
	      v.setY(v_y);
	      v.setZ(v_z);
	      p.setVelocity(v);
	    }
	  }
	  
	  @EventHandler
	  public void onClick(PlayerInteractEvent e)
	  {
	    Player p = e.getPlayer();
	    if (e.getPlayer().getItemInHand().getType().equals(Material.LEASH))
	    {
	      e.setCancelled(true);
	      if ((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK)){
	    	  if(!grappler.contains(p.getName())){
		    	  return;
		      }
	        if (this.hooks.containsKey(p)) {
	          ((CordaGrappler)this.hooks.get(p)).remove();
	        }
	        CordaGrappler nmsHook = new CordaGrappler(p.getWorld(), 
	          ((CraftPlayer)p).getHandle());
	        nmsHook.spawn(p.getEyeLocation().add(
	          p.getLocation().getDirection().getX(), 
	          p.getLocation().getDirection().getY(), 
	          p.getLocation().getDirection().getZ()));
	        nmsHook.move(p.getLocation().getDirection().getX() * 5.0D, p
	          .getLocation().getDirection().getY() * 5.0D, p
	          .getLocation().getDirection().getZ() * 5.0D);
	        this.hooks.put(p, nmsHook);
	      }
	      else
	      {
	        if (!this.hooks.containsKey(p)) {
	          return;
	        }
	        if (!((CordaGrappler)this.hooks.get(p)).isHooked()) {
	          return;
	        }
	        double d = ((CordaGrappler)this.hooks.get(p))
	          .getBukkitEntity().getLocation()
	          .distance(p.getLocation());
	        double t = d;
	        double v_x = (1.0D + 0.2D * t) * (
	          ((CordaGrappler)this.hooks.get(p))
	          .getBukkitEntity().getLocation().getX() - p
	          .getLocation().getX()) / t;
	        double v_y = (1.0D + 0.03D * t) * (
	          ((CordaGrappler)this.hooks.get(p))
	          .getBukkitEntity().getLocation().getY() - p
	          .getLocation().getY()) / t;
	        double v_z = (1.0D + 0.2D * t) * (
	          ((CordaGrappler)this.hooks.get(p))
	          .getBukkitEntity().getLocation().getZ() - p
	          .getLocation().getZ()) / t;
	        
	        org.bukkit.util.Vector v = p.getVelocity();
	        v.setX(v_x);
	        v.setY(v_y);
	        v.setZ(v_z);
	        p.setVelocity(v);
	      }
	    }
	  }
	  
	  @EventHandler
	  public void onTomarDano(EntityDamageEvent event)
	  {
	    Entity e = event.getEntity();
	    if ((e instanceof Player))
	    {
	      Player player = (Player)e;
	      if(!grappler.contains(player.getName())){
	    	  return;
	      }
	      if (((event.getEntity() instanceof Player)) && 
	        (event.getCause() == EntityDamageEvent.DamageCause.FALL) && 
	        (player.getInventory().contains(Material.LEASH)) && 
	        (event.getDamage() >= 5.0D)) {
	        event.setDamage(5.0D);
	      }
	    }
	  }
	
}
