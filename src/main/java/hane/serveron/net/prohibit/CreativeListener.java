package hane.serveron.net.prohibit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class CreativeListener implements Listener {

    private final Prohibit plugin;
    public CreativeListener(Prohibit plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if(!e.getWhoClicked().hasPermission("op")){
            e.setCancelled(true);
            e.getWhoClicked().sendMessage(ChatColor.RED + e.getWhoClicked().getName()+"さん。クリエイティブでのクラフトは禁止されています。");
        }
    }

    @EventHandler
    public void onGetFromCreative(InventoryCreativeEvent e) {

        if(!e.getWhoClicked().hasPermission("op")){
            Material material = e.getCursor().getType();
            if(material == Material.WITHER_SKELETON_WALL_SKULL){
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(ChatColor.RED + e.getWhoClicked().getName()+"さん。スケルトンの頭は禁止されています。");
            } else if(material == Material.POTION || material == Material.LINGERING_POTION || material == Material.SPLASH_POTION){
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(ChatColor.RED + e.getWhoClicked().getName()+"さん。ポーションは禁止されています。");
            } else if(material == Material.TNT || material == Material.TNT_MINECART){
                e.setCancelled(true);
                Bukkit.broadcastMessage(ChatColor.RED + e.getWhoClicked().getName()+"さんがTNTをインベントリの中に入れようとしました。");
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLAVA(PlayerBucketEmptyEvent e) {
        if(!e.getPlayer().hasPermission("op")) {
            if (e.getBucket() == Material.LAVA_BUCKET) {
                Location loc = e.getBlock().getLocation();
                int x = loc.getBlockX();
                int y = loc.getBlockY() + 1;
                int z = loc.getBlockZ();
                Bukkit.broadcastMessage(ChatColor.RED + e.getPlayer().getName() + "が(" + x + "," + y + "," + z + ")でマグマを置きました。");
            }
        }
    }
}
