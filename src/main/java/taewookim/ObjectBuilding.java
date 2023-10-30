package taewookim;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Map;

public class ObjectBuilding {

    ArrayList<CustomBlockDisplay> displays = new ArrayList<>();
    World w;
    double x;
    double y;
    double z;
    int yaw = 0;
    int pitch = 0;
    int roll = 0;
    boolean isexit = true;

    public World getWorld() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public boolean isExit() {
        return isexit;
    }

    public double getRoll() {
        return roll;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(double yaw) {
        this.yaw = ((int)yaw*10)%3600;
    }

    public void setPitch(double pitch) {
        this.pitch = ((int)pitch*10)%3600;
    }

    public void setRoll(double roll) {
        this.roll = ((int)roll*10)%3600;
    }

    public ObjectBuilding(World w, double x, double y, double z, int yaw, int pitch, int roll, Building building) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw%3600;
        this.pitch = pitch%3600;
        this.roll = roll%3600;
        Location loc = new Location(w, x, y, z);
        for(Map.Entry<Vector, Material> entry : building.blockdata.entrySet()) {
            net.minecraft.world.level.World nw = ((CraftWorld) w).getHandle();
            CustomBlockDisplay bd = new CustomBlockDisplay(nw, entry.getValue(), this, entry.getKey(), loc);
            nw.addFreshEntity(bd, CreatureSpawnEvent.SpawnReason.CUSTOM);
            displays.add(bd);
        }
    }

    public void Update() {
        for(CustomBlockDisplay dis : displays) {
            dis.Update();
        }
    }

}
