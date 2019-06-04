package com.dong.luong.tuvungtienganh.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.dong.luong.tuvungtienganh.model.GrammarModel;
import com.dong.luong.tuvungtienganh.model.QuestionModel;
import com.dong.luong.tuvungtienganh.model.WordModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DatabaseManager {
    private static final String DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath() + "/data/com.dong.luong.tuvungtienganh/";
    private static final String DATABSE_NAME = "tienganh.sqlite";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    private static final String TABLE_NAME_GRAMMAR = "nguphap";
    private static final String TABLE_NAME_QUESTION = "tracnghiem";
    private static final String SQL_GET_GRAMMAR = "SELECT * FROM " + TABLE_NAME_GRAMMAR;
    private static final String SQL_GET_QUESTION = "SELECT * FROM " + TABLE_NAME_QUESTION;


    public DatabaseManager(Context context) {

        this.context = context;
        copyDatabases();

    }

    private void copyDatabases() {
        new File(DATABASE_PATH).mkdir();
        try {
            File file = new File(DATABASE_PATH + DATABSE_NAME);
            if (file.exists()) return;
            InputStream input = context.getAssets().open(DATABSE_NAME);

            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);
            int len;
            byte buff[] = new byte[1024];
            while ((len = input.read(buff)) > 0) {
                output.write(buff, 0, len);
            }
            output.close();
            input.close();

            Log.i("a", "Copy Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDB() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABSE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDB() {
        if (sqLiteDatabase != null || sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<GrammarModel> getListGrammar() {
        openDB();
        Cursor c = sqLiteDatabase.rawQuery(SQL_GET_GRAMMAR, null);
        if (c == null) {
            return null;
        }
        int indexGrammarId = c.getColumnIndex("id");
        int indexGrammar1 = c.getColumnIndex("indexGrammar");
        int indexTitleGrammar = c.getColumnIndex("titleGrammar");
        int indexLevelGrammar = c.getColumnIndex("level");
        int indexContentGrammar = c.getColumnIndex("contentGrammar");

        int indexGrammar, level, id;
        String titleGrammar;
        String contentGrammar;

        c.moveToFirst();

        ArrayList<GrammarModel> grammarModels = new ArrayList<>();
        while (c.isAfterLast() == false) {
            //Lay du lieu dua vao thu tu cot

            id = c.getInt(indexGrammarId);
            indexGrammar = c.getInt(indexGrammar1);
            level = c.getInt(indexLevelGrammar);
            titleGrammar = c.getString(indexTitleGrammar);
            contentGrammar = c.getString(indexContentGrammar);
            grammarModels.add(new GrammarModel(id, indexGrammar, level, titleGrammar, contentGrammar));
            c.moveToNext();
        }
        c.close();
        closeDB();
        return grammarModels;
    }

    public ArrayList<WordModel> getWordOrderTopic(int index) {
        openDB();
        ArrayList<WordModel> arr = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("select * from tudien where topicID=" + index, null);
        WordModel itemWord = null;
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            String spelling = c.getString(c.getColumnIndex("spelling"));
            String contain = c.getString(c.getColumnIndex("contain"));
            String audio = c.getString(c.getColumnIndex("audio"));
            int selected = c.getInt(c.getColumnIndex("selected"));
            String topicName = c.getString(c.getColumnIndex("topicName"));
            itemWord = new WordModel(id, name, spelling, contain, audio, selected, topicName);
            arr.add(itemWord);
            c.moveToNext();
        }
        closeDB();
        return arr;
    }

    public ArrayList<QuestionModel> getQuestion(int position) {
        openDB();
        Cursor c = null;
        switch (position) {
            case 0:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 0 and 19", null);
                break;
            case 1:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 20 and 40", null);
                break;
            case 2:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 41 and 61", null);
                break;
            case 3:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 62 and 82", null);
                break;
            case 4:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 83 and 103", null);
                break;
            case 5:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 104 and 124", null);
                break;
            case 6:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 125 and 145", null);
                break;
            case 7:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 146 and 166", null);
                break;
            case 8:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 167 and 187", null);
                break;
            case 9:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 188 and 208", null);
                break;
            case 10:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 209 and 229", null);
                break;
            case 11:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 230 and 250", null);
                break;
            case 12:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 251 and 271", null);
                break;
            case 13:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 272 and 292", null);
                break;
            case 14:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 293 and 313", null);
                break;
            case 15:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 314 and 334", null);
                break;
            case 16:
                c = sqLiteDatabase.rawQuery(SQL_GET_QUESTION + " where cast (id as bigint) between 335 and 355", null);
                break;


        }

        if (c == null) {
            return null;
        }
        int indexQuestionId = c.getColumnIndex("id");
        int indexTitle = c.getColumnIndex("title");
        int indexAnswerA = c.getColumnIndex("answerA");
        int indexAnswerB = c.getColumnIndex("answerB");
        int indexAnswerC = c.getColumnIndex("answerC");
        int indexAnswerD = c.getColumnIndex("answerD");
        int indexTrueAnswer = c.getColumnIndex("trueAnswer");
        int indexSubAnswer = c.getColumnIndex("subAnswer");

        String title;
        String answerA;
        String answerB;
        String answerC;
        String answerD;
        String trueAnswer;
        String subAnswer;

        c.moveToFirst();

        ArrayList<QuestionModel> questionModels = new ArrayList<>();
        while (c.isAfterLast() == false) {
            title = c.getString(indexTitle);
            answerA = c.getString(indexAnswerA);
            answerB = c.getString(indexAnswerB);
            answerC = c.getString(indexAnswerC);
            answerD = c.getString(indexAnswerD);
            trueAnswer = c.getString(indexTrueAnswer);
            subAnswer = c.getString(indexSubAnswer);

            questionModels.add(new QuestionModel(title, answerA, answerB, answerC, answerD, trueAnswer, subAnswer));
            c.moveToNext();
        }
        c.close();
        closeDB();
        return questionModels;
    }

    public int updateLevelGrammar(GrammarModel grammarModel) {
        openDB();
        int index = grammarModel.getIndexGrammar();
        ContentValues values = new ContentValues();
        values.put("level", grammarModel.getLevel());
        values.put("id", grammarModel.getId());
        values.put("indexGrammar", grammarModel.getIndexGrammar());
        values.put("contentGrammar", grammarModel.getContentGrammar());
        int row = sqLiteDatabase.update(TABLE_NAME_GRAMMAR, values, "indexGrammar" + " = ?", new String[]{String.valueOf(index)});
        closeDB();
        return row;
    }


}

