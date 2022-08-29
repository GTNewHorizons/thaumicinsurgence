package thaumicinsurgence.tileentity;

import java.util.Iterator;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumicinsurgence.main.utils.VersionInfo;

public class TileEntityInfusionIntercepter extends TileEntity implements IAspectContainer, IEssentiaTransport {

    public static final String tileEntityName = VersionInfo.ModID + ".infusionIntercepter";

    // added directly from the crucible of souls
    public AspectList myAspects = new AspectList();
    public AspectList grabbedAspects = new AspectList();
    Aspect[] listOfAspects;
    public int desired_amount = 0;
    public int grabbed_amount = 0;
    public int listSlot = 0;
    public int incre = 0;
    public Aspect currentSuction;
    public TileInfusionMatrix matrix = null;
    public IEssentiaTransport essentiaEntity = null;

    /* public static final Aspect AIR = new Aspect("aer", 16777086, "e", 1);
    public static final Aspect EARTH = new Aspect("terra", 5685248, "2", 1); */

    // the commented lines above show how the essentias are labeled, you use the actual name of the essentia to grab it.
    // public Aspect AIR = aspect.getAspect("aer");

    // added directly from the crucible of souls
    @Override
    public void setAspects(AspectList aspects) {
        this.myAspects = aspects;
    }

    public TileEntityInfusionIntercepter() {
        this.currentSuction = null;
    }

    @Override
    public void updateEntity() {

        // these two if statements handle setting the TE variables the interceptor deals with.
        if (matrix == null) setMatrix();
        if (essentiaEntity == null) setInput();

        /* this method handles literally everything this does, I moved it to it's own method so that I could
         * make it not do anything if the matrix didn't exist to save idle performance */
        if (matrix != null) {
            matrixFuckery();
        }
    }

    public void matrixFuckery() {

        // this statement grabs the matrix's aspects every 5 seconds and the line after increments the incremented
        // variable, assuming the matrix exists anyway.
        if (matrix != null) {
            if (incre == 100) {
                try {
                    grabbedAspects = matrix.getAspects();
                } catch (Exception ignored) {
                }
                incre = 0;
            }
            incre++;
        }

        /* this method handles the Aspect array that I use to handle suction, if the matrix's AspectList has anything in it,
         *  the Aspect array is set to be a mirrored copy of it, otherwise,
         *  it sets the interceptor's internal AspectList to a new blank AspectList so that it can be reused without breaking. */
        setTodoList();

        // just a bit of logic to prevent it from using unnecessary resources.
        if (!grabbedAspects.equals(new AspectList())) {
            //   if (listOfAspects != null) {

            // this set handles the suction methods and variable resetting related to the lists and suction
            try {
                if (listSlot < listOfAspects.length) {
                    setInterceptorSuction();
                } else {
                    resetLists();
                }
            } catch (Exception ignored) {
            }

            // this method handles removing essentia from the essentia source.
            if (this.currentSuction != null) {
                removeFromSource();
            }
        }
    }

    /** If an input exists, this method will see if it contains the IEssentiaTransport interface, and if it does, it will
     *  set the current input to the whatever is trying to input */
    public void setInput() {
        try {
            if (worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof IEssentiaTransport) {
                essentiaEntity = (IEssentiaTransport) worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
            }
        } catch (Exception ignored) {
        }
    }

    /** this is obvious, but it grabs the infusion matrix that the interceptor is going to screw with. */
    public void setMatrix() {
        try {
            if (worldObj.getTileEntity(this.xCoord, this.yCoord + 3, this.zCoord) instanceof TileInfusionMatrix) {
                matrix = (TileInfusionMatrix) worldObj.getTileEntity(this.xCoord, this.yCoord + 3, this.zCoord);
            }
        } catch (Exception ignored) {
        }
    }

    /** if the matrix has an AspectList active, then it sets the Aspect array so that suction can be set, otherwise
     *  it resets the interceptor's AspectList into a clean list. */
    private void setTodoList() {
        try {
            if (!grabbedAspects.equals(new AspectList())) {
                listOfAspects = grabbedAspects.getAspects();
            } else if (grabbedAspects.equals(new AspectList())) {
                myAspects = new AspectList();
            }
        } catch (Exception ignored) {
        }
    }

    /** This methods Takes into consideration if the suction is null, and sets it if it can, otherwise, if the interceptor AspectList
     * is equal to the grabbedAspects AspectList of the matrix, it tiers up the listSlot iteration int and removes the
     * essentia from the infusion matrix while setting the suction to null so the next tick can set the suction to the new aspect. */
    private void setInterceptorSuction() {
        int set_desired_amount = grabbedAspects.getAmount(listOfAspects[listSlot]);
        if (currentSuction == null) {
            this.currentSuction = listOfAspects[listSlot];
            this.desired_amount = set_desired_amount;
        } else if (myAspects.getAmount(listOfAspects[listSlot]) >= set_desired_amount) {
            grabbedAspects.remove(listOfAspects[listSlot], myAspects.getAmount(listOfAspects[listSlot]));
            listSlot += 1;
            this.currentSuction = null;
            this.desired_amount = 0;
        }
    }

