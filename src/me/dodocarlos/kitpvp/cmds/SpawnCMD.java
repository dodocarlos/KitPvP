package me.dodocarlos.kitpvp.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.kits.KitManager;
import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class SpawnCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player){
			Player p = (Player) sender;
			p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sera teletransportado em" 
					+ Vars.infoColor + " 3" + Vars.defaultColor + " segundos!"));
			Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable() {
				
				@Override
				public void run() {
					KitManager.removeKit(p);
					Methods.giveSpawnItems(p);
					p.teleport(Vars.spawn);
					p.sendMessage(Vars.tag + Vars.defaultColor + "Teletransportado para o spawn");
					if(!Arrays.spawnProtection.contains(p.getName())){
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Você ganhou a proteçao do spawn!"));
						Arrays.spawnProtection.add(p.getName());
					}
				}
			}, 60l);
		}
		
		return false;
	}
	
	
	
}
