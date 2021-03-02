package top.guoziyang.jvm.classfile.constantpool.impl;

import top.guoziyang.jvm.classfile.ClassReader;
import top.guoziyang.jvm.classfile.constantpool.ConstantInfo;
import top.guoziyang.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 21:25
 * @Description 对一个类或接口的符号引用
 */
public class ConstantClassInfo implements ConstantInfo {

    public ConstantPool constantPool;
    public int nameIdx;

    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIdx = reader.readUint16();
    }

    public String name(){
        return this.constantPool.getUTF8(this.nameIdx);
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_CLASS;
    }

}
