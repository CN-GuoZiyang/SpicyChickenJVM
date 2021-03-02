package top.guoziyang.jvm.classfile.constantpool.impl;

import top.guoziyang.jvm.classfile.ClassReader;
import top.guoziyang.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 21:31
 * @Description int 类型的字面值
 */
public class ConstantIntegerInfo implements ConstantInfo{

    private int val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint32TInteger();
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_INTEGER;
    }

    public int value(){
        return this.val;
    }


}
