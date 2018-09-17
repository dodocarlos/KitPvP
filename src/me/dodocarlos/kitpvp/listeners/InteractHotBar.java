package me.dodocarlos.kitpvp.listeners;

import java.util.Calendar;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.utils.DailyKit;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class InteractHotBar implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getType() != Material.AIR){
				if(e.getItem().getType().equals(Material.CHEST)){
					e.getPlayer().chat("/kit");
				}
				if(e.getItem().getType().equals(Material.EMERALD)){
					e.getPlayer().sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Nossa loja: " 
				+ Vars.infoColor + "http://estcraft.com.br/loja"));
				}
				if(e.getItem().getType().equals(Material.COMPASS)){
					Methods.openWarpGui(e.getPlayer());
				}
				if(e.getItem().getType().equals(Material.ENDER_CHEST)){
					if(DailyKit.canGetTheKit(e.getPlayer())){
						DailyKit.giveRandomKit(e.getPlayer());
					}else{
						Date date = new Date(Long.valueOf(Main.db.getKitDiarioTime(e.getPlayer())) - Calendar.getInstance().getTimeInMillis());
						e.getPlayer().sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor 
								+ "Voce nao pode pegar seu kit. Faltam " + Vars.infoColor + date.getHours() + "h "
								+ date.getMinutes() + "m " + date.getSeconds() + "s"));
					}
				}
			}
		}
	}
	
}
