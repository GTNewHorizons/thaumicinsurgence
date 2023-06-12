//
// Decompiled by Procyon v0.5.30
//

package thaumicinsurgence.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import thaumicinsurgence.main.Config;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(final GuiScreen parent) {
        super(
                parent,
                configElements(),
                "thaumicinsurgence",
                false,
                false,
                GuiConfig.getAbridgedConfigPath(Config.configuration.toString()));
    }

    private static List<IConfigElement> configElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        return list;
    }

    private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(
                name,
                tooltip_key,
                new ConfigElement(Config.configuration.getCategory(category)).getChildElements());
    }
}
