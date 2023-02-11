package thaumicinsurgence.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.projectile.EntityAlumentum;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.InventoryUtils;

public class ItemResource extends Item {
    public IIcon[] icon = new IIcon[1];
    private IIcon iconOverlay;

    public ItemResource() {
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(Thaumcraft.tabTC);
    }

    public void registerIcons(IIconRegister ir) {
        this.icon[0] = ir.registerIcon("thaumicinsurgence:ringAmber");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icon[par1];
    }

    @SideOnly(Side.CLIENT)
    public int getRenderPasses(int metadata) {
        return metadata == 13 ? 2 : 1;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        return pass != 0 && this.getAspects(stack) != null
                ? this.iconOverlay
                : this.getIconFromDamage(stack.getItemDamage());
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int par2) {
        return par2 == 1 && stack.getItemDamage() == 13 && this.getAspects(stack) != null
                ? this.getAspects(stack).getAspects()[0].getColor()
                : 16777215;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int a = 0; a <= 18; ++a) {
            if (a != 5) {
                par3List.add(new ItemStack(this, 1, a));
            }
        }
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        super.onUpdate(stack, world, entity, par4, par5);
        if (!entity.worldObj.isRemote
                && (stack.getItemDamage() == 11 || stack.getItemDamage() == 12)
                && entity instanceof EntityLivingBase
                && !((EntityLivingBase) entity).isEntityUndead()
                && !((EntityLivingBase) entity).isPotionActive(Config.potionTaintPoisonID)
                && world.rand.nextInt(4321) <= stack.stackSize) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
            if (entity instanceof EntityPlayer) {
                String s = StatCollector.translateToLocal("tc.taint_item_poison")
                        .replace("%s", "§5§o" + stack.getDisplayName() + "§r");
                ((EntityPlayer) entity).addChatMessage(new ChatComponentTranslation(s, new Object[0]));
                InventoryUtils.consumeInventoryItem((EntityPlayer) entity, stack.getItem(), stack.getItemDamage());
            }
        } else if (!entity.worldObj.isRemote && stack.getItemDamage() == 15) {
            int r = world.rand.nextInt(20000);
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("blurb")) {
                stack.stackTagCompound.removeTag("blurb");
            }

            if (r < 20) {
                Aspect aspect = null;
                switch (world.rand.nextInt(6)) {
                    case 0:
                        aspect = Aspect.AIR;
                        break;
                    case 1:
                        aspect = Aspect.EARTH;
                        break;
                    case 2:
                        aspect = Aspect.FIRE;
                        break;
                    case 3:
                        aspect = Aspect.WATER;
                        break;
                    case 4:
                        aspect = Aspect.ORDER;
                        break;
                    case 5:
                        aspect = Aspect.ENTROPY;
                }

                if (aspect != null) {
                    EntityAspectOrb orb = new EntityAspectOrb(world, entity.posX, entity.posY, entity.posZ, aspect, 1);
                    world.spawnEntityInWorld(orb);
                }
            } else if (r == 42
                    && entity instanceof EntityPlayer
                    && !ResearchManager.isResearchComplete(
                            ((EntityPlayer) entity).getCommandSenderName(), "FOCUSPRIMAL")
                    && !ResearchManager.isResearchComplete(
                            ((EntityPlayer) entity).getCommandSenderName(), "@FOCUSPRIMAL")) {
                ((EntityPlayer) entity)
                        .addChatMessage(new ChatComponentTranslation(
                                "§5§o" + StatCollector.translateToLocal("tc.primalcharm.trigger"), new Object[0]));
                PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("@FOCUSPRIMAL"), (EntityPlayerMP) entity);
                Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer) entity, "@FOCUSPRIMAL");
            }
        }
    }

    public boolean onItemUse(
            ItemStack itemstack,
            EntityPlayer player,
            World world,
            int x,
            int y,
            int z,
            int par7,
            float par8,
            float par9,
            float par10) {
        if (itemstack.getItemDamage() != 1) {
            return super.onItemUse(itemstack, player, world, x, y, z, par7, par8, par9, par10);
        } else {
            Block var11 = world.getBlock(x, y, z);
            if (var11 == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
                par7 = 1;
            } else if (var11 != Blocks.vine
                    && var11 != Blocks.tallgrass
                    && var11 != Blocks.deadbush
                    && !var11.isReplaceable(world, x, y, z)) {
                if (par7 == 0) {
                    --y;
                }

                if (par7 == 1) {
                    ++y;
                }

                if (par7 == 2) {
                    --z;
                }

                if (par7 == 3) {
                    ++z;
                }

                if (par7 == 4) {
                    --x;
                }

                if (par7 == 5) {
                    ++x;
                }
            }

            if (itemstack.stackSize == 0) {
                return false;
            } else if (!player.canPlayerEdit(x, y, z, par7, itemstack)) {
                return false;
            } else if (world.canPlaceEntityOnSide(ConfigBlocks.blockAiry, x, y, z, false, par7, player, itemstack)) {
                if (this.placeBlockAt(
                        itemstack,
                        player,
                        world,
                        x,
                        y,
                        z,
                        par7,
                        par8,
                        par9,
                        par10,
                        ConfigBlocks.blockAiry,
                        itemstack.getItemDamage())) {
                    world.playSoundEffect(
                            (double) ((float) x + 0.5F),
                            (double) ((float) y + 0.5F),
                            (double) ((float) z + 0.5F),
                            ConfigBlocks.blockAiry.stepSound.getStepResourcePath(),
                            (ConfigBlocks.blockAiry.stepSound.getVolume() + 1.0F) / 2.0F,
                            ConfigBlocks.blockAiry.stepSound.getPitch() * 0.8F);
                    --itemstack.stackSize;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public boolean placeBlockAt(
            ItemStack stack,
            EntityPlayer player,
            World world,
            int x,
            int y,
            int z,
            int side,
            float hitX,
            float hitY,
            float hitZ,
            Block bid,
            int metadata) {
        if (!world.setBlock(x, y, z, bid, metadata, 3)) {
            return false;
        } else {
            if (world.getBlock(x, y, z) == bid) {
                bid.onBlockPlacedBy(world, x, y, z, player, stack);
                bid.onPostBlockPlaced(world, x, y, z, metadata);
            }

            return true;
        }
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if (itemstack.getItemDamage() == 0) {
            if (!player.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }

            world.playSoundAtEntity(player, "random.bow", 0.3F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityAlumentum(world, player));
            }
        } else if (itemstack.getItemDamage() == 9) {
            if (!player.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }

            if (!world.isRemote) {
                for (Aspect a : Aspect.getPrimalAspects()) {
                    short q = (short) (world.rand.nextInt(2) + 1);
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), a, q);
                    ResearchManager.scheduleSave(player);
                    PacketHandler.INSTANCE.sendTo(
                            new PacketAspectPool(
                                    a.getTag(),
                                    Short.valueOf(q),
                                    Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(
                                            player.getCommandSenderName(), a))),
                            (EntityPlayerMP) player);
                }
            }
        }

        return itemstack;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList aspects = this.getAspects(stack);
        if (aspects != null && aspects.size() > 0) {
            for (Aspect tag : aspects.getAspectsSorted()) {
                if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.getCommandSenderName(), tag)) {
                    list.add(tag.getName());
                } else {
                    list.add(StatCollector.translateToLocal("tc.aspect.unknown"));
                }
            }
        }

        if (stack.getItemDamage() == 15) {
            Random rand = new Random((long) (stack.hashCode() + player.ticksExisted / 120));
            int r = rand.nextInt(200);
            if (r < 25) {
                list.add("§6" + StatCollector.translateToLocal("tc.primalcharm." + rand.nextInt(5)));
            }
        }

        super.addInformation(stack, player, list, par4);
    }

    public AspectList getAspects(ItemStack itemstack) {
        if (itemstack.hasTagCompound()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(itemstack.getTagCompound());
            return aspects.size() > 0 ? aspects : null;
        } else {
            return null;
        }
    }

    public void setAspects(ItemStack itemstack, AspectList aspects) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        aspects.writeToNBT(itemstack.getTagCompound());
    }

    public int getItemStackLimit(ItemStack stack) {
        return stack.getItemDamage() == 15 ? 1 : super.getItemStackLimit(stack);
    }
}
