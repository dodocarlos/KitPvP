package me.dodocarlos.kitpvp.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Build implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

			if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("cmd.build")){
					if(!Arrays.build.contains(p.getName())){
						Arrays.build.add(p.getName());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce " + Vars.infoColor + "entrou " + Vars.defaultColor + "no modo " + Vars.infoColor + "build"));
						p.setGameMode(GameMode.CREATIVE);
					}else{
						Arrays.build.remove(p.getName());
						p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce " + Vars.infoColor + "saiu " + Vars.defaultColor + "do modo " + Vars.infoColor + "build"));
						p.setGameMode(GameMode.ADVENTURE);
					}
				}else{
					p.sendMessage(Methods.toColoredString(Vars.tag + "Sem permissao"));
				}
			}
			
		return false;
	}

}
