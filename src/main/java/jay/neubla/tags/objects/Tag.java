package jay.neubla.tags.objects;

import jay.neubla.tags.utils.TagUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Tag {

    private String name, formatedName, tag, description, permission, key;
    private ItemStack menuItem;
    private ConfigurationSection itemSection;

    public Tag(ConfigurationSection section) {
        this.itemSection = section.getConfigurationSection("menuItem");
        this.name = section.getString("name");
        this.key = section.getString("name").replace(" ", "");
        this.formatedName = section.getString("formatedName");
        this.tag = section.getString("tag");
        this.description = section.getString("description");
        this.permission = section.getString("permission");
        this.menuItem = TagUtils.createItem(section.getConfigurationSection("menuItem"));
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getFormatedName() { return formatedName; }

    public void setFormatedName(String formatedName) { this.formatedName = formatedName; }

    public String getTag() { return tag; }

    public void setTag(String tag) { this.tag = tag; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPermission() { return permission; }

    public void setPermission(String permission) { this.permission = permission; }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }

    public ItemStack getMenuItem() { return menuItem; }

    public void setMenuItem(ItemStack menuItem) { this.menuItem = menuItem; }

    public ConfigurationSection getItemSection() { return itemSection; }

    public void setItemSection(ConfigurationSection itemSection) { this.itemSection = itemSection; }

    public ItemStack getPlayerBasedMenuItem(Player player) { return TagUtils.createPlayerBasedMenuItem(itemSection, player, permission); }

}
