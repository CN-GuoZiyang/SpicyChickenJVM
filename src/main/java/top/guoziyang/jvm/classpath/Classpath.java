package top.guoziyang.jvm.classpath;

import top.guoziyang.jvm.classpath.impl.WildcardEntry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 17:02
 * @Description 三种主要需要加载的类路径包装
 */
public class Classpath {

    // JVM 启动时必须要加载的三类类路径
    private Entry bootstrapClasspath;       // 启动类路径
    private Entry extensionClasspath;       // 扩展类路径
    private Entry userClasspath;            // 用户类路径

    public Classpath(String jreOption, String cpOption) {
        // 启动类和扩展类由 jre 提供
        parseBootAndExtensionClasspath(jreOption);
        // 解析用户自定义的类的路径
        parseUserClasspath(cpOption);
    }

    private void parseBootAndExtensionClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);

        // 启动类在 jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        bootstrapClasspath = new WildcardEntry(jreLibPath);

        // 扩展类在 jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        extensionClasspath = new WildcardEntry(jreExtPath);

    }

    private static String getJreDir(String jreOption) {
        /*
         * 寻找 jre 路径顺序
         * 1. 用户指定路径
         * 2. 当前文件夹下的 jre 文件夹
         * 3. 系统环境变量 JAVA_HOME 指定的文件夹
         */
        if(jreOption != null && Files.exists(Paths.get(jreOption))) {
            return jreOption;
        }
        if(Files.exists(Paths.get("./jre"))) {
            return "./jre";
        }
        String jh = System.getenv("JAVA_HOME");
        if(jh != null) {
            return Paths.get(jh, "jre").toString();
        }
        throw new RuntimeException("找不到 jre 路径！");
    }

    private void parseUserClasspath(String cpOption) {
        // 默认的用户类路径就是当前文件夹
        if(cpOption == null) {
            cpOption = ".";
        }
        userClasspath = Entry.create(cpOption);
    }

    public byte[] readClass(String className) throws Exception {
        // 根据类名获取一个类的字节码
        // 根据双亲委派机制，按顺序读取，并且在前两个读取不到时不会报错
        className = className + ".class";

        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception e) {
            // ignored
        }

        try {
            return extensionClasspath.readClass(className);
        } catch (Exception e) {
            // ignored
        }

        return userClasspath.readClass(className);
    }

}
