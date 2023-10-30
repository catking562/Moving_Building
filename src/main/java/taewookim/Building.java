package taewookim;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Building {

    public Map<Vector, Material> blockdata = new HashMap<>();
    public double mx = 0;
    public double my = 0;
    public double mz = 0;

    public Building(Block block1, Block block2) {
        int dx = block2.getX()-block1.getX();
        int dy = block2.getY()-block1.getY();
        int dz = block2.getZ()-block1.getZ();
        int c = 0;
        for(int x = 0; x<dx;x++) {
            for(int y = 0;y<dy;y++) {
                for(int z = 0;z<dz;z++) {
                    if(!block1.getLocation().add(x, y, z).getBlock().getType().equals(Material.AIR)) {
                        c++;
                        mx+=x;
                        my+=y;
                        mz+=z;
                        blockdata.put(new Vector(x, y, z), block1.getLocation().add(x, y, z).getBlock().getType());
                    }
                }
            }
        }
        mx/=((double)c);
        my/=((double)c);
        mz/=((double)c);
        Set<Map.Entry<Vector, Material>> datas = blockdata.entrySet();
        blockdata.clear();
        for(Map.Entry<Vector, Material> entry : datas) {
            blockdata.put(entry.getKey().add(new Vector(-mx, -my, -mz)), entry.getValue());
        }
    }

    public Building(YamlConfiguration config) {
        for(String key : config.getKeys(false)) {
            Material m = Material.valueOf(key);
            int size = config.getInt(key + ".size");
            for(int i = 0; i<size;i++) {
                blockdata.put(config.getVector(key + ".v" + i), m);
            }
        }
    }

    public void save(YamlConfiguration config) {
        Map<Material, ArrayList<Vector>> list = new HashMap<>();
        for(Map.Entry<Vector, Material> entry : blockdata.entrySet()) {
            Material m = entry.getValue();
            if(list.get(m)!=null) {
                list.get(m).add(entry.getKey());
            }else {
                list.put(m, new ArrayList<>());
                list.get(m).add(entry.getKey());
            }
        }
        for(Map.Entry<Material, ArrayList<Vector>> entry : list.entrySet()) {
            config.set(entry.getKey().toString() + ".size", entry.getValue().size());
            for(int i = 0;i<entry.getValue().size();i++) {
                config.set(entry.getKey().toString() + ".v" + i, entry.getValue().get(i));
            }
        }
        config.set("mx", mx);
        config.set("my", my);
        config.set("mz", mz);
    }

}
