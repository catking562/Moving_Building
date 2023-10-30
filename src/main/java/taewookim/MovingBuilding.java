package taewookim;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import taewookim.commands.CreateBuild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovingBuilding extends JavaPlugin {

    public static Map<Player, Block> target1 = new HashMap<>();
    public static Map<Player, Block> target2 = new HashMap<>();
    public static Map<String, Building> buildinglist = new HashMap<>();
    public static ArrayList<ObjectBuilding> objectlist = new ArrayList<>();
    public static ArrayList<ObjectBuilding> addinglist = new ArrayList<>();
    public static boolean isupdating = false;
    public static Map<Player, ObjectBuilding> selectobject = new HashMap<>();

    public static void addObjectBuilding(ObjectBuilding building) {
        if(isupdating) {
            addinglist.add(building);
        }else {
            objectlist.add(building);
        }
    }

    public void Update() {
        BukkitRunnable brun = new BukkitRunnable() {
            @Override
            public void run() {
                isupdating = true;
                for(ObjectBuilding building : objectlist) {
                    building.Update();
                }
                isupdating = false;
                objectlist.addAll(addinglist);
                addinglist.clear();
            }
        };brun.runTaskTimer(this, 0, 0);
    }

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        CreateBuild cb = new CreateBuild();
        Bukkit.getPluginCommand("createbuild").setExecutor(cb);
        Bukkit.getPluginCommand("createbuild").setTabCompleter(cb);
        Update();
    }

    public void onDisable() {
        for(ObjectBuilding b : objectlist) {
            for(CustomBlockDisplay d : b.displays) {
                d.getBukkitEntity().remove();
            }
        }
    }

}
