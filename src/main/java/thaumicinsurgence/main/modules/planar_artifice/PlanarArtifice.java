package thaumicinsurgence.main.modules.planar_artifice;

import thaumicinsurgence.main.modules.planar_artifice.utils.PlanarBlocks;
import thaumicinsurgence.main.utils.compat.IModHelper;

public class PlanarArtifice implements IModHelper {

    public void preInit() {
        PlanarBlocks.setup();
    }

    public void init() {}

    public void postInit() {
        // TBRecipes.setup();
        // TBThaumonomicon.setup();
    }

}
