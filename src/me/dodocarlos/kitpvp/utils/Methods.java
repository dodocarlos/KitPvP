package me.dodocarlos.kitpvp.utils;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import me.dodocarlos.kitpvp.Main;

public class Methods {
	
	public static String toColoredString(String s){
		return s.replaceAll("&", "§");
	}
	
	public static void giveSpawnItems(Player p){
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		ItemStack kits = new ItemStack(Material.CHEST);
		ItemMeta kitsmeta = kits.getItemMeta();
		kitsmeta.setDisplayName(Methods.toColoredString(Vars.infoColor + "Kits"));
		kits.setItemMeta(kitsmeta);

		ItemStack dailyKit = new ItemStack(Material.ENDER_CHEST);
		ItemMeta dailymeta = dailyKit.getItemMeta();
		dailymeta.setDisplayName(Methods.toColoredString(Vars.infoColor + "Kit Diario"));
		dailyKit.setItemMeta(dailymeta);
		
		ItemStack warps = new ItemStack(Material.COMPASS);
		ItemMeta warpsmeta = warps.getItemMeta();
		warpsmeta.setDisplayName(Methods.toColoredString(Vars.infoColor + "Warps"));
		warps.setItemMeta(warpsmeta);
		
		ItemStack loja = new ItemStack(Material.EMERALD);
		ItemMeta lojameta = loja.getItemMeta();
		lojameta.setDisplayName(Methods.toColoredString(Vars.infoColor + "Loja"));
		loja.setItemMeta(lojameta);
		
		p.getInventory().setItem(0, warps);
		p.getInventory().setItem(2, dailyKit);
		p.getInventory().setItem(4, kits);
		p.getInventory().setItem(8, loja);
		p.getInventory().setHeldItemSlot(4);
	}
	
	public static void updatePvPScore(Player p){
		SimpleScoreboard sb = new SimpleScoreboard(Methods.toColoredString(Vars.scoreTitle));
		
		for(String line : Arrays.scoreBoard){
			sb.add(Methods.toColoredString(line.replaceAll("%kit%", Arrays.kitPlayer.containsKey(p.getName()) ? Arrays.kitPlayer.get(p.getName()) : "Nenhum").replaceAll("%kills%", Main.db.getKills(p) + "").replaceAll("%deaths%", Main.db.getDeaths(p) + "").replaceAll("%xp%", Main.db.getExp(p) + "").replaceAll("%kd%", (Main.db.getDeaths(p) > 0 ? Main.db.getKills(p) / Main.db.getDeaths(p) : 0) + "")));
		}
		
		sb.build();
		setupTags(sb);
		p.setScoreboard(sb.getScoreboard());
	}
	
	public static ItemStack createItem(Material material, int Byte, List<String> lore){
		ItemStack item = new ItemStack(material, 1, (byte)Byte);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}	
	
