package jay.neubla.tags.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Jay's config class
 *
 * Modifications:
 *     [2021-10-10] (v1.0.0) - Created class
 *     [2022-07-06] (v1.1.0) - Refactored structure
 * @author Jay
 * @version 1.1.0
 * @since 2021-10-08
 */
public class Config {

    private final String FILE_NAME;
    private final JavaPlugin PLUGIN;
    private final File FILE;
    private FileConfiguration fileConfig;

    /**
     * Constructor method for config class
     * @param fileName Name of the config file
     * @param plugin Instance of your plugin
     * @since 1.0.0
     */
    public Config(String fileName, JavaPlugin plugin) {
        this.FILE_NAME = fileName;
        this.PLUGIN = plugin;
        FILE = new File(PLUGIN.getDataFolder(), FILE_NAME);
        fileConfig = new YamlConfiguration();
    }

    /**
     * Run this during server start up for each config file.
     * This will initialize the config file
     * @since 1.0.0
     */
    public void setup() {
        Logger.action("Beggining setup for config file " + FILE_NAME);

        if (!FILE.exists()) {
            FILE.getParentFile().mkdirs();
            PLUGIN.saveResource(FILE_NAME, false);
        }

        try {
            fileConfig.load(FILE);
            Logger.action("Config file setup for " + FILE_NAME + " complete!");
        } catch (IOException | InvalidConfigurationException exception) {
            Logger.action("Config file setup for " + FILE_NAME + " failed!");
            exception.printStackTrace();
        }

    }

    /**
     * Fetch the config data to use
     * @return The file's configuration
     * @since 1.0.0
     */
    public FileConfiguration get() { return fileConfig; }

    /**
     * Relaods the configuration file
     * @since 1.0.0
     */
    public void reload() {
        fileConfig = YamlConfiguration.loadConfiguration(FILE);
        Logger.log("The " + FILE_NAME + " file has been reloaded!", "log");
    }

    /**
     * Saves the configuration file
     * @since 1.0.0
     */
    public void save() {
        try {
            fileConfig.save(FILE);
            Logger.log("The " + FILE_NAME + " file has been saved", "log");

        } catch (IOException e) {
            Logger.warn("The " + FILE_NAME + " file could not be saved!");
        }
    }

}
