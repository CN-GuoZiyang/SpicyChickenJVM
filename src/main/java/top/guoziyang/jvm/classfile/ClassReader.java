package top.guoziyang.jvm.classfile;

import java.math.BigInteger;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 19:06
 * @Description 用于读取 class 文件中不同位宽的数据
 *
 * jvm 定义了 u1、u2 和 u4 三种数据类型表示 1 字节、2 字节和 4 字节的无符号整数
 * u1、u2 可以用 int 存储，u4 用 long 存储
 */
public class ClassReader {

    private byte[] data;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    // read u1
    public int readUint8() {
        byte[] val = readByte(1);
        return byte2int(val);
    }

    //read u2
    public int readUint16() {
        byte[] val = readByte(2);
        return byte2int(val);
    }

    //read u4
    public long readUint32() {
        byte[] val = readByte(4);
        String str_hex = new BigInteger(1, val).toString(16);
        return Long.parseLong(str_hex, 16);
    }

    public int readUint32TInteger(){
        byte[] val = readByte(4);
        return new BigInteger(1, val).intValue();
    }

    public float readUint64TFloat() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).floatValue();
    }

    public long readUint64TLong() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).longValue();
    }

    public double readUint64TDouble() {
        byte[] val = readByte(8);
        return new BigInteger(1, val).doubleValue();
    }

    public int[] readUint16s() {
        int n = this.readUint16();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = this.readUint16();
        }
        return s;
    }

    public byte[] readBytes(int n) {
        return readByte(n);
    }

    private byte[] readByte(int length) {
        byte[] copy = new byte[length];
        System.arraycopy(data, 0, copy, 0, length);
        System.arraycopy(data, length, data, 0, data.length - length);
        return copy;
    }

    private int byte2int(byte[] val) {
        String str_hex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(str_hex, 16);
    }

}
