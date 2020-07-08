package com.github.xianzhan.jvmjava.java.runtime.heap;

import com.github.xianzhan.jvmjava.java.classloader.ClassLoader;
import com.github.xianzhan.jvmjava.java.util.CollectionUtils;
import com.github.xianzhan.jvmjava.java.util.Symbol;

import java.util.Map;

/**
 * 字符串常量池
 *
 * @author xianzhan
 * @since 2020-07-05
 */
public class StringPool {

    private static final Map<String, JObject> INTERNED_STRINGS = CollectionUtils.newHashMap();

    public static JObject jString(ClassLoader loader, String str) {
        var internedStr = INTERNED_STRINGS.get(str);
        if (internedStr != null) {
            return internedStr;
        }

        var chars = str.toCharArray();
        var charArrClass = loader.loadClass(Symbol.DESCRIPTOR_ARR_CHAR);
        var jChars = new JObject(charArrClass, chars);

        var jStr = loader.loadClass(Symbol.CLASS_STR).newObject();
        jStr.setRefVar(Symbol.FIELD_VALUE, Symbol.DESCRIPTOR_ARR_CHAR, jChars);

        INTERNED_STRINGS.put(str, jStr);
        return jStr;
    }

    public static String string(JObject jStr) {
        var charArrObj = jStr.getRefVar(Symbol.FIELD_VALUE, Symbol.DESCRIPTOR_ARR_CHAR);
        var charArr = charArrObj.chars();
        return new String(charArr);
    }

    public static JObject internString(JObject jStr) {
        var string = string(jStr);
        var internedStr = INTERNED_STRINGS.get(string);
        if (internedStr != null) {
            return internedStr;
        }

        INTERNED_STRINGS.put(string, jStr);
        return jStr;
    }
}
