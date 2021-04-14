package com.example.roomtext;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
    private static AppDataBase INSTANCE;
//创建这个类利用单例。有这个类，就有Instance，还可以避免有很多实例
    public static AppDataBase getDataBase(final Context context){
        //类第一次没有被创建
        if (INSTANCE == null){
            synchronized (AppDataBase.class){
                //
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,"database_happy")//name:数据库保存之后文件的文件名
                            //.allowMainThreadQueries()//允许在主线程中查询
                            //Add a migration strategy for the database.？？
                            //修改数据库架构的时候要更新版本号还有调整改变？
                            // Wipes and rebuilds instead of migrating
                            .fallbackToDestructiveMigration()
                            //.addCallback(sCallback);
                            .build();//返回实体来取得Dao的实体
                }
            }
        }
        return INSTANCE;
    }
//To delete all content and repopulate the database
// whenever the app is started
    private static RoomDatabase.Callback sCallback =
        new RoomDatabase.Callback(){

        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{

        private final UserDao mDao;
        String[] users = {"A","b","c"};

        PopulateDbAsync(AppDataBase db){
            mDao = db.userDao();
        }

        protected Void doInBackground(final Void... params){
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= users.length - 1; i++){
                User user = new User("##"+i,users[i]);
                mDao.insertUser(user);
            }

            return null;
        }
    }
}
