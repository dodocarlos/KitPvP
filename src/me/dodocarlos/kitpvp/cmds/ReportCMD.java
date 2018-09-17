package me.dodocarlos.kitpvp.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class ReportCMD implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length < 2){
				p.sendMessage(Methods.toColoredString(Vars.tag + Vars.infoColor + "Use: /report <nick> <motivo>"));
			}else{
				try{
					Player target = Bukkit.getPlayer(args[0]);
					StringBuilder reason = new StringBuilder();
					
					for(int i = 1; i < args.length; i++){
						reason.append(args[i]);
					}
					
					for(Player pl : Bukkit.getOnlinePlayers()){
						if(pl.hasPermission("report.view")){
							pl.sendMessage(Methods.toColoredString(Vars.tag + Vars.infoColor + "------ Report ------\n"
									+ Vars.defaultColor + "Reportou: " + Vars.infoColor + p.getDisplayName() + "\n"
									+ Vars.defaultColor + "Reportado: " + Vars.infoColor + target.getDisplayName() + "\n"
									+ Vars.defaultColor + "Motivo: " + Vars.infoColor + reason
									+ Vars.tag + Vars.infoColor + "------ Report ------"));
							
						}
					}
					
					p.sendMessage(Methods.toColoredString(Vars.tag + Vars.infoColor + "Seu report foi enviado!"));
					
				}catch(Exception e){
					p.sendMessage(Methods.toColoredString(Vars.tag + "&cEste jogador esta offline"));
				}
			}
		}
		
		return false;
	}
	
}
