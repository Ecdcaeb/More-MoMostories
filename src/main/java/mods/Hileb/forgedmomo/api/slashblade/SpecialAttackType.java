package mods.Hileb.forgedmomo.api.slashblade;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public abstract class SpecialAttackType {
    public static final SpecialAttackType NULL=new Builder(()->-1,()->null).build();
    public static List<SpecialAttackType> REGISTER=new LinkedList<>();
    public abstract int getID();//int 通常使用配置文件

    public abstract SpecialAttackBase getSpecialAttack();

    //flammpfeil.slashblade.specialattack.name=xxx  固定格式


    public SpecialAttackType(){
        REGISTER.add(this);
    }
    public void register(){
        ItemSlashBlade.specialAttacks.put(this.getID(), this.getSpecialAttack());
    }
    public boolean isNull(){return getID()==-1;}
    public static class Builder{
        private Impl impl;
        public Builder(Supplier<Integer> id,Supplier<SpecialAttackBase> supplier){
            impl=new Impl(id,supplier);
        }
        public SpecialAttackType build(){
            return impl;
        }
        private static class Impl extends SpecialAttackType{
            public Supplier<Integer> id;
            public Supplier<SpecialAttackBase> supplier;

            public Impl(Supplier<Integer> id,Supplier<SpecialAttackBase> supplier){
                this.id=id;
                this.supplier=supplier;
            }
            @Override
            public int getID() {
                return id.get();
            }

            @Override
            public SpecialAttackBase getSpecialAttack() {
                return supplier.get();
            }
        }
    }

}
