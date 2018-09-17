package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class CookieMonster implements Listener{

	public static ArrayList<Player> cookiemonster = new ArrayList<Player>();
	
	public static void darCookiemonster(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		p.getInventory().addItem(new ItemStack(Material.COOKIE, 64));
		KitManager.darRecraft(p);
		cookiemonster.add(p);		
		Arrays.kitPlayer.put(p.getName(), "Cookiemonster");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "CookieMonster"));
		KitManager.tpToRandomLocation(p);

	}
	
	@EventHandler
	public void quebrar(BlockBreakEvent e){
		if(cookiemonster.contains(e.getPlayer())){
			if(!e.isCancelled()){
				if(e.getBlock().getType() == Material.LONG_GRASS){
					if(new Random().nextInt(4) == 1){
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.COOKIE));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void interagir(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(cookiemonster.contains(p)){
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(p.getItemInHand().getType() == Material.COOKIE){
					if(p.getItemInHand().getAmount() > 1){
						p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
					}else{
						p.getItemInHand().setType(Material.AIR);
					}
					Damageable d = (Damageable) p;
					if(d.getHealth() < 20D){
						p.setHealth(d.getHealth() + 3.5 > d.getMaxHealth() ? d.getMaxHealth() : d.getHealth() + 3.5);
						return;
					}
					if(p.getFoodLevel() < 20D){
						p.setFoodLevel(p.getFoodLevel() + 3 > 20 ? 20: p.getFoodLevel() + 3);
						return;
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
				}
			}
		}
	}
}
