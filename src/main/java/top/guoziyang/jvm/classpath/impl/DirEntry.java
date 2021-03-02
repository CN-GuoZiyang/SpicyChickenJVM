package top.guoziyang.jvm.classpath.impl;

import top.guoziyang.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 17:00
 * @Description 以当前文件夹作为类路径的实现
 */
public class DirEntry implements Entry {

    private final Path absolutePath;

    public DirEntry(String path) {
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        return Files.readAllBytes(absolutePath.resolve(className));
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
