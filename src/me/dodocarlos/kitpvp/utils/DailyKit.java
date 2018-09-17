package me.dodocarlos.kitpvp.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.Main;
import me.dodocarlos.kitpvp.cmds.Kit;

public class DailyKit {
	
	public static boolean canGetTheKit(Player p){
		return Long.valueOf(Main.db.getKitDiarioTime(p)) - new Date().getTime() >= 0;
	}
	
	public static void giveRandomKit(Player p){
		String kit = Kit.kits[new Random().nextInt(Kit.kits.length)];
		Main.db.addKitDiario(p, kit);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "perm player add " + p.getName() + " kit." + Main.db.getKitDiarioKit(p));
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce ganhou o kit " + Vars.infoColor + kit));
	}
	
	public static void checkRemoveKit(Player p){
		if(Long.valueOf(Main.db.getKitDiarioTime(p)) - Calendar.getInstance().getTimeInMillis() <= 0){
			//remove permission
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "perm player remove " + p.getName() + " kit." + Main.db.getKitDiarioKit(p));
		}
	}
	
}
