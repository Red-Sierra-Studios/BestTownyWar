package net.reinosmc.besttownywar.commands.subcommands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.Random;

public class StartSCMD {

    public void startSubcommand(CommandSender s, Configuration c, String[] args) {

        String prefix = c.getString("prefix");

        TownyAPI tApi = TownyAPI.getInstance();
        Player player = Bukkit.getServer().getPlayer(s.getName());

        Resident r = tApi.getResident(player.getUniqueId());

        if (!tApi.getResident(player.getUniqueId()).hasNation())
        {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    prefix + "&7 You need to be in a nation to do this"));
        }
        else
        {
            try
            {
                assert r != null;
                if (!r.getNation().getKing().equals(r))
                {
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            prefix + "&7 You need to be the king of your nation to do this"));
                }
                else
                {
                    if(args.length < 2)
                    {
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                prefix + "&7 You need to provide a valid nation (Syntax: /war start PepitoLand)"));
                    }
                    else
                    {
                        if(tApi.getNation(args[1]) == null)
                        {
                            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    prefix + "&7 The "+args[1]+" nation does not exist, try again"));
                        }
                        else
                        {
                            if(r.getNation().isAlliedWith(tApi.getNation(args[1])))
                            {
                                s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                        prefix + "&7 You cannot declare war on an allied nation, for this you need to undo it"));
                            }
                            else
                            {
                                if(!r.getNation().hasEnemy(tApi.getNation(args[1])))
                                {
                                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                            prefix + "&7 "+args[1]+" is not an enemy of your nation, you must put it as an enemy "));
                                }
                                else
                                {
                                    Nation startNation = r.getNation();
                                    Nation attackedNation = tApi.getNation(args[1]);

                                    if(tApi.getOnlinePlayersInNation(startNation).size() < 2 || tApi.getOnlinePlayersInNation(attackedNation).size() < 2)
                                    {
                                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                                prefix + "&7 Both sides must have at least 2 players connected"));
                                    }
                                    else
                                    {
                                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                                prefix + "&4 From now on "+startNation.getName()+" and "+attackedNation.getName()+" go to war"));
                                        Random random = new Random();
                                        int f = random.nextInt( 2);
                                        Town battlefront = (f == 1 ? attackedNation.getCapital() : startNation.getCapital());
                                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                                prefix + "&7 The first battlefront to be disputed in the war of "+startNation.getName()+" and "+
                                                        attackedNation.getName()+" is in " + battlefront.getName()));

                                        battlefront.setPVP(true);
                                        battlefront.setNeutral(false);
                                        battlefront.setActiveWar(true);
                                        startNation.setActiveWar(true);
                                        attackedNation.setActiveWar(true);


                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (TownyException e)
            {
                s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        prefix + "&4 A fatal error has occurred, please try again later"));
                throw new RuntimeException(e);
            }
        }
    }

}
