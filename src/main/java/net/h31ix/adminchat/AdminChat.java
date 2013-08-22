package net.h31ix.adminchat;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class AdminChat extends JavaPlugin {

    private int msglength;
    private String[ ] message= new String[20];
    private String result = "";
	
    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        getCommand("bc").setExecutor(new CommandExecutor() {
            public boolean onCommand(CommandSender cs, Command cmnd, String alias, String[] args) {
                Player player = (Player)cs;
                Server server = getServer();
                String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
                String safenick1 = safenick.replaceAll("§f", "");
                if (player.hasPermission("adminchat.broadcast")|| player.isOp()) {
                    if (args.length <= 0) {
						player.sendMessage(ChatColor.RED + "Usage: /bc <message>");
                    } else {
						msglength = args.length;
						
						for (int i = 0;i<msglength;i++) {
							message[i]= args[i]; 
						}
						
						result = message[0];
						for (int i=1; i<msglength; i++) {
							result = result + " " + message[i];
						}
						Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "ALL: "+safenick1+": "+ChatColor.RED+result);
					}    
                } else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use that command.");
				}
                return true; 
			}
        }); 
		getCommand("ac").setExecutor(new CommandExecutor() {
			public boolean onCommand(CommandSender cs, Command cmnd, String alias, String[] args) {
				Player player = (Player)cs;
				Server server = getServer();
				String safenick = player.getName().toLowerCase().replaceAll("'", "\"");
				String safenick1 = safenick.replaceAll("§f", "");
				if (player.hasPermission("adminchat.chat")|| player.isOp()) {
					if (args.length <= 0) {
						player.sendMessage(ChatColor.RED + "Usage: /ac <message>");
					} else {
						msglength = args.length;

						for (int i = 0;i<msglength;i++) {
							message[i]= args[i]; 
						}

						result = message[0];
						for (int i=1; i<msglength; i++) {
							result = result + " " + message[i];
						}
						
						Player[] online = server.getOnlinePlayers();
						for (int o=0;o<online.length;o++){
							Player name = online[o];
							if (name.hasPermission("adminchat.read") || name.isOp()) {
								name.sendMessage(ChatColor.GREEN + "ADMIN: "+safenick1+": "+ChatColor.RED+result);  
							}
						}							
					}
				} else { 
					player.sendMessage(ChatColor.RED + "You don't have permission to use that command.");
				}
				return true; 
			}
        }); 
    }
}
