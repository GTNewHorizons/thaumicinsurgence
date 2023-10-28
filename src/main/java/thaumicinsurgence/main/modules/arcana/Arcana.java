package thaumicinsurgence.main.modules.arcana;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;
import thaumicinsurgence.main.modules.ModuleConfig;
import thaumicinsurgence.main.modules.arcana.utils.ArcanaBlocks;
import thaumicinsurgence.main.modules.arcana.utils.ArcanaItems;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarBlocks;
import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarItems;
import thaumicinsurgence.main.utils.VersionInfo;
import thaumicinsurgence.main.utils.compat.IModHelper;

public class Arcana implements IModHelper {

    public static EnumRarity arcanaRed = EnumHelper.addRarity(VersionInfo.ModID, EnumChatFormatting.RED, VersionInfo.ModName);
    public void preInit() {
        if (ModuleConfig.arcanaActive) {
            ArcanaBlocks.setup();
            ArcanaItems.setup();
        }
    }

    public void init() {}

    public void postInit() {

    }

}
