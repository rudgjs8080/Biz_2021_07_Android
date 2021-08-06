package com.callor.cacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.callor.cacao.adapter.ChattAdapter;
import com.callor.cacao.model.Chatt;
import com.callor.cacao.service.FirebaseServiceImplV1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txt_msg;
    private AppCompatButton btn_send;

    private RecyclerView chat_list_view;
    private ChattAdapter chattAdapter;
    private List<Chatt> chattList;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String nickname = preferences.getString("nick_name","익명");
        String alarm = preferences.getString("alarm","On");

        Log.d("닉네임",nickname);
        Log.d("알람",alarm);


        /**
         * custom 된 toolbar를 ActionBar로 설정하기 위한 코드
         */
        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        /**
         * 새로운 Activity가 열렸을 때
         * 이전 Activity(page)로 돌아가기 아이콘을 표시하기
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txt_msg = findViewById(R.id.text_msg);
        btn_send = findViewById(R.id.btn_send);

        chat_list_view = findViewById(R.id.chatt_list_view);

        chattList = new ArrayList<Chatt>();

        chattAdapter = new ChattAdapter(chattList);

        chat_list_view.setAdapter(chattAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        chat_list_view.setLayoutManager(layoutManager);

        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();

        dbRef = dbConn.getReference("chatting");

        ChildEventListener childEventListener = new FirebaseServiceImplV1(chattAdapter);

        dbRef.addChildEventListener(childEventListener);

        btn_send.setOnClickListener(view->{
            String msg = txt_msg.getText().toString();
            if(msg != null && !msg.isEmpty()){
                Chatt chattVO = new Chatt();
                chattVO.setMsg(msg);
                chattVO.setName("기경헌");

                dbRef.push().setValue(chattVO);
                txt_msg.setText("");
            }
        });

    }

    /**
     * Custom 한 ToolBar가 (main)activity에 적용될 때
     * setSupportActionBar() method가 실행될 때
     * event가 발생하고 자동으로 호출되는 method
     *
     * Toolbar를 사용하여 ActionBar를 Custom
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_tool_menu,menu);
        return true;
    }

    /**
     * ActionBar에 설정된 Option Menu의 특정한 항목(item)을
     * 클릭하면 호출되는 method
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_item = item.getItemId();
        if(menu_item == R.id.app_bar_search){
            Toast.makeText(this,"설정메뉴 클릭됨",Toast.LENGTH_SHORT).show();
            Intent setting_intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(setting_intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}