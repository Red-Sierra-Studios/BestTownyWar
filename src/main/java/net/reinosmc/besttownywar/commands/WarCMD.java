package net.reinosmc.besttownywar.commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import net.reinosmc.besttownywar.Plugin;
import net.reinosmc.besttownywar.commands.subcommands.StartSCMD;
import net.reinosmc.besttownywar.commands.subcommands.SurrenderSCMD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarCMD implements CommandExecutor
{

    Plugin plugin;

    public WarCMD(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {

        Configuration c = plugin.getConfig();
        String prefix = c.getString("prefix");

        if(!(s instanceof Player))
        {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    prefix + "&7 Only players can use this"));
        }
        else
        {

            String[] subCommands = {"start", "surrender", "stats", "list"};
            if (args.length < 1) {
                s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        prefix + "&7 You need to provide a valid subcommand."));
            }
            else
            {

                for (int i = 0; i < subCommands.length; i++)
                {
                    if (!args[0].contains(subCommands[i]))
                    {
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                prefix + "&7 " + args[0] + " is not a valid subcommand, try again."));
                    }
                    else {
                        if (args[0].equalsIgnoreCase("start"))
                        {
                            StartSCMD start = new StartSCMD();
                            start.startSubcommand(s, c, args);
                        }
                        else
                        {
                            if(args[0].equalsIgnoreCase("surrender"))
                            {
                                SurrenderSCMD surrender = new SurrenderSCMD();
                                surrender.startSubcommand(s, c, args);
                            }
                        }

                    }
                }
            }
        }
        return false;
    }
}
