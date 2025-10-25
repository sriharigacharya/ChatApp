package com.example.chatapp.repository;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.chatapp.model.ChatGroup;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.views.GroupsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Repository {


    MutableLiveData<List<ChatGroup>> chatGroupMutableLiveData;

    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference groupReference;

    MutableLiveData<List<ChatMessage>> messagesLiveData;

    public Repository(){
        this.chatGroupMutableLiveData=new MutableLiveData<>();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();

        messagesLiveData=new MutableLiveData<>();
    }

    public MutableLiveData<List<ChatMessage>> getMessagesLiveData(String groupname) {
        groupReference=database.getReference().child(groupname);
        List<ChatMessage> messagesList=new ArrayList<>();
        groupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesList.clear();

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ChatMessage message=dataSnapshot.getValue(ChatMessage.class);
                    messagesList.add(message);
                }

                messagesLiveData.postValue(messagesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return messagesLiveData;
    }

    public void firebaseAnonymousAuth(Context context){
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Intent intent = new Intent(context, GroupsActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                });
    }

    public String getCurrentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
    }


    public MutableLiveData<List<ChatGroup>> getChatGroupMutableLiveData() {
        List<ChatGroup> groupList=new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groupList.clear();

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ChatGroup group=new ChatGroup(dataSnapshot.getKey());
                    groupList.add(group);
                }

                chatGroupMutableLiveData.postValue(groupList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return chatGroupMutableLiveData;
    }

    public void createNewChatGroup(String groupName){
        reference.child(groupName).setValue(groupName);
    }

    public void sendMessage(String messageText,String chatGroup){
        DatabaseReference reference1 = database.getReference(chatGroup);
        if(!messageText.trim().equals("")){
            ChatMessage msg=new ChatMessage(FirebaseAuth.getInstance().getUid(),
                    messageText,
                    System.currentTimeMillis());

            String randomKey=reference1.push().getKey();
            reference1.child(randomKey).setValue(msg);

        }
    }
}
