package com.example.aluno.projetores.database;

import android.content.Context;

import com.example.aluno.projetores.models.Projetor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Julio on 19/03/2017.
 */

public class Database {

    public static void save(Context context, Serializable object, String fileName) {

        String ser = SerializeObject.objectToString(object);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializeObject.WriteSettings(context, ser, fileName);
        } else {
            SerializeObject.WriteSettings(context, "", fileName);
        }

//        File f = new File(context.getFilesDir() + File.separator + fileName);
//
//        try {
//
//            FileOutputStream fos = new FileOutputStream(f);
//            ObjectOutputStream objectwrite = new ObjectOutputStream(fos);
//            objectwrite.writeObject(object);
//            fos.close();
//
//            if (!f.exists()) {
//                f.mkdirs();
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
