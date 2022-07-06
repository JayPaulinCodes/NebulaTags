package jay.neubla.tags.utils;

import org.bukkit.Bukkit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Jay's server console logging class
 *
 * Modifications:
 *     [2021-10-08] (V1.0.0) - Created class
 *     [2021-10-10] (V1.0.1) - Addressed bug which caused logs to be sent twice
 * @author Jay
 * @version 1.0.1
 * @since 2021-10-08
 */
public class Logger {

    /**
     * Server console logging function.
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     * @param type The type of log to send, available types include: log, warn, error, debug, cmd, ready, action (If no type is specified default will be log) **This field is optional**
     */
    public static void log(String content, String type) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "[" + formatter.format(new Date(System.currentTimeMillis())) + "] ";

        switch( type.toLowerCase() ) {
            case "log": Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&f" + type.toUpperCase() + " &r" + content)); break;
            case "warn": Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&e" + type.toUpperCase() + " &r" + content)); break;
            case "error": Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&c" + type.toUpperCase() + " &r" + content)); break;
            case "debug": Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&a" + type.toUpperCase() + " &r" + content)); break;
            case "cmd": Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&f" + type.toUpperCase() + " &r" + content)); break;
            case "ready": Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&2" + type.toUpperCase() + " &r" + content)); break;
            case "action": Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&b" + type.toUpperCase() + " &r" + content)); break;
            default: Bukkit.getConsoleSender().sendMessage(
                    JayUtils.colour("&r" + timestamp + " &l&f" + type.toUpperCase() + " &r" + content)); break;
        }
    }

    /**
     * Server console logging function.
     *
     * Logs to the console using type warn
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     */
    public static void warn(String content) { log(content, "warn"); }

    /**
     * Server console logging function.
     *
     * Logs to the console using type error
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     */
    public static void error(String content) { log(content, "error"); }

    /**
     * Server console logging function.
     *
     * Logs to the console using type debug
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     */
    public static void debug(String content) { log(content, "debug"); }

    /**
     * Server console logging function.
     *
     * Logs to the console using type cmd
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     */
    public static void cmd(String content) { log(content, "cmd"); }

    /**
     * Server console logging function.
     *
     * Logs to the console using type ready
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     */
    public static void ready(String content) { log(content, "ready"); }

    /**
     * Server console logging function.
     *
     * Logs to the console using type action
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     */
    public static void action(String content) { log(content, "action"); }

    /**
     * Server console logging function, with custom type option and ability to change the type colour
     * @param content The message to be sent to the console (Supports minecraft colour codes)
     * @param type What ever you want to display as the type
     * @param typeColour The minecraft colour code of the type
     */
    public static void logCustom(String content, String type, String typeColour) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = "[" + formatter.format(new Date(System.currentTimeMillis())) + "] ";

        Bukkit.getConsoleSender().sendMessage(
                JayUtils.colour("&r" + timestamp + " &l" + typeColour + type.toUpperCase() + " &r" + content));
    }
}