	public static void setKitMaterials(){
		Arrays.kitMaterial.put("PvP", createItem(Material.STONE_SWORD, 0, java.util.Arrays.asList(Vars.defaultColor + "Kit padrao", Vars.defaultColor + "Sem habilidade")));
		Arrays.kitMaterial.put("Stomper", createItem(Material.IRON_BOOTS, 0, java.util.Arrays.asList(Vars.defaultColor + "Pisoteie seus inimigos,", Vars.defaultColor + "dando a eles o seu dano de queda")));
		Arrays.kitMaterial.put("Viper", createItem(Material.SPIDER_EYE, 0, java.util.Arrays.asList(Vars.defaultColor + "Tenha 33% de change de", Vars.defaultColor + "envenenar seus inimigos")));
		Arrays.kitMaterial.put("Gladiator", createItem(Material.IRON_FENCE, 0, java.util.Arrays.asList(Vars.defaultColor + "Crie uma arena de vidro aonde", Vars.defaultColor + "ir tirar pvp com seu oponente")));
		Arrays.kitMaterial.put("Berserker", createItem(Material.POTION, 0, java.util.Arrays.asList(Vars.defaultColor + "Ao matar um jogador ganhe", Vars.defaultColor + "força por 30 segundos")));
		Arrays.kitMaterial.put("Thor", createItem(Material.WOOD_AXE, 0, java.util.Arrays.asList(Vars.defaultColor + "Ao clicar com o seu machado", Vars.defaultColor + "solte um raio ou cause uma explosao")));
		Arrays.kitMaterial.put("Anchor", createItem(Material.ANVIL, 0, java.util.Arrays.asList(Vars.defaultColor + "Nao tome e nem de knockback")));
		Arrays.kitMaterial.put("Archer", createItem(Material.BOW, 0, java.util.Arrays.asList(Vars.defaultColor + "Ganhe um arco e 10 flechas", Vars.defaultColor + "Ao acertar o oponente, recupera a flecha")));
		Arrays.kitMaterial.put("Cannibal", createItem(Material.ROTTEN_FLESH, 0, java.util.Arrays.asList(Vars.defaultColor + "O dano gerado da fome ao seu oponente", Vars.defaultColor + "Voce recupera fome ao bater em seu oponente")));
		Arrays.kitMaterial.put("Boxer", createItem(Material.STONE_SWORD, 0, java.util.Arrays.asList(Vars.defaultColor + "Sua mao da tanto dano quanto uma espada de pedra", Vars.defaultColor + "O dano levado reduzido para 1 coracao")));	
		Arrays.kitMaterial.put("Camel", createItem(Material.SAND, 0, java.util.Arrays.asList(Vars.defaultColor + "Faça sopas com cacto e areia", Vars.defaultColor + "Ganhe velocidade no deserto")));	
		Arrays.kitMaterial.put("Checkpoint", createItem(Material.SIGN, 0, java.util.Arrays.asList(Vars.defaultColor + "Marque um local no qual", Vars.defaultColor + "Voce pode voltar depois")));	
		Arrays.kitMaterial.put("Cookiemonster", createItem(Material.COOKIE, 0, java.util.Arrays.asList(Vars.defaultColor + "Seu cookie pode regenerar vida, fome e te dar speed", Vars.defaultColor + "Para conseguir mais, quebre matos")));	
		Arrays.kitMaterial.put("Fireman", createItem(Material.LAVA_BUCKET, 0, java.util.Arrays.asList(Vars.defaultColor + "Inicie com um balde de agua", Vars.defaultColor + "Imune a fogo e raios")));	
		Arrays.kitMaterial.put("Fisherman", createItem(Material.FISHING_ROD, 0, java.util.Arrays.asList(Vars.defaultColor + "Pesque seus inimigos e", Vars.defaultColor + "os teletransporte para o seu local")));	
		Arrays.kitMaterial.put("Forcefield", createItem(Material.MAGMA_CREAM, 0, java.util.Arrays.asList(Vars.defaultColor + "Use o creme de magma para ativar seu Forcefield", Vars.defaultColor + "Com ele voce ira hitar seu inimigo sem toca-lo")));	
		Arrays.kitMaterial.put("C4", createItem(Material.TNT, 0, java.util.Arrays.asList(Vars.defaultColor + "Jogue um C4 e o exploda")));	
		Arrays.kitMaterial.put("Urgal", createItem(Material.POTION, 0, java.util.Arrays.asList(Vars.defaultColor + "Ganhe 3 pocoes de forca")));	
		Arrays.kitMaterial.put("Grandpa", createItem(Material.STICK, 0, java.util.Arrays.asList(Vars.defaultColor + "Ganhe 1 graveto com repulsao 2")));
		Arrays.kitMaterial.put("Turtle", createItem(Material.BEDROCK, 0, java.util.Arrays.asList(Vars.defaultColor + "Segurando o shift nao da e nem recebe dano")));
		Arrays.kitMaterial.put("Ninja", createItem(Material.NETHER_STAR, 0, java.util.Arrays.asList(Vars.defaultColor + "Aperte shift e va para o ultimo jogador hitado")));
		Arrays.kitMaterial.put("Kangaroo", createItem(Material.FIREWORK, 0, java.util.Arrays.asList(Vars.defaultColor + "Use seu firework para fugir de seus inimigos")));
		Arrays.kitMaterial.put("Grappler", createItem(Material.LEASH, 0, java.util.Arrays.asList(Vars.defaultColor + "Use seu laco para se puxar")));
		Arrays.kitMaterial.put("Hulk", createItem(Material.GHAST_TEAR, 0, java.util.Arrays.asList(Vars.defaultColor + "Pegue seu inimigo nas costas")));
	}
	
