package com.example.eugeney.fishprobelarus;

import android.content.Context;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JSONHelper {
    private static final String FILE_NAME = "data.json";

    static boolean exportToJSON(Context context, List<InformationFish> dataList) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setFishs(dataList);
        String jsonString = gson.toJson(dataItems);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
    private static class DataItems {
        private List<InformationFish> fishs;

        List<InformationFish> getFishs() {
            return fishs;
        }
        void setFishs(List<InformationFish> fishs) {
            this.fishs = fishs;
        }
    }
}
