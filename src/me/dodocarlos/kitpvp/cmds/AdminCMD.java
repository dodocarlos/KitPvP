package me.dodocarlos.kitpvp.cmds;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class AdminCMD implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender.hasPermission("cmd.admin")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(Arrays.admin.contains(p.getName())){
					//Remove
					Arrays.admin.remove(p.getName());
					p.getInventory().clear();
					p.getInventory().setContents(Arrays.adminItems.get(p.getName()));
					p.getInventory().setArmorContents(Arrays.adminArmor.get(p.getName()));
					p.setGameMode(GameMode.ADVENTURE);
					
					Arrays.adminItems.remove(p.getName());
					Arrays.adminArmor.remove(p.getName());
					
					for(Player pl : Bukkit.getOnlinePlayers()){
						pl.showPlayer(p);
					}
					
					p.sendMessage(Methods.toColoredString(Vars.tag + "&cVoce saiu do modo admin"));
				}else{
					//Add
					Arrays.admin.add(p.getName());

					Arrays.adminItems.put(p.getName(), p.getInventory().getContents());
					Arrays.adminArmor.put(p.getName(), p.getInventory().getArmorContents());
					
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
					p.setGameMode(GameMode.CREATIVE);
					
					for(Player pl : Bukkit.getOnlinePlayers()){
						if(!pl.hasPermission("cmd.admin")){
							pl.hidePlayer(p);
						}
					}
					
					p.sendMessage(Methods.toColoredString(Vars.tag + "&aVoce entrou no modo admin"));
				}
			}
		}
		
		return false;
	}

	
	
}
