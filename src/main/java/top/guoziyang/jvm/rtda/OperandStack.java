package top.guoziyang.jvm.rtda;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-07 19:03
 * @Description 操作数栈
 */
public class OperandStack {

    private int size = 0;
    private Slot[] slots;

    public OperandStack(int maxStack) {
        if(maxStack > 0) {
            slots = new Slot[maxStack];
            for(int i = 0; i < maxStack; i ++) {
                slots[i] = new Slot();
            }
        }
    }

    public void pushInt(int val) {
        slots[size].num = val;
        size ++;
    }

    public int popInt() {
        size --;
        return slots[size].num;
    }

    public void pushFloat(float val) {
        slots[size].num = Float.floatToRawIntBits(val);
        size ++;
    }

    public Float popFloat() {
        size --;
        int num = this.slots[size].num;
        return Float.intBitsToFloat(num);
    }

    public void pushLong(long val) {
        slots[size].num = (int) (val & 0x000000ffffffffL);
        slots[size + 1].num = (int)(val >> 32);
        size += 2;
    }

    public long popLong() {
        size -= 2;
        long low = this.slots[size].num & 0x000000ffffffffL;
        long high = this.slots[size + 1].num & 0x000000ffffffffL;
        return (high << 32) | low;
    }

    public void pushDouble(double val) {
        pushLong(Double.doubleToRawLongBits(val));
    }

    public Double popDouble() {
        return Double.longBitsToDouble(popLong());
    }

    public void pushRef(Object ref) {
        slots[size].ref = ref;
        size ++;
    }

    public Object popRef(){
        size --;
        Object ref = slots[size].ref;
        slots[size].ref = null;
        return ref;
    }


}
