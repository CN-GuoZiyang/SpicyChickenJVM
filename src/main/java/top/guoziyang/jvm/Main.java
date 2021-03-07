package top.guoziyang.jvm;

import top.guoziyang.jvm.classfile.ClassFile;
import top.guoziyang.jvm.classfile.MemberInfo;
import top.guoziyang.jvm.classpath.Classpath;
import top.guoziyang.jvm.rtda.Frame;
import top.guoziyang.jvm.rtda.LocalVars;
import top.guoziyang.jvm.rtda.OperandStack;

import java.util.Arrays;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 16:52
 * @Description JVM入口类
 */
public class Main {

    // run args:
    // -Xjre "C:\Program Files\Java\jdk1.8.0_271\jre" java.lang.String
    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if(!cmd.ok || cmd.helpFlag) {
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if(cmd.versionFlag) {
            System.out.println("java version \"1.8.0\"");
            return;
        }
        startJVM(cmd);
    }

    private static void startJVM(Cmd args) {
        Frame frame = new Frame(100, 100);
        test_localVars(frame.localVars());
        test_operandStack(frame.operandStack());
    }

    private static void test_localVars(LocalVars vars) {
        vars.setInt(0,100);
        vars.setInt(1,-100);
        vars.setLong(2,2997924580L);
        vars.setLong(4,-2997924580L);
        vars.setFloat(6, 3.1415926f);
        vars.setDouble(7, 2.71828182845);
        vars.setRef(9, null);
        System.out.println(vars.getInt(0));
        System.out.println(vars.getInt(1));
        System.out.println(vars.getLong(2));
        System.out.println(vars.getLong(4));
        System.out.println(vars.getFloat(6));
        System.out.println(vars.getDouble(7));
        System.out.println(vars.getRef(9));
    }

    private static void test_operandStack(OperandStack ops) {
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushLong(2997924580L);
        ops.pushLong(-2997924580L);
        ops.pushFloat(3.1415926f);
        ops.pushDouble(2.71828182845);
        ops.pushRef(null);
        System.out.println(ops.popRef());
        System.out.println(ops.popDouble());
        System.out.println(ops.popFloat());
        System.out.println(ops.popLong());
        System.out.println(ops.popLong());
        System.out.println(ops.popInt());
        System.out.println(ops.popInt());
    }

}
