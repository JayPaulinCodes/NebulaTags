package jay.neubla.tags.utils;

import jay.neubla.tags.TagsPlugin;
import jay.neubla.tags.objects.Tag;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TagUtils {
    private static final TagsPlugin PLUGIN = TagsPlugin.getInstance();

    public static ArrayList<Tag> getAllTags() {
        ArrayList<Tag> tagList = new ArrayList<>();
        ConfigurationSection tagsSection = PLUGIN.config.get().getConfigurationSection("Tags");

        for(String key : tagsSection.getKeys(false)) {
            if(TagsPlugin.debug) Logger.action("Registered tag named: " + key);
            tagList.add(new Tag(tagsSection.getConfigurationSection(key)));
        }

        return tagList;
    }

    public static Tag getTagFromKey(String key) {
        TagsPlugin plugin = TagsPlugin.getInstance();

        Tag result = null;

        for(Tag tag : plugin.tags) {
            if(tag.getKey().equalsIgnoreCase(key)) result = tag; break;
        }

        return result;
    }

    public static Tag getTagFromItem(ItemStack item, Player p) {
        TagsPlugin plugin = TagsPlugin.getInstance();

        Tag result = null;

        for(Tag tag : plugin.tags) {
            if(tag.getMenuItem() == item || tag.getPlayerBasedMenuItem(p) == item) result = tag; break;
        }

        return result;
    }

    public static ItemStack createPlayerBasedMenuItem(ConfigurationSection section, Player player, String permission) {
        ItemStack item = new ItemStack(Material.getMaterial(section.getString("material")));
        ItemMeta itemMeta = item.getItemMeta();
        if(Integer.getInteger(section.getString("amount")) != null) item.setAmount(section.getInt("amount"));
        if(Integer.getInteger(section.getString("damage")) != null) item.setDurability((short) section.getInt("damage"));
        if(section.getString("name") != null) itemMeta.setDisplayName(JayUtils.colour(section.getString("name")));
        if(section.getStringList("lore").size() > 0) {
            ArrayList<String> lore = new ArrayList<>();
            for(String line : section.getStringList("lore")) {
                lore.add(JayUtils.colour(line));
            }
        }

        if(player.hasPermission(permission)) {
            item.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 77);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItem(ConfigurationSection section) {
        ItemStack item = new ItemStack(Material.getMaterial(section.getString("material")));
        ItemMeta itemMeta = item.getItemMeta();
        if(Integer.getInteger(section.getString("amount")) != null) item.setAmount(section.getInt("amount"));
        if(Integer.getInteger(section.getString("damage")) != null) item.setDurability((short) section.getInt("damage"));
        if(section.getString("name") != null) itemMeta.setDisplayName(JayUtils.colour(section.getString("name")));
        if(section.getStringList("lore").size() > 0) {
            ArrayList<String> lore = new ArrayList<>();
            for(String line : section.getStringList("lore")) {
                lore.add(JayUtils.colour(line));
            }
        }

//        if(section.getBoolean("enchanted")) {
//            item.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 77);
//            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        }

        item.setItemMeta(itemMeta);
        return item;
    }
}
