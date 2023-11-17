//package com.Hileb.forgedmomo.core.asmtransformers;
//
//import net.minecraft.launchwrapper.IClassTransformer;
//import org.objectweb.asm.ClassReader;
//import org.objectweb.asm.ClassWriter;
//import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.tree.*;
//
//import java.util.ListIterator;
//
///**
// * @Project More-MoMostories
// * @Author Hileb
// * @Date 2023/9/28 17:19
// **/
//@SuppressWarnings("unused")
//public class DataFixManagerTransformer implements IClassTransformer {
//
//    @Override
//    public byte[] transform(String name, String transformedName, byte[] basicClass) {
//        if ("net.minecraftforge.common.util.CompoundDataFixer".equals(transformedName)){
//            ClassReader classReader=new ClassReader(basicClass);
//            ClassNode cn=new ClassNode();
//            classReader.accept(cn,0);
//            /**
//             * {@link net.minecraftforge.common.util.CompoundDataFixer#DataFixer(int)}
//             */
//            for(MethodNode mn:cn.methods){
//                if ("<init>".equals(mn.name)){
//                    InsnList il=mn.instructions;
//
//                    AbstractInsnNode returnNote=null;
//                    ListIterator<AbstractInsnNode> iterator=il.iterator();
//
//                    while (iterator.hasNext()){
//                        returnNote=iterator.next();
//                        if (returnNote.getOpcode()==Opcodes.RETURN){
//                            break;
//                        }
//                    }
//                    /*
//                     ALOAD 0
//                     INVOKESTATIC com/Hileb/moremomostories/common/events/datafix/DataFixerSetupEvent.fireSetUpDataFixEvent (Lnet/minecraftforge/common/util/CompoundDataFixer;)V
//                    */
//                    InsnList ilnew=new InsnList();
//                    ilnew.add(new IntInsnNode(Opcodes.ALOAD,0));
//                    ilnew.add(new MethodInsnNode(Opcodes.INVOKESTATIC,"com/Hileb/moremomostories/common/events/datafix/DataFixerSetupEvent","fireSetUpDataFixEvent","(Lnet/minecraftforge/common/util/CompoundDataFixer;)V",false));
//
//                    il.insertBefore(returnNote,ilnew);
//                    break;
//                }
//            }
//            ClassWriter classWriter=new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
//            cn.accept(classWriter);
//            return classWriter.toByteArray();
//        }
//        return basicClass;
//    }
//}
