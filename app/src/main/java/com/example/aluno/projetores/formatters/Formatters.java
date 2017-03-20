package com.example.aluno.projetores.formatters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bzeymer on 20/03/17.
 */

public class Formatters {

    public static String dateToString(Date data) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(data);
    }
}
