package jay.neubla.tags.expansion;

import jay.neubla.tags.TagsPlugin;
import jay.neubla.tags.utils.TagUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.apache.commons.lang.StringUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PAPI extends PlaceholderExpansion {
    private final TagsPlugin plugin;

    public PAPI(TagsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return plugin.getDescription().getName();
    }

    @Override
    public @NotNull String getAuthor() {
        return StringUtils.join(plugin.getDescription().getAuthors(), ", ");
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("tag")){
            if(plugin.dataConfig.get().getString(player.getUniqueId().toString()) != null)
                return TagUtils.getTagFromKey(plugin.dataConfig.get().getString(player.getUniqueId().toString())).getTag();
            else return "";
        }

        return null; // Placeholder is unknown by the Expansion
    }

}
