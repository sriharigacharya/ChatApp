package com.example.chatapp.views;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.databinding.ActivityGroupsBinding;
import com.example.chatapp.model.ChatGroup;
import com.example.chatapp.views.adapters.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

    private ArrayList<ChatGroup> chatGroupArrayList;
    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;
    private ActivityGroupsBinding binding;
    private com.example.chatapp.viewmodel.MyViewModel myViewModel;

    private Dialog chatGroupDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_groups);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_groups);

        myViewModel=new ViewModelProvider(this).get(com.example.chatapp.viewmodel.MyViewModel.class);

        recyclerView=binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel.getGroupList().observe(this, new Observer<List<ChatGroup>>() {
            @Override
            public void onChanged(List<ChatGroup> chatGroups) {
                chatGroupArrayList=new ArrayList<>();
                chatGroupArrayList.addAll(chatGroups);

                groupAdapter=new GroupAdapter(chatGroupArrayList);
                recyclerView.setAdapter(groupAdapter);
                groupAdapter.notifyDataSetChanged();
            }
        });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }


    public void showDialog(){
        chatGroupDialog=new Dialog(this);
        chatGroupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view= LayoutInflater.from(this).inflate(R.layout.dialog_layout,null);

        chatGroupDialog.setContentView(view);
        chatGroupDialog.show();
        Button submit=view.findViewById(R.id.submit_btn);
        EditText editText = view.findViewById(R.id.chat_group_edt);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupName=editText.getText().toString();

                Toast.makeText(GroupsActivity.this, "ChatGroupName:"+groupName, Toast.LENGTH_SHORT).show();
                myViewModel.createNewGroup(groupName);

                chatGroupDialog.dismiss();
            }
        });
    }
}