package getCustmerPermission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class getPermission {
    public static void main(String[] args) throws IOException {
        File file = new File("/mal/xiaoyu/whd/CustomPermissionOutGoodApk/" + args[0]);
        System.out.println("filename" + file);
        func(file);

    }

    private static void func(File file) throws IOException {
        System.out.println("filename" + file);
        File[] fs = file.listFiles();
        for(File f:fs){
            try{
                String data1 = "<permission";
                String data2 = "<uses-permission";
                String data3 = "android.permission";
                String data4 = "com.google.android";
                String data5 = "package=";
                String data6 = "com.android";
                if(f.isDirectory()){
                    func(f);
                }
                if(f.isFile() && f.getName().equals("AndroidManifest.xml")){

                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(f));
                    BufferedReader bufferedReader = new BufferedReader(read);
                    List<String> strs = new ArrayList<>();
                    String lineTxt;
//                int flag = 1;

                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        if((lineTxt.contains(data1) && !(lineTxt.contains(data3) || lineTxt.contains(data4)|| lineTxt.contains(data6))) || (lineTxt.contains(data2)&& !(lineTxt.contains(data3) || lineTxt.contains(data4)|| lineTxt.contains(data6))) || lineTxt.contains(data5)) {
                            strs.add(lineTxt);
                        }
                    }
                    read.close();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("/home/androidfuzz/custompermission/goodCustomPermissionOutTxt/" + f.getParentFile().getName() + ".txt")); //get permission
                    for (String l:strs){
                        writer.write(l + "\r\n");
                    }
                    writer.close();
                }
            } catch (Exception e) {
                System.out.println("Error in IO");
                e.printStackTrace();
            }

        }
    }
}
