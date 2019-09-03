
package cn.featherfly.appmanager.supports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * <p>
 * JavaMain
 * </p>
 *
 * @author zhongj
 */
public class JavaMain {

    public static <E> String toString(@SuppressWarnings("unchecked") E... es) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (E e : es) {
            sb.append(e.toString()).append(",");
        }
        if (es.length > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        String s = JavaMain.class.getName() + " main running  !! argu.length = " + args.length;
        String s2 = "args: " + toString(args);
        System.out.println(s);
        System.out.println(s2);
        //        FileUtils.write(new File(JavaMain.class.getSimpleName() + ".txt"), JavaMain.class.getName() + " main running");
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        File file = new File(url.getFile() + "../" + JavaMain.class.getSimpleName() + ".txt");
        FileOutputStream out = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(out);
        writer.println(s);
        writer.println(s2);
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
