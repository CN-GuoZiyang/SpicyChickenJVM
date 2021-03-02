package top.guoziyang.jvm.classpath.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 16:58
 * @Description 以通配符（*）结尾的类路径实现，实际上转换为 CompositeEntry 处理
 */
public class WildcardEntry extends CompositeEntry {

    public WildcardEntry(String pathList) {
        super(toPathList(pathList));
    }

    private static String toPathList(String wildcardPath) {
        String baseDir = wildcardPath.replace("*", "");
        try {
            // 遍历文件夹下所有文件，拼接所有 jar 文件的路径
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                    .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            return "";
        }
    }
}
