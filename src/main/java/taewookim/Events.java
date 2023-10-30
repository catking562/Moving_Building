package taewookim;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {

    @EventHandler
    public void interblock(PlayerInteractEvent e) {
        Action action = e.getAction();
        Player p = e.getPlayer();
        if(p.getItemInHand()!=null&&e.getPlayer().isOp()) {
            ItemStack i = p.getItemInHand();
            if(i.hasItemMeta()&&i.getType().equals(Material.WOODEN_AXE)) {
                if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    MovingBuilding.target2.put(e.getPlayer(), e.getClickedBlock());
                    p.sendMessage("타겟2 지정완료");
                }else if(action.equals(Action.LEFT_CLICK_BLOCK)) {
                    MovingBuilding.target1.put(e.getPlayer(), e.getClickedBlock());
                    p.sendMessage("타겟1 지정완료");
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e) {
        MovingBuilding.target1.remove(e.getPlayer());
        MovingBuilding.target2.remove(e.getPlayer());
        MovingBuilding.selectobject.remove(e.getPlayer());
    }

}
