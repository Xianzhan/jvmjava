package com.github.xianzhan.jvmjava.java.flag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 参数解析
 *
 * @author xianzhan
 * @since 2020-02-26
 */
public class Flag {

    private String[]            args;
    private Map<String, String> helper;
    private List<String>        used;

    public Flag(String... args) {
        this.args = args;
        helper = new HashMap<>();
        used = new ArrayList<>();
    }

    public void boolVar(Consumer<Boolean> setBool, String arg, boolean defVal, String usage) {
        this.helper.put(arg, usage);
        for (String a : args) {
            if (Objects.equals(arg, a)) {
                setBool.accept(true);
                return;
            }
        }
        setBool.accept(defVal);
    }

    public void stringVar(Consumer<String> setStr, String arg, String defVal, String usage) {
        this.helper.put(arg, usage);
        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(arg, args[i])) {
                String a = args[i + 1];
                setStr.accept(a);
                used.add(a);
                return;
            }
        }
        setStr.accept(defVal);
    }

    public void parse() {
        List<String> newArgs = new ArrayList<>();
        for (String a : args) {
            if (!helper.containsKey(a) && !used.contains(a)) {
                newArgs.add(a);
            }
        }
        args = newArgs.toArray(new String[0]);
    }

    public String[] args() {
        return args;
    }
}
