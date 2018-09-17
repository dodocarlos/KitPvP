package me.dodocarlos.kitpvp.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.kits.FPS;
import me.dodocarlos.kitpvp.kits.Lava;
import me.dodocarlos.kitpvp.kits.OneVsOne;
import me.dodocarlos.kitpvp.kits.Refil;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Warps implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
		if(sender instanceof Player){
			final Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("fps")){
				try{
					final Location l = Main.warps.getLocation("Fps");
					
					p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sera teletransportado em" 
					+ Vars.infoColor + " 3" + Vars.defaultColor + " segundos!"));
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable() {
						
						@Override
						public void run() {
							p.teleport(l);
							FPS.darKit(p);
						}
					}, 60);
					
				}catch(Exception e){
					p.sendMessage(Methods.toColoredString(Vars.tag + "&cWarp nao definida"));
				}
			}
			if(cmd.getName().equalsIgnoreCase("lava")){
				try{
					final Location l = Main.warps.getLocation("Lava");
					
					p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sera teletransportado em" 
					+ Vars.infoColor + " 3" + Vars.defaultColor + " segundos!"));
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable() {
						
						@Override
						public void run() {
							p.teleport(l);
							Lava.darKit(p);
						}
					}, 60);
					
				}catch(Exception e){
					p.sendMessage(Methods.toColoredString(Vars.tag + "&cWarp nao definida"));
				}
			}
			if(cmd.getName().equalsIgnoreCase("refil")){
				try{
					final Location l = Main.warps.getLocation("Refil");
					
					p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sera teletransportado em" 
					+ Vars.infoColor + " 3" + Vars.defaultColor + " segundos!"));
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable() {
						
						@Override
						public void run() {
							p.teleport(l);
							Refil.darKit(p);
						}
					}, 60);
					
				}catch(Exception e){
					p.sendMessage(Methods.toColoredString(Vars.tag + "&cWarp nao definida"));
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("1v1")){
				try{
					final Location l = Main.warps.getLocation("1v1");
					
					p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sera teletransportado em" 
					+ Vars.infoColor + " 3" + Vars.defaultColor + " segundos!"));
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable() {
						
						@Override
						public void run() {
							p.teleport(l);
							OneVsOne.darKit(p);
						}
					}, 60);
					
				}catch(Exception e){
					p.sendMessage(Methods.toColoredString(Vars.tag + "&cWarp nao definida"));
				}
			}
			
//			if(cmd.getName().equalsIgnoreCase("mlg")){
//				try{
//					final Location l = Main.warps.getLocation("Mlg");
//					
//					p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sera teletransportado em" 
//					+ Vars.infoColor + " 3" + Vars.defaultColor + " segundos!"));
//					
//					Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable() {
//						
//						@Override
//						public void run() {
//							p.teleport(l);
//							MLG.darKit(p);
//						}
//					}, 60);
//					
//				}catch(Exception e){
//					p.sendMessage(Methods.toColoredString(Vars.tag + "&cWarp nao definida"));
//				}
//			}
		}
		
		return false;
	}
	
}
