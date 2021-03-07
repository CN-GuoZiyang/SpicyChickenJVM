package top.guoziyang.jvm.rtda;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-07 18:51
 * @Description 局部变量表
 */
public class LocalVars {

    private Slot[] slots;

    public LocalVars(int maxLocals) {
        if(maxLocals > 0) {
            slots = new Slot[maxLocals];
            for(int i = 0; i < maxLocals; i ++) {
                slots[i] = new Slot();
            }
        }
    }

    public void setInt(int idx, int val) {
        this.slots[idx].num = val;
    }

    public int getInt(int idx) {
        return slots[idx].num;
    }

    public void setFloat(int idx, float val) {
        this.slots[idx].num = Float.floatToRawIntBits(val);
    }

    public Float getFloat(int idx) {
        int num = this.slots[idx].num;
        return Float.intBitsToFloat(num);
    }

    public void setLong(int idx, long val) {
        this.slots[idx].num = (int) (val & 0x000000ffffffffL);
        this.slots[idx + 1].num = (int) (val >> 32);
    }

    public Long getLong(int idx) {
        long low = this.slots[idx].num & 0x000000ffffffffL;
        long high = this.slots[idx + 1].num  & 0x000000ffffffffL;
        return (high << 32) | low;
    }

    public void setDouble(int idx, double val) {
        setLong(idx, Double.doubleToRawLongBits(val));
    }

    public Double getDouble(int idx) {
        return Double.longBitsToDouble(getLong(idx));
    }

    public void setRef(int idx, Object ref) {
        slots[idx].ref = ref;
    }

    public Object getRef(int idx) {
        return slots[idx].ref;
    }

}
