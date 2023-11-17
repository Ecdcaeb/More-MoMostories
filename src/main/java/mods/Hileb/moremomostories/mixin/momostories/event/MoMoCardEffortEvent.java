package mods.Hileb.moremomostories.mixin.momostories.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nullable;

public class MoMoCardEffortEvent extends PlayerEvent {
    public ItemStack cardStack;
    public EntityLivingBase target;
    public OnItemUseType onItemUseType=null;
    public String type=null;
    /**
     * type means 用一个string附加更多的额外信息，这些信息都会在ExtraMessage里获得**/
    public MoMoCardEffortEvent(EntityPlayer playerIn, ItemStack stackIn, @Nullable EntityLivingBase targetIn){
        super(playerIn);
        cardStack=stackIn;
        target=targetIn;
    }
    public MoMoCardEffortEvent setItemUseType(OnItemUseType onItemUseTypeIn){
        onItemUseType=onItemUseTypeIn;
        return this;
    }
    public MoMoCardEffortEvent setMessage(String messageIn){
        this.type=messageIn;
        return this;
    }
    @Override
    public boolean isCancelable() {
        return true;
    }
    public static class OnItemUseType{
        public EntityPlayer player;
        public World worldIn;
        public BlockPos pos;
        public EnumHand hand;
        public EnumFacing facing;
        public float hitX;
        public float hitY;
        public float hitZ;
        public OnItemUseType(EntityPlayer playerIn, World worldInIn, BlockPos posIn, EnumHand handIn, EnumFacing facingIn, float hitXIn, float hitYIn, float hitZIn){
            player=playerIn;
            worldIn=worldInIn;
            pos=posIn;
            hand=handIn;
            facing=facingIn;
            hitX=hitXIn;
            hitY=hitYIn;
            hitZ=hitZIn;
        }
    }
    public static class ExtraMessage{
        public static class TheAngelProject{
            public static String JUMP_MESSAGE="message_jump_1";
            public static String FIRST_MESSAGE="first_1";

            public static String THE_SUPREME_MAGI_DEEPLAKE="tshmfjineignfn";
            public static String ESTES="ESTES";
        }
        public static class Common{
            public static String UPDATE="update";
            public static String RIGHT_CLICK="right_click";
            public static String USE="use";
            public static String EVENT="event";
            public static String JUMP="message_jump_1";
        }
        public static class CompoundFertilizer{
            public static String CROPS="BlockCrops";
            public static String SAPLING="Sapling";
        }
    }
}
