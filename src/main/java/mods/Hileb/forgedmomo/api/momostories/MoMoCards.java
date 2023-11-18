package mods.Hileb.forgedmomo.api.momostories;

import com.gq2529.momostories.MoMoFramework;
import com.gq2529.momostories.item.ModItems;
import mods.Hileb.forgedmomo.announces.ModPlugin;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.LoaderState;

import java.util.*;

@ModPlugin(state = LoaderState.POSTINITIALIZATION,modid = MoMoFramework.MODID,method = "cardInit")
public class MoMoCards {
    private static HashMap<CardType,ArrayList<Item>> CARDS=new HashMap<>();

    public static boolean registerCard(Item item,CardType type){
        if (!CARDS.keySet().contains(type))CARDS.put(type,new ArrayList<>());
        ArrayList<Item> list=CARDS.get(type);
        if (!list.contains(item))list.add(item);
        return list.contains(item);
    }
    public static boolean isThisKindOfCard(Item item,CardType type){
        if (CARDS.keySet().contains(type)){
            ArrayList<Item> list=CARDS.get(type);
            return list.contains(item);
        }else return false;
    }
    public static boolean isCard(Item item){
        for(CardType type:CARDS.keySet()){
            ArrayList<Item> list=CARDS.get(type);
            if (list.contains(item))return true;
        }
        return false;
    }
    public static Item getCard(int index){
        if (index<getCount()) {
            int pass=0;
            for(CardType type:CARDS.keySet()){
                ArrayList<Item> list=CARDS.get(type);
                if (index > pass+list.size()){
                    pass+=list.size();
                }else {
                    pass=index-pass;
                    return list.get(pass);
                }
            }
        }
        return null;
    }
    public static int getCount(){
        int i=0;
        for(CardType type:CARDS.keySet()){
            i+=CARDS.get(type).size();
        }
        return i;
    }
    public static void cardInit(){
        registerCard(ModItems.FRESHMAN,CardType.NATURE);
        registerCard(ModItems.REED,CardType.NATURE);
        registerCard(ModItems.WISEREED,CardType.NATURE);
        registerCard(ModItems.DAYTIME,CardType.NATURE);
        registerCard(ModItems.NIGHT,CardType.NATURE);
        registerCard(ModItems.THE_FRUIT_OF_THE_FLOWING_YEARS_CARD,CardType.NATURE);
        registerCard(ModItems.SCAVENGERS,CardType.NATURE);
        registerCard(ModItems.FINE,CardType.NATURE);
        registerCard(ModItems.DARK_CLOUDS,CardType.NATURE);
        registerCard(ModItems.FOUR_WAY_TRIP,CardType.NATURE);
        registerCard(ModItems.THE_LAMP_OF_TIAMAT,CardType.NATURE);

        registerCard(ModItems.THE_BOOK_OF_FORGERY,CardType.MYSTERIOUS);
        registerCard(ModItems.ABYSS_CLOISTER,CardType.MYSTERIOUS);
        registerCard(ModItems.PLAGUE_DOCTOR,CardType.MYSTERIOUS);
        registerCard(ModItems.FRAUDULENT_BOTTLES,CardType.MYSTERIOUS);
        registerCard(ModItems.TWIST,CardType.MYSTERIOUS);
        registerCard(ModItems.SPLIT,CardType.MYSTERIOUS);
        registerCard(ModItems.ETERNA_KINGSHIP,CardType.MYSTERIOUS);

        registerCard(ModItems.LUNA_CHURCH,CardType.INTERFERENCE);
        registerCard(ModItems.CHURCH_OF_THE_SUN_GOD,CardType.INTERFERENCE);
        registerCard(ModItems.THE_SUPREME_MAGI_DEEPLAKE,CardType.INTERFERENCE);
        registerCard(ModItems.FORGOTTEN_ANCIENT_MIRRORS,CardType.INTERFERENCE);
        registerCard(ModItems.CONSCRIPTION_ORDER,CardType.INTERFERENCE);
        registerCard(ModItems.GARLANFAA_REDEMTION,CardType.INTERFERENCE);
        registerCard(ModItems.FORT_SLIM,CardType.INTERFERENCE);
        registerCard(ModItems.LEYDEN_JAR,CardType.INTERFERENCE);
        registerCard(ModItems.BLOOD_COOLORED_CALIDAN,CardType.INTERFERENCE);
        registerCard(ModItems.DEVILS_BLOOD,CardType.INTERFERENCE);
        registerCard(ModItems.LUCY_THE_AXE_CARD,CardType.INTERFERENCE);
        registerCard(ModItems.THE_ANGERL_PROJECT,CardType.INTERFERENCE);
        registerCard(ModItems.OHAM_HEAVY_CAVALRY_REGIMENT,CardType.INTERFERENCE);
        registerCard(ModItems.ADJUDICATOR_BALANCE,CardType.INTERFERENCE);
        registerCard(ModItems.ESTES,CardType.INTERFERENCE);
        registerCard(ModItems.SIRIN,CardType.INTERFERENCE);
        registerCard(ModItems.MISER,CardType.INTERFERENCE);
        registerCard(ModItems.CENTRAL_ACADEMY_OF_SCIENCES,CardType.INTERFERENCE);
        registerCard(ModItems.NORD_BLACKSMITH_WORKSHOP,CardType.INTERFERENCE);

        registerCard(ModItems.THE_BOOK_OF_MANIFESTATION,CardType.ITEMS);
    }

    //enum
    public static class CardType{
        public static final HashMap<String,CardType> TYPES=new HashMap<>();
        public static final CardType INTERFERENCE=CardType.of("interference");
        public static final CardType MYSTERIOUS=CardType.of("mysterious");
        public static final CardType NATURE=CardType.of("nature");
        public static final CardType ITEMS=CardType.of("items");
        public static final CardType HILEB=CardType.of("hileb");
        private CardType(String name){
            TYPES.put(name,this);
        }
        public static CardType of(String name){
            return new CardType(name);
        }
        public static Collection<CardType> values(){
            return TYPES.values();
        }

    }
    
}
