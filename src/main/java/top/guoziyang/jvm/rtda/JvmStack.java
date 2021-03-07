package top.guoziyang.jvm.rtda;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-07 18:44
 * @Description 虚拟机栈
 */
public class JvmStack {

    private int maxSize;
    private int size;
    // 栈顶指针
    private Frame _top;

    public JvmStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(Frame frame) {
        if(this.size > this.maxSize) {
            throw new StackOverflowError();
        }

        if(this._top != null) {
            frame.lower = this._top;
        }

        this._top = frame;
        this.size ++;
    }

    public Frame pop() {
        if(this._top == null) {
            throw new RuntimeException("Jvm stack is empty!");
        }
        Frame top = this._top;
        this._top = top.lower;
        top.lower = null;
        this.size --;

        return top;
    }

    public Frame top() {
        if(this._top == null) {
            throw new RuntimeException("Jvm stack is empty!");
        }
        return this._top;
    }

}
