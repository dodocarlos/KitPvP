package me.dodocarlos.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitInv {

	private Player p;
	private Inventory invkit;
	private Inventory invkit2;
	
	public KitInv(Player p, String kits[]){
		Inventory kitinv = Bukkit.createInventory(p, 54, "§a§lKits");
		Inventory kitinv2 = Bukkit.createInventory(p, 54, "§a§lKits 2");
		
		@SuppressWarnings("deprecation")
		ItemStack vidro = new ItemStack(Material.getMaterial(160));
		ItemMeta vidrometa = vidro.getItemMeta();
		vidrometa.setDisplayName(" ");
		vidro.setItemMeta(vidrometa);
		
		ItemStack wool = new ItemStack(Material.WOOL);
		ItemMeta woolmeta = vidro.getItemMeta();
		woolmeta.setDisplayName(" ");
		wool.setItemMeta(woolmeta);
		
		ItemStack wool2 = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta woolmeta2 = vidro.getItemMeta();
		woolmeta2.setDisplayName("§aProxima pagina");
		wool2.setItemMeta(woolmeta2);
		
		ItemStack wool3 = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta woolmeta3 = vidro.getItemMeta();
		woolmeta3.setDisplayName("§aPagina anterior");
		wool3.setItemMeta(woolmeta3);
		
		ItemStack vidro2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta imv = vidro2.getItemMeta();
		imv.setDisplayName(" ");
		vidro2.setItemMeta(imv);
		
		ItemStack dima = new ItemStack(Material.DIAMOND);
		ItemMeta dimameta = vidro.getItemMeta();
		dimameta.setDisplayName(" ");
		dima.setItemMeta(dimameta);
		
		kitinv.setItem(0, wool);
		kitinv.setItem(1, vidro2);
		kitinv.setItem(2, vidro2);
		kitinv.setItem(3, vidro2);
		kitinv.setItem(4, dima);
		kitinv.setItem(5, vidro2);
		kitinv.setItem(6, vidro2);
		kitinv.setItem(7, vidro2);
		kitinv.setItem(8, wool2);
		
		kitinv2.setItem(0, wool3);
		kitinv2.setItem(1, vidro2);
		kitinv2.setItem(2, vidro2);
		kitinv2.setItem(3, vidro2);
		kitinv2.setItem(4, dima);
		kitinv2.setItem(5, vidro2);
		kitinv2.setItem(6, vidro2);
		kitinv2.setItem(7, vidro2);
		kitinv2.setItem(8, wool);
								
		for(String kit : kits){
			if(p.hasPermission("kit." + kit)){
				ItemStack item = Arrays.kitMaterial.get(kit);
				ItemMeta im = item.getItemMeta();
				im.setDisplayName(Vars.infoColor + kit);
				item.setItemMeta(im);								
				if(kitinv.getItem(53) == null){
					kitinv.addItem(item);
				}else{
					kitinv2.addItem(item);
				}
			}
		}
		
		for(int i = 0; i < 54; i++){
			if(kitinv.getItem(i) == null){
				kitinv.setItem(i, vidro);
			}
			if(kitinv2.getItem(i) == null){
				kitinv2.setItem(i, vidro);
			}
		}
		
		setInv1(kitinv);
		setInv2(kitinv2);
		
		p.openInventory(kitinv);
	}
	
	public Player getPlayer(){
		return this.p;
	}
	
	public Inventory getInv1(){
		return this.invkit;
	}
	
	public Inventory getInv2(){
		return this.invkit2;
	}
	
	public void setInv1(Inventory inv){
		this.invkit = inv;
	}
	
	public void setInv2(Inventory inv){
		this.invkit2 = inv;
	}
	
}
