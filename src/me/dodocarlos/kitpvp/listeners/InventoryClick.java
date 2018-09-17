package me.dodocarlos.kitpvp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.dodocarlos.kitpvp.utils.Vars;

public class InventoryClick implements Listener{

	@EventHandler
	public void clickInv(InventoryClickEvent e){
		if(e.getInventory().getName().equals(Vars.infoColor + "Warps")){
			Player p = (Player) e.getWhoClicked();
			if(e.getCurrentItem() != null){
				p.closeInventory();
				if(e.getCurrentItem().getType().equals(Material.GLASS)){
					p.chat("/fps");
				}
				if(e.getCurrentItem().getType().equals(Material.BLAZE_ROD)){
					p.chat("/1v1");
				}
				if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)){
					p.chat("/rdm");
				}
				if(e.getCurrentItem().getType().equals(Material.QUARTZ_BLOCK)){
					p.chat("/mdr");
				}
				if(e.getCurrentItem().getType().equals(Material.LAVA_BUCKET)){
					p.chat("/lava");
				}
			}
			e.setCancelled(true);
		}
	}
	
}
