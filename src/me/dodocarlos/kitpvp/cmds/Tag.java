package me.dodocarlos.kitpvp.cmds;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Vars;

public class Tag implements CommandExecutor {

	String tags[] = {"Default", "Vip", "MvP", "PRO", "Youtuber", "Coder", "Trial", "Mod", "Admin", "Dono"};

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {

		if(sender instanceof Player){
			Player p = (Player) sender;
			StringBuilder sb = new StringBuilder();
			sb.append(Vars.tag + Vars.defaultColor +  "Use: (");
			for(String tag : tags){
				if(p.hasPermission("tag." + tag)){
					sb.append(tag + " | ");
				}
			}
			sb.setCharAt(sb.length() -1, ')');
			
			if(args.length == 0){
				p.sendMessage(sb.toString());
			}
			
//			if(args.length == 1){
//				if(args[0].equalsIgnoreCase("Normal")){
//					Metodos.setTag(p, "§f", "§f");
//				}
//				
//				if(args[0].equalsIgnoreCase("Vip")){
//					if(p.hasPermission("dHG.tag.vip")){
//						Metodos.setTag(p, "§a§lVIP §a", "§a");
//					}
//				}
//				
////				if(args[0].equalsIgnoreCase("MvP")){
////					if(p.hasPermission("dHG.tag.mvp")){
////						Metodos.setTag(p, "§1§lMvP §1", "§1");
////					}
////				}
////				
////				if(args[0].equalsIgnoreCase("Pro")){
////					if(p.hasPermission("dHG.tag.pro")){
////						Metodos.setTag(p, "§6§lPRO §6", "§6");
////					}
////				}
//				
//				if(args[0].equalsIgnoreCase("Youtuber")){
//					if(p.hasPermission("dHG.tag.youtuber")){
//						Metodos.setTag(p, "§b§lYOUTUBER §b", "§b");
//					}
//				}
//				
//				if(args[0].equalsIgnoreCase("Coder")){
//					if(p.hasPermission("dHG.tag.coder")){
//						Metodos.setTag(p, "§9§lCODER §9", "§9");
//					}
//				}
//				
//				if(args[0].equalsIgnoreCase("Trial")){
//					if(p.hasPermission("dHG.tag.trial")){
//						Metodos.setTag(p, "§d§lTRIAL §d", "§d");
//					}
//				}
//				
//				if(args[0].equalsIgnoreCase("Mod")){
//					if(p.hasPermission("dHG.tag.mod")){
//						Metodos.setTag(p, "§5§lMOD §5", "§5");
//					}
//				}
//				
//				if(args[0].equalsIgnoreCase("Admin")){
//					if(p.hasPermission("dHG.tag.admin")){
//						Metodos.setTag(p, "§c§lADMIN §c", "§c");
//					}
//				}
//				
//				if(args[0].equalsIgnoreCase("Dono")){
//					if(p.hasPermission("dHG.tag.dono")){
//						Metodos.setTag(p, "§4§lDONO §4", "§4");
//					}
//				}
//			}
			
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("Default")){
					Arrays.playerTag.put(p.getName(), new String[]{"§7", "NORMAL"});
					p.sendMessage("§aVoce esta usando a tag: §7Default");
				}
				
				if(args[0].equalsIgnoreCase("Vip")){
					if(p.hasPermission("tag.vip")){
						Arrays.playerTag.put(p.getName(), new String[]{"§a", "VIP"});
						p.sendMessage("§aVoce esta usando a tag: §a§lVIP");
					}
				}
				
				if(args[0].equalsIgnoreCase("MvP")){
					if(p.hasPermission("tag.mvp")){
						Arrays.playerTag.put(p.getName(), new String[]{"§1", "MvP"});
						p.sendMessage("§aVoce esta usando a tag: §1§lMvP");
					}
				}
				
				if(args[0].equalsIgnoreCase("Pro")){
					if(p.hasPermission("tag.pro")){
						Arrays.playerTag.put(p.getName(), new String[]{"§6", "PRO"});
						p.sendMessage("§aVoce esta usando a tag: §6§lPRO");
					}
				}
				
				if(args[0].equalsIgnoreCase("Youtuber")){
					if(p.hasPermission("tag.youtuber")){
						Arrays.playerTag.put(p.getName(), new String[]{"§b", "YOUTUBER"});
						p.sendMessage("§aVoce esta usando a tag: §b§lYOUTUBER");
					}
				}
				
//				if(args[0].equalsIgnoreCase("Dlc")){
//					if(p.hasPermission("tag.dlc")){
//						Arrays.playerTag.put(p.getName(), new String[]{"§d", "DLC"});
//						p.sendMessage("§aVoce esta usando a tag: §d§lDLÇ");
//					}
//				}
				
				if(args[0].equalsIgnoreCase("Coder")){
					if(p.hasPermission("tag.coder")){
						Arrays.playerTag.put(p.getName(), new String[]{"§9", "CODER"});
						p.sendMessage("§aVoce esta usando a tag: §9§lCODER");
					}
				}
				
				if(args[0].equalsIgnoreCase("Trial")){
					if(p.hasPermission("tag.trial")){
						Arrays.playerTag.put(p.getName(), new String[]{"§d", "TRIAL"});
						p.sendMessage("§aVoce esta usando a tag: §d§lTRIAL");
					}
				}
				
				if(args[0].equalsIgnoreCase("Builder")){
					if(p.hasPermission("tag.trial")){
						Arrays.playerTag.put(p.getName(), new String[]{"§e", "BUILDER"});
						p.sendMessage("§aVoce esta usando a tag: §e§lBUILDER");
					}
				}
				
				if(args[0].equalsIgnoreCase("Mod")){
					if(p.hasPermission("tag.mod")){
						Arrays.playerTag.put(p.getName(), new String[]{"§5", "MOD"});
						p.sendMessage("§aVoce esta usando a tag: §5§lMOD");
					}
				}
				
				if(args[0].equalsIgnoreCase("Admin")){
					if(p.hasPermission("tag.admin")){
						Arrays.playerTag.put(p.getName(), new String[]{"§c", "ADMIN"});
						p.sendMessage("§aVoce esta usando a tag: §c§lADMIN");
					}
				}
				
				if(args[0].equalsIgnoreCase("Dono")){
					if(p.hasPermission("tag.dono")){
						Arrays.playerTag.put(p.getName(), new String[]{"§4", "DONO"});
						p.sendMessage("§aVoce esta usando a tag: §4§lDONO");
					}
				}
			}
			
			
			
		}else{
			sender.sendMessage(Vars.tag + Vars.defaultColor +  "Apenas Jogadores");
		}
		
		return false;
	}

}
