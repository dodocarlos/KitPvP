package me.dodocarlos.kitpvp.cmds;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.dodocarlos.kitpvp.kits.Anchor;
import me.dodocarlos.kitpvp.kits.Archer;
import me.dodocarlos.kitpvp.kits.Berserker;
import me.dodocarlos.kitpvp.kits.Boxer;
import me.dodocarlos.kitpvp.kits.C4;
import me.dodocarlos.kitpvp.kits.Camel;
import me.dodocarlos.kitpvp.kits.Cannibal;
import me.dodocarlos.kitpvp.kits.CheckPoint;
import me.dodocarlos.kitpvp.kits.CookieMonster;
import me.dodocarlos.kitpvp.kits.Fireman;
import me.dodocarlos.kitpvp.kits.Fisherman;
import me.dodocarlos.kitpvp.kits.ForceField;
import me.dodocarlos.kitpvp.kits.Gladiator;
import me.dodocarlos.kitpvp.kits.Grandpa;
import me.dodocarlos.kitpvp.kits.Grappler;
import me.dodocarlos.kitpvp.kits.Hulk;
import me.dodocarlos.kitpvp.kits.Kangaroo;
import me.dodocarlos.kitpvp.kits.Ninja;
import me.dodocarlos.kitpvp.kits.PvP;
import me.dodocarlos.kitpvp.kits.Stomper;
import me.dodocarlos.kitpvp.kits.Thor;
import me.dodocarlos.kitpvp.kits.Turtle;
import me.dodocarlos.kitpvp.kits.Urgal;
import me.dodocarlos.kitpvp.kits.Viper;
import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.KitInv;
import me.dodocarlos.kitpvp.utils.Vars;

public class Kit implements CommandExecutor, Listener{
	
	public static HashMap<String, KitInv> inv = new HashMap<>();
	
	public static String kits[] = {"PvP", "Anchor", "Archer", "Berserker", "Boxer", "C4", "Camel", "Cannibal", "Checkpoint", "Cookiemonster", "Fireman"
		, "Fisherman", "Forcefield", "Gladiator", "Grandpa", "Grappler", "Hulk", "Kangaroo", "Ninja", "Stomper", "Thor", "Turtle", "Urgal", "Viper"};
		
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
				if(sender instanceof Player){
					final Player p = (Player) sender;				
					if(!Arrays.kitPlayer.keySet().contains(p.getName())){
						KitInv kitinv = new KitInv(p, kits);
						inv.put(p.getName(), kitinv);
					}else{
						p.sendMessage(Vars.tag + "§cVoce ja tem um kit");
					}
				}
				
		return false;
	}
		
	@EventHandler
	public void invClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory() != null){
			if(e.getInventory().getName().equals("§a§lKits") || e.getInventory().getName().equals("§a§lKits 2")){
				e.setCancelled(true);
				
				if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aProxima pagina") || e.getCurrentItem().getItemMeta().getDisplayName().equals("§aPagina anterior")){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aProxima pagina")){
							p.openInventory(inv.get(p.getName()).getInv2());
						}						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aPagina anterior")){
							p.openInventory(inv.get(p.getName()).getInv1());
						}
					}else{
						if(inv.containsKey(p.getName())){
							inv.remove(p.getName());
						}
						p.closeInventory();
					}
					
					//Kit
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "PvP")){						
							PvP.darKit(p);
						}	
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Anchor")){						
							Anchor.darAnchor(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Stomper")){						
							Stomper.darStomper(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Viper")){						
							Viper.darViper(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Gladiator")){						
							Gladiator.darGladiator(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Berserker")){						
							Berserker.darBerserker(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Thor")){						
							Thor.darThor(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Archer")){						
							Archer.darArcher(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Cannibal")){						
							Cannibal.darCannibal(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Boxer")){						
							Boxer.darBoxer(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Camel")){						
							Camel.darCamel(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Checkpoint")){						
							CheckPoint.darCheckpoint(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Cookiemonster")){						
							CookieMonster.darCookiemonster(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Fireman")){						
							Fireman.darFireman(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Fisherman")){						
							Fisherman.darFisherman(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Forcefield")){						
							ForceField.darForceField(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "C4")){						
							C4.darC4(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Urgal")){						
							Urgal.darUrgal(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Grandpa")){						
							Grandpa.darGrandpa(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Turtle")){						
							Turtle.darTurtle(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Ninja")){						
							Ninja.darNinja(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Kangaroo")){						
							Kangaroo.darKangaroo(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Grappler")){						
							Grappler.darGrappler(p);
						}
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Vars.infoColor + "Hulk")){						
							Hulk.darHulk(p);
						}
						
					
				}
				
			}
		}
	}
		
}