    /** this method just resets the lists, duh */
    private void resetLists() {
        myAspects = new AspectList();
        grabbedAspects = new AspectList();
        listOfAspects = null;
        this.currentSuction = null;
        listSlot = 0;
    }

    /** this method handles removing essentia from the essentia source */
    private void removeFromSource() {
        try {
            if (essentiaEntity != null) {
                if (essentiaEntity.getEssentiaAmount(ForgeDirection.UP) >= 1) {
                    Aspect tubeAspect = essentiaEntity.getEssentiaType(ForgeDirection.UP);
                    int aspectAmount = essentiaEntity.getEssentiaAmount(ForgeDirection.UP);

                    if (aspectAmount >= desired_amount) {
                        grabbed_amount = desired_amount;
                    } else {
                        grabbed_amount = aspectAmount;
                    }

                    if (tubeAspect.equals(currentSuction)) {
                        essentiaEntity.takeEssentia(tubeAspect, grabbed_amount, ForgeDirection.UP);
                        myAspects.add(tubeAspect, grabbed_amount);
                        this.desired_amount -= grabbed_amount;
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* This point marks the end of my custom methods used for this TE itself.
     *  Everything after this is based off other author's code.
     *  So only judge my code based off my own shit methods, I'll fix the rest later. :kekw:  */
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // added directly from the crucible of souls
    @Override
    public AspectList getAspects() {
        return this.myAspects;
    }

    // added directly from the crucible of souls
    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    // modified for infusion fuckery
    @Override
    public int addToContainer(Aspect tag, int am) {
        if (am == 0) {
            return am;
        } else {
            this.myAspects.add(tag, am);
            am = 0;
            return am;
        }
    }

    // modified for infusion fuckery
    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    // modified for infusion fuckery
    @Override
    public boolean takeFromContainer(AspectList ot) {
        // TODO Auto-generated method stub
        return false;
    }

    // added directly from the crucible of souls
    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        // TODO Auto-generated method stub
        return (this.myAspects.getAmount(tag) > amount);
    }

    // added directly from the crucible of souls
    @Override
    public boolean doesContainerContain(AspectList ot) {
        boolean hasIt = true;
        ot.aspects.keySet().iterator();
        Iterator iterator = ot.aspects.keySet().iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (this.myAspects.getAmount((Aspect) next) < ot.getAmount((Aspect) next)) hasIt = false;
        }

        return hasIt;
    }

    // added directly from the crucible of souls
    @Override
    public int containerContains(Aspect tag) {
        return this.myAspects.getAmount(tag);
    }

    // added directly from the crucible of souls
    @Override
    public boolean isConnectable(ForgeDirection face) {
        return (face != ForgeDirection.UP);
    }

    // added directly from the crucible of souls
    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return (face != ForgeDirection.UP);
    }

    // added directly from the crucible of souls
    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    // leave alone
    @Override
    public void setSuction(Aspect aspect, int amount) {
        // TODO Auto-generated method stub
        this.currentSuction = aspect;
    }

    // modified from the jar implementation
    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection arg2) {
        return this.canOutputTo(arg2) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    // added directly from the crucible of souls
    @Override
    public int getMinimumSuction() {
        // TODO Auto-generated method stub
        return 0;
    }

    // added directly from the crucible of souls
    @Override
    public boolean renderExtendedTube() {
        // TODO Auto-generated method stub
        return false;
        // NEW AFTER THIS LINE
    }

    // modified from the thaumatorium implementation
    @Override
    public Aspect getSuctionType(ForgeDirection face) {
        return this.currentSuction;
    }

    // added directly from the crucible of souls
    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return this.currentSuction != null ? 128 : 0;
    }

    // added directly from the crucible of souls
    @Override
    public Aspect getEssentiaType(ForgeDirection face) {
        return this.myAspects.visSize() > 0 && face == ForgeDirection.UNKNOWN
                ? this.myAspects.getAspects()[0]
                : null;
    }

    // modified
    @Override
    public int getEssentiaAmount(ForgeDirection face) {
        return this.myAspects.visSize();
    }

    // taken from the thaumatorium
    @Override
    public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
        if (arg0 == currentSuction) {
            return this.canInputFrom(arg2) ? arg1 - this.addToContainer(arg0, arg1) : 0;
        }
        return 0;
    }

    // added directly from the crucible of souls
    private float tagAmount() {
        int amount = 0;
        for (Aspect next : this.myAspects.aspects.keySet()) {
            amount += this.myAspects.getAmount(next);
        }
        return amount;
    }
}
