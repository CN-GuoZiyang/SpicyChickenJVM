package top.guoziyang.jvm;

import top.guoziyang.jvm.classpath.Classpath;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 16:52
 * @Description JVM入口类
 */
public class Main {

    // run args:
    // -Xjre "C:\Program Files\Java\jdk1.8.0_271\jre" C:\Users\guozi\Desktop\SpicyChickenJVM\src\main\resources\HelloWorld
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

    private static void startJVM(Cmd cmd) {
        Classpath cp = new Classpath(cmd.jre, cmd.classpath);
        System.out.printf("classpath：%s class：%s args：%s\n", cp, cmd.getMainClass(), cmd.getArgs());
        // 获取主类的目录名
        String className = cmd.getMainClass().replace(".", "/");
        try {
            byte[] classData = cp.readClass(className);
            for(byte b : classData) {
                System.out.print(String.format("%02x", b & 0xff) + " ");
            }
        } catch (Exception e) {
            System.out.println("找不到主类：" + cmd.getMainClass());
            e.printStackTrace();
        }
    }

}
