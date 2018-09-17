package me.dodocarlos.kitpvp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class SetFeastLocationCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("cmd.setfeastlocation")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				Vars.feastConfig.setLocation("FeastEnchantLocation", p.getLocation().getBlock().getLocation().add(0, -1, 0));
				p.sendMessage(Methods.toColoredString(Vars.tag + Vars.infoColor + "Spawn do feast definido!"));
			}
		}
		
		return false;
	}
	
}
