package com.example.english_words01;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    WordsDBHelper mDbHelper;
    private ListView list;
    private final static String TAG = "mytag";
    //判断横屏
    private Configuration mConfiguration;
    private int ori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        ori = mConfiguration.orientation; //获取屏幕方向
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //新增单词
                InsertDialog();
            }
        });


        //listView注册上下文菜单
        list = findViewById(R.id.list_words);
        //registerForContextMenu(list);
        //更新单词列表

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(ori == mConfiguration.ORIENTATION_LANDSCAPE){
                    TextView t3 = v.findViewById(R.id.text_words);
                    TextView t4 = v.findViewById(R.id.text_notes);
                    TextView t5 = v.findViewById(R.id.text_eg);
                    String words = t3.getText().toString();
                    Log.v(TAG,"prewords="+words);
                    String notes =  t4.getText().toString();
                    String eg =  t5.getText().toString();
                    TextView pre1 = findViewById(R.id.text_pre_words);
                    TextView pre2 = findViewById(R.id.text_pre_notes);
                    TextView pre3 = findViewById(R.id.text_pre_eg);
                    pre1.setText(words);
                    pre2.setText(notes);
                    pre3.setText(eg);
                }
            }
        });
        mDbHelper = new WordsDBHelper(this);
        Log.v(TAG,"更新列表前");
        refreshWordsList(list);
    }

    //新增对话框
    private void InsertDialog() {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.insert, null);
        new AlertDialog.Builder(this)
                .setTitle("新增单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strWord = ((EditText) tableLayout.findViewById(R.id.txtWord)).getText().toString();
                        String strMeaning = ((EditText) tableLayout.findViewById(R.id.txtMeaning)).getText().toString();
                        String strSample = ((EditText) tableLayout.findViewById(R.id.txtSample)).getText().toString();

                        //既可以使用Sql语句插入，也可以使用使用insert方法插入
                        // InsertUserSql(strWord, strMeaning, strSample);
                        Insert(strWord, strMeaning, strSample);

                        //单词已经插入到数据库，更新显示列表
                        refreshWordsList(list);


                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框
    }

    //修改对话框
    private void UpdateDialog(final String strId, final String strWord, final String strMeaning, final String strSample) {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.insert, null);
        ((EditText) tableLayout.findViewById(R.id.txtWord)).setText(strWord);
        ((EditText) tableLayout.findViewById(R.id.txtMeaning)).setText(strMeaning);
        ((EditText) tableLayout.findViewById(R.id.txtSample)).setText(strSample);
        new AlertDialog.Builder(this)
                .setTitle("修改单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strNewWord = ((EditText) tableLayout.findViewById(R.id.txtWord)).getText().toString();
                        String strNewMeaning = ((EditText) tableLayout.findViewById(R.id.txtMeaning)).getText().toString();
                        String strNewSample = ((EditText) tableLayout.findViewById(R.id.txtSample)).getText().toString();

                        //既可以使用Sql语句更新，也可以使用使用update方法更新

                        Update(strId, strWord, strNewMeaning, strNewSample);

                        //单词已经更新，更新显示列表
                        refreshWordsList(list);
                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框


    }

    //查找对话框
    private void SearchDialog() {
        final TableLayout tableLayout = (TableLayout) getLayoutInflater().inflate(R.layout.search, null);
        new AlertDialog.Builder(this)
                .setTitle("查找单词")//标题
                .setView(tableLayout)//设置视图
                //确定按钮及其动作
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String txtSearchWord = ((EditText) tableLayout.findViewById(R.id.txtSearchWord)).getText().toString();

                        //单词已经插入到数据库，更新显示列表
                        search_list(txtSearchWord);
                    }
                })
                //取消按钮及其动作
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()//创建对话框
                .show();//显示对话框

    }

    //更新列表
    private void refreshWordsList(ListView list) {
        if (mDbHelper != null) {
            Log.v(TAG,"mDbHelper不为空");
            ArrayList<Map<String,String>> items = getAllWords();
            Log.v(TAG,"items获取");
            SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.words_items,

                    new String[]{Words.Word._ID, Words.Word.COLUMN_NAME_WORD, Words.Word.COLUMN_NAME_MEANING, Words.Word.COLUMN_NAME_SAMPLE},
                    new int[]{R.id.text_id, R.id.text_words,R.id.text_notes,R.id.text_eg});
            Log.v(TAG,"配置Adapter");
            list.setAdapter(adapter);
            this.registerForContextMenu(list);
        }else{
            Log.v(TAG,"mDbHelper为空");
            Toast.makeText(this,"Not found", Toast.LENGTH_LONG).show();
        }
    }

    private void search_list(String strWord){
        ArrayList<Map<String,String>> items = getAllWords();
        if (items != null) {
            ArrayList<Map<String, String>> search_item = Search(strWord);
            if(search_item.size()>0){
                Log.v(TAG,"search_item.size()="+search_item.size());
                SimpleAdapter adapter = new SimpleAdapter(this, search_item, R.layout.words_items,
                        new String[]{Words.Word._ID, Words.Word.COLUMN_NAME_WORD},
                        new int[]{R.id.text_id, R.id.text_words});

                list.setAdapter(adapter);
            }else{
                Toast.makeText(this,"Not found",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("词条操作");
        menu.add(1,1,1,"删除");
        menu.add(1,2,1,"修改");
        menu.add(1,3,1,"更多");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
        item.getMenuInfo();
        int q = (int)info.id;
        Log.v(TAG,"q="+q);
        View v1 = list.getChildAt(q);
        TextView t2 = v1.findViewById(R.id.text_id);
        TextView t3 = v1.findViewById(R.id.text_words);
        TextView t4 = v1.findViewById(R.id.text_notes);
        TextView t5 = v1.findViewById(R.id.text_eg);
        String id = t2.getText().toString();
        String words = t3.getText().toString();
        String notes =  t4.getText().toString();
        String eg =  t5.getText().toString();
        switch(item.getItemId()){
            case 1:
                Delete(id);
                Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                refreshWordsList(list);
                break;
            case 2:
                UpdateDialog(id,words,notes,eg);
                Toast.makeText(MainActivity.this,"点击修改",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this,"打开网页",Toast.LENGTH_LONG).show();
                jumpBrowser("http://m.youdao.com/dict?le=eng&q="+words);
                break;
        }
        return super.onContextItemSelected(item);
    }

    //网页搜索跳转
    public  void jumpBrowser(String value) {
        /* 取得网页搜寻的intent */
        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
        search.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        search.putExtra(SearchManager.QUERY, value);
        final Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
        if (appData != null) {
            search.putExtra(SearchManager.APP_DATA, appData);
        }
        startActivity(search);
    }

    //获取数据库数据
    private ArrayList<Map<String, String>> getAllWords() {
        if(mDbHelper == null){
            Log.v(TAG,"mDbHelper == null");
            return null;
        }
        Log.v(TAG,"获取SQLiteDatabase db");
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] box = {
                Words.Word._ID,
                Words.Word.COLUMN_NAME_WORD,
                Words.Word.COLUMN_NAME_MEANING,
                Words.Word.COLUMN_NAME_SAMPLE
        };
        //排序
        String sortOrder = Words.Word.COLUMN_NAME_WORD+" DESC";
        Log.v(TAG,"获取Cursor c");
        Cursor c = db.query(
                Words.Word.TABLE_NAME,
                box,
                null,
                null,
                null,
                null,
                sortOrder
        );
        Log.v(TAG,"Cursor c");
        return Cursor_WordList(c);
    }
    //将cursor转为列表数据
    private ArrayList<Map<String, String>> Cursor_WordList(Cursor c) {
        ArrayList<Map<String,String>> result = new ArrayList<>();
        while(c.moveToNext()){
            Map<String,String>map = new HashMap<>();
            map.put(Words.Word._ID,String.valueOf(c.getString(c.getColumnIndex(Words.Word._ID))));
            map.put(Words.Word.COLUMN_NAME_WORD,c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_WORD)));
            Log.v(TAG,"WordName="+c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_WORD)));
            map.put(Words.Word.COLUMN_NAME_MEANING,c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_MEANING)));
            map.put(Words.Word.COLUMN_NAME_SAMPLE,c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE)));
            result.add(map);
        }
        Log.v(TAG,"result");

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String sql = "insert into  words(_id,word,meaning,sample) values(?,?,?,?)";

        Log.v(TAG,"插入成功");
        return result;
    }

    private ArrayList<Map<String, String>> Cursor_WordList2(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put(Words.Word._ID, String.valueOf(cursor.getString(cursor.getColumnIndex(Words.Word._ID))));
            Log.v(TAG,"search = "+Words.Word.COLUMN_NAME_WORD);
            map.put(Words.Word.COLUMN_NAME_WORD, cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_WORD)));
            Log.v(TAG,"search = "+Words.Word.COLUMN_NAME_WORD);
            result.add(map);
        }
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//菜单按钮
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            SearchDialog();
            return true;
        }else if(id == R.id.action_quit){
            System.exit(0);
        }else if(id == R.id.action_main){
            refreshWordsList(list);
        }else if(id == R.id.action_help){
            Toast.makeText(this,"关于我们XXXXXX",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getGUID(){
        // 创建 GUID 对象
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String a = uuid.toString();
        // 转换为大写
        a = a.toUpperCase();
        // 替换 -
        a = a.replaceAll("-", "");
        return a;
    }
    
    public void Insert(String strWord, String strMeaning, String strSample) {

        //Gets the data repository in write mode*/
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Words.Word._ID, getGUID());
        values.put(Words.Word.COLUMN_NAME_WORD, strWord);
        values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
        values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                Words.Word.TABLE_NAME,
                null,
                values);
    }

    public void Delete(String strId){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // 定义where子句
        String selection = Words.Word._ID + " = ?";

        // 指定占位符对应的实际参数
        String[] selectionArgs = {strId};

        // Issue SQL statement.
        db.delete(Words.Word.TABLE_NAME, selection, selectionArgs);
    }

    //模糊查询代码
    public ArrayList<Map<String, String>> Search(String strWord){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sql = "select * from words where word like ? order by word desc";
        Cursor c = db.rawQuery(sql, new String[]{"%" + strWord + "%"});//模糊查询

        return Cursor_WordList2(c);
    }

    public void Update(String strId, String strWord, String strMeaning, String strSample) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Words.Word.COLUMN_NAME_WORD, strWord);
        values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
        values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

        String selection = Words.Word._ID + " = ?";
        String[] selectionArgs = {strId};

        int count = db.update(
                Words.Word.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    //关闭数据库
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDbHelper.close();
    }
}