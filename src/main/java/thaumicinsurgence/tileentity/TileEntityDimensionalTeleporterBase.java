package thaumicinsurgence.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityDimensionalTeleporterBase extends TileEntity {

    public TileEntityDimensionalTeleporterBase() {}

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            return;
        }

    }
}
