package lsw.system.member.business.model;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by swli on 10/12/2016.
 */
public class ModelContext {

    private Context currentContext;

    public static final String folderName = Environment.getExternalStorageDirectory() + "/" + "lsw.business";
    public static final String fileName = "data.json";

    protected final int BUFFER_SIZE = 400000;

    public ModelContext(Context context)
    {
        currentContext = context;
    }

    public String getGsonFromFile(String filename){

        StringBuilder sb = new StringBuilder();

        try {
            FileInputStream fis = currentContext.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }catch (Exception ex)
        {

        }
        String json = sb.toString();
        return json;
    }

    public void createFolder(String path)
    {
        java.io.File folder = new java.io.File(path);
        boolean success = true;
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void saveToFile(String json)
    {
        try {
            createFolder(folderName);
            FileOutputStream outputStream = new FileOutputStream(folderName + "/" + fileName);
            outputStream.write(json.getBytes());
            outputStream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
