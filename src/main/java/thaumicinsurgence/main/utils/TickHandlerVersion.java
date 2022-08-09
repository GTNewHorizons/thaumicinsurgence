package thaumicinsurgence.main.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class TickHandlerVersion {

    private static int TICK_POLL_INTERVAL = 250;

    private static class LazyHolder {
        private static final TickHandlerVersion instance = new TickHandlerVersion();
    }

    private int ticksToPoll;
    private VersionInfo versionInfo;

    private final ChatStyle newVersion =
            new ChatStyle().setColor(EnumChatFormatting.GOLD).setBold(true);
    private final ChatStyle description = new ChatStyle().setColor(EnumChatFormatting.GRAY);
    private final ChatStyle alert = new ChatStyle().setColor(EnumChatFormatting.RED);

    private TickHandlerVersion() {
        ticksToPoll = TICK_POLL_INTERVAL;
    }

    private void setVersionInfo(VersionInfo vInfo) {
        versionInfo = vInfo;
    }

    @SubscribeEvent
    public void tickStart(PlayerTickEvent evt) {
        if (evt.phase != Phase.START) {
            return;
        }
        if (ticksToPoll > 0) {
            ticksToPoll--;
            return;
        }
        ticksToPoll = TICK_POLL_INTERVAL;

        if (versionInfo.versionCheckComplete) {
            unsubscribeFromBus();

            if (updateNotificationsEnabledOrCriticalUpdate()) {
                sendNotificationToPlayer(evt.player);
            }
        }
    }

    private void sendNotificationToPlayer(EntityPlayer player) {
        if (versionInfo.isCriticalUpdate()) {
            player.addChatMessage(
                    new ChatComponentTranslation("magicbees.versioning.critical", versionInfo.getLatestVersion())
                            .setChatStyle(alert));
        } else {
            player.addChatMessage(
                    new ChatComponentTranslation("magicbees.versioning.newVersion", versionInfo.getLatestVersion())
                            .setChatStyle(newVersion));
        }
        ChatStyle thisDescription = description.createShallowCopy();
        thisDescription.setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, VersionInfo.DownloadURL));
        thisDescription.setChatHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT, new ChatComponentText(versionInfo.getVersionDescription())));
        player.addChatMessage(
                new ChatComponentTranslation("magicbees.versioning.clickDownload").setChatStyle(thisDescription));
    }

    private boolean updateNotificationsEnabledOrCriticalUpdate() {
        return (versionInfo.isCriticalUpdate()) && versionInfo.isNewVersionAvailable();
    }

    private void subscribeToBus() {
        FMLCommonHandler.instance().bus().register(this);
    }

    private void unsubscribeFromBus() {
        FMLCommonHandler.instance().bus().unregister(this);
    }

    public static void go(VersionInfo vInfo) {
        LazyHolder.instance.setVersionInfo(vInfo);
        LazyHolder.instance.subscribeToBus();
    }

    /*@Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if (sent) {
            return;
        }

        if (modIndex < modVersionInfo.size()) {
            VersionInfo anInfo = modVersionInfo.get(modIndex);

            if ((!Config.DisableUpdateNotification || anInfo.isCriticalUpdate()) && anInfo.isNewVersionAvailable())
            {
                EntityPlayer player = (EntityPlayer) tickData[0];
                player.sendChatToPlayer(ChatMessageComponent.createFromText("[" + anInfo.modName + "] A new version is available: " + anInfo.getLatestVersion()));
                player.sendChatToPlayer(ChatMessageComponent.createFromText(anInfo.getVersionDescription()));
            }
            modIndex += 1;
        }
        else {
            sent = true;
        }
    }
    */
}
