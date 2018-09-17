package me.dodocarlos.kitpvp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.bukkit.entity.Player;

public class DB {
	
	public Connection conn;
	public Statement stm;
	
	public DB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		    String url = "jdbc:mysql://" + Vars.server + "/" + Vars.db + "?autoReconnect=true";
		    
		    conn = DriverManager.getConnection(url, Vars.user, Vars.pass);

			stm = conn.createStatement();

			stm.executeUpdate("CREATE TABLE IF NOT EXISTS status(id int(11) NOT NULL AUTO_INCREMENT, uuid varchar(500), kills integer, deaths integer, exp integer, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
			stm.executeUpdate("CREATE TABLE IF NOT EXISTS kitdiario(id int(11) NOT NULL AUTO_INCREMENT, uuid varchar(500), kit varchar(100), time varchar(100), PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
			stm.executeUpdate("CREATE TABLE IF NOT EXISTS `bans` (`id` int(11) NOT NULL AUTO_INCREMENT, `banner` varchar(500) NOT NULL, `banned` varchar(500) NOT NULL, `uuid` varchar(500) NOT NULL, `motivo` varchar(5000) NOT NULL, `time` varchar(500) NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void registerPlayer(Player p){
		try{
			String uuid = p.getUniqueId().toString();
			if(!hasPlayerData(p)){
				stm.executeUpdate("INSERT INTO status(uuid, kills, deaths, exp) values('" + uuid + "', 0, 0, 0)");
			}
		}catch(SQLException e){
		}
	}
	
	public boolean hasPlayerData(Player p){
		try {
			String uuid = p.getUniqueId().toString();
			PreparedStatement query = conn.prepareStatement("SELECT * FROM status where uuid = ?");
			query.setString(1, uuid);
			ResultSet rs = query.executeQuery();
			return rs.next();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean hasPlayerKitDiarioData(Player p){
		try {
			String uuid = p.getUniqueId().toString();
			PreparedStatement query = conn.prepareStatement("SELECT * FROM kitdiario where uuid = ?");
			query.setString(1, uuid);
			ResultSet rs = query.executeQuery();
			return rs.next();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	public String getKitDiarioTime(Player p){
		String uuid = p.getUniqueId().toString();
		String reason = "";		
		try{
			if(!hasPlayerKitDiarioData(p)){
				stm.executeUpdate("INSERT INTO kitdiario(uuid, kit, time) values('" + uuid + "', 'null', 0)");
			}
			ResultSet rs = stm.executeQuery("SELECT time from kitdiario where uuid = '" + uuid + "'");			
			while(rs.next()){
			reason = rs.getString(1);
			}
		}catch(SQLException e){
			
		}
		return reason;
	}
	
	public String getKitDiarioKit(Player p){
		String uuid = p.getUniqueId().toString();
		String reason = "";		
		try{
			if(!hasPlayerKitDiarioData(p)){
				stm.executeUpdate("INSERT INTO kitdiario(uuid, kit, time) values('" + uuid + "', 'null', 0)");
			}
			ResultSet rs = stm.executeQuery("SELECT kit from kitdiario where uuid = '" + uuid + "'");			
			while(rs.next()){
				reason = rs.getString(1);
			}
		}catch(SQLException e){
			
		}
		return reason;
	}
	
	public void addKitDiario(Player p, String kit){
		String uuid = p.getUniqueId().toString();
		try{
			if(!hasPlayerKitDiarioData(p)){
				stm.executeUpdate("INSERT INTO kitdiario(uuid, kit, time) values('" + uuid + "', 'null', 0)");
			}
			stm.executeUpdate("UPDATE kitdiario set time = '" + new Date().getTime() + "' where uuid = '" + uuid + "'");
			stm.executeUpdate("UPDATE kitdiario set kit = '" + kit + "' where uuid = '" + uuid + "'");
		}catch(SQLException e){
			try {
				stm.executeUpdate("INSERT INTO kitdiario(uuid, kit, time) values('" + uuid + "', 'null', 0)");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void addKill(Player p, int i){
		String uuid = p.getUniqueId().toString();
		try{
			if(!hasPlayerData(p)){
				registerPlayer(p);
			}
				stm.executeUpdate("UPDATE status set kills = kills+" + i + " where uuid = '" + uuid + "'");
		}catch(SQLException e){
			registerPlayer(p);
		}
	}
	
	public void addDeath(Player p, int i){
		String uuid = p.getUniqueId().toString();
		try{
			if(!hasPlayerData(p)){
				registerPlayer(p);
			}
				stm.executeUpdate("UPDATE status set deaths = deaths+" + i + " where uuid = '" + uuid + "'");
		}catch(SQLException e){
			registerPlayer(p);
		}
	}
	
	public void addExp(Player p, int i){
		String uuid = p.getUniqueId().toString();
		try{
			if(!hasPlayerData(p)){
				registerPlayer(p);
			}
				stm.executeUpdate("UPDATE status set exp = exp+" + i + " where uuid = '" + uuid + "'");
		}catch(SQLException e){
			registerPlayer(p);
		}
	}
	
	public int getKills(Player p){
		String uuid = p.getUniqueId().toString();
		int kills = 0;		
		try{
			if(!hasPlayerData(p)){
				registerPlayer(p);
			}
				ResultSet rs = stm.executeQuery("SELECT kills from status where uuid = '" + uuid + "'");			
				while(rs.next()){
					kills = rs.getInt(1);
			}
		}catch(SQLException e){
			registerPlayer(p);
		}
		return kills;
	}
	
	public int getDeaths(Player p){
		String uuid = p.getUniqueId().toString();
		int deaths = 0;		
		try{
			if(!hasPlayerData(p)){
				registerPlayer(p);
			}
				ResultSet rs = stm.executeQuery("SELECT deaths from status where uuid = '" + uuid + "'");			
				while(rs.next()){
					deaths = rs.getInt(1);
			}
		}catch(SQLException e){
			registerPlayer(p);
		}
		return deaths;
	}
	
	public int getExp(Player p){
		String uuid = p.getUniqueId().toString();
		int exp = 0;		
		try{
			if(!hasPlayerData(p)){
				registerPlayer(p);
			}
				ResultSet rs = stm.executeQuery("SELECT exp from status where uuid = '" + uuid + "'");			
				while(rs.next()){
					exp = rs.getInt(1);
			}
		}catch(SQLException e){
			registerPlayer(p);
		}
		return exp;
	}
	
	public boolean hasPlayerbanData(Player p){
		try {
			String uuid = p.getUniqueId().toString();
			PreparedStatement query = conn.prepareStatement("SELECT * FROM bans where uuid = ?");
			query.setString(1, uuid);
			ResultSet rs = query.executeQuery();
			return rs.next();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean hasPlayerbanData(String nick){
		try {
			PreparedStatement query = conn.prepareStatement("SELECT * FROM bans where banned = ?");
			query.setString(1, nick);
			ResultSet rs = query.executeQuery();
			return rs.next();
						
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
		
	}
	
	//Bans
		public void banPlayer(Player p, String banner, String reason, String time){
			String uuid = p.getUniqueId().toString();
			try{
				if(!hasPlayerbanData(p)){
					stm.executeUpdate("INSERT INTO bans(id, banner, banned, uuid, motivo, time) values(0, '" + banner + "', '" + p.getName() + "', '" + uuid + "', '" + reason + "', '" + time + "')");
				}
			}catch(SQLException e){
				try {
					stm.executeUpdate("INSERT INTO bans(id, banner, banned, uuid, motivo, time) values(0, '" + banner + "', '" + p.getName() + "', '" + uuid + "', '" + reason + "', '" + time + "')");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		public void unbanPlayer(Player p, String banner, String reason, String time){
			String uuid = p.getUniqueId().toString();
			try{
				stm.executeUpdate("DELETE FROM bans WHERE uuid = '" + uuid + "'");
			}catch(SQLException e){
		
			}
		}
		
		public void banPlayer(String nick, String banner, String reason, String time){
			try{
				if(!hasPlayerbanData(nick)){
					stm.executeUpdate("INSERT INTO bans(id, banner, banned, uuid, motivo, time) values(0, '" + banner + "', '" + nick + "', '0', '" + reason + "', '" + time + "')");
				}
				
			}catch(SQLException e){
				try {
					stm.executeUpdate("INSERT INTO bans(nick, banner, reason, time, banned) values('" + nick + "', '" + banner + "', '" + reason + "', '" + time + "', 'true')");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		public void unbanPlayer(String nick){
			try{
				stm.executeUpdate("DELETE FROM bans WHERE banned = '" + nick + "'");
			}catch(SQLException e){
				try {
					stm.executeUpdate("INSERT INTO bans(nick, banner, reason, time, banned) values('" + nick + "', '', '', '', 'false')");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		public boolean hasBanned(Player p){
			try {
				String uuid = p.getUniqueId().toString();
				PreparedStatement query = conn.prepareStatement("SELECT * FROM bans where uuid = ?");
				query.setString(1, uuid);
				ResultSet rs = query.executeQuery();
				return rs.next();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			return false;
			
		}
		
		public boolean hasBanned(String nick){
			try {
				PreparedStatement query = conn.prepareStatement("SELECT * FROM bans where banned = ?");
				query.setString(1, nick);
				ResultSet rs = query.executeQuery();
				return rs.next();
						
			} catch (SQLException e) {			
				e.printStackTrace();
			}
			return false;
			
		}
		
		public String getBanReason(Player p){
			String uuid = p.getUniqueId().toString();
			String reason = "";		
			try{
				ResultSet rs = stm.executeQuery("SELECT motivo from bans where uuid = '" + uuid + "'");			
				while(rs.next()){
					reason = rs.getString(1);
				}
			}catch(SQLException e){
				
			}
			
			return reason;
		}
		
		public String getBanReason(String nick){
			String reason = "";		
			try{
				ResultSet rs = stm.executeQuery("SELECT motivo from bans where banned = '" + nick + "'");			
				while(rs.next()){
					reason = rs.getString(1);
				}
			}catch(SQLException e){
				
			}
			
			return reason;
		}
	
}
