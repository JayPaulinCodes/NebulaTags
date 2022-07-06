package jay.neubla.tags.commands;

import jay.neubla.tags.TagsPlugin;
import jay.neubla.tags.menuManager.menus.SelectTagMenu;
import org.bukkit.entity.Player;

@NebulaCommandInfo(
        name = "tags",
        usage = "/tags",
        description = "Allows players to select a tag to use.",
        aliases = {
                "tag",
                "selecttags",
                "selecttag"
        },
        requiresPlayer = true
)
public class SelectTagCommand extends NebulaCommand {

        @Override
        public void execute( Player player, String[] args ) {
                new SelectTagMenu(TagsPlugin.getPlayerMenuUtility(player)).open();
        }

}
