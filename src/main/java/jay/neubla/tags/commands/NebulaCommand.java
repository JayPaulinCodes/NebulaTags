package jay.neubla.tags.commands;

import jay.neubla.tags.TagsPlugin;
import jay.neubla.tags.utils.JayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class NebulaCommand implements CommandExecutor {
    protected static final TagsPlugin plugin = TagsPlugin.getInstance();
    private final NebulaCommandInfo commandInfo;

    public NebulaCommand() {
        commandInfo = getClass().getDeclaredAnnotation( NebulaCommandInfo.class );
        Objects.requireNonNull( commandInfo, "Commands must have CommandInfo annotations" );
    }

    public NebulaCommandInfo getCommandInfo() { return commandInfo; }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {

        // Check if the command needs a permission, and if it does, make sure the user has that permission
        if( !commandInfo.permission().isEmpty() && !sender.hasPermission( commandInfo.permission() ) ) {
            sender.sendMessage( JayUtils.colour( "&cYou don't have the required permission for this command!" ) );
            return true;
        }

        // Check if the executor needs to be a player, and if it does, check if the executor is a player
        if( commandInfo.requiresPlayer() && !( sender instanceof Player ) ) {
            sender.sendMessage( JayUtils.colour( "&cYou must be a player to execute this command!" ) );
            return true;
        } else if( commandInfo.requiresPlayer() ){
            execute( ( Player ) sender, args );
            return true;
        }

        execute( sender, args );
        return true;
    }

    public void execute( Player player, String[] args ) {}
    
    public void execute( CommandSender sender, String[] args ) {}

}
