package me.dodocarlos.kitpvp.feast;

import org.bukkit.Bukkit;

import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class FeastRunnable implements Runnable{

	@Override
	public void run() {
		if(Vars.timeToSpawnFeast > 0){
			switch(Vars.timeToSpawnFeast){
				case 300:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "5" + Vars.defaultColor + " minutos!"));
					break;
				case 240:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "4" + Vars.defaultColor + " minutos!"));
					break;
				case 180:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "3" + Vars.defaultColor + " minutos!"));
					break;
				case 120:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "2" + Vars.defaultColor + " minutos!"));
					break;
				case 60:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "1" + Vars.defaultColor + " minuto!"));
					break;
				case 30:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "30" + Vars.defaultColor + " segundos!"));
					break;
				case 10:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "10" + Vars.defaultColor + " segundos!"));
					break;
				case 5:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "5" + Vars.defaultColor + " segundos!"));
					break;
				case 4:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "4" + Vars.defaultColor + " segundos!"));
					break;
				case 3:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "3" + Vars.defaultColor + " segundos!"));
					break;
				case 2:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "2" + Vars.defaultColor + " segundos!"));
					break;
				case 1:
					Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O Feast vai ser spawnado em " +
					Vars.infoColor + "1" + Vars.defaultColor + " segundo!"));
					break;
			}
			
			Vars.timeToSpawnFeast --;
		}else{
			FeastManager.spawnFeast();
			Vars.timeToSpawnFeast = 300;
		}
	}
	
}
