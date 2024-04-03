package thaumicinsurgence.main.modules.planar_artifice.core.blocks.tiles;

public class TileVoidAlkimiumAlchemicalFurnace extends TileAlkimiumAlchemicalFurnace {

    public static final String tileEntityName = "planarartifice.alkimium_smeltery_void";

    public void setValues() {
        speedValue = 5;
        maxVis = 112; // approximately 112.5 from the 1.5x capacity of the previous
        tierSpeed = 0.46;
    }

}
