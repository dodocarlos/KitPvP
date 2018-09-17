package me.dodocarlos.kitpvp.utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Box implements Listener {
	
	private Location lado1;
	private Location lado2;
	
	private Location saida;
	
	private ArrayList<Player> inbox = new ArrayList<Player>();	
	private ArrayList<Block> blocos = new ArrayList<Block>();

	public Box(Location loc, Type type){
		if(type.equals(Type.Gladiator)){
			saida = loc;
			int x1 = (int) loc.getX();
			int y1 = (int) loc.clone().add(0, 100, 0).getY();
			int z1 = (int) loc.getZ();
		
			lado1 = new Location(loc.getWorld(), (x1 + 2), (y1 + 1), (z1 + 2));
			lado2 = new Location(loc.getWorld(), (x1 + 8), (y1 + 1), (z1 + 8));
		
		
				for(int x = x1; x <= x1 + 10; x++){
					if(x == x1 || x == x1 + 10){
						for(int z = z1; z <= z1 + 10; z++){
							for(int y = y1; y <= y1 + 7; y ++){
								loc.getWorld().getBlockAt(x, y ,z).setType(Material.GLASS);							
								blocos.add(loc.getWorld().getBlockAt(x, y ,z));
							}
						}
					}
				}
				for(int z = z1; z <= z1 + 10; z++){
					if(z == z1 || z == z1 + 10){
						for(int x = x1; x <= x1 + 10; x++){
							for(int y = y1; y <= y1 + 7; y ++){
								loc.getWorld().getBlockAt(x, y ,z).setType(Material.GLASS);							
								blocos.add(loc.getWorld().getBlockAt(x, y ,z));
							}
						}
					}
				}
				for(int y = y1; y <= y1 + 7; y++){
					if(y == y1 || y == y1 + 7){
						for(int x = x1; x <= x1 + 10; x++){
							for(int z = z1; z <= z1 + 10; z ++){
								loc.getWorld().getBlockAt(x, y ,z).setType(Material.GLASS);
								blocos.add(loc.getWorld().getBlockAt(x, y ,z));
							}
						}
					}
				}
			}
		
		if(type.equals(Type.ArenaBedrock)){
			saida = loc;
			
			int x1 = (int) loc.getX();
			int y1 = (int) loc.clone().add(0, 100, 0).getY();
			int z1 = (int) loc.getZ();
			
			lado1 = new Location(loc.getWorld(), (x1 + 10), (y1 + 1), (z1 + 10));
			
			for(int x = x1; x <= x1 + 20; x++){
				if(x == x1 || x == x1 + 20){
					for(int z = z1; z <= z1 + 20; z++){
						for(int y = y1; y <= y1 + 15; y ++){
							loc.getWorld().getBlockAt(x, y ,z).setType(Material.BEDROCK);							
							blocos.add(loc.getWorld().getBlockAt(x, y ,z));
						}
					}
				}
			}
			for(int z = z1; z <= z1 + 20; z++){
				if(z == z1 || z == z1 + 20){
					for(int x = x1; x <= x1 + 20; x++){
						for(int y = y1; y <= y1 + 15; y ++){
							loc.getWorld().getBlockAt(x, y ,z).setType(Material.BEDROCK);							
							blocos.add(loc.getWorld().getBlockAt(x, y ,z));
						}
					}
				}
			}
			for(int y = y1; y <= y1 + 15; y++){
				if(y == y1){
					for(int x = x1; x <= x1 + 20; x++){
						for(int z = z1; z <= z1 + 20; z ++){
							loc.getWorld().getBlockAt(x, y ,z).setType(Material.BEDROCK);
							blocos.add(loc.getWorld().getBlockAt(x, y ,z));
						}
					}
				}
			}
			
		}
		
	}
	
	public enum Type{
		Gladiator, ArenaBedrock;
	}
	
	public void teleportToBox(Player p, Player p2){
		p.teleport(lado1);
		p2.teleport(lado2);
		inbox.add(p);
		inbox.add(p2);
	}
	
	public void removeBox(){
		for(Block b : blocos){
			b.setType(Material.AIR);
		}
		inbox.clear();
	}
	
	public boolean isBoxBlock(Block b){
		return blocos.contains(b);
	}
	
	public Location getSaida(){
		return saida;
	}
	
	public boolean isInBox(Player p){
		return inbox.contains(p);
	}
	
	public Location getLado1(){
		return lado1;
	}
	
	public Location getLado2(){
		return lado2;
	}
}


