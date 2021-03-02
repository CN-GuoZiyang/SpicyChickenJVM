package top.guoziyang.jvm.classpath.impl;

import com.beust.jcommander.Strings;
import top.guoziyang.jvm.classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 16:56
 * @Description 多种情况混合的类路径读取实现（以分隔符分隔），实际上分割成多个路径分别处理
 */
public class CompositeEntry implements Entry {

    private final List<Entry> entryList = new ArrayList<>();

    public CompositeEntry(String pathList) {
        String[] paths = pathList.split(File.pathSeparator);
        for(String path : paths) {
            entryList.add(Entry.create(path));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for(Entry entry : entryList) {
            try {
                return entry.readClass(className);
            } catch (Exception e) {
                // ignored
            }
        }
        throw new IOException("找不到类：" + className);
    }

    @Override
    public String toString() {
        String[] strs = new String[entryList.size()];
        for(int i = 0; i < entryList.size(); i ++) {
            strs[i] = entryList.get(i).toString();
        }
        return Strings.join(File.pathSeparator, strs);
    }
}
