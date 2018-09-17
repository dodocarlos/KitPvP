package me.dodocarlos.kitpvp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class SetCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission("cmd.set")){
				if(args.length < 1){
					p.sendMessage(Methods.toColoredString(Vars.tag + Vars.infoColor + "Use: /set <fps|rdm|mdr|1v1|1v1p1|1v1p2|lava|refil|mlg>"));
				}else{
					if(args[0].equalsIgnoreCase("fps")){
						Main.warps.setLocation("Fps", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("lava")){
						Main.warps.setLocation("Lava", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("refil")){
						Main.warps.setLocation("Refil", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("mlg")){
						Main.warps.setLocation("Mlg", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("rdm")){
						Main.warps.setLocation("Rdm", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("mdr")){
						Main.warps.setLocation("Mdr", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("1v1")){
						Main.warps.setLocation("1v1", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("1v1p1")){
						Main.warps.setLocation("1v1p1", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
					if(args[0].equalsIgnoreCase("1v1p2")){
						Main.warps.setLocation("1v1p2", p.getLocation());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "A warp " + Vars.infoColor 
								+ args[0].toUpperCase() + Vars.defaultColor + " foi definida"));
					}
				}
			}else{
				p.sendMessage(Methods.toColoredString(Vars.tag + "&cSem permissao"));
			}
		}
		
		return false;
	}
	
}
