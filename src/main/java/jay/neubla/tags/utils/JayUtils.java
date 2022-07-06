package jay.neubla.tags.utils;

import jay.neubla.tags.commands.NebulaCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jay's Minecraft plugin untility class
 *
 * Modifications:
 *     [2022-07-06] (1.0.0) - Created class
 * @author JayPaulinCodes
 * @version 1.0.0
 * @since 2022-07-06
 */
public final class JayUtils {

    /**
     * Allows the use of colours in your messages.<br>
     * <br>
     * Colour Codes:<br>
     * &0 = Black,<br>
     * &1 = Dark Blue,<br>
     * &2 = Dark Green,<br>
     * &3 = Dark Aqua,<br>
     * &4 = Dark Red,<br>
     * &5 = Dark Purple,<br>
     * &6 = Gold,<br>
     * &7 = Gray,<br>
     * &8 = Dark Gray,<br>
     * &9 = Blue,<br>
     * &a = Green,<br>
     * &b = Aqua,<br>
     * &c = Red,<br>
     * &d = Light Purple,<br>
     * &e = Yellow,<br>
     * &f = White,<br>
     * <br>
     * Formatting Codes:<br>
     * &k = Obfuscated,<br>
     * &l = Bold,<br>
     * &m = Strikethrough,<br>
     * &n = Underline,<br>
     * &o = Italic,<br>
     * &r = Reset
     * @param content Your message
     * @return Your message but with color
     * @since 1.0.0
     */
    public static String colour(String content) {
        return ChatColor.translateAlternateColorCodes( '&', content );
    }

    /**
     * Allows the use of colours in your messages.<br>
     * <br>
     * Colour Codes:<br>
     * &0 = Black,<br>
     * &1 = Dark Blue,<br>
     * &2 = Dark Green,<br>
     * &3 = Dark Aqua,<br>
     * &4 = Dark Red,<br>
     * &5 = Dark Purple,<br>
     * &6 = Gold,<br>
     * &7 = Gray,<br>
     * &8 = Dark Gray,<br>
     * &9 = Blue,<br>
     * &a = Green,<br>
     * &b = Aqua,<br>
     * &c = Red,<br>
     * &d = Light Purple,<br>
     * &e = Yellow,<br>
     * &f = White,<br>
     * <br>
     * Formatting Codes:<br>
     * &k = Obfuscated,<br>
     * &l = Bold,<br>
     * &m = Strikethrough,<br>
     * &n = Underline,<br>
     * &o = Italic,<br>
     * &r = Reset
     * @param content Your message
     * @return Your message but with color
     * @since 1.0.0
     */
    public static String color(String content) {
        return ChatColor.translateAlternateColorCodes( '&', content );
    }

    /**
     * Generates a list of blocks between 2 given points.
     * @param location1 Location object of the first point
     * @param location2 Location object of the second point
     * @return Returns a list of blocks in the range
     * @since 1.0.0
     */
    public static List<Block> blocksFromTwoPoints(Location location1, Location location2) {
        List<Block> blocks = new ArrayList<>();

        int topBlockX = (Math.max(location1.getBlockX(), location2.getBlockX()));
        int bottomBlockX = (Math.min(location1.getBlockX(), location2.getBlockX()));

        int topBlockY = (Math.max(location1.getBlockY(), location2.getBlockY()));
        int bottomBlockY = (Math.min(location1.getBlockY(), location2.getBlockY()));

        int topBlockZ = (Math.max(location1.getBlockZ(), location2.getBlockZ()));
        int bottomBlockZ = (Math.min(location1.getBlockZ(), location2.getBlockZ()));

        for(int x = bottomBlockX; x <= topBlockX; x++) {
            for(int y = bottomBlockY; y <= topBlockY; y++) {
                for(int z = bottomBlockZ; z <= topBlockZ; z++) {
                    Block block = location1.getWorld().getBlockAt(x, y, z);
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    /**
     * Registers every listener class inside the
     * package named "listeners" which are a
     * subclass of Listener
     * @param plugin Instance of your plugin
     * @since 1.0.0
     */
    public static void registerListeners(JavaPlugin plugin) {
        String packageName = plugin.getClass().getPackage().getName();

        for (Class<?> listenerClass : new Reflections(packageName + ".listeners").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) listenerClass.getDeclaredConstructor().newInstance();
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Registers every command class inside the
     * package named "commands" which are a
     * subclass of NebulaCommand
     * @param plugin Instance of your plugin
     * @see NebulaCommand
     * @since 1.0.0
     */
    public static void registerCommands(JavaPlugin plugin) {
        String packageName = plugin.getClass().getPackage().getName();

        for (Class<?> commandClass : new Reflections(packageName + ".commands").getSubTypesOf(NebulaCommand.class)) {
            try {
                NebulaCommand nebulaCommand = (NebulaCommand) commandClass.getDeclaredConstructor().newInstance();
                plugin.getCommand(nebulaCommand.getCommandInfo().name()).setExecutor(nebulaCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Creates a item stack with a specified item material and amount
     * @param material The material of the item stack
     * @param amount The amonunt of items in the stack
     * @return Returns a ItemStack in the amount and material specified.
     * @see Material
     * @since 1.0.0
     */
    public static ItemStack createItem(Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        return item;
    }

    /**
     * Set the name of a specifed item stack
     * @param item The item stack you wish to set the name of
     * @param name The name you want to apply to the item stack
     * @return Returns the specifed item stack but with a new name
     * @since 1.0.0
     */
    public static ItemStack setName(ItemStack item, String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(JayUtils.colour(name));
        item.setItemMeta(itemMeta);
        return item;
    }

    /**
     * Applies a lore to a item.
     * @param item The item stack to apply the lore to
     * @param lore The lore to apply to the item
     * @return Returns the specified item stack with the new lore applied
     * @since 1.0.0
     */
    public static ItemStack setLore(ItemStack item, ArrayList<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, JayUtils.colour(lore.get(i)));
        }
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    /**
     * Sets the unbreakable property on an item
     * @param item The item to modify
     * @param isUnbreakable Wether or not the item should have the unbreakable flag
     * @return Returns the specified item stack with the specified flag
     * @since 1.0.0
     */
    public static ItemStack setUnbreakable(ItemStack item, boolean isUnbreakable) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.spigot().setUnbreakable(isUnbreakable);
        item.setItemMeta(itemMeta);
        return item;
    }

    /**
     * Adds a specified enchant to a item with a specific level
     * @param item The item stack to apply the enchant to
     * @param enchantment The enchant to apply
     * @param level The level of the enchant to apply
     * @return Returns the item stack with the new enchant at the specified level
     * @see Enchantment
     * @since 1.0.0
     */
    public static ItemStack addEnchant(ItemStack item, Enchantment enchantment, int level) {
        if (level > enchantment.getMaxLevel()) item.addUnsafeEnchantment(enchantment, level);
        else item.addEnchantment(enchantment, level);
        return item;
    }

    /**
     * Removes the specified enchant from a item
     * @param item The item stack to remove the enchant from
     * @param enchantment The enchant to remove
     * @return Returns the item stack without the specified enchant
     * @see Enchantment
     * @since 1.0.0
     */
    public static ItemStack removeEnchant(ItemStack item, Enchantment enchantment) {
        if (item.containsEnchantment(enchantment)) item.removeEnchantment(enchantment);
        return item;
    }
}
