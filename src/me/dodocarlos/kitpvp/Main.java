package me.dodocarlos.kitpvp;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.dodocarlos.kitpvp.admin.AdminListener;
import me.dodocarlos.kitpvp.cmds.AddSpawnLocation;
import me.dodocarlos.kitpvp.cmds.AdminCMD;
import me.dodocarlos.kitpvp.cmds.BanCMD;
import me.dodocarlos.kitpvp.cmds.Build;
import me.dodocarlos.kitpvp.cmds.Kit;
import me.dodocarlos.kitpvp.cmds.ReportCMD;
import me.dodocarlos.kitpvp.cmds.ResetKit;
import me.dodocarlos.kitpvp.cmds.SKitCMD;
import me.dodocarlos.kitpvp.cmds.SetCMD;
import me.dodocarlos.kitpvp.cmds.SetFeastLocationCMD;
import me.dodocarlos.kitpvp.cmds.SetSpawn;
import me.dodocarlos.kitpvp.cmds.SpawnCMD;
import me.dodocarlos.kitpvp.cmds.Tag;
import me.dodocarlos.kitpvp.cmds.UnbanCMD;
import me.dodocarlos.kitpvp.cmds.Warps;
import me.dodocarlos.kitpvp.kits.Anchor;
import me.dodocarlos.kitpvp.kits.Archer;
import me.dodocarlos.kitpvp.kits.Berserker;
import me.dodocarlos.kitpvp.kits.Boxer;
import me.dodocarlos.kitpvp.kits.C4;
import me.dodocarlos.kitpvp.kits.Camel;
import me.dodocarlos.kitpvp.kits.Cannibal;
import me.dodocarlos.kitpvp.kits.CheckPoint;
import me.dodocarlos.kitpvp.kits.CookieMonster;
import me.dodocarlos.kitpvp.kits.Fireman;
import me.dodocarlos.kitpvp.kits.Fisherman;
import me.dodocarlos.kitpvp.kits.ForceField;
import me.dodocarlos.kitpvp.kits.Gladiator;
import me.dodocarlos.kitpvp.kits.Grappler;
import me.dodocarlos.kitpvp.kits.Hulk;
import me.dodocarlos.kitpvp.kits.Kangaroo;
import me.dodocarlos.kitpvp.kits.KitManager;
import me.dodocarlos.kitpvp.kits.Lava;
import me.dodocarlos.kitpvp.kits.Ninja;
import me.dodocarlos.kitpvp.kits.OneVsOne;
import me.dodocarlos.kitpvp.kits.Refil;
import me.dodocarlos.kitpvp.kits.Stomper;
import me.dodocarlos.kitpvp.kits.Thor;
import me.dodocarlos.kitpvp.kits.Turtle;
import me.dodocarlos.kitpvp.kits.Viper;
import me.dodocarlos.kitpvp.listeners.Blocks;
import me.dodocarlos.kitpvp.listeners.Chat;
import me.dodocarlos.kitpvp.listeners.Damage;
import me.dodocarlos.kitpvp.listeners.Death;
import me.dodocarlos.kitpvp.listeners.EntitySpawn;
import me.dodocarlos.kitpvp.listeners.Explosion;
import me.dodocarlos.kitpvp.listeners.Hunger;
import me.dodocarlos.kitpvp.listeners.Interact;
import me.dodocarlos.kitpvp.listeners.InteractHotBar;
import me.dodocarlos.kitpvp.listeners.InventoryClick;
import me.dodocarlos.kitpvp.listeners.Items;
import me.dodocarlos.kitpvp.listeners.Join;
import me.dodocarlos.kitpvp.listeners.JumpBlock;
import me.dodocarlos.kitpvp.listeners.Ping;
import me.dodocarlos.kitpvp.listeners.PreLogin;
import me.dodocarlos.kitpvp.listeners.Quit;
import me.dodocarlos.kitpvp.listeners.Respawn;
import me.dodocarlos.kitpvp.listeners.Sopa;
import me.dodocarlos.kitpvp.listeners.SoupSign;
import me.dodocarlos.kitpvp.listeners.Void;
import me.dodocarlos.kitpvp.listeners.Weather;
import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Config;
import me.dodocarlos.kitpvp.utils.DB;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;
import net.minecraft.server.v1_7_R4.DamageSource;

public class Main extends JavaPlugin{
	
	public static DB db;
	public static Config warps;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		for(Entity en : Bukkit.getWorld("world").getEntities()){
			if(en instanceof Animals || en instanceof Monster){
				en.remove();
			}
		}
		
		Vars.main = this;
		warps = new Config("Warps");
		Vars.feastConfig = new Config("Feast");
		config();
		Vars.setup();
		Bukkit.getConsoleSender().sendMessage("§a[KitPvP]Iniciando o plugin...");
		System.gc();
//		Stats.setup();
		db = new DB();
		Methods.setKitMaterials();
		
