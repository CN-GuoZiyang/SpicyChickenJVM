package top.guoziyang.jvm.rtda;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-07 18:17
 * @Description 运行时线程
 */
public class Thread {

    // 程序计数器
    private int pc;

    // 虚拟机栈
    private JvmStack stack;

    public Thread() {
        this.stack = new JvmStack(1024);
    }

    public int pc() {
        return pc;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame popFrame() {
        return this.stack.pop();
    }

    public Frame currentFrame() {
        return this.stack.top();
    }

}
