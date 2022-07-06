package jay.neubla.tags.menuManager.menus;

import jay.neubla.tags.TagsPlugin;
import jay.neubla.tags.menuManager.PaginatedMenu;
import jay.neubla.tags.menuManager.PlayerMenuUtility;
import jay.neubla.tags.utils.JayUtils;
import jay.neubla.tags.utils.TagUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SelectTagMenu extends PaginatedMenu {

    public SelectTagMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return JayUtils.colour(plugin.config.get().getString("menuTitle").substring(0, Math.min(plugin.config.get().getString("menuTitle").length(), 32)));
    }

    @Override
    public int getSlots() { return 54; }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if(clickedItem.getType().equals(Material.BARRIER)) player.closeInventory();
        else if(clickedItem.getType().equals(Material.WOOD_BUTTON)) {

            if(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).equalsIgnoreCase("↩")) {
                // Back Button
                if(page == 0) {
                    ItemStack defaultItem = event.getInventory().getItem(event.getSlot());
                    ItemStack temporaryErrorItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                    ItemMeta temporaryErrorItemMeta = temporaryErrorItem.getItemMeta();
                    temporaryErrorItemMeta.setDisplayName(JayUtils.colour("&4&oError"));
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(JayUtils.colour("&cYou are already on the first page!"));
                    temporaryErrorItemMeta.setLore(lore);
                    temporaryErrorItem.setItemMeta(temporaryErrorItemMeta);

                    inventory.setItem(event.getSlot(), temporaryErrorItem);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            inventory.setItem(event.getSlot(), defaultItem);
                        }
                    }, 60L);
                } else {
                    page = page - 1;
                    super.open();
                }
            } else if(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).equalsIgnoreCase("↪")) {
                // Next Button
                if((index + 1) >= plugin.tags.size()) {
                    ItemStack defaultItem = event.getInventory().getItem(event.getSlot());
                    ItemStack temporaryErrorItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                    ItemMeta temporaryErrorItemMeta = temporaryErrorItem.getItemMeta();
                    temporaryErrorItemMeta.setDisplayName(JayUtils.colour("&4&oError"));
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(JayUtils.colour("&cYou are already on the last page!"));
                    temporaryErrorItemMeta.setLore(lore);
                    temporaryErrorItem.setItemMeta(temporaryErrorItemMeta);

                    inventory.setItem(event.getSlot(), temporaryErrorItem);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            inventory.setItem(event.getSlot(), defaultItem);
                        }
                    }, 60L);


                } else {
                    page = page + 1;
                    super.open();
                }
            }

        } else {
            if( !(clickedItem.getType().equals(Material.STAINED_GLASS_PANE) && clickedItem.getDurability() == (short) 15) && !ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).equalsIgnoreCase(" ")) {
                PlayerMenuUtility playerMenuUtility = TagsPlugin.getPlayerMenuUtility(player);

                if(TagUtils.getTagFromItem(clickedItem, player) != null) {
                    playerMenuUtility.setTagToEquip(TagUtils.getTagFromItem(clickedItem, player));

                    new ConfirmTagSelectMenu(playerMenuUtility).open();
                }

            }
        }

    }

    @Override
    public void setMenuItems() {

        // Start by adding the border to the menu
        addMenuBorder();

        if(!plugin.tags.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= plugin.tags.size()) break;
                if(plugin.tags.get(index) != null) inventory.addItem(plugin.tags.get(index).getPlayerBasedMenuItem(playerMenuUtility.getOwner()));
            }
        }

    }

}
