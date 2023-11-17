package com.Hileb.moremomostories.common.world.blocks.tile;

import com.Hileb.forgedmomo.utils.registry.SimpleClassRegistry;
import com.Hileb.forgedmomo.utils.registry.SimpleClassRegistryObject;
import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.world.blocks.tile.bookshelf.TileEntityBookShelf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/17 17:29
 **/
public class ModTileEntities {
    public static final SimpleClassRegistry<TileEntity> REGISTRY = SimpleClassRegistry.of((object, name, args) -> GameRegistry.registerTileEntity(object,name));
    public static SimpleClassRegistryObject<TileEntityBookShelf> BOOK_SHELF=REGISTRY.addValue(
            new SimpleClassRegistryObject<>(
                    new ResourceLocation(MoreMoMoSrories.MODID, "tile_entity_book_shelf"),
                    () -> TileEntityBookShelf.class, simpleClassRegistryObject -> true));
}
