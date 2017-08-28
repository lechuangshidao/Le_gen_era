package com.Lechuang.app.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Lechuang.app.Bean.Friend;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 1 on 2016/1/13.
 */
public class RongYunFriendMeanageDao {
    private SqliteHelper helper = null;
    private SQLiteDatabase db;
    /**
     * 构造函数
     * 调用getWritableDatabase()或getReadableDatabase()方法后，会缓存SQLiteDatabase实例；
     * 因为这里是手机应用程序，一般只有一个用户访问数据库，所以建议不关闭数据库，保持连接状态。
     * getWritableDatabase()，getReadableDatabase的区别是当数据库写满时，调用前者会报错，调用后者不会，
     * 所以如果不是更新数据库的话，最好调用后者来获得数据库连接。
     * 对于熟悉SQL语句的程序员最好使用exeSQL(),rawQuery(),因为比较直观明了
     * @param context
     */
    public RongYunFriendMeanageDao(Context context){
        helper = new SqliteHelper(context);
        db = helper.getWritableDatabase();
    }
//    //实现对该数据库的增加
//    public void add(List<Friend> friend){
//        db.beginTransaction();  //开始事务
//        try {
//            for (Friend person : friend) {
//                //此方法推荐使用
//                String sqlStr="insert into friendinfo(userid,name,imagehead)values(?,?,?)";
//                //执行SQL语句
//                db.execSQL(sqlStr,new Object[]{person.getUserid(),person.getName(),
//                        person.getUserIcon()});
//            }
//            db.setTransactionSuccessful();  //设置事务成功完成
//        } finally {
//            db.endTransaction();    //结束事务
//        }
//    }
    //实现对该数据库的增加
    public void addFriend(Friend friend){
        //此方法推荐使用
        String sqlStr="insert into friendinfo(userid,name,imagehead)values(?,?,?)";
        //执行SQL语句
        db.execSQL(sqlStr,new Object[]{friend.getUserid(),friend.getName(),
                friend.getUserIcon()});
        //关闭数据库
//        db.close();
    }
    //实现对数据库的删除
    public void deleteFriend(String userid){
        //获取数据库操作的实例
        SQLiteDatabase db=helper.getWritableDatabase();
        //创建SQL字符串
        String sqlStr="delete from friendinfo where userid=?";
        db.execSQL(sqlStr,new String[]{userid});
        //关闭数据库
        db.close();
    }
    //实现对数据库的修改
    public void updateFriend(Friend friend){
        //获取数据库的操作实例
        SQLiteDatabase db=helper.getWritableDatabase();
        //创建SQl字符串
        String sqlStr="update friendinfo set userid=?where name=?,imagehead=?";
        //执行SQL语句
        db.execSQL(sqlStr,new Object[]{friend.getUserid(),friend.getName(),
                friend.getUserIcon()});
        //关闭数据库
//        db.close();
    }
    //实现对数据库的查询
    public List<Friend> selectFriend(){
        //创建集合
        List<Friend> persons=new ArrayList<Friend>();
        //获取数据库操作实例
        SQLiteDatabase db=helper.getReadableDatabase();
        //创建Cursor对象
        Cursor cursor=null;
        try {
            //正序排列
            cursor = db.rawQuery("select * from friendinfo",null);
            //倒序排列
//            cursor = db.rawQuery("select * from friendinfo order by userid desc",null);
            while(cursor.moveToNext()){
//
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String userid=cursor.getString(cursor.getColumnIndex("userid"));
                String userIcon=cursor.getString(cursor.getColumnIndex("imagehead"));
                //创建Person对象
                Friend p=new Friend(userid,name,userIcon);
                //将创建出来的Person对象添加到集合中去
                persons.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //关闭相应的资源
            if(cursor!=null){
                cursor.close();
            }
//            if(db!=null){
//                db.close();
//            }
        }
        return persons;
    }
    //实现对数据库的查询
    public List<Friend> queryFriend(){
        //创建集合
        List<Friend> persons=new ArrayList<Friend>();
        //获取数据库操作实例
        SQLiteDatabase db=helper.getReadableDatabase();
        //创建Cursor对象
        Cursor cursor=null;
        try {
            //正序排列
            cursor = db.rawQuery("select * from friendinfo",null);
            //倒序排列
//            cursor = db.rawQuery("select * from friendinfo order by userid desc",null);
            while(cursor.moveToNext()){
//
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String userid=cursor.getString(cursor.getColumnIndex("userid"));
                String userIcon=cursor.getString(cursor.getColumnIndex("imagehead"));
                //创建Person对象
                Friend p=new Friend(userid,name,userIcon);
                //将创建出来的Person对象添加到集合中去
                persons.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //关闭相应的资源
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
        }
        return persons;
    }
    public void closeDB() {
        db.close();
    }
}
