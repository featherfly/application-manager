
package cn.featherfly.appmanager.supports;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.testng.annotations.Test;

import cn.featherfly.appmanager.ApplicationEvent;
import cn.featherfly.appmanager.ApplicationListener;
import cn.featherfly.common.lang.ClassLoaderUtils;
import cn.featherfly.common.lang.SystemPropertyUtils;

/**
 * <p>
 * JavaApplicationTest
 * </p>
 *
 * @author zhongj
 */
public class JavaApplicationTest {

    boolean running = true;

    URL url = ClassLoaderUtils.getResource("", JavaApplicationTest.class);

    File file = new File(url.getPath());

    String filePath = file.getPath() + "/../";

    @Test
    public void test() throws IOException, URISyntaxException {
        JavaApplication app = new JavaApplication(file.getPath() + "\\..\\test", JavaMain.class.getName(), "abc",
                "ddd");
        app.setRedirectOutput(new File(filePath + JavaApplication.class.getSimpleName() + ".out.txt"));
        app.setRedirectError(new File(filePath + JavaApplication.class.getSimpleName() + ".err.txt"));
        app.start(l -> {
            System.out.println(l.getApplication().getState());
        });

        app.addListener(new ApplicationListener<JavaApplication>() {

            @Override
            public void onStop(ApplicationEvent<JavaApplication> event) {
                System.out.println("shutdown");
                running = false;
            }

            @Override
            public void onStart(ApplicationEvent<JavaApplication> event) {
                // YUFEI_TODO Auto-generated method stub

            }
        });

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // YUFEI_TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("running " + running);
        }
    }

    @Test
    public void testStopOnShutdown() throws IOException, URISyntaxException {
        URL url = ClassLoaderUtils.getResource("", JavaApplicationTest.class);
        File file = new File(url.toURI());
        JavaApplication app = new JavaApplication(file.getAbsolutePath() + "\\..\\test", JavaMain.class.getName(),
                "abc", "ddd");
        app.setRedirectOutput(new File(filePath + JavaApplication.class.getSimpleName() + ".stoponshutdown.out.txt"));
        app.setStopOnShutdown(true);
        app.start(l -> {
            System.out.println(l.getApplication().getState());
        });

        app.addListener(new ApplicationListener<JavaApplication>() {

            @Override
            public void onStop(ApplicationEvent<JavaApplication> event) {
                System.out.println("shutdown");
                running = false;
            }

            @Override
            public void onStart(ApplicationEvent<JavaApplication> event) {
                // YUFEI_TODO Auto-generated method stub

            }
        });

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // YUFEI_TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("running " + running);
            System.exit(1);
        }
    }

    @Test
    public void testStop() throws IOException, URISyntaxException {
        URL url = ClassLoaderUtils.getResource("", JavaApplicationTest.class);
        File file = new File(url.toURI());
        JavaApplication app = new JavaApplication(file.getAbsolutePath() + "\\..\\test", JavaMain.class.getName(),
                "abc", "ddd");
        app.setRedirectOutput(new File(filePath + JavaApplication.class.getSimpleName() + ".stop.out.txt"));
        app.start(l -> {
            System.out.println(l.getApplication().getState());
        });

        app.addListener(new ApplicationListener<JavaApplication>() {

            @Override
            public void onStop(ApplicationEvent<JavaApplication> event) {
                System.out.println("shutdown");
                running = false;
            }

            @Override
            public void onStart(ApplicationEvent<JavaApplication> event) {
                // YUFEI_TODO Auto-generated method stub

            }
        });

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // YUFEI_TODO Auto-generated catch block
                e.printStackTrace();
            }
            app.stop(l -> {
                System.out.println("stoped");
                System.out.println(l.getApplication().getState());
            });
            System.out.println("running " + running);
        }
    }

    @Test
    public void testJar() throws Exception {
        JavaApplication app = new JavaApplication(SystemPropertyUtils.getUserDir() + "/app",
                "gradle-executed-jar-0.1.0.jar", "aaa", "bbb", "ccc", "ddd");
        app.setRedirectOutput(new File(filePath + JavaApplication.class.getSimpleName() + ".jar.out.txt"));
        app.setRedirectError(new File(filePath + JavaApplication.class.getSimpleName() + ".jar.err.txt"));
        app.start(l -> {
            System.out.println("app state = " + l.getApplication().getState());
        });

        app.addListener(new ApplicationListener<JavaApplication>() {

            @Override
            public void onStop(ApplicationEvent<JavaApplication> event) {
                System.out.println("shutdown");
                running = false;
            }

            @Override
            public void onStart(ApplicationEvent<JavaApplication> event) {
                // YUFEI_TODO Auto-generated method stub

            }
        });

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // YUFEI_TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("running " + running);
        }
    }
}
