package hane.serveron.net.prohibit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class SurvivalListener implements Listener {

    private final Prohibit plugin;
    public SurvivalListener(Prohibit plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(!e.getPlayer().hasPermission("op")){
            Material material = e.getBlock().getType();
            if (material == Material.TNT) {
                e.setCancelled(true);
                Location loc = e.getBlock().getLocation();
                Bukkit.broadcastMessage(ChatColor.RED + e.getPlayer().getName()+"が("+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ()+")" +
                        "でTNTを置きました。このワールドでは禁止されています。");
            } else if(material == Material.OBSERVER){
                e.setCancelled(true);
                Bukkit.broadcastMessage(ChatColor.RED + "オブザーバーはサーバーに常時負荷がかかるので禁止されています。\nご協力をよろしくお願いします。");
            } else if(material == Material.FIRE){
                Location loc = e.getBlock().getLocation();
                Bukkit.broadcastMessage(ChatColor.RED + e.getPlayer().getName()+"が("+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ()+")" +
                        "で火をつけました。");
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if(!e.getWhoClicked().hasPermission("op")){
            Material material = e.getCurrentItem().getType();
            Player player = (Player)e.getWhoClicked();
            if(material == Material.ITEM_FRAME){
                e.setCancelled(true);
                player.sendMessage(ChatColor.GREEN + player.getName()+"さん。額縁はサーバーに負荷をかけます。\n看板への変更のご協力をよろしくお願いします。");
            } else if(material == Material.PAINTING){
                player.sendMessage(ChatColor.GREEN + player.getName()+"さん。絵画はサーバーに負荷をかけます。\n最小限の使用のご協力をよろしくお願いします。");
            } else if(material == Material.OBSERVER){
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "オブザーバーはサーバーに常時負荷がかかるので禁止されています。\nご協力をよろしくお願いします。");
            } else if(material == Material.TNT){
                e.setCancelled(true);
                Bukkit.broadcastMessage(ChatColor.RED + player.getName()+"がTNTを作成しようとしました。");
            }
        }
    }


    @EventHandler
    public void onLAVA(PlayerBucketEmptyEvent e) {
        if(!e.getPlayer().hasPermission("op")){
            if(e.getBucket()== Material.LAVA_BUCKET){
                Location loc = e.getBlock().getLocation();
                int x = loc.getBlockX();
                int y = loc.getBlockY()+1;
                int z = loc.getBlockZ();
                Bukkit.broadcastMessage(ChatColor.RED + e.getPlayer().getName()+"が("+x+","+y+","+z+")でマグマを置きました。");
            }
        }
    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        String name = e.getEntity().getCustomName();
        if(name != null){
            Location loc = e.getEntity().getLocation();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();
            Bukkit.broadcastMessage(ChatColor.RED + name+"が("+x+","+y+","+z+")でお亡くなりになりました。");
        }
    }
}
