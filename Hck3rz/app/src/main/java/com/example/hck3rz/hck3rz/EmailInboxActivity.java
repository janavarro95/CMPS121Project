package com.example.hck3rz.hck3rz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_inbox);

        inboxList=findViewById(R.id.LIST_VIEW_EMAIL_INBOX);
        InboxAdapter inboxAdapter=new InboxAdapter();
        inboxList.setAdapter(inboxAdapter);

        PostMan.addEmailToInbox(new Email(R.drawable.email,"HOT NEW EMAIL","My Mom","mom@aol.com",R.mipmap.ic_launcher,"Hello","momLovesYou"));

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