		//Registro de eventos (listeners)
		Bukkit.getPluginManager().registerEvents(new Ping(), this);
		Bukkit.getPluginManager().registerEvents(new Join(), this);
		Bukkit.getPluginManager().registerEvents(new Items(), this);
		Bukkit.getPluginManager().registerEvents(new Blocks(), this);
		Bukkit.getPluginManager().registerEvents(new Quit(), this);
		Bukkit.getPluginManager().registerEvents(new Hunger(), this);
		Bukkit.getPluginManager().registerEvents(new Sopa(), this);
		Bukkit.getPluginManager().registerEvents(new EntitySpawn(), this);
		Bukkit.getPluginManager().registerEvents(new Weather(), this);
		Bukkit.getPluginManager().registerEvents(new Kit(), this);
		Bukkit.getPluginManager().registerEvents(new InteractHotBar(), this);
		Bukkit.getPluginManager().registerEvents(new Death(), this);
		Bukkit.getPluginManager().registerEvents(new Respawn(), this);
		Bukkit.getPluginManager().registerEvents(new Damage(), this);
		Bukkit.getPluginManager().registerEvents(new Chat(), this);
		Bukkit.getPluginManager().registerEvents(new PreLogin(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new JumpBlock(), this);
		Bukkit.getPluginManager().registerEvents(new Lava(), this);
		Bukkit.getPluginManager().registerEvents(new Interact(), this);
		Bukkit.getPluginManager().registerEvents(new Explosion(), this);
		Bukkit.getPluginManager().registerEvents(new SoupSign(), this);
		Bukkit.getPluginManager().registerEvents(new AdminListener(), this);
		Bukkit.getPluginManager().registerEvents(new OneVsOne(), this);
		Bukkit.getPluginManager().registerEvents(new Refil(), this);
		Bukkit.getPluginManager().registerEvents(new Void(), this);
		
		//Registro de eventos de kits
		getServer().getPluginManager().registerEvents(new Stomper(), this);
		getServer().getPluginManager().registerEvents(new Viper(), this);
		getServer().getPluginManager().registerEvents(new Gladiator(), this);
		getServer().getPluginManager().registerEvents(new Berserker(), this);
		getServer().getPluginManager().registerEvents(new Thor(), this);
		getServer().getPluginManager().registerEvents(new Anchor(), this);
		getServer().getPluginManager().registerEvents(new Archer(), this);
		getServer().getPluginManager().registerEvents(new Cannibal(), this);
		getServer().getPluginManager().registerEvents(new Boxer(), this);
		getServer().getPluginManager().registerEvents(new Camel(), this);
		getServer().getPluginManager().registerEvents(new CheckPoint(), this);
		getServer().getPluginManager().registerEvents(new CookieMonster(), this);
		getServer().getPluginManager().registerEvents(new Fireman(), this);
		getServer().getPluginManager().registerEvents(new Fisherman(), this);
		getServer().getPluginManager().registerEvents(new ForceField(), this);
		getServer().getPluginManager().registerEvents(new C4(), this);
		getServer().getPluginManager().registerEvents(new Turtle(), this);
		getServer().getPluginManager().registerEvents(new Ninja(), this);
		getServer().getPluginManager().registerEvents(new Kangaroo(), this);
		getServer().getPluginManager().registerEvents(new Grappler(), this);
		getServer().getPluginManager().registerEvents(new Hulk(), this);
		
		//Registro de comandos
		getCommand("setSpawn").setExecutor(new SetSpawn());
		getCommand("kit").setExecutor(new Kit());
		getCommand("resetkit").setExecutor(new ResetKit());
		getCommand("build").setExecutor(new Build());
		getCommand("tag").setExecutor(new Tag());
		getCommand("addspawnlocation").setExecutor(new AddSpawnLocation());
		getCommand("report").setExecutor(new ReportCMD());
		getCommand("setfeastlocation").setExecutor(new SetFeastLocationCMD());
		getCommand("spawn").setExecutor(new SpawnCMD());
		getCommand("admin").setExecutor(new AdminCMD());
		getCommand("unban").setExecutor(new UnbanCMD());
		getCommand("ban").setExecutor(new BanCMD());
		getCommand("skit").setExecutor(new SKitCMD());
		
		//Warps
		getCommand("set").setExecutor(new SetCMD());
		getCommand("fps").setExecutor(new Warps());
		getCommand("rdm").setExecutor(new Warps());
		getCommand("mdr").setExecutor(new Warps());
		getCommand("1v1").setExecutor(new Warps());
		getCommand("lava").setExecutor(new Warps());
		getCommand("refil").setExecutor(new Warps());
		getCommand("mlg").setExecutor(new Warps());
		
		for(Player p : Bukkit.getOnlinePlayers()){
			p.teleport(Vars.spawn);
			Arrays.playerTag.put(p.getName(), new String[]{"§7", "NORMAL"});
			Methods.giveSpawnItems(p);
			Methods.updatePvPScore(p);
			p.setLevel(0);
			p.setExp(0f);
			KitManager.removeKit(p);
		}
		
		Bukkit.getConsoleSender().sendMessage("§a[KitPvP]Plugin iniciado com sucesso !");
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
					Methods.updatePvPScore(p);
				}
			}
		}, 0l, 20l);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){				
				for(String name : ForceField.ffAtivo){
					Player p = Bukkit.getPlayer(name);
					for(Entity en : p.getNearbyEntities(4, 4, 4)){
						((CraftEntity)en).getHandle().damageEntity(DamageSource.GENERIC, 3f);
					}
				}
				
			}
		}, 0, 10l);
		
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§c[KitPvP]Desativando o plugin...");
		System.gc();
		
		
		
		Bukkit.getConsoleSender().sendMessage("§c[KitPvP]Plugin desativado com sucesso !");
	}
	
	public void config(){
		File configf = new File(getDataFolder(), "config.yml");
		if(!configf.exists()){
			saveDefaultConfig();		
		}	
	}
	
}
