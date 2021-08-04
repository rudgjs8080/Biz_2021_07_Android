package com.rudgjs8080.hellochatt.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.rudgjs8080.hellochatt.adapter.ChattAdapter;
import com.rudgjs8080.hellochatt.model.Chatt;

public class FirebaseServiceImplV1 implements ChildEventListener {

    private ChattAdapter adapter;

    public FirebaseServiceImplV1(ChattAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        Chatt chattVO = snapshot.getValue(Chatt.class);
        adapter.addChatList(chattVO);
        /**
         * Exception이 발생할 것이다
         * 어디서 왜 발생할까?
         * NullPointException이 발생할 것이다 어디서?
         * 
         * .addChatList를 할 때 NullPointException이 뜬다
         * adapter를 선언했지 초기화를 하지 않아서 그렇다
         * 
         */


    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
