package taewookim;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.api.TriangleMath;

public class CustomBlockDisplay extends Display.BlockDisplay {

    final org.bukkit.entity.BlockDisplay display;
    final ObjectBuilding building;
    final Vector v;

    public CustomBlockDisplay(World var1, BlockData material, ObjectBuilding building, Vector v, Location loc) {
        super(EntityTypes.j, var1);
        display = ((org.bukkit.entity.BlockDisplay) this.getBukkitEntity());
        display.setBlock(material);
        this.building = building;
        this.v = v;
        display.teleport(loc);
    }

    public void Update() {
        int x = building.pitch;
        int y = building.yaw;
        int z = building.roll;
        double vx = v.getX();
        double vy = v.getY();
        double vz = v.getZ();
        Location loc = new Location(building.w
                , building.x + (TriangleMath.cos.get(y)*TriangleMath.cos.get(z)+TriangleMath.sin.get(z)*TriangleMath.sin.get(x)*TriangleMath.sin.get(y))*vx - (TriangleMath.sin.get(z)*TriangleMath.cos.get(y)+TriangleMath.cos.get(z)*TriangleMath.sin.get(x)*TriangleMath.sin.get(y))*vy - TriangleMath.cos.get(x)*TriangleMath.sin.get(y)*vz
                , building.y + TriangleMath.sin.get(z)*TriangleMath.cos.get(x)*vx + TriangleMath.cos.get(z)*TriangleMath.cos.get(x)*vy - TriangleMath.sin.get(x)*vz
                , building.z + (TriangleMath.cos.get(z)*TriangleMath.sin.get(y)+TriangleMath.sin.get(x)*TriangleMath.cos.get(y)*TriangleMath.sin.get(z))*vx - (TriangleMath.sin.get(z)*TriangleMath.sin.get(y)-TriangleMath.cos.get(z)*TriangleMath.sin.get(x)*TriangleMath.cos.get(y))*vy + TriangleMath.cos.get(x)*TriangleMath.cos.get(y)*vz
        );
        loc.setYaw(building.yaw/10.0F);
        loc.setPitch(building.pitch/10.0F);
        display.teleport(loc);
        final int hr = z/2;
        Transformation tran = display.getTransformation();
        tran.getLeftRotation().set(0, 0, (float)(double)TriangleMath.sin.get(hr), (float)(double)TriangleMath.cos.get(hr));
        display.setTransformation(tran);
    }

    @Override
    public boolean k_() {
        return super.k_();
    }
}
