package jay.neubla.tags.menuManager;

import jay.neubla.tags.objects.Tag;
import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private Player owner;
    private Tag tagToEquip;

    public PlayerMenuUtility(Player player) {
        this.owner = player;
    }

    /**
     * Accessor method for the varriable owner
     * Fetches the player that playerMenuUtility is assigned to
     * @return Player - owner
     */
    public Player getOwner() { return owner; }

    /**
     * Accessor method for the varriable tagToEquip
     * Fetch the tagToEquip variable
     * @return Tag - tagToEquip
     */
    public Tag getTagToEquip() { return tagToEquip; }

    /**
     * Mutator method for the varriable tagToEquip
     * @param tagToEquip
     */
    public void setTagToEquip(Tag tagToEquip) { this.tagToEquip = tagToEquip; }

    /**
     * Reset the tagToEquip variable to null
     */
    public void resetTagToEquip() { this.tagToEquip = null; }

}
