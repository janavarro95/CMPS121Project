package com.example.hck3rz.hck3rz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import User.Email;
import User.Game;
import User.PostMan;


//Reference: https://www.youtube.com/watch?v=tLVz5wmNyrw

public class EmailInboxActivity extends AppCompatActivity {

    ListView inboxList;
    EmailInboxActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();
        setContentView(R.layout.activity_email_inbox);

        instance=this;

        inboxList=findViewById(R.id.LIST_VIEW_EMAIL_INBOX);
        InboxAdapter inboxAdapter=new InboxAdapter();
        inboxList.setAdapter(inboxAdapter);

        PostMan.addEmailToInbox(PostMan.getEmailByUniqueID("FirstEmail"),false);
        PostMan.addEmailToInbox(PostMan.getEmailByUniqueID("momLovesYou"),false);

        inboxList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Email e = Game.player.emails.get(position);

                Log.v("EmailClicked:",e.subject);

                EmailDisplayActivity.currentEmail=e;
                Intent i =new Intent(instance,EmailDisplayActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * Custom adapter class that allows us to interface emails.
     */
    public class InboxAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return Game.player.emails.size();
        }

        @Override
        public Object getItem(int position) {
            Email e = Game.player.emails.get(position);
            return e;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v= getLayoutInflater().inflate(R.layout.custom_list_view_email_inbox,null);
            ImageView image = v.findViewById(R.id.IMAGE_VIEW_EMAIL_ICON);
            TextView text = v.findViewById(R.id.TEXTVIEW_EMAIL_SUBJECT);

            Email email =(Email) getItem(position);

            image.setImageResource(email.image);
            text.setText(email.subject);


            return v;
        }

    }
}
