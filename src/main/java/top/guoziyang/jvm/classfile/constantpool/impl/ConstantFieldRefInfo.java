package top.guoziyang.jvm.classfile.constantpool.impl;

import top.guoziyang.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 21:29
 * @Description 对一个字段的符号引用
 */
public class ConstantFieldRefInfo extends ConstantMemberRefInfo  {

    public ConstantFieldRefInfo(ConstantPool constantPool) {
        super(constantPool);
    }

    @Override
    public int tag() {
        return this.CONSTANT_TAG_FIELDREF;
    }

}
