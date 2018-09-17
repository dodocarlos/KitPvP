package me.dodocarlos.kitpvp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Arrays {
	
	public static ArrayList<String> admin = new ArrayList<String> ();
	public static ArrayList<String> build = new ArrayList<String> ();
	public static ArrayList<String> spawnProtection = new ArrayList<String> ();
	public static ArrayList<Location> spawnLocations = new ArrayList<Location> ();
	
	public static HashMap<String, String> kitPlayer = new HashMap<String, String>();
	public static HashMap<String, ItemStack> kitMaterial = new HashMap<String, ItemStack>();
	public static HashMap<String, ItemStack[]> adminItems = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> adminArmor = new HashMap<String, ItemStack[]>();
	public static Hashtable<String, String[]> playerTag = new Hashtable<> ();
	public static HashMap<String, ItemStack[]> skititem = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> skitarmor = new HashMap<String, ItemStack[]>();
	
	
	public static ArrayList<String> scoreBoard = new ArrayList<> ();
	
}
