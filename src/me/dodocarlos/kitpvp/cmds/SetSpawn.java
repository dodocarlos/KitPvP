package me.dodocarlos.kitpvp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.utils.Vars;

public class SetSpawn implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission("cmd.setspawn")){
				Vars.main.getConfig().set("Spawn.w", p.getWorld().getName());
				Vars.main.getConfig().set("Spawn.x", p.getLocation().getX());
				Vars.main.getConfig().set("Spawn.y", p.getLocation().getY());
				Vars.main.getConfig().set("Spawn.z", p.getLocation().getZ());
				Vars.main.saveConfig();
				Vars.spawn = p.getLocation();
				p.sendMessage(Vars.tag + Vars.defaultColor + "§cSpawn definido !");
			}
		}
		
		return false;
	}
	
}
