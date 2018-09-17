package me.dodocarlos.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class SoupSign implements Listener{

	@EventHandler
	public void place(SignChangeEvent e){
		if(e.getPlayer().hasPermission("event.soupsign")){
			if(e.getLine(0).equalsIgnoreCase("[recraft]")){
				e.setLine(0, Methods.toColoredString(Vars.infoColor + "Placa"));
				e.setLine(2, Methods.toColoredString(Vars.defaultColor + "Recraft"));
			}
			if(e.getLine(0).equalsIgnoreCase("[sopa]")){
				e.setLine(0, Methods.toColoredString(Vars.infoColor + "Placa"));
				e.setLine(2, Methods.toColoredString(Vars.defaultColor + "Sopas"));		
			}
		}
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.SIGN)
					|| e.getClickedBlock().getType().equals(Material.SIGN_POST)
					|| e.getClickedBlock().getType().equals(Material.WALL_SIGN)){
				Sign placa = (Sign) e.getClickedBlock().getState();
				if(placa.getLine(2).equals(Methods.toColoredString(Vars.defaultColor + "Recraft"))){
					e.getPlayer().getInventory().addItem(new ItemStack(Material.BOWL, 64));
					e.getPlayer().getInventory().addItem(new ItemStack(Material.RED_MUSHROOM, 64));
					e.getPlayer().getInventory().addItem(new ItemStack(Material.BROWN_MUSHROOM, 64));
					e.getPlayer().updateInventory();
				}
				if(placa.getLine(2).equals(Methods.toColoredString(Vars.defaultColor + "Sopas"))){
					Inventory sopas = Bukkit.createInventory(e.getPlayer(), 36);
					for(int i = 0; i < 36; i ++){
						sopas.addItem(new ItemStack(Material.MUSHROOM_SOUP));
					}
					e.getPlayer().openInventory(sopas);
				}
			}
		}
	}
	
}
