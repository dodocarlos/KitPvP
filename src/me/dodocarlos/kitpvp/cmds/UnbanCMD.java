package me.dodocarlos.kitpvp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.utils.Vars;

public class UnbanCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

			if(sender.hasPermission("dHG.cmd.unban")){
				if(args.length < 1){
					sender.sendMessage(Vars.tag + Vars.defaultColor +  "Use: /unban " + Vars.infoColor + " nick");	
					return true;
				}
				String nick = args[0];
				
				if(sender instanceof Player){
					if(!Main.db.hasBanned(nick)){
						sender.sendMessage(Vars.tag + Vars.defaultColor +  "Este jogador nao esta banido");
						return true;
					}
					Main.db.unbanPlayer(nick);
				}else{
					if(!Main.db.hasBanned(nick)){
						sender.sendMessage(Vars.tag + Vars.defaultColor +  "Este jogador nao esta banido");
						return true;
					}
					Main.db.unbanPlayer(nick);
				}
				sender.sendMessage(Vars.tag + Vars.defaultColor +  "O jogador " + Vars.infoColor + nick + Vars.defaultColor + " foi desbanido");
			}
		
		return false;
	}

	
	
}
