package me.dodocarlos.kitpvp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.kits.KitManager;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Death implements Listener{

	@EventHandler
	public void death(PlayerDeathEvent e){
		e.setDeathMessage(null);
		
		KitManager.removeKit(e.getEntity());
		
		Player morreu = e.getEntity();
		Main.db.addDeath(morreu, 1);
		
		if(morreu.getKiller() instanceof Player){
			Player matou = morreu.getKiller();
			Main.db.addKill(matou, 1);
			matou.sendMessage(Vars.tag + Vars.defaultColor + "Voce matou o jogador " + Vars.infoColor + morreu.getDisplayName());
			morreu.sendMessage(Vars.tag + Vars.defaultColor + "Voce morreu para o jogador " + Vars.infoColor + matou.getDisplayName());
			Main.db.addExp(matou, 50);
			if(Main.db.getExp(morreu) >= 25){
				Main.db.addExp(morreu, -25);
			}else{
				Main.db.addExp(morreu, - Main.db.getExp(morreu));
			}
		}else{
			morreu.sendMessage(Vars.tag + Vars.defaultColor + "Voce morreu");
		}
		
		Methods.respawn(morreu);
	}
	
}
