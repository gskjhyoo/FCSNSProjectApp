package edu.skku.httphumanict.fcsnsprojectver001.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import edu.skku.httphumanict.fcsnsprojectver001.R;
import edu.skku.httphumanict.fcsnsprojectver001.app.FCSNSAppManager;
import edu.skku.httphumanict.fcsnsprojectver001.dto.Dialog;
import edu.skku.httphumanict.fcsnsprojectver001.dto.Room;
import edu.skku.httphumanict.fcsnsprojectver001.dto.User;
import edu.skku.httphumanict.fcsnsprojectver001.util.UtilGJSON;
import edu.skku.httphumanict.fcsnsprojectver001.util.UtilHttp;
import edu.skku.httphumanict.fcsnsprojectver001.util.UtilSPrefer;

public class FCSNSLogoActivity extends AppCompatActivity implements FCSNSable {
    /** */
    final static int HDR_DELAY_FINISH = 1;
    final static int HDR_RCV_FROM_SERVER = 2;

    public static final String TAG = FCSNSLogoActivity.class.getName();

    /* 비동기 요청 사항 */
    // 로그인 수행
    public ServerLoginAsyncTask loginTask;
    // 방데이터 동기화 수행
    public ServerRoomDataAsyncTask roomDataTask;
    // 대화 데이터 동기화 수행
    public ServerDialogDataAsyncTask dialogDataTask;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        // 액티비티 설정
        FCSNSAppManager.getInstance().setLogoActivity(this);

        // 시간 측정 용
        long procTime = System.currentTimeMillis();

        // FCM
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        // 토큰 값 받기
        String strPushToken = FirebaseInstanceId.getInstance().getToken();
        // 트큰 값 저장

//
//
        ////////////////////////////////////////////////////////////////////////////////////////////////
        /*// 이종성 정보
        User savedUser = new User();
        savedUser.set_id("57c6464cfb6a8e141146a1b7");
        savedUser.setPhone("01029429511");
        savedUser.setName("이종성");
        savedUser.setBirthDate(new Date());
        savedUser.setSex(1);
        savedUser.setPushKey("test");
        savedUser.setRole("아들");
        */
        // 김진황 정보
        User savedUser = new User();
        savedUser.set_id("57c478fd641796041bca7936");
        savedUser.setPhone("01092342879");
        savedUser.setName("김진황");
        savedUser.setBirthDate(new Date());
        savedUser.setSex(1);
        savedUser.setPushKey("test");
        savedUser.setRole("아들");
        savedUser.setPushKey(strPushToken);
        // 방 정보 로딩


//        Toast.makeText(this.getApplicationContext(), user, ).show();
        UtilSPrefer.saveStrData(this, SP_KEY, SP_KEY_USER, savedUser.toJson());
        ////////////////////////////////////////////////////////////////////////////////////////////////

        // 1. 사용자 저장 정보 로딩
        String strSavedUserData = UtilSPrefer.getSharedPreference(this, SP_KEY).getString(SP_KEY_USER, null);
        Log.d(TAG, "사용자 정보 로딩: " + strSavedUserData);
        // 2.1 사용자 정보 없는 경우
        // 사용자 가입으로 진행 - 액티비티 전환
        /* // 처음 실행하는 경우
        if(strSavedUserData == null){
            Log.d('사용자 처음 실행. 사용자 가입으로 전환');
            Intent cStartIntnet = new Intent(this, FCSNSRegistActivity.class);
            startActivity(cStartIntnet);
            finish();
        }
        */
        // 2.2 사용자 정보가 있는 경우
        // 사용자 정보를 이용하여 로그인 수행
        User user = User.fromJson(strSavedUserData);
        loginTask = new ServerLoginAsyncTask();
        FCSNSAppManager.getInstance().setUser(user);


        // 로그인 수행
        loginTask.execute(user);

        Log.d(TAG, String.format("사용자 정보 출력: %s", FCSNSAppManager.getInstance().getUser()));

        // 3. 방 정보 로딩
        roomDataTask = new ServerRoomDataAsyncTask();
        dialogDataTask = new ServerDialogDataAsyncTask();

        Room[] rooms = UtilGJSON.getGson().fromJson(UtilSPrefer.getSharedPreference(this, SP_KEY).getString(SP_KEY_ROOMS, null), Room[].class);
        ArrayList<Room> altRooms = new ArrayList<>();
        if (rooms != null) {
            for (Room room : altRooms) {
                altRooms.add(room);
            }
        }
        // 현재 정보로 저장
        FCSNSAppManager.getInstance().setRooms(altRooms);
        // 서버측에 동기화 수행
        roomDataTask.execute(FCSNSAppManager.getInstance().getUser());
        // 이후 현재 갱신된 모든 정보 데이터로 저장
        dialogDataTask.execute(FCSNSAppManager.getInstance().getRooms());

        // 갱신된 방 대화 정보 저장
        UtilSPrefer.saveStrData(this, SP_KEY, SP_KEY_USER, UtilGJSON.getGson().toJson(FCSNSAppManager.getInstance().getRooms()));

        Log.d(TAG, String.format("대화 방 정보 출력: %s", FCSNSAppManager.getInstance().getRooms()));

//        String strSavedRoomsData =
        // 4.1 방 정보가 없는 경우
        // 서버측에 방 생성 정보 요청
        // 4.2 방 정보가 있는 경우
        // 대화 내용 업데이트 확인 요청

