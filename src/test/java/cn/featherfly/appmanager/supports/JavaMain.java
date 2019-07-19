
package cn.featherfly.appmanager.supports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * JavaMain
 * </p>
 *
 * @author zhongj
 */
public class JavaMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        String s = JavaMain.class.getName() + " main running  !! argu.length = " + args.length;
        System.out.println(s);
        //        FileUtils.write(new File(JavaMain.class.getSimpleName() + ".txt"), JavaMain.class.getName() + " main running");
        FileOutputStream out = new FileOutputStream(new File(JavaMain.class.getSimpleName() + ".txt"));
        PrintWriter writer = new PrintWriter(out);
        writer.println(s);
        writer.flush();

        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            System.out.println(JavaMain.class.getName() + " sleep 2000");
            writer.println(JavaMain.class.getName() + " sleep 2000");
            writer.flush();
        }

        writer.close();

    }
}
