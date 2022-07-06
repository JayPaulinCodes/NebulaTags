package jay.neubla.tags.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface NebulaCommandInfo {
    String name();
    String usage();
    String description();
    String permission() default "";
    String[] aliases();
    boolean requiresPlayer();
}
