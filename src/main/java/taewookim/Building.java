package taewookim;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Building {

    public Map<Vector, BlockData> blockdata = new HashMap<>();
    public double mx = 0;
    public double my = 0;
    public double mz = 0;

    public Building(Block block1, Block block2) {
        Map<Vector, BlockData> dlatl = new HashMap<>();
        int fx = block1.getX();
        int fy = block1.getY();
        int fz = block1.getZ();
        int dx = block2.getX()-block1.getX();
        int dy = block2.getY()-block1.getY();
        int dz = block2.getZ()-block1.getZ();
        if(dx<0) {
            fx+=dx;
            dx*=-1;
        }
        if(dy<0) {
            fy+=dy;
            dy*=-1;
        }
        if(dz<0) {
            fz+=dz;
            dz*=-1;
        }
        int c = 0;
        for(int x = 0; x<dx+1;x++) {
            for(int y = 0;y<dy+1;y++) {
                for(int z = 0;z<dz+1;z++) {
                    Block b = block1.getWorld().getBlockAt(fx+x, fy+y, fz+z);
                    if(!b.getType().equals(Material.AIR)) {
                        c++;
                        mx+=x;
                        my+=y;
                        mz+=z;
                        dlatl.put(new Vector(x, y, z), b.getBlockData());
                    }
                }
            }
        }
        mx/=((double)c);
        my/=((double)c);
        mz/=((double)c);
        for(Map.Entry<Vector, BlockData> entry : dlatl.entrySet()) {
            blockdata.put(entry.getKey().add(new Vector(-mx, -my, -mz)), entry.getValue());
        }
    }

    public Building(YamlConfiguration config) {

    }

    public void save(YamlConfiguration config) {

    }

}
