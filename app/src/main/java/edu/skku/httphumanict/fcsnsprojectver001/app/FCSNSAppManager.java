package edu.skku.httphumanict.fcsnsprojectver001.app;

import java.util.ArrayList;
import java.util.List;

import edu.skku.httphumanict.fcsnsprojectver001.app.activity.FCSNSLogoActivity;
import edu.skku.httphumanict.fcsnsprojectver001.app.activity.FCSNSRoomActivity;
import edu.skku.httphumanict.fcsnsprojectver001.dto.Room;
import edu.skku.httphumanict.fcsnsprojectver001.dto.User;

/**
 *
 * Created by ProLab on 2016-07-26.
 */
public class FCSNSAppManager {
    private static FCSNSAppManager instance = new FCSNSAppManager();
    private FCSNSAppManager() {
        // null
    }
    public static FCSNSAppManager getInstance() {
        return instance;
    }

    FCSNSRoomActivity roomActivity;
    FCSNSLogoActivity logoActivity;

    /* App 에 필요한 DTO 클래스*/
    User user;
    List<Room> rooms;

    public void init(){
        //
    }

    public FCSNSRoomActivity getRoomActivity() {
        return roomActivity;
    }
    public void setRoomActivity(FCSNSRoomActivity roomActivity) {
        this.roomActivity = roomActivity;
    }
    public FCSNSLogoActivity getLogoActivity() {
        return logoActivity;
    }
    public void setLogoActivity(FCSNSLogoActivity logoActivity) {
        this.logoActivity = logoActivity;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    //
}// end of class
