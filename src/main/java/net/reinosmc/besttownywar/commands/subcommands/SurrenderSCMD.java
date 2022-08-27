package net.reinosmc.besttownywar.commands.subcommands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class SurrenderSCMD
{

    public void startSubcommand(CommandSender s, Configuration c, String[] args)
    {

        String prefix = c.getString("prefix");
        TownyAPI tApi = TownyAPI.getInstance();
        Player player = Bukkit.getServer().getPlayer(s.getName());
        Resident r = tApi.getResident(player.getUniqueId());

        if(!r.hasNation())
        {

                s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        prefix + "&7 You need to be in a nation to do this"));
            }
            else
            {
                try
                {
                    if (!r.getNation().getKing().equals(r))
                    {
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                prefix + "&7 You need to be the king of your nation to do this"));
                    }
                    else
                    {
                        if(r.getNation().hasActiveWar()) {}
                    }

                }
                catch (TownyException ex)
                {
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            prefix + "&4 A fatal error has occurred, please try again later"));
                    throw new RuntimeException(ex);
                }
            }

    }
}
