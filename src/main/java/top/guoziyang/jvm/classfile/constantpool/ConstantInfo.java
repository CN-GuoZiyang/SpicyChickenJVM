package top.guoziyang.jvm.classfile.constantpool;

import top.guoziyang.jvm.classfile.ClassReader;
import top.guoziyang.jvm.classfile.constantpool.impl.*;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 19:03
 * @Description class 文件中的常量池信息
 */
public interface ConstantInfo {

    int CONSTANT_TAG_UTF8               = 1;
    int CONSTANT_TAG_INTEGER            = 3;
    int CONSTANT_TAG_FLOAT              = 4;
    int CONSTANT_TAG_LONG               = 5;
    int CONSTANT_TAG_DOUBLE             = 6;
    int CONSTANT_TAG_CLASS              = 7;
    int CONSTANT_TAG_STRING             = 8;
    int CONSTANT_TAG_FIELDREF           = 9;
    int CONSTANT_TAG_METHODREF          = 10;
    int CONSTANT_TAG_INTERFACEMETHODREF = 11;
    int CONSTANT_TAG_NAMEANDTYPE        = 12;
    int CONSTANT_TAG_METHODHANDLE       = 15;
    int CONSTANT_TAG_METHODTYPE         = 16;
    int CONSTANT_TAG_INVOKEDYNAMIC      = 18;

    void readInfo(ClassReader reader);

    int tag();

    static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool) {
        int tag = reader.readUint8();
        ConstantInfo constantInfo = newConstantInfo(tag, constantPool);
        constantInfo.readInfo(reader);
        return constantInfo;
    }

    static ConstantInfo newConstantInfo(int tag, ConstantPool constantPool) {
        //System.out.println(tag);
        switch (tag) {
            case CONSTANT_TAG_UTF8:
                return new ConstantUtf8Info();
            case CONSTANT_TAG_INTEGER:
                return new ConstantIntegerInfo();
            case CONSTANT_TAG_FLOAT:
                return new ConstantFloatInfo();
            case CONSTANT_TAG_LONG:
                return new ConstantLongInfo();
            case CONSTANT_TAG_DOUBLE:
                return new ConstantDoubleInfo();
            case CONSTANT_TAG_CLASS:
                return new ConstantClassInfo(constantPool);
            case CONSTANT_TAG_STRING:
                return new ConstantStringInfo(constantPool);
            case CONSTANT_TAG_FIELDREF:
                return new ConstantFieldRefInfo(constantPool);
            case CONSTANT_TAG_METHODREF:
                return new ConstantMethodRefInfo(constantPool);
            case CONSTANT_TAG_INTERFACEMETHODREF:
                return new ConstantInterfaceMethodRefInfo(constantPool);
            case CONSTANT_TAG_NAMEANDTYPE:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_TAG_METHODHANDLE:
                return new ConstantMethodHandleInfo();
            case CONSTANT_TAG_METHODTYPE:
                return new ConstantMethodTypeInfo();
            case CONSTANT_TAG_INVOKEDYNAMIC:
                return new ConstantInvokeDynamicInfo();
            default:
                throw new ClassFormatError("constant pool tag");
        }
    }

}
