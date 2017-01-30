package com.example.aluno.projetores.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Julio_Cezar on 30/01/17.
 */
public class Database {

    private DatabaseManager databaseManager;
    private SQLiteDatabase sqld;

    public Database(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void open() {
        sqld = databaseManager.getWritableDatabase();
    }

    public SQLiteDatabase get() {
        if (sqld != null && sqld.isOpen()) {
            return sqld;
        }
        return null;
    }

    public void close() {
        databaseManager.close();
    }

}
