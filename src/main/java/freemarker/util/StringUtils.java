package freemarker.util;

import javax.swing.plaf.metal.MetalIconFactory;

/**
 * TODO Description
 *
 * @author JinGuiBo
 * @date 2018/7/6.
 */
public class StringUtils {

    public static String replaceToPath(String packageName) {
        return packageName.replace(".","/");
    }

    public static boolean isEmpty(String str) {

        if(str == null || "".equals(str) || "null".equals(str) || "NULL".equals(str)) {
            return true;
        }

        return false;
    }
}
