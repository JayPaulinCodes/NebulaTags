package jay.neubla.tags.menuManager;

import jay.neubla.tags.TagsPlugin;
import jay.neubla.tags.utils.JayUtils;
import org.bukkit.Material;

public abstract class PaginatedMenu extends Menu {
    protected static final TagsPlugin plugin = TagsPlugin.getInstance();

    // Keep track of what page the menu is on
    protected int page = 0;

    // 28 is max items because with the border set below,
    // 28 empty slots are remaining.
    protected int maxItemsPerPage = 28;

    // the index represents the index of the slot
    // that the loop is on
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    //Set the border and menu buttons for the menu
    public void addMenuBorder(){
        inventory.setItem(
                48,
                makeItem(
                        Material.WOOD_BUTTON,
                null,
                        JayUtils.colour("&a↩"),
                        JayUtils.colour("&7Click to navigate"),
                        JayUtils.colour("&7to the previous page.")
                )
        );

        inventory.setItem(
                49,
                makeItem(
                        Material.BARRIER,
                null,
                        JayUtils.colour("&c✖"),
                        JayUtils.colour("&7Click to close the menu")
                )
        );

        inventory.setItem(
                50,
                makeItem(
                        Material.WOOD_BUTTON,
                null,
                        JayUtils.colour("&a↪"),
                        JayUtils.colour("&7Click to navigate"),
                        JayUtils.colour("&7to the next page.")
                )
        );

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_ITEM);
            }
        }

        inventory.setItem(17, super.FILLER_ITEM);
        inventory.setItem(18, super.FILLER_ITEM);
        inventory.setItem(26, super.FILLER_ITEM);
        inventory.setItem(27, super.FILLER_ITEM);
        inventory.setItem(35, super.FILLER_ITEM);
        inventory.setItem(36, super.FILLER_ITEM);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_ITEM);
            }
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

}

