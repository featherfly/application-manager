
package cn.featherfly.appmanager.supports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.StringUtils;

/**
 * <p>
 * JavaApplication
 * </p>
 *
 * @author zhongj
 */
public class JavaApplication extends ProcessApplication<JavaApplication> {

    private String main;

    private boolean mainIsJar;

    private Set<String> classpaths = new HashSet<>();

    private List<String> argus = new ArrayList<>();

    /**
     * @param baseDir
     * @param mainClass
     * @param argus
     */
    public JavaApplication(String baseDir, String mainClass, String... argus) {
        this(baseDir, mainClass, null, argus);
    }

    /**
     * @param baseDir
     * @param mainClass
     * @param classpaths
     * @param argus
     */
    public JavaApplication(String baseDir, String mainClass,
            Set<String> classpaths, String... argus) {
        this(baseDir, mainClass, StringUtils.substringAfterLast(mainClass, ".")
                .equalsIgnoreCase("jar"), classpaths, argus);
    }

    /**
     * @param baseDir
     * @param main
     * @param mainIsJar
     * @param classpaths
     * @param argus
     */
    public JavaApplication(String baseDir, String main, boolean mainIsJar,
            Set<String> classpaths, String... argus) {
        super(baseDir);
        this.main = main;
        this.mainIsJar = mainIsJar;
        if (classpaths != null) {
            this.classpaths.addAll(classpaths);
        }
        CollectionUtils.addAll(this.argus, argus);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> getCommands() {
        List<String> commands = new ArrayList<>();
        commands.add("java");
        if (mainIsJar) {
            commands.add("-jar");
        }
        commands.add(main);
        for (String argu : argus) {
            commands.add(argu);
        }
        if (!classpaths.isEmpty()) {
            commands.add("-cp");
            commands.add(getClassPath());
        }
        return commands;
    }

    private String getClassPath() {
        StringBuilder classpath = new StringBuilder();
        classpaths.forEach(cp -> {
            classpath.append(cp).append(";");
        });
        return classpath.toString();
    }
}
