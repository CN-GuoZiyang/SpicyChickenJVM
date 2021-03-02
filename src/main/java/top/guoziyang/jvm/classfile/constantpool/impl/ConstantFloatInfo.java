package top.guoziyang.jvm.classfile.constantpool.impl;

import top.guoziyang.jvm.classfile.ClassReader;
import top.guoziyang.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 21:30
 * @Description float 类型的字面值
 */
public class ConstantFloatInfo implements ConstantInfo {

    private float val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint64TFloat();
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_FLOAT;
    }

    public float value(){
        return this.val;
    }

}
