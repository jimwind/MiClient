package me.mi.miclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.up360.mi.Book;
import com.up360.mi.IBookManager;

import java.util.List;

public class BooManagerActivity extends Activity implements View.OnClickListener {
    private final String TAG = "BookManagerActivity ";
    private Button btnGetBookList;
    IBookManager bookManager;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bookManager = IBookManager.Stub.asInterface(iBinder);
            Log.e("jimwind", "book manager service is connected");

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private void getBookList(){
        if(bookManager == null){
            return;
        }
        try {
            List<Book> list = bookManager.getBookList();
            Log.e("jimwind", "query book list, list type:"+list.getClass().getCanonicalName());
            Log.e("jimwind", "query book list:"+list.toString());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        btnGetBookList = findViewById(R.id.get_book_list);
        btnGetBookList.setOnClickListener(this);

        Intent intent = new Intent();
        intent.setAction("com.up360.mi.services.BookManagerService");
        intent.setPackage("com.up360.mi");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get_book_list:{
                getBookList();
            }
        }
    }
}
