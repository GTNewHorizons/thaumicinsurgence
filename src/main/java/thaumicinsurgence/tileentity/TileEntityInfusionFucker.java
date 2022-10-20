package thaumicinsurgence.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumicinsurgence.main.utils.VersionInfo;

public class TileEntityInfusionFucker extends TileEntity implements IAspectContainer, IEssentiaTransport {

    public static final String tileEntityName = VersionInfo.ModID + ".infusionIntercepter";

    // added directly from the crucible of souls
    private AspectList myAspects = new AspectList();
    private AspectList matrixAspects = new AspectList();
    private Aspect[] listOfAspects;
    private int desiredAmount = 0;
    private int grabbedAmount = 0;
    private int listSlot = 0;
    private Aspect currentSuction;
    private TileInfusionMatrix matrix = null;
    private IEssentiaTransport essentiaEntity = null;

    /* public static final Aspect AIR = new Aspect("aer", 16777086, "e", 1);
    public static final Aspect EARTH = new Aspect("terra", 5685248, "2", 1); */

    // the commented lines above show how the essentias are labeled, you use the actual name of the essentia to grab it.
    // public Aspect AIR = aspect.getAspect("aer");

    // added directly from the crucible of souls
    @Override
    public void setAspects(AspectList aspects) {
        this.myAspects = aspects;
    }

    public TileEntityInfusionFucker() {
        this.currentSuction = null;
    }

    @Override
    public void updateEntity() {

        // these two if statements handle setting the TE variables the intercepter deals with.
        if (matrix == null) setMatrix();
        if (essentiaEntity == null) setInput();

        /* this method handles literally everything this does, I moved it to it's own method so that I could
         * make it not do anything if the matrix didn't exist to save idle performance */
        if (matrix != null) {
            matrixFuckery();
        }
    }

    public void matrixFuckery() {

        matrixAspects = matrix.getAspects();
        if (matrixAspects.size() != 0) {
            listOfAspects = matrixAspects.getAspects();
            // this set handles the suction methods and variable resetting related to the lists and suction
            if (listSlot < listOfAspects.length) {
                setIntercepterSuction();
            } else {
                resetLists();
            }

            // this method handles removing essentia from the essentia source.
            if (this.currentSuction != null) {
                removeFromSource();
            }
        } else if (myAspects.size() != 0) {
            // reset internal aspects if there are no matrix aspects to process
            myAspects = new AspectList();
        }
    }

    /** If an input exists, this method will see if it contains the IEssentiaTransport interface, and if it does, it will
     *  set the current input to the whatever is trying to input */
    public void setInput() {
        try {
            TileEntity inputCandidate = worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
            if (inputCandidate instanceof IEssentiaTransport) {
                essentiaEntity = (IEssentiaTransport) inputCandidate;
            }
        } catch (Exception ignored) {
        }
    }

    /** this is obvious, but it grabs the infusion matrix that the intercepter is going to screw with. */
    public void setMatrix() {
        try {
            TileEntity matrixCandidate = worldObj.getTileEntity(this.xCoord, this.yCoord + 3, this.zCoord);
            if (matrixCandidate instanceof TileInfusionMatrix) {
                matrix = (TileInfusionMatrix) matrixCandidate;
            }
        } catch (Exception ignored) {
        }
    }

    /** if the matrix has an AspectList active, then it sets the Aspect array so that suction can be set, otherwise
     *  it resets the intercepter's AspectList into a clean list. */
    private void setTodoList() {
        if (matrixAspects.size() == 0) {
            myAspects = new AspectList();
        } else {
            listOfAspects = matrixAspects.getAspects();
        }
    }

    /** This methods Takes into consideration if the suction is null, and sets it if it can, otherwise, if the intercepter AspectList
     * is equal to the grabbedAspects AspectList of the matrix, it tiers up the listSlot iteration int and removes the
     * essentia from the infusion matrix while setting the suction to null so the next tick can set the suction to the new aspect. */
    private void setIntercepterSuction() {
        int desiredAmount = matrixAspects.getAmount(listOfAspects[listSlot]);
        if (currentSuction == null) {
            this.currentSuction = listOfAspects[listSlot];
            this.desiredAmount = desiredAmount;
        } else if (myAspects.getAmount(listOfAspects[listSlot]) >= desiredAmount) {
            matrixAspects.remove(listOfAspects[listSlot], myAspects.getAmount(listOfAspects[listSlot]));
            listSlot += 1;
            this.currentSuction = null;
            this.desiredAmount = 0;
        }
    }

    /** this method just resets the lists, duh */
    private void resetLists() {
        myAspects = new AspectList();
        matrixAspects = new AspectList();
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

                    if (aspectAmount >= desiredAmount) {
                        grabbedAmount = desiredAmount;
                    } else {
                        grabbedAmount = aspectAmount;
                    }

                    if (tubeAspect.equals(currentSuction)) {
                        essentiaEntity.takeEssentia(tubeAspect, grabbedAmount, ForgeDirection.UP);
                        myAspects.add(tubeAspect, grabbedAmount);
                        this.desiredAmount -= grabbedAmount;
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
        if (am != 0) {
            this.myAspects.add(tag, am);
            am = 0;
        }
        return am;
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
        for (Aspect next : ot.aspects.keySet()) {
            if (this.myAspects.getAmount(next) < ot.getAmount(next)) hasIt = false;
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
