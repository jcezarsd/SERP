package com.example.aluno.projetores.database;

import android.content.Context;

import java.io.Serializable;

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

    }

}
