package top.guoziyang.jvm.classpath.impl;

import top.guoziyang.jvm.classpath.Entry;

import java.io.IOException;
import java.nio.file.*;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 16:59
 * @Description 压缩包形式（jar 或 zip）的类路径实现
 */
public class ZipEntry implements Entry {

    private final Path absolutePath;

    public ZipEntry(String path) {
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        try (FileSystem zipFs = FileSystems.newFileSystem(absolutePath, null)) {
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
