package mods.Hileb.forgedmomo.announces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/21 13:06
 **/
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.TYPE_USE,ElementType.LOCAL_VARIABLE,ElementType.METHOD,ElementType.PACKAGE,ElementType.PARAMETER,ElementType.TYPE_PARAMETER})
public @interface PrivateAPI {
}
