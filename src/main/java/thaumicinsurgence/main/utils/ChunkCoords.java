package thaumicinsurgence.main.utils;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ChunkCoords {

    public final int dimension;
    public final int x;
    public final int y;
    public final int z;

    public ChunkCoords(TileEntity entity) {
        this(entity.getWorldObj(), entity.xCoord, entity.yCoord, entity.zCoord);
    }

    public ChunkCoords(Entity entity) {
        this(entity.worldObj, entity.chunkCoordX, entity.chunkCoordY, entity.chunkCoordZ);
    }

    public ChunkCoords(World world, int xCoord, int yCoord, int zCoord) {
        this(world.provider.dimensionId, xCoord, yCoord, zCoord);
    }

    public ChunkCoords(int dimId, int xCoord, int yCoord, int zCoord) {
        this.dimension = dimId;
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
    }

    /**
     * Adds chunk coords together & returns the result within the same dimension.
     */
    public ChunkCoords add(ChunkCoords coords) {
        return new ChunkCoords(coords.dimension, this.x + coords.x, this.y + coords.y, this.z + coords.z);
    }

    /**
     * Subtracts chunk coords (a.subtract(b) == a - b) & returns the result within the same dimension.
     */
    public ChunkCoords subtract(ChunkCoords coords) {
        return new ChunkCoords(coords.dimension, this.x - coords.x, this.y - coords.y, this.z - coords.z);
    }

    /**
     * Returns a new ChunkCoords in the specified dimension.
     */
    public ChunkCoords inDimension(int dimId) {
        return new ChunkCoords(dimId, x, y, z);
    }

    /**
     * Returns a new ChunkCoords in the specified world.
     */
    public ChunkCoords inDimension(World world) {
        return new ChunkCoords(world.provider.dimensionId, x, y, z);
    }

    /**
     * Writes obj to the given tag compound as a subtag. Writes nothing if obj is null.
     */
    public static void writeToNBT(ChunkCoords obj, NBTTagCompound tagRoot) {
        if (obj != null) {
            NBTTagCompound coordsTag = new NBTTagCompound();
            coordsTag.setInteger("dim", obj.dimension);
            coordsTag.setInteger("xPos", obj.x);
            coordsTag.setInteger("yPos", obj.y);
            coordsTag.setInteger("zPos", obj.z);
            tagRoot.setTag("chunkCoords", coordsTag);
        }
    }

    /**
     * Reads a ChunkCoords from an NBT tag. If no ChunkCoords subtag exists, returns null.
     */
    public static ChunkCoords readFromNBT(NBTTagCompound tagRoot) {
        if (tagRoot.hasKey("chunkCoords")) {
            NBTTagCompound coordsTag = (NBTTagCompound) tagRoot.getTag("chunkCoords");
            int dim = coordsTag.getInteger("dim");
            int x = coordsTag.getInteger("xPos");
            int y = coordsTag.getInteger("yPos");
            int z = coordsTag.getInteger("zPos");
            return new ChunkCoords(dim, x, y, z);
        }
        return null;
    }
}
