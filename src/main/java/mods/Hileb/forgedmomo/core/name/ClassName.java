package mods.Hileb.forgedmomo.core.name;

import org.objectweb.asm.tree.ClassNode;

public class ClassName {
    public String mcpName;
    public String srgName;
    public String notchName;
    ClassName(){}
    public static Builder of(){
        return new Builder();
    }
    public boolean is(ClassNode mn){
        return is(mn.name);
    }
    public boolean is(String name){
        if (srgName.equals(name))return true;
        else if (mcpName.equals(name))return true;
        else return notchName.equals(name);
    }
    public static class Builder{
        ClassName className;
        public Builder(){
            className =new ClassName();
            className.notchName="<null>";
            className.mcpName="<null>";
            className.srgName="<null>";
        }
        public Builder mcp(String name){
            className.mcpName=name;
            return this;
        }
        public Builder srg(String name){
            className.srgName=name;
            return this;
        }
        public Builder notch(String name){
            className.notchName=name;
            return this;
        }
        public ClassName build(){
            return className;
        }
    }
}
