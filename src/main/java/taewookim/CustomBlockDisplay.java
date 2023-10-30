package taewookim;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.api.TriangleMath;

public class CustomBlockDisplay extends Display.BlockDisplay {

    final org.bukkit.entity.ItemDisplay display;
    final ObjectBuilding building;
    final Vector v;

    public CustomBlockDisplay(World var1, Material material, ObjectBuilding building, Vector v, Location loc) {
        super(EntityTypes.j, var1);
        display = ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity());
        display.setItemStack(new ItemStack(material));
        this.building = building;
        this.v = v;
        display.teleport(loc);
    }

    public void Update() {
        int x = building.yaw;
        int y = building.pitch;
        int z = building.roll;
        double vx = v.getX();
        double vy = v.getY();
        double vz = v.getZ();
        display.teleport(new Location(building.w
                , building.x + TriangleMath.cos.get(y)*TriangleMath.cos.get(z)*vx - TriangleMath.cos.get(y)*TriangleMath.sin.get(z)*vy + TriangleMath.cos.get(y)*vz
                , building.y + (TriangleMath.cos.get(x)*TriangleMath.sin.get(z) + TriangleMath.sin.get(x)*TriangleMath.sin.get(y)*TriangleMath.cos.get(z))*vx + (TriangleMath.cos.get(x)*TriangleMath.cos.get(z)-TriangleMath.sin.get(x)*TriangleMath.sin.get(y)*TriangleMath.sin.get(z))*vy - TriangleMath.sin.get(x)*TriangleMath.cos.get(y)*vz
                , building.z + (TriangleMath.sin.get(x)*TriangleMath.sin.get(z) - TriangleMath.cos.get(x)*TriangleMath.sin.get(y)*TriangleMath.cos.get(z))*vx + (TriangleMath.sin.get(x)*TriangleMath.cos.get(z)+TriangleMath.cos.get(x)*TriangleMath.sin.get(y)*TriangleMath.sin.get(z))*vy + TriangleMath.cos.get(x)*TriangleMath.cos.get(y)*vz
        ));
    }

    @Override
    public boolean k_() {
        return super.k_();
    }
}
