package getCustmerPermission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class linuxDecompile {

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        try {
            System.out.println(filename);
            String command = "sudo timeout 45m apktool d -f" + " /mal/xiaoyu/CustomPermissionGoodware/" + filename + " -o /mal/xiaoyu/whd/CustomPermissionOutGoodApk/" + filename;
            Runtime.getRuntime().exec(command);

            System.out.println(command);
            Process ps = Runtime.getRuntime().exec(command);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
            System.out.println("successful compile the apk: " + filename);
        } catch (Exception e) {
            System.out.println("Unable to decompile the apk: " + filename);
            e.printStackTrace();
        }

    }
}
