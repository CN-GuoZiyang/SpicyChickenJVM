package top.guoziyang.jvm.classfile.attributes.impl;

import top.guoziyang.jvm.classfile.ClassReader;
import top.guoziyang.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 21:56
 */
public class UnparsedAttribute implements AttributeInfo {

    private String name;
    private int length;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen) {
        this.name = attrName;
        this.length = attrLen;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length);
    }

    public byte[] info(){
        return this.info;
    }

}
