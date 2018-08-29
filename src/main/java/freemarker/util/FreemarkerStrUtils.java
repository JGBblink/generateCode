package freemarker.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO Description
 *
 * @author JinGuiBo
 * @date 2018/7/4.
 */
public class FreemarkerStrUtils {

    private static Pattern UPPER = Pattern.compile("[A-Z]");

    private FreemarkerStrUtils() {
    }

    /**
     * 大写字母转小写，中间使用下划线分割  test_string_name
     *
     * @param param
     * @return
     */
    public static String upperCharToUnderLine(String param) {
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = UPPER.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }

        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 首字母小写
     * @param param
     * @return
     */
    public static String lowerFirst(String param) {
        char[]chars = param.toCharArray();

        chars[0] += 32;

        return String.valueOf(chars);
    }

    /**
     * 截取path目录
     * @param path
     * @return
     */
    public static String findDir(String path,String regex) {
        int index = path.lastIndexOf(regex);
        return path.substring(0,index);
    }

    public static void main(String[] args) {
        System.out.println(upperCharToUnderLine("TestModel"));
    }
}
