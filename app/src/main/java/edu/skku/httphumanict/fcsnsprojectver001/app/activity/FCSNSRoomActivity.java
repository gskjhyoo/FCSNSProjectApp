package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import edu.skku.httphumanict.fcsnsprojectver001.R;
import edu.skku.httphumanict.fcsnsprojectver001.dto.Dialog;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class FCSNSRoomActivity extends AppCompatActivity {

    ServerSendDialogAsyncTask sendDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
    }

    public class ServerSendDialogAsyncTask extends AsyncTask<Dialog, Void, String>{

        @Override
        protected String doInBackground(Dialog... params) {

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            // 대화 내용 보이기

        }
    }// end of inner clss

}// end of class
