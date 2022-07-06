package jay.neubla.tags;

import jay.neubla.tags.expansion.PAPI;
import jay.neubla.tags.menuManager.PlayerMenuUtility;
import jay.neubla.tags.objects.Tag;
import jay.neubla.tags.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TagsPlugin extends JavaPlugin {
    public static boolean debug = false;
    public Config config = new Config("config.yml", this); // config object for config.yml
    public Config dataConfig = new Config("data.yml", this); // config object for data.yml
    public List<Tag> tags;
    public static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public static TagsPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        Logger.logCustom( "Checking for dependencies...", "startup", "&a" );
        Logger.action( "Looking for PlaceholderAPI..." );

        // Check if the server has PlaceholderAPI (PAPI)
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Logger.error( "Dependency \"PlaceholderAPI\" not present! Stopping plugin from starting!" );
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        Logger.logCustom( "All dependencies present!", "startup", "&a" );

        Logger.logCustom( "Setting up configs...", "startup", "&a" );
        config.setup();
        dataConfig.setup();
        debug = config.get().getBoolean("debug");
        Logger.logCustom( "Finished setting up configs!", "startup", "&a" );

        Logger.logCustom( "Fetching tags from config.yml...", "startup", "&a" );
        this.tags = new ArrayList<>(TagUtils.getAllTags());
        Logger.logCustom( "Finished fetching tags from config.yml!", "startup", "&a" );

        Logger.logCustom( "Registering PlaceholderAPI expansion...", "startup", "&a" );
        new PAPI(this).register();
        Logger.logCustom( "Finished registering PlaceholderAPI expansion!", "startup", "&a" );

        Logger.logCustom( "Registering listeners...", "startup", "&a" );
        // Register every listener class in the package jay.nebula.tags.listeners that implements org.bukkit.event.Listener
        JayUtils.registerListeners( this);
        Logger.logCustom( "Finished registering listeners!", "startup", "&a" );

        Logger.logCustom( "Registering commands...", "startup", "&a" );
        // Register every command class in the package jay.nebula.tags.commands that extends NebulaCommand
        JayUtils.registerCommands( this);
        Logger.logCustom( "Finished registering commands!", "startup", "&a" );

    }

    @Override
    public void onDisable() {

    }

    /**
     * Provide a player and return a menu system for that player,
     * create one if they don't already have one
     * @param player org.bukkit.entity.Player
     * @return jay.nebula.tags.menuManager.PlayerMenuUtility that corresponds with the provided player
     */
    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(player))) { // See if the player has a playermenuutility "saved" for them

            // This player doesn't. Make one for them add add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(player); // Return the object by using the provided player
        }
    }

    /**
     * Fetch an instance of the plugin
     * @return TagsPlugin instance
     */
    public static TagsPlugin getInstance() { return plugin; }


}
