# jvmjava

A jvm implemented by java

<自己动手写 Java 虚拟机>---张秀宏 [Github](https://github.com/zxh0/jvmgo-book)

# 环境

Windows 10
<br>
[Adopt openjdk 14](https://adoptopenjdk.net/?variant=openjdk14&jvmVariant=hotspot)
<br>
[Adopt openjdk 8](https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=hotspot)
<br>
[Apache Maven 3.6.3](https://maven.apache.org/download.cgi)
<br>
[IntelliJ IDEA](https://www.jetbrains.com/idea/)

## 环境变量

```cmd
%JAVA_HOME%  = ~\jdk\jdk-14
%JAVA8_HOME% = ~\jdk\jdk-8.0.242.08-hotspot
```

# 执行

编译与运行都需要添加参数 `--enable-preview`

[JEP 12: Preview Language and VM Features](http://openjdk.java.net/jeps/12)