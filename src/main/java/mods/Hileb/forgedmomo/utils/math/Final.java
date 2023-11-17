package mods.Hileb.forgedmomo.utils.math;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/10 17:05
 **/
public class Final<T> {
    private T value;
    private boolean isFinal;
    public Final(T valueIn){
        value=valueIn;
        isFinal=false;
    }
    public T getValue(){
        return value;
    }
    public void setValue(T valueIn) throws IllegalArgumentException{
       if (isFinal) throw new IllegalArgumentException();
       else value=valueIn;
    }
    public boolean isFinal(){
        return isFinal;
    }
    public void setFinal(){
        isFinal=true;
    }
}
