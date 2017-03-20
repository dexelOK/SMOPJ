package me.dexel.smopj;

import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SMOPJ extends JavaPlugin implements Listener {

    public boolean j = false;

    public void onEnable() {
        getLogger().info("Done! SMOPJ activated successfully.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {}

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(ChatColor.YELLOW + "Привет, " + ChatColor.BLUE + e.getPlayer().getName());
        e.getPlayer().sendMessage(ChatColor.YELLOW + "Добро пожаловать на сервер " + ChatColor.RED + "Test" +
                ChatColor.BLUE + "Craft" + ChatColor.YELLOW + "!");
        e.getPlayer().sendMessage(ChatColor.YELLOW + "Узнай список доступных тебе команд: " + ChatColor.RED +
                "/help");
        j = true;
        e.getPlayer().setGameMode(GameMode.CREATIVE);
        e.getPlayer().setAllowFlight(false);
        e.getPlayer().setFlying(false);
        j = false;
        e.getPlayer().sendMessage("Небольшой троллинг :)");
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getAllowFlight() == false && j == false) {
            p.sendMessage(ChatColor.RED + "Ах ты хитрая жопа!");
            p.setGameMode(GameMode.CREATIVE);
            p.setAllowFlight(false);
            p.setFlying(false);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE && p.getAllowFlight() == false) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE && p.getAllowFlight() == false) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.getGameMode() == GameMode.CREATIVE && p.getAllowFlight() == false) {
            e.setCancelled(true);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("zaebal")) {
            if (p.getGameMode() == GameMode.CREATIVE && p.getAllowFlight() == false) {
                p.sendMessage(ChatColor.GREEN + "Окей-окей, прости. Не удержался.");
                p.setAllowFlight(true);
                p.setFlying(false);
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(ChatColor.YELLOW + "Приятной игры! ;)");
                p.damage(1000.0);
                return true;
            }
            else {
                p.sendMessage(ChatColor.RED + "А хуй ты уже меня обманешь, мразь.");
                p.setGameMode(GameMode.SPECTATOR);
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage(ChatColor.RED + "Сдохни в тяжких муках, гнида.");
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("ya-admin")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage("Я и так знаю, что ты админ.");
                return true;
            }
            if (p.hasPermission("smopj.admin")) {
                p.sendMessage(ChatColor.GOLD + "Добрый вечер, господин Админ.");
                p.setAllowFlight(true);
                p.setFlying(false);
                p.setGameMode(GameMode.CREATIVE);
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("dai-almazov")) {
            p.sendMessage(ChatColor.DARK_PURPLE + "Не хочу.");
        }
        return false;
    }
}
