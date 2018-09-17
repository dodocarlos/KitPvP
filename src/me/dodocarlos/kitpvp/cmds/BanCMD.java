package me.dodocarlos.kitpvp.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.utils.Vars;

public class BanCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

			if(sender.hasPermission("cmd.ban")){
				if(args.length < 2){
					sender.sendMessage(Vars.tag + Vars.defaultColor + "Use: /ban " + Vars.infoColor + "nick " + Vars.infoColor + "motivo");	
					return true;
				}
				String nick = args[0];
				StringBuilder motivo = new StringBuilder();
				for(int i = 1; i < args.length; i ++){
					motivo.append(args[i] + " ");
				}
				
				if(sender instanceof Player){
					Player banner = (Player) sender;
					if(!Main.db.hasBanned(nick)){
						try{
							Player p = Bukkit.getPlayer(nick);
							Main.db.banPlayer(p, banner.getName(), motivo.toString(), "0");
							p.kickPlayer(Vars.tag + Vars.defaultColor + "Voce foi banido\nMotivo: " + Vars.infoColor + motivo.toString());
						}catch(Exception e){
							Main.db.banPlayer(nick, banner.getName(), motivo.toString(), "0");
						}
					}else{
						sender.sendMessage(Vars.tag + Vars.defaultColor + "Esse jogador ja esta banido");
						return true;
					}
				}else{
					if(!Main.db.hasBanned(nick)){
						try{
							Player p = Bukkit.getPlayer(nick);
							Main.db.banPlayer(p, "Console", motivo.toString(), "0");
							p.kickPlayer(Vars.tag + Vars.defaultColor + "Voce foi banido \nMotivo: " + Vars.infoColor + motivo.toString());
						}catch(Exception e){
							Main.db.banPlayer(nick, "Console", motivo.toString(), "0");
						}
					}else{
						sender.sendMessage(Vars.tag + Vars.defaultColor + "Esse jogador ja esta banido");
						return true;
					}
				}
				sender.sendMessage(Vars.tag + Vars.defaultColor + "O jogador " + Vars.infoColor + nick + Vars.defaultColor + " foi banido");
			}
		
		return false;
	}

	
	
}
