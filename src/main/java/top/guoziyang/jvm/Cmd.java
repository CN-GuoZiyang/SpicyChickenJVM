package top.guoziyang.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/**
 * @Author Ziyang Guo
 * @Date 2021-03-02 16:52
 * @Description 命令行参数解析
 */
public class Cmd {

    @Parameter(names = {"-?", "-help"}, description = "输出帮助信息", order = 3, help = true)
    boolean helpFlag = false;

    @Parameter(names = "-version", description = "输出 jvm 版本号并退出", order = 2)
    boolean versionFlag = false;

    @Parameter(names = {"-cp", "-classpath"}, description = "类路径", order = 1)
    String classpath;

    @Parameter(names = "-Xjre", description = "path to jre", order = 4)
    String jre;

    @Parameter(description = "主类和参数")
    List<String> mainClassAndArgs;

    boolean ok;

    public String getMainClass() {
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty()
                ? mainClassAndArgs.get(0)
                : null;
    }

    public List<String> getArgs() {
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1
                ? mainClassAndArgs.subList(1, mainClassAndArgs.size())
                : null;
    }

    public static Cmd parse(String[] argv) {
        Cmd args = new Cmd();
        JCommander cmd = JCommander.newBuilder().addObject(args).build();
        cmd.parse(argv);
        args.ok = true;
        return args;
    }

}
