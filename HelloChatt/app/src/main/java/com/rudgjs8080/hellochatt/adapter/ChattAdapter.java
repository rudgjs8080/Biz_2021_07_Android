package com.rudgjs8080.hellochatt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rudgjs8080.hellochatt.R;
import com.rudgjs8080.hellochatt.model.Chatt;

import java.util.List;

public class ChattAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
/**
RecyclerView.Adapter 구현한 클래스
RecyclerView 에 데이터를 표현하고자 할 때 사용하는 Helper 클래스(도와주는 도구 클래스)
 */

    private List<Chatt> chattList;

    /**
     * 외부에서 chatt 데이터 아이템을 List를 추가하고
     * 추가된 List는 RecyclerView를 통해서 화면에 다시 그려지게 된다
     * @param chatt
     */
    public void addChatList(Chatt chatt){
        // 리스트에 추가하기
        chattList.add(chatt);

        // RecyclerView에게 chattList가 변화 되었으니
        // 다시 화면에 그려라 라고 지시하기
        // chattList의 끝(size() -1 위치에 값이 추가 되었으니)에
        // 값이 추가되었으니 다시 그려라
        notifyItemInserted(chattList.size() -1);

    }

    /**
     * RecyclerView가 화면에 그릴 데이터들을 전달받을 통로
     * @param chattList
     */
    public ChattAdapter(List<Chatt> chattList) {
        this.chattList = chattList;
    }

    /**
     * chat_item.xml 파일을 읽어서 한개의 아이템을 화면에 그릴 준비
     *
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /**
         * LayoutInflater.from().inflate(chatt_item)
         *
         * chatt_item.xml 파일은 한개의 파일이 화면 전체를 구성하지 않고
         * 여기에서는 RecyclerView 내부에 각각 데이터 아이템을 그릴 도구로 사용이 된다
         * 이러한 layout은 setContentView()를 사용하지 않고
         * LayoutInflater.inflate() 함수를 사용하여 만든다
         */
        View item_layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatt_item,parent,false);

        ChattViewHoler viewHolder = new ChattViewHoler(item_layout);

        return viewHolder;
    }

    // chattList에서 한개의 데이터를 getter하여
    // chat_item.xml 파일과 함께 rendering을 할...?
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // 전체 chattList에서 현재 화면에 그릴 item 추출하기
        Chatt chat = chattList.get(position);

        ChattViewHoler chattViewHoler = (ChattViewHoler) holder;
        chattViewHoler.item_name.setText(chat.getName());
        chattViewHoler.item_msg.setText(chat.getMsg());


    }

    /**
    RecyclerView가 데이터들을 화면에 표시할 때
    참조하는 함수
    @return
     */
    @Override
    public int getItemCount() {

        return chattList == null ? 0 : chattList.size();

    }

    // class 내부에 in class 선언
    public static class ChattViewHoler extends RecyclerView.ViewHolder{

        private TextView item_name;
        private TextView item_msg;

        // 각 데이터를 표현하기 위한
        // chat_item.xml의 view 객체(두개의 TextView)를 초기화 하는 method
        // 초기화 해서 Adapter한테 전달
        public ChattViewHoler(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_msg = itemView.findViewById(R.id.item_msg);


        }
    }


}
