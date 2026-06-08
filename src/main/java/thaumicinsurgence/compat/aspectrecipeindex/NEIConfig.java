package thaumicinsurgence.compat.aspectrecipeindex;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import thaumicinsurgence.main.utils.VersionInfo;

public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.registerRecipeHandler(new VisweaverRecipeHandler());
        API.registerUsageHandler(new VisweaverRecipeHandler());
    }

    @Override
    public String getName() {
        return VersionInfo.ModName;
    }

    @Override
    public String getVersion() {
        return VersionInfo.Version;
    }
}
