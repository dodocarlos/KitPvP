package me.dodocarlos.kitpvp.feast;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class FeastManager {

	public static void spawnFeast(){
		//Mesa de encantamentos
		Location mesa = Vars.feastLocation;
		
		//Baus
		Location c1 = mesa.clone().add(1, -1, -1);
		Location c2 = mesa.clone().add(1, -1, 1);
		Location c3 = mesa.clone().add(-1, -1, -1);
		Location c4 = mesa.clone().add(-1, -1, 1);
		Location c5 = mesa.clone().add(2, -1, 0);
		Location c6 = mesa.clone().add(-2, -1, 0);
		Location c7 = mesa.clone().add(0, -1, 2);
		Location c8 = mesa.clone().add(0, -1, -2);
		Location c9 = mesa.clone().add(2, -1, 2);
		Location c10 = mesa.clone().add(2, -1, -2);
		Location c11 = mesa.clone().add(-2, -1, 2);
		Location c12 = mesa.clone().add(-2, -1, -2);

		Chest chest1 = (Chest) c1.getBlock().getState();
		Chest chest2 = (Chest) c2.getBlock().getState();
		Chest chest3 = (Chest) c3.getBlock().getState();
		Chest chest4 = (Chest) c4.getBlock().getState();
		Chest chest5 = (Chest) c5.getBlock().getState();
		Chest chest6 = (Chest) c6.getBlock().getState();
		Chest chest7 = (Chest) c7.getBlock().getState();
		Chest chest8 = (Chest) c8.getBlock().getState();
		Chest chest9 = (Chest) c9.getBlock().getState();
		Chest chest10 = (Chest) c10.getBlock().getState();
		Chest chest11 = (Chest) c11.getBlock().getState();
		Chest chest12 = (Chest) c12.getBlock().getState();
		
		Chest baus[] = {chest1, chest2, chest3, chest4, chest5, chest6, chest7, chest8, chest9, chest10, chest11, chest12};
		for(Chest bau : baus){
			Inventory bauinv = bau.getInventory();
			bauinv.clear();
			for(ItemStack item : Vars.feastItems.keySet()){
				if(new Random().nextInt(100) <= Vars.feastItems.get(item)){
					bauinv.addItem(item);
				}
			}
		}
		
		Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.infoColor + "O Feast foi spawnado, corra para pegá-lo!"));
		
	}
	
}
