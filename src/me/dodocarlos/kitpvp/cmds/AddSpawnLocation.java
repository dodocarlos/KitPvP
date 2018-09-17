package me.dodocarlos.kitpvp.cmds;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Vars;

public class AddSpawnLocation implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("cmd.addspawnlocation")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				Location l = new Location(p.getWorld(), (int) p.getLocation().getX(), (int) p.getLocation().getY() + 2, (int) p.getLocation().getZ());
				Arrays.spawnLocations.add(l);

				ArrayList<String> save = new ArrayList<> ();
				for(Location l2 : Arrays.spawnLocations){
					if(l2 != null){
						save.add(l2.getWorld().getName() + ":" + l2.getX() + ":" + l2.getY() + ":" + l2.getZ());
					}
				}
				
				Vars.main.getConfig().set("SpawnLocations", save);
				Vars.main.saveConfig();
				
				p.sendMessage(Vars.tag + Vars.infoColor + "Novo ponto de spawn adicionado!");
			}
		}else{
			sender.sendMessage(Vars.tag + "§cSem permissao");
		}
		
		return false;
	}
	
}
