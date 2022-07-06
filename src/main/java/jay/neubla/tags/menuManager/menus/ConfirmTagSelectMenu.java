package jay.neubla.tags.menuManager.menus;

import jay.neubla.tags.TagsPlugin;
import jay.neubla.tags.menuManager.PaginatedMenu;
import jay.neubla.tags.menuManager.PlayerMenuUtility;
import jay.neubla.tags.utils.JayUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfirmTagSelectMenu extends PaginatedMenu {

    public ConfirmTagSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return JayUtils.colour( plugin.config.get().getString("confirmMenuTitle") );
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem.getType().equals(Material.WOOL) && clickedItem.getDurability() == (short) 5) {
            // Yes
            plugin.dataConfig.get().set(p.getUniqueId().toString(), playerMenuUtility.getTagToEquip().getKey());
            plugin.dataConfig.save();

            playerMenuUtility.resetTagToEquip();
        } else if (clickedItem.getType().equals(Material.WOOL) && clickedItem.getDurability() == (short) 15) {
            // No
            playerMenuUtility.resetTagToEquip();
            new SelectTagMenu(TagsPlugin.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() {
        // [0] [1] [2 - Confirm No] [3] [4 - Tag Item] [5] [6 - Confirm Yes] [7] [8]

        ItemStack confirmNoItem = new ItemStack(Material.WOOL, 1, (short) 15);
        ItemMeta confirmNoItemMeta = confirmNoItem.getItemMeta();
        confirmNoItemMeta.setDisplayName(JayUtils.colour("&4No"));
        confirmNoItem.setItemMeta(confirmNoItemMeta);

        ItemStack confirmYesItem = new ItemStack(Material.WOOL, 1, (short) 5);
        ItemMeta confirmYesItemMeta = confirmYesItem.getItemMeta();
        confirmYesItemMeta.setDisplayName(JayUtils.colour("&2Yes"));
        confirmYesItem.setItemMeta(confirmYesItemMeta);

        ItemStack tagItem = playerMenuUtility.getTagToEquip().getPlayerBasedMenuItem(playerMenuUtility.getOwner());

        inventory.setItem(2, confirmNoItem);
        inventory.setItem(4, tagItem);
        inventory.setItem(6, confirmYesItem);

        for(int i = 0; i < getSlots(); i++) { inventory.addItem(FILLER_ITEM); }

    }

}