	public static int getRemainingCooldown(Player p, int delay, HashMap<String, Long> cooldown){
		if(!cooldown.containsKey(p.getName())){
			cooldown.put(p.getName(), 0l);
		}
		return (int) (((cooldown.get(p.getName()) + delay * 1000) - System.currentTimeMillis()) / 1000);
	}
	
	public static boolean acabouCooldown(Player p, int delay, HashMap<String, Long> cooldown){
		if(!cooldown.containsKey(p.getName())){
			cooldown.put(p.getName(), 0l);
		}
		
		if(System.currentTimeMillis() - cooldown.get(p.getName()) >= delay * 1000){
			cooldown.put(p.getName(), System.currentTimeMillis());
			return true;			
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static void setupTags(SimpleScoreboard score) {
		
		Team normal = score.registerNewTeam("NORMAL");
		normal.setPrefix("§7");

		Team DEV = score.registerNewTeam("CODER");
		DEV.setPrefix("§9§lCODER §9");

		Team DLC = score.registerNewTeam("DLC");
		DLC.setPrefix("§d§lDLÇ §d");

		Team dono = score.registerNewTeam("DONO");
		dono.setPrefix("§4§lDONO §4");

		Team admin = score.registerNewTeam("ADMIN");
		admin.setPrefix("§c§lADMIN §c");

		Team mod = score.registerNewTeam("MOD");
		mod.setPrefix("§5§lMOD §5");

		Team trial = score.registerNewTeam("TRIAL");
		trial.setPrefix("§d§lTRIAL §d");

		Team PRO = score.registerNewTeam("PRO");
		PRO.setPrefix("§6§lPRO §6");

		Team MVP = score.registerNewTeam("MVP");
		MVP.setPrefix("§1§lMVP §1");

		Team YT = score.registerNewTeam("YOUTUBER");
		YT.setPrefix("§b§lYOUTUBER §b");

		Team VIP = score.registerNewTeam("VIP");
		VIP.setPrefix("§a§lVIP §a");

		Team BUILDER = score.registerNewTeam("BUILDER");
		BUILDER.setPrefix("§e§lBUILDER §e");

		for(Player p : Bukkit.getOnlinePlayers()){
			for (Team t : score.getScoreboard().getTeams()) {
				if (t.getName().equals(Arrays.playerTag.get(p.getName())[1].toString().toUpperCase())) {
					t.addPlayer(p);
				}
			}
		}
		
	}
	
	public static void openWarpGui(Player p){
		Inventory inv = Bukkit.createInventory(p, InventoryType.HOPPER, Vars.infoColor + "Warps");
		
		ItemStack fps = new ItemStack(Material.GLASS);
		ItemMeta fpsMeta = fps.getItemMeta();
		fpsMeta.setDisplayName(Vars.infoColor + "FPS");
		fps.setItemMeta(fpsMeta);
		
		ItemStack rdm = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta rdmMeta = rdm.getItemMeta();
		rdmMeta.setDisplayName(Vars.infoColor + "RDM");
		rdm.setItemMeta(rdmMeta);
		
		ItemStack mdr = new ItemStack(Material.QUARTZ_BLOCK);
		ItemMeta mdrMeta = mdr.getItemMeta();
		mdrMeta.setDisplayName(Vars.infoColor + "MDR");
		mdr.setItemMeta(mdrMeta);
		
		ItemStack umvsum = new ItemStack(Material.BLAZE_ROD);
		ItemMeta umvsumMeta = umvsum.getItemMeta();
		umvsumMeta.setDisplayName(Vars.infoColor + "1v1");
		umvsum.setItemMeta(umvsumMeta);
		
		ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta lavaMeta = lava.getItemMeta();
		lavaMeta.setDisplayName(Vars.infoColor + "Lava Challange");
		lava.setItemMeta(lavaMeta);
		
		inv.setItem(0, fps);
		inv.setItem(1, rdm);
		inv.setItem(2, mdr);
		inv.setItem(3, umvsum);
		inv.setItem(4, lava);
		
		p.openInventory(inv);
		
	}
	
	public static void respawn(final Player p){
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable()
	    {
	        public void run()
	        {
	            if(p.isDead())
	            {
	                try
	                {
	                    Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
	                    Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
	                    Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");

	                    for(Object ob : enumClass.getEnumConstants()){
	                        if(ob.toString().equals("PERFORM_RESPAWN")){
	                            packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
	                        }
	                    }

	                    Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
	                    con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
	                }
	                catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    });
	}
	
}
