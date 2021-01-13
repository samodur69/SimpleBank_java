package banking;

import java.io.File;

public class FileUtil {
    final public static String path = "";
//    final public static String path = "./Simple Banking System/task/src/banking/";
//      use for test DBase

    public static boolean checkFile(String fileName) {
        File file = new File(path + fileName);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        if (file.exists()) {
            System.out.println("File found");
            return true;
        } else {
            System.out.println("SMTHG WRONG");
        }
        return false;
    }
}
