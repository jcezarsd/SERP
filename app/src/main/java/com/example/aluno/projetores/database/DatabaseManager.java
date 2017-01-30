package com.example.aluno.projetores.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Julio_Cezar on 30/01/17.
 * Baseado no tutorial: helpdev.com.br/2012/05/15/banco-de-dados-para-android/
 */
public abstract class DatabaseManager extends SQLiteOpenHelper {

    protected Context context;

    public DatabaseManager(Context context, String nome, int versao) {
        super(context, nome, null, versao);
        this.context = context;
    }

    public abstract void onCreate(SQLiteDatabase bd);

    public abstract void onUpgrade(SQLiteDatabase bd, int versaoAtual, int versaoNova);

    /** Atravez do id do arquivo sql será gerado o banco de dados.
     *
     * @param fileID
     * @param bd
     * @throws IOException
     */
    protected void byFile(int fileID, SQLiteDatabase bd) throws IOException {
        StringBuilder sql = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(fileID)));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.length() > 0) {
                sql.append(line);
                if (line.endsWith(";")) {
                    bd.execSQL(sql.toString());
                    sql.delete(0, sql.length());
                }
            }
        }
    }
}
