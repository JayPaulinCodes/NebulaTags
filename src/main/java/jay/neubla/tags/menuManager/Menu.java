package jay.neubla.tags.menuManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtility playerMenuUtility;
    protected Inventory inventory;
    protected ItemStack FILLER_ITEM = makeItem(Material.STAINED_GLASS_PANE, (short) 15, " ");

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    /**
     * Accessor method for the name of the menu
     * @return Returns the name of the menu
     */
    public abstract String getMenuName();

    /**
     * Accessor method for the slots in the menu
     * @return Returns the amount of slots in the menu
     */
    public abstract int getSlots();

    /**
     * Handle item click events for the menu
     * @param event The inventoryClickEvent
     */
    public abstract void handleMenu(InventoryClickEvent event);

    /**
     * Method called when opening the menu which will set items
     */
    public abstract void setMenuItems();

    /**
     * Creates and opens a menu for the player
     */
    public void open() {
        // The owner of the inventory created is the Menu itself,
        // so we are able to reverse engineer the Menu object from the
        // inventoryHolder in the MenuListener class when handling clicks
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        // Grab all the items specified to be used for this menu and add to inventory
        this.setMenuItems();

        // Finish by opening the inventory for the player
        playerMenuUtility.getOwner().openInventory(inventory);
    }

    // Overridden method from the InventoryHolder interface
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    // Helpful utility method to fill all remaining slots with "filler item"
    public void setFillerItem(){
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null){
                inventory.setItem(i, FILLER_ITEM);
            }
        }
    }

    public ItemStack makeItem(Material material, Short damage, String displayName, String... lore) {

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        if (damage != null) item.setDurability(damage);
        itemMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemMeta);

        return item;
    }

}
