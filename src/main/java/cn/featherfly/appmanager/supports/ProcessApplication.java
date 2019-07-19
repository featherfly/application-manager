
package cn.featherfly.appmanager.supports;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.featherfly.appmanager.AbstractApplication;
import cn.featherfly.appmanager.Application;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * ProcessApplication
 * </p>
 *
 * @author zhongj
 */
public abstract class ProcessApplication<A extends Application<A>> extends AbstractApplication<A> {

    private String baseDir;

    private File redirectOutput;

    private File redirectInput;

    private File redirectError;

    private Process process;

    /**
     * @param baseDir
     */
    public ProcessApplication(String baseDir) {
        super();
        this.baseDir = baseDir;
    }

    /**
     * 返回baseDir
     *
     * @return baseDir
     */
    public String getBaseDir() {
        return baseDir;
    }

    /**
     * 设置baseDir
     *
     * @param baseDir baseDir
     */
    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * 返回redirectOutput
     *
     * @return redirectOutput
     */
    public File getRedirectOutput() {
        return redirectOutput;
    }

    /**
     * 设置redirectOutput
     *
     * @param redirectOutput redirectOutput
     */
    public void setRedirectOutput(File redirectOutput) {
        this.redirectOutput = redirectOutput;
    }

    /**
     * 返回redirectInput
     *
     * @return redirectInput
     */
    public File getRedirectInput() {
        return redirectInput;
    }

    /**
     * 设置redirectInput
     *
     * @param redirectInput redirectInput
     */
    public void setRedirectInput(File redirectInput) {
        this.redirectInput = redirectInput;
    }

    /**
     * 返回redirectError
     *
     * @return redirectError
     */
    public File getRedirectError() {
        return redirectError;
    }

    /**
     * 设置redirectError
     *
     * @param redirectError redirectError
     */
    public void setRedirectError(File redirectError) {
        this.redirectError = redirectError;
    }

    /**
     * 返回process
     *
     * @return process
     */
    public Process getProcess() {
        return process;
    }

    /**
     * 设置process
     *
     * @param process process
     */
    public void setProcess(Process process) {
        this.process = process;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doStart() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (LangUtils.isNotEmpty(baseDir)) {
            processBuilder.directory(new File(baseDir));
        }

        System.out.println(getCommands());
        try {
            //            FileUtils.createFile(file);
            if (redirectOutput != null) {
                processBuilder.redirectOutput(redirectOutput);
            }
            if (redirectInput != null) {
                processBuilder.redirectInput(redirectInput);
            }
            if (redirectError != null) {
                processBuilder.redirectError(redirectError);
            }

            process = processBuilder.command(getCommands()).start();

            new Thread(() -> {
                while (process.isAlive()) {
                    //                    System.out.println("process is alive");
                    //process.exitValue();
                    // 后续加入正常非退出的判断
                }
                stop();
            }).start();

        } catch (IOException e) {
            // YUFEI_TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doStop() {
        process.destroy();
    }

    /**
     * 获取进程的标准输出（相对应调用程序是输入流）,用来读取应用的标准输出信息
     *
     * @return InputStream
     */
    public InputStream getInputStream() {
        return process.getInputStream();
    }

    /**
     * 获取应用的标准输入（相对应调用程序是输出流）,用来向应用的标准输入写入信息
     *
     * @return OutputStream
     */
    public OutputStream getOutputStream() {
        return process.getOutputStream();
    }

    protected abstract List<String> getCommands();

}
