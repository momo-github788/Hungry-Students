package za.ac.cput.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.domain.PointsHistory;
import za.ac.cput.repository.interfaces.IPointsHistoryRepository;
import za.ac.cput.utils.DBUtils;

public class PointHistoryRepositoryImpl extends SQLiteOpenHelper implements IPointsHistoryRepository {

    private final Context context;

    public PointHistoryRepositoryImpl(@Nullable Context context){
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_POINTS_HISTORY_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_POINTS_HISTORY_QUERY);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PointsHistory create(PointsHistory pointsHistory){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DBUtils.COLUMN_TITLE, pointsHistory.getTitle());
        cv.put(DBUtils.COLUMN_POINTS_TRANSACTION_ID, pointsHistory.getTransactionId());
        cv.put(DBUtils.COLUMN_DESCRIPTION, pointsHistory.getDescription());
        cv.put(DBUtils.COLUMN_POINTS, pointsHistory.getPoints());

        long result = db.insert(DBUtils.POINTS_HISTORY_TABLE, null, cv);

        if(result == -1) {
            return null;
        }

        return pointsHistory;

    }

    @RequiresApi (api = Build.VERSION_CODES.O)
    public PointsHistory getPointsHistory(int pointsHistoryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBUtils.POINTS_HISTORY_TABLE +
                " WHERE " + DBUtils.COLUMN_POINTS_TRANSACTION_ID + " = ?" , new String[]{String.valueOf(pointsHistoryId)});

        if(cursor.moveToFirst()) {
            String title = cursor.getString(0);
            String transactionId = cursor.getString(1);
            String description = cursor.getString(2);
            int points = cursor.getInt(3);

            return new PointsHistory.Builder()
                    .setTransactionId(transactionId)
                    .setTitle(title)
                    .setDescription(description)
                    .setPoints(points)
                    .build();

        }
        cursor.close();
        return null;
    }

    @Override
    public List<PointsHistory> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<PointsHistory> pointsHistoryList = new ArrayList<>();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + DBUtils.POINTS_HISTORY_TABLE, null);

        while(cursor.moveToNext()){
            String title = cursor.getString(0);
            String transactionId = cursor.getString(1);
            String description = cursor.getString(2);
            int points = cursor.getInt(3);

            PointsHistory pointsHistory = new PointsHistory.Builder()
                    .setTitle(title)
                    .setTransactionId(transactionId)
                    .setDescription(description)
                    .setPoints(points)
                    .build();
            pointsHistoryList.add(pointsHistory);
        }
        cursor.close();
        return null;
    }
}
