package thaumicinsurgence.main.utils.compat;

import cpw.mods.fml.common.Loader;
import net.minecraft.tileentity.TileEntity;
import thaumicinsurgence.main.Config;

public class EnergisticsHelper implements IModHelper {

    private static boolean isEnergisticsActive = false;
    public static final String THAUMIC_ENERGISTICS = "thaumicenergistics";

    public static boolean isActive() {
        return isEnergisticsActive;
    }

    @Override
    public void preInit() {
        if (Loader.isModLoaded(THAUMIC_ENERGISTICS) && Config.energisticsActive) {
            isEnergisticsActive = true;
        }
    }

    @Override
    public void init() {}

    @Override
    public void postInit() {}


}