        // 해당 내용을 바탕으로 방 액티비티 수행


        procTime = procTime - System.currentTimeMillis();
        final FCSNSLogoActivity temp = this;
        if (procTime < 1000) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(temp, "방 화면으로 전환.", Toast.LENGTH_SHORT).show();
                    // 1초 이후 실행
                    Intent intent = new Intent(temp.getApplicationContext(), FCSNSRoomActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000 - procTime);
        } else {
            Intent intent = new Intent(temp.getApplicationContext(), FCSNSRoomActivity.class);
            startActivity(intent);
            finish();
        }

       /* Intent intent = new Intent(this, FCSNSRoomActivity.class);
        startActivity(intent);
        finish();*/


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }// end of onCreate

    @SuppressLint("HandlerLeak")
    public static Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HDR_RCV_FROM_SERVER:
                    // 서버로 부터 데이터를 수신한 경우
                    // 서버로부터 바로 응답을 받기 때문에 수신 부가 있어야 하는가?
                    break;
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FCSNSLogo Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://edu.skku.httphumanict.fcsnsprojectver001.app.activity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FCSNSLogo Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://edu.skku.httphumanict.fcsnsprojectver001.app.activity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     *
     */
    public class ServerLoginAsyncTask extends AsyncTask<User, Void, String> {
        @Override
        protected String doInBackground(User... params) {
            String strResData = null;
            try {
                User user = params[0];
                strResData = UtilHttp.getInstance().postURL(URL_APP_SERVER + "/user/login",
                        String.format("data={\"userId\":\"%s\", \"pushKey\":\"%s\"}",
                                user.get_id(), user.getPushKey()));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return strResData;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                // 비정상 실행된 경우
                Toast.makeText(FCSNSLogoActivity.this.getApplicationContext(), "로그인 실패. 서버 연결 실패", Toast.LENGTH_SHORT).show();
                return;
            }
            // parsing 작업
            LinkedTreeMap ltmParsingData = UtilGJSON.getGson().fromJson(result, LinkedTreeMap.class);
            if (ltmParsingData.get("state").equals("success")) {
                // 로그인 성공
                Toast.makeText(FCSNSLogoActivity.this.getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
            } else {
                // 로그인 실패
                Toast.makeText(FCSNSLogoActivity.this.getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }// end of class

    /**
     *
     */
    public class ServerRoomDataAsyncTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... params) {
            String strResData = null;
            User user = params[0];
            // 서버로 부터 사용자 방 정보 및 대화 정보 가져오기
            // 첫번째 인자 User 정보
            try {
                // 대화방 정보를 갱신한다.
                Gson gson = UtilGJSON.getGson();

                strResData = UtilHttp.getInstance().getURL(URL_APP_SERVER + String.format("/user/%s/room/find", "_id", user.get_id()));

                LinkedTreeMap resData = gson.fromJson(strResData, LinkedTreeMap.class);
                // 수신 정보 조회
                Room[] rooms = gson.fromJson(gson.toJson(resData.get("data")), Room[].class);
                ArrayList<Integer> altIndex = new ArrayList<>();
                for (int i = 0; i < rooms.length; i++) {
                    // 현재 저장되어 있는 room 객체 정보 갱신해야함.
                    boolean isEqual = false;
                    for (Room orgRoom : FCSNSAppManager.getInstance().getRooms()) {
                        if (orgRoom.get_id().equals(rooms[i].get_id())) {
                            isEqual = true;
                            orgRoom = rooms[i];
                            break;
                        }
                    }
                    if (!isEqual)
                        altIndex.add(i);
                }
                // 없는 인자들만 모아서 새로히 삽입 처리
                for (int idx : altIndex) {
                    FCSNSAppManager.getInstance().getRooms().add(rooms[idx]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }// end of inner class

    public class ServerDialogDataAsyncTask extends AsyncTask<List<Room>, Void, Void> {
        @Override
        protected Void doInBackground(List<Room>... params) {
            List<Room> lstRooms = params[0];
            Gson gson = UtilGJSON.getGson();
            String strResData = null;
            try {
                // 대화방 리스트를 이용하여 대화 갱신
                for (Room room : lstRooms) {
                    // room /room/:id/contained/dialogs
                    strResData = UtilHttp.getInstance().getURL(String.format(URL_APP_SERVER
                            + "/room/%s/contained/dialogs", "_id", room.get_id()));
                    LinkedTreeMap resData = gson.fromJson(strResData, LinkedTreeMap.class);
                    Dialog[] dialogs = gson.fromJson(gson.toJson(resData.get("data")), Dialog[].class);
                    // 현재 있는 갯수와 새로 받은 갯수 비교
                    //! 차후 서버로 부터 갱신 날짜와 갯수 비교를 통해 재요청을 수행하여 정보를 갱신하는 쪽으로 구현 해야 함.
                    //! 지금은 모든 대화를 서버를 통해 받고 이를 비교하는 것으로 함.
                    if (room.getDialogs().size() != dialogs.length) {
                        // 같지 않은 경우 갱신
                        room.setDialogs((ArrayList<Dialog>) Arrays.asList(dialogs));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}// end of class
