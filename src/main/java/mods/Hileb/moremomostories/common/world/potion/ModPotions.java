package mods.Hileb.moremomostories.common.world.potion;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.potion.myBuff.PotionBaKin;
import mods.Hileb.moremomostories.common.world.potion.myBuff.PotionDayTime;
import mods.Hileb.moremomostories.common.util.Reference;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModPotions {

    public static final List<Potion> INSTANCES = new ArrayList<Potion>();

    public static final Potion BAKIN = new PotionBaKin("potion_blind_bakin");
    public static final Potion DAY_BLIND= new PotionDayTime("potion_blind_day");

    public static final Potion BLOOD= new PotionDayTime("potion_blood");

//    public static final PotionZenHeart ZEN_HEART = new PotionZenHeart(false, 0xcccc00, "zen_heart", 1);

    @Nullable
    private static Potion getRegisteredMobEffect(String id)
    {
        Potion potion = Potion.REGISTRY.getObject(new ResourceLocation(id));

        if (potion == null)
        {
            throw new IllegalStateException("Invalid MobEffect requested: " + id);
        }
        else
        {
            return potion;
        }
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> evt)
    {
        //VIRUS_ONE.tuples.add(new EffectTuple(0.2f, MobEffects.NAUSEA, 100));

        evt.getRegistry().registerAll(INSTANCES.toArray(new Potion[0]));
        MoreMoMoSrories.LOGGER.info("registered {} potion", INSTANCES.size());
    }
}
