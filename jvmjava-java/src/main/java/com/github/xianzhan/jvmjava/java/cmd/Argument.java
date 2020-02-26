package com.github.xianzhan.jvmjava.java.cmd;

/**
 * 命令行参数
 *
 * @author xianzhan
 * @since 2020-02-26
 */
public class Argument {

    private boolean  helpFlag;
    private boolean  versionFlag;
    private String   cpOption;
    private String   clazz;
    private String[] args;

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public void setHelpFlag(boolean helpFlag) {
        this.helpFlag = helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public void setVersionFlag(boolean versionFlag) {
        this.versionFlag = versionFlag;
    }

    public String getCpOption() {
        return cpOption == null ? "" : cpOption;
    }

    public void setCpOption(String cpOption) {
        this.cpOption = cpOption;
    }

    public String getClazz() {
        return clazz == null ? "" : clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
