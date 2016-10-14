package lsw.system.member.business.utility;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by swli on 10/13/2016.
 */
public class FileHelper {

    public static String openJsonFile(Context context, String fileName) {

        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception e) {
            Log.e("LP", "Load product", e);
        }
        return json;

    }
}
