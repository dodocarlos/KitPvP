package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class OneVsOne implements Listener{
	
	public static ArrayList<String> ovo = new ArrayList<> ();
	public static ArrayList<String> inBattle = new ArrayList<> ();
	public static ArrayList<String> invites =  new ArrayList<>();
	
	public static void darKit(Player p){
		KitManager.preparePlayer(p);
		ovo.add(p.getName());
		p.getInventory().setItem(4, new ItemStack(Material.BLAZE_ROD));
		Arrays.kitPlayer.put(p.getName(), "OneVsOne");
		Methods.updatePvPScore(p);
	}
	
	public static void tpToPvP(Player p1, Player p2){
		inBattle.add(p1.getName());
		inBattle.add(p2.getName());
		
		p1.teleport(Main.warps.getLocation("1v1p1"));
		p2.teleport(Main.warps.getLocation("1v1p2"));
		
		p1.getInventory().clear();
		
		p1.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		p1.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p1.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p1.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		
		p1.getInventory().addItem(sword);
		
		for(int i = 0; i < 8; i ++){
			p1.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		
		p2.getInventory().clear();
		
		p2.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
		p2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
		p2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
		
		p2.getInventory().addItem(sword);
		
		for(int i = 0; i < 8; i ++){
			p2.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
		
	}
	
	@EventHandler
	public void interact(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Player){
			Player p1 = e.getPlayer();
			if(p1.getItemInHand().getType().equals(Material.BLAZE_ROD)){
				Player p2 = (Player) e.getRightClicked();
				if(ovo.contains(p1.getName()) && ovo.contains(p2.getName())){
					if(!inBattle.contains(p1.getName()) && !inBattle.contains(p2.getName())){
						if(hasInvite(p1, p2)){
							p1.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce ja convidou este jogador"));
						}else{
							invites.add(p1.getName().toLowerCase() + "_" + p2.getName().toLowerCase());
							p1.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce convidou " 
							+ Vars.infoColor + p2.getDisplayName() + Vars.defaultColor + " para um 1v1"));
							p2.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce foi convidado por  " 
									+ Vars.infoColor + p1.getDisplayName() + Vars.defaultColor + " para um 1v1"));
							Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable(){
								public void run(){
									invites.remove(p1.getName().toLowerCase() + "_" + p2.getName().toLowerCase());
								}
							}, 100l);
						}
						if(hasInvite(p2, p1)){
							p2.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce aceitou o convite de " 
						+ Vars.infoColor + p1.getDisplayName()));
							p1.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O jogador " 
									+ Vars.infoColor + p2.getDisplayName() + Vars.defaultColor + " aceitou seu convite"));
							tpToPvP(p1, p2);
						}
					}else{
						p1.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Este jogador já esta em 1v1!"));
					}
				}
			}
		}
	}
	
	public boolean hasInvite(Player p1, Player p2){
		return invites.contains(p1.getName().toLowerCase() + "_" + p2.getName().toLowerCase());
	}

	public int soups(Player p){
		int soup = 0;
		for(int i = 0; i < 36; i ++){
			if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType().equals(Material.MUSHROOM_SOUP)){
				soup ++;
			}
		}
		return soup;
	}
	
	@EventHandler
	public void death(PlayerDeathEvent e){
		if(e.getEntity().getKiller() instanceof Player){
			Player p = e.getEntity();
			Player killer = e.getEntity().getKiller();
			if(ovo.contains(p.getName())){
				ovo.remove(p.getName());
				inBattle.remove(p.getName());
				ovo.remove(killer.getName());
				inBattle.remove(killer.getName());
				Damageable killerd = (Damageable) killer;
				Damageable pd = (Damageable) p;
				
				p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce perdeu o 1v1 para " + Vars.infoColor 
						+ killer.getDisplayName() + Vars.defaultColor + ". Ele ficou com " + Vars.infoColor 
						+ (int) killerd.getHealth() / 10 + Vars.defaultColor + " coraçoes e " + Vars.infoColor + soups(killer) 
						+ Vars.defaultColor + " sopas"));
				
				killer.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce ganhou o 1v1 de " + Vars.infoColor 
						+ p.getDisplayName() + Vars.defaultColor + ". Ele ficou com " + Vars.infoColor 
						+ (int) pd.getHealth() / 10 + Vars.defaultColor + " coraçoes e " + Vars.infoColor + soups(p) 
						+ Vars.defaultColor + " sopas"));
			
				Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable(){
					public void run(){
						darKit(killer);
						killer.teleport(Main.warps.getLocation("1v1"));
					}
				}, 20l);
			}
		}
			
	}
	
	@EventHandler
	public void damage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(ovo.contains(p.getName())){
				if(!inBattle.contains(p.getName())){
					e.setCancelled(true);
				}
			}
		}
	}
	
}
