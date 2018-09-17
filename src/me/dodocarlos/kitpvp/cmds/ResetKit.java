package me.dodocarlos.kitpvp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.kits.KitManager;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class ResetKit implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,	String[] args) {

			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("cmd.resetkit")){
					KitManager.removeKit(p);
					Methods.giveSpawnItems(p);
					p.teleport(Vars.spawn);
					p.sendMessage(Vars.tag + Vars.defaultColor + "Voce resetou seu kit");
				}else{
					p.sendMessage(Vars.tag + "§cSem permissao");
				}
			}
		
		return false;
	}
	
}
