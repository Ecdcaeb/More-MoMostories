package mods.Hileb.moremomostories.client.resource;

import mods.Hileb.forgedmomo.api.client.resource.ResourceGenI18nChannel;
import mods.Hileb.forgedmomo.api.client.resource.ResourceGenI18nChannel.Language;
import mods.Hileb.forgedmomo.api.client.resource.ResourceGenerateEvent;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.init.ModCreativeTab;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/12/2 13:21
 **/
@Mod.EventBusSubscriber(modid= MoreMoMoSrories.MODID)
public class RCGI18n {
    @SubscribeEvent
    public static void onGeneral(ResourceGenerateEvent event) {
        ResourceGenI18nChannel i18nChannel = new ResourceGenI18nChannel();
        //define lang
        Language zh_cn = i18nChannel.of("zh_cn");
        Language en_us = i18nChannel.of("en_us");

        //start text
        i18nChannel.commit(MoreMoMoSrories.MODID);
        i18nChannel.commit("Generate Data:" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        i18nChannel.commit("");

        {
            zh_cn.commit("致译者");
            zh_cn.commit("翻译请修改 mods/Hileb/moremomostories/client/resource/RCGI18n.java");
            zh_cn.commit("所有的语言文件都是构建模组时自动生成的，因此直接在文件上的修改无效");
        }
        //tab
        {
            {
                i18nChannel.pop(ModCreativeTab.IDL_MISC.getTranslatedTabLabel());
                zh_cn.push("更多momo学");
                en_us.push("More MoMostories");
            }
        }
        //item
        {
            {
                i18nChannel.pop(ModItems.ITEM_DO_FOREVER.getUnlocalizedName());
                zh_cn.push("永动机");
                en_us.push("Do It Forever");
                i18nChannel.pop("item.item_do_forever.desc1");
                zh_cn.push("宇宙三大最高科技之一\\n但具显之书似乎不想将其具显");
                en_us.push("One of the three highest technologies in the universe  \\nHowever, it seems that the book of showing doesn't want to show it");
            }
            {
                i18nChannel.pop(ModItems.ITEM_SILVER_CHEST.getUnlocalizedName());
                zh_cn.push("银鳞胸甲");
                en_us.push("Shining Silver Breastplate");
            }
            {
                i18nChannel.pop(ModItems.ITEM_XE.getUnlocalizedName());
                zh_cn.push("未知氙");
                en_us.push("Unknown-X Dust");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.red.name");
                zh_cn.push("§r红色氙石粉末");
                en_us.push("RX Dust");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.blue.name");
                zh_cn.push("§b蓝色氙石粉末");
                en_us.push("BX Dust");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.black.name");
                zh_cn.push("黑色氙石粉末");
                en_us.push("BLX Dust");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.other.name");
                zh_cn.push("氙石粉末");
                en_us.push("X Dust");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.empty.name");
                zh_cn.push("空氙石粉末瓶");
                en_us.push("Empty-X Dust Bottle");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.golden.name");
                zh_cn.push("氙金粉末");
                en_us.push("GX Dust");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.unknown.name");
                zh_cn.push("未知氙石粉末");
                en_us.push("Unknown-X Dust");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.red.name");
                zh_cn.push("红色氙石");
                en_us.push("RX");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.blue.name");
                zh_cn.push("蓝色氙石");
                en_us.push("BX");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.black.name");
                zh_cn.push("黑色氙石");
                en_us.push("BLX");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.other.name");
                zh_cn.push("氙石");
                en_us.push("Unknown-X");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.unknown.name");
                zh_cn.push("未知氙石");
                en_us.push("Unknown-X Stone");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.golden.name");
                zh_cn.push("氙金");
                en_us.push("GX");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.level.name");
                zh_cn.push("等级%d");
                en_us.push("Level:%d");
                i18nChannel.pop("com.hileb.moremomostories.item.xe.stone.color.name");
                zh_cn.push("成色%d");
                en_us.push("Color:%d");
            }
            {
                i18nChannel.pop(ModItems.ITEM_11_A.getUnlocalizedName());
                zh_cn.push("核心制造技术");
                en_us.push("Core Manufacturing Technology");
            }
            {
                i18nChannel.pop(ModItems.ITEM_CARD_CONTAINER.getUnlocalizedName());
                zh_cn.push("已知的卡牌册");
                en_us.push("Known Card Book");
            }
            {
                i18nChannel.pop(ModItems.ITEM_CARD_ZFP.getUnlocalizedName());
                zh_cn.push("「煮饭婆」");
                en_us.push("\"Entity GQ2529\"");
                i18nChannel.pop("desc.cardzfp.desc");
                zh_cn.push("煮饭婆举高高！~");
                en_us.push("煮饭婆举高高！~");
                i18nChannel.pop("desc.cardzfp2.desc");
                zh_cn.push("煮饭婆可爱捏！~");
                en_us.push("煮饭婆可爱捏！~");
            }
            {
                i18nChannel.pop(ModItems.ITEM_CARD_GET_FROM_NULL.getUnlocalizedName());
                zh_cn.push("「无中生有」");
                en_us.push("\"Nothing makes anything\"");
                i18nChannel.pop("item.item_card_get_from_null.desc1");
                zh_cn.push("出牌阶段，对自己使用。摸一张牌。\\n“天下万物生于有，有生于无”\\n<老子>\\n获得一张momostroies牌\\n没有加载momostories时返回原料.");
                en_us.push("Play cards for yourself. Touch a card. \\N \"Everything in the world is born of something, and something is born of nothing\" n<Lao Tzu> \\n Get a momostroies card. \\n Return the raw materials when the momostories are not loaded");
                i18nChannel.pop("item.item_card_get_from_null.desc2");
                zh_cn.push("出牌阶段，对自己使用。摸一张牌。\\n“天下万物生于有，有生于无”\\n<老子>\\n按住 §eLShift §f显示更多");
                en_us.push("Play cards for yourself. Touch a card. \\N \"Everything in the world is born of something, something is born of nothing\"  n<Lao Tzu> \\n Press and hold § eLShift § f to display more");
            }
            {
                i18nChannel.pop(ModItems.ITEM_YTXSY_SOUND.getUnlocalizedName());
                zh_cn.push("唱片");
                en_us.push("Record");
            }
            {
                i18nChannel.pop(ModItems.ITEM_SWOOD_SAKURA_END.getUnlocalizedName());
                zh_cn.push("终焉彩虹");
                en_us.push("End Rainbow Sword");
                i18nChannel.pop("momo.energy.rainbow.damage.name");
                zh_cn.push("耐久");
                en_us.push("Max Damage");
                i18nChannel.pop("momo.energy.rainbow.en.name");
                zh_cn.push("能量");
                en_us.push("Energy");
                i18nChannel.pop("momo.energy.rainbow.en.end");
                zh_cn.push("RE(RainbowEnergy)");
                en_us.push("RE(RainbowEnergy)");
                i18nChannel.pop("com.hileb.achievement.rainbow.title");
                zh_cn.push("最终之剑");
                en_us.push("Final Sword");
                i18nChannel.pop("com.hileb.achievement.rainbow.desc");
                en_us.push("get End Rainbow Sword");
                zh_cn.push("获得终焉彩虹。");
                i18nChannel.pop("skill.left.desc");
                en_us.push("Left Click:");
                zh_cn.push("以下技能按下左键触发");
                i18nChannel.pop("skill.right.desc");
                en_us.push("Right Click(500RE)");
                zh_cn.push("以下技能按下右键随机触发(消耗500RE)");
                i18nChannel.pop("skill.pass.desc");
                zh_cn.push("Skill:");
                en_us.push("以下技能被动触发");
                i18nChannel.pop("skill.rain.desc");
                zh_cn.push("生成大量攻击性雨滴");
                en_us.push("Spawn EntityRain");
                i18nChannel.pop("skill.bakin.desc");
                zh_cn.push("驱逐淫气，恢复视野。");
                en_us.push("Clear 淫气 Effect");
                i18nChannel.pop("skill.fire.desc");
                en_us.push("Spawn EntityFire");
                zh_cn.push("生成攻击性火焰");
                i18nChannel.pop("skill.eu.desc");
                zh_cn.push("攻击时，给背包内物品充EU，1Damage=1EU");
                en_us.push("Get EU from damage.1Damage=1EU");
            }
            {
                i18nChannel.pop(ModItems.ITEM_SWOOD_MEMORY_END.getUnlocalizedName());
                en_us.push("Chopping Memory Sword");
                zh_cn.push("斩记剑");
                i18nChannel.pop("com.hileb.momo.lang.skill.endmemory.name");
                zh_cn.push("§e斩忆");
                en_us.push("§eChop one's memory");
                i18nChannel.pop("com.hileb.momo.lang.skill.endmemory.desc");
                en_us.push("Causes the victim to lose their target within 30 seconds.");
                zh_cn.push("使被击中者30刻内失去目标。");
                i18nChannel.pop("com.hileb.achievement.memory.title");
                zh_cn.push("记忆斩断者");
                en_us.push("Memory Chopper");
                i18nChannel.pop("com.hileb.achievement.memory.desc");
                en_us.push("get Chopping Memory Sword");
                zh_cn.push("获得斩忆剑");
            }
            {
                i18nChannel.pop(ModItems.ITEM_BOOK_DUST.getUnlocalizedName());
                en_us.push("Fragments of a Book");
                zh_cn.push("书的碎片");
            }
            {
                i18nChannel.pop(ModItems.ITEM_BOOK_BUDDHA_GOD_PALM.getUnlocalizedName());
                zh_cn.push("《如来神掌》");
                en_us.push("《如来神掌》");
                i18nChannel.pop("book.name.com.hileb.momo.llf.name");
                zh_cn.push("你记不记得有一招从天而降的掌法");
                en_us.push("Do you remember the palm technique of falling from the sky");
            }
            {
                i18nChannel.pop(ModItems.ITEM_ZFP_HEADSET.getUnlocalizedName());
                zh_cn.push("煮饭婆耳机");
                en_us.push("GQ2529 Headset");
            }
            {
                i18nChannel.pop(ModItems.ITEM_VAN_CHEST.getUnlocalizedName());
                zh_cn.push("束缚腰带");
                en_us.push("Binding belt");
            }
            {
                i18nChannel.pop("book.name.0.name");i18nChannel.push("《迈恩可拉福特使用指南》");
                i18nChannel.pop("book.author.0.name");i18nChannel.push("网难");
                i18nChannel.pop("book.name.1.name");i18nChannel.push("《红石入门》");
                i18nChannel.pop("book.author.1.name");i18nChannel.push("网难");
                i18nChannel.pop("book.name.2.name");i18nChannel.push("《霸道史蒂夫爱上我》");
                i18nChannel.pop("book.author.2.name");i18nChannel.push("匿名网友");
                i18nChannel.pop("book.name.3.name");i18nChannel.push("《逸一世误一世》");
                i18nChannel.pop("book.author.3.name");i18nChannel.push("田所浩二");
                i18nChannel.pop("book.name.4.name");i18nChannel.push("《别人的世界》");
                i18nChannel.pop("book.author.4.name");i18nChannel.push("别人");
                i18nChannel.pop("book.name.5.name");i18nChannel.push("《重生是失望》");
                i18nChannel.pop("book.author.5.name");i18nChannel.push("匿名网友");
                i18nChannel.pop("book.name.6.name");i18nChannel.push("《加速火把（加粗）真正的（斜体）使用方法》");
                i18nChannel.pop("book.author.6.name");i18nChannel.push("森林蝙蝠");
                i18nChannel.pop("book.name.7.name");i18nChannel.push("《§k§4Hello Minecraft§f》");
                i18nChannel.pop("book.author.7.name");i18nChannel.push("§kHerobrine");
                i18nChannel.pop("book.name.8.name");i18nChannel.push("《逸久逸九罢一龄》");
                i18nChannel.pop("book.author.8.name");i18nChannel.push("田所浩二");
                i18nChannel.pop("book.name.9.name");i18nChannel.push("《怎么下载我的世界》");
                i18nChannel.pop("book.author.9.name");i18nChannel.push("匿名网友");
                i18nChannel.pop("book.name.10.name");i18nChannel.push("《蓝楼梦》");
                i18nChannel.pop("book.author.10.name");i18nChannel.push("蔡靴轻");
                i18nChannel.pop("book.name.11.name");i18nChannel.push("《怎么装光影》");
                i18nChannel.pop("book.author.11.name");i18nChannel.push("OptiFine");
                i18nChannel.pop("book.name.12.name");i18nChannel.push("《怎么在Fabric上装光影》");
                i18nChannel.pop("book.author.12.name");i18nChannel.push("OptiFabric");
                i18nChannel.pop("book.name.13.name");i18nChannel.push("《为什么光影不能用》");
                i18nChannel.pop("book.author.13.name");i18nChannel.push("钠（Sodium）");
                i18nChannel.pop("book.name.14.name");i18nChannel.push("《OmOm事故会》");
                i18nChannel.pop("book.author.14.name");i18nChannel.push("Hileb");
                i18nChannel.pop("book.name.15.name");i18nChannel.push("《这是一本书》");
                i18nChannel.pop("book.author.15.name");i18nChannel.push("这是这本书的作者");
                i18nChannel.pop("book.name.16.name");i18nChannel.push("《怎么装模组》");
                i18nChannel.pop("book.author.16.name");i18nChannel.push("Forge，Fabric");
                i18nChannel.pop("book.name.17.name");i18nChannel.push("《红石大神》");
                i18nChannel.pop("book.author.17.name");i18nChannel.push("网难");
                i18nChannel.pop("book.name.18.name");i18nChannel.push("《北游记》");
                i18nChannel.pop("book.author.18.name");i18nChannel.push("武城摁");
                i18nChannel.pop("book.name.19.name");i18nChannel.push("《千度万科》");
                i18nChannel.pop("book.author.19.name");i18nChannel.push("千度（Du·Qian）");
                i18nChannel.pop("book.name.20.name");i18nChannel.push("《火浒传》");
                i18nChannel.pop("book.author.20.name");i18nChannel.push("势奈艳");
                i18nChannel.pop("book.name.21.name");i18nChannel.push("《死着》");
                i18nChannel.pop("book.author.21.name");i18nChannel.push("剩华");
                i18nChannel.pop("book.name.22.name");i18nChannel.push("《七体》");
                i18nChannel.pop("book.author.22.name");i18nChannel.push("六瓷醒");
                i18nChannel.pop("book.name.23.name");i18nChannel.push("《九国演狗》");
                i18nChannel.pop("book.author.23.name");i18nChannel.push("洛惯重");
                i18nChannel.pop("book.name.24.name");i18nChannel.push("《被风筝追的人》");
                i18nChannel.pop("book.author.24.name");i18nChannel.push("不卡列·胡谁尼（Bukalie Huseini）");
                i18nChannel.pop("book.name.25.name");i18nChannel.push("《十年孤独》");
                i18nChannel.pop("book.author.25.name");i18nChannel.push("减夫六尔·加冬压·马摁克锡（Jianfuliuer Jiadongya Maenkexi）");
                i18nChannel.pop("book.name.26.name");i18nChannel.push("《呵马莱普》");
                i18nChannel.pop("book.author.26.name");i18nChannel.push("窝世·伊戈萨比（Woshi Yigegesb）");
                i18nChannel.pop("book.name.27.name");i18nChannel.push("《这是一本书的续作》");
                i18nChannel.pop("book.author.27.name");i18nChannel.push("这是这本书的续作的作者");
                i18nChannel.pop("book.name.28.name");i18nChannel.push("（C96）煮饭婆ちゃんの小さな秘密");
                i18nChannel.pop("book.author.28.name");i18nChannel.push("Hileb");
            }
            {

                i18nChannel.pop("say.zfp.0.say");
                i18nChannel.push("<煮饭婆>传说，帝君在出征之时，曾言道...");
                i18nChannel.pop("say.zfp.1.say");
                i18nChannel.push("<煮饭婆>我感觉我需要换一个脑子");
                i18nChannel.pop("say.zfp.2.say");
                i18nChannel.push("<煮饭婆>好了，湖神赢了");
                i18nChannel.pop("say.zfp.3.say");
                i18nChannel.push("<煮饭婆>昨晚梦梦全是闪电");
                i18nChannel.pop("say.zfp.4.say");
                i18nChannel.push("<煮饭婆>指令可以做，而且更简单");
                i18nChannel.pop("say.zfp.5.say");
                i18nChannel.push("<煮饭婆>空间问题");
                i18nChannel.pop("say.zfp.6.say");
                i18nChannel.push("<煮饭婆>你这合成有多少？");
                i18nChannel.pop("say.zfp.7.say");
                i18nChannel.push("<煮饭婆>主要这个闪电画成很容易被人理解为电，但这种图片又是最常见的闪电");
                i18nChannel.pop("say.zfp.8.say");
                i18nChannel.push("<煮饭婆>我记得这是高版本的特性");
                i18nChannel.pop("say.zfp.9.say");
                i18nChannel.push("<煮饭婆>微型自动化模组代码不错，就是基类太多了");
                i18nChannel.pop("say.zfp.10.say");
                i18nChannel.push("<煮饭婆>你可以想一想");
                i18nChannel.pop("say.zfp.11.say");
                i18nChannel.push("<煮饭婆>嗯");
                i18nChannel.pop("say.zfp.12.say");
                i18nChannel.push("<煮饭婆>@凌晨の茶 小茶茶");
                i18nChannel.pop("say.zfp.13.say");
                i18nChannel.push("<煮饭婆>我们这是智障危机");
                i18nChannel.pop("say.zfp.14.say");
                i18nChannel.push("<煮饭婆>赛博朋克酒馆");
                i18nChannel.pop("say.zfp.15.say");
                i18nChannel.push("<煮饭婆>白茶，捏捏");
                i18nChannel.pop("say.zfp.16.say");
                i18nChannel.push("<煮饭婆>一堆铁甲战舰出一个独特的风帆船");
                i18nChannel.pop("say.zfp.17.say");
                i18nChannel.push("<煮饭婆>完全没搞懂");
                i18nChannel.pop("say.zfp.18.say");
                i18nChannel.push("<煮饭婆>哒咩~");
                i18nChannel.pop("say.zfp.19.say");
                i18nChannel.push("<煮饭婆>洗把脸，感觉脑子烧坏了");
                i18nChannel.pop("say.zfp.20.say");
                i18nChannel.push("<煮饭婆>高兴也得过，不高兴也得过，干脆高兴一点");
                i18nChannel.pop("say.zfp.21.say");
                i18nChannel.push("<煮饭婆>屹立不倒Ι（）");
                i18nChannel.pop("say.zfp.22.say");
                i18nChannel.push("<煮饭婆>（高端局，看不懂）");
                i18nChannel.pop("say.zfp.23.say");
                i18nChannel.push("<煮饭婆>日，tank一车拍死四个人，真离谱");
                i18nChannel.pop("say.zfp.24.say");
                i18nChannel.push("<煮饭婆>32的话一些装饰带樱花纹也可以的");
            }
            {}

        }

        //push and gen
        try {
            i18nChannel.save(event.root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
