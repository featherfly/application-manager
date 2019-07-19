
package cn.featherfly.appmanager.supports;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.testng.annotations.Test;

import cn.featherfly.appmanager.ApplicationEvent;
import cn.featherfly.appmanager.ApplicationListener;
import cn.featherfly.common.lang.ClassLoaderUtils;

/**
 * <p>
 * JavaApplicationTest
 * </p>
 *
 * @author zhongj
 */
public class JavaApplicationTest {

    boolean running = true;

    @Test
    public void test() throws IOException, URISyntaxException {
        URL url = ClassLoaderUtils.getResource("", JavaApplicationTest.class);
        File file = new File(url.toURI());
        JavaApplication app = new JavaApplication(file.getAbsolutePath() + "\\..\\test", JavaMain.class.getName(),
                "abc", "ddd");
        app.setRedirectOutput(new File(JavaApplication.class.getSimpleName() + ".out.txt"));
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
            System.out.println(running);
        }
    }
}
