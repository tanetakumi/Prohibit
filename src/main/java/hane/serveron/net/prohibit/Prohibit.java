package hane.serveron.net.prohibit;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Prohibit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Prohibit Start");

        if(Bukkit.getDefaultGameMode() == GameMode.CREATIVE){
            new CreativeListener(this);
        } else if(Bukkit.getDefaultGameMode() == GameMode.SURVIVAL){
            new SurvivalListener(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Prohibit Stop");
        HandlerList.unregisterAll(this);
    }
}
