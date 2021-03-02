package top.guoziyang.jvm.classfile.constantpool;

import top.guoziyang.jvm.classfile.ClassReader;
import top.guoziyang.jvm.classfile.constantpool.impl.ConstantClassInfo;
import top.guoziyang.jvm.classfile.constantpool.impl.ConstantNameAndTypeInfo;
import top.guoziyang.jvm.classfile.constantpool.impl.ConstantUtf8Info;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 21:09
 * @Description 常量池，内含 ConstantInfo 数组
 */
public class ConstantPool {

    private ConstantInfo[] constantInfos;
    private final int siz;

    public ConstantPool(ClassReader reader) {
        siz = reader.readUint16();
        constantInfos = new ConstantInfo[siz];
        for (int i = 1; i < siz; i++) {
            constantInfos[i] = ConstantInfo.readConstantInfo(reader, this);
            switch (constantInfos[i].tag()) {
                // 这两种类型需要占用两个位置
                case ConstantInfo.CONSTANT_TAG_DOUBLE:
                case ConstantInfo.CONSTANT_TAG_LONG:
                    i++;
                    break;
            }
        }
    }

    public Map<String, String> getNameAndType(int idx) {
        ConstantNameAndTypeInfo constantInfo = (ConstantNameAndTypeInfo) this.constantInfos[idx];
        Map<String, String> map = new HashMap<>();
        map.put("name", this.getUTF8(constantInfo.nameIdx));
        map.put("_type", this.getUTF8(constantInfo.descIdx));
        return map;
    }

    public String getClassName(int idx){
        ConstantClassInfo classInfo = (ConstantClassInfo) this.constantInfos[idx];
        return this.getUTF8(classInfo.nameIdx);
    }

    public String getUTF8(int idx) {
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) this.constantInfos[idx];
        return utf8Info == null ? "" : utf8Info.str();
    }

    public ConstantInfo[] getConstantInfos() {
        return constantInfos;
    }

    public void setConstantInfos(ConstantInfo[] constantInfos) {
        this.constantInfos = constantInfos;
    }

    public int getSiz() {
        return siz;
    }

}
