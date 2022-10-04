package com.Hileb.moremomostories.item.paper;

import com.Hileb.moremomostories.Advancements.Advancementkeys;
import com.Hileb.moremomostories.blocks.ModBlocks;
import com.Hileb.moremomostories.entity.EntityDeathMM;
import com.Hileb.moremomostories.item.ItemInformationAdder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ItemScene1 extends ItemSceneBase {
    public ItemScene1(){
        super("item_scene_1", Advancementkeys.AD_SCENE1);
        this.setMaxStackSize(1);
    }

    @Override
    public void doScene(PlayerInteractEvent.LeftClickBlock event) {
        //isRemote==false
        BlockPos pos=event.getPos();
        World world=event.getWorld();
        int x=pos.getX()-8;
        int y=pos.getY()+1;
        int z=pos.getZ()-8;
        for(int i=x;i<=x+16;i++){
            for(int j=z;j<=z+16;j++){
                world.setBlockState(new BlockPos(i,y,j), ModBlocks.BLOCK_BLACK_STONE_BRICK.getDefaultState(),3);
            }
        }
        if (!world.isRemote){
            EntityDeathMM entity=new EntityDeathMM(world);
            entity.setPosition(pos.getX(),y+1,pos.getZ());
            event.getWorld().spawnEntity(entity);
            event.getItemStack().setCount(0);
        }
    }
    @Override
    public ItemInformationAdder sceneDesc(){
        return new ItemInformationAdder("gui.hileb.paper1.desc.12.tip");
    }
}
