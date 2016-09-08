package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.util.ArrayList;
import java.util.Date;

import edu.skku.httphumanict.fcsnsprojectver001.util.UtilGJSON;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class Room implements FCSNSDTOable {
    String _id;
    ArrayList<Participant> participations;
    Date regDate;
    ArrayList<Notice> notices;
    ArrayList<Dialog> dialogs;
    ArrayList<Dialog> savedDialogs;

    public Room(ArrayList<Participant> participations, Date regDate, ArrayList<Notice> notices, ArrayList<Dialog> dialogs, ArrayList<Dialog> savedDialogs) {
        this.participations = participations;
        this.regDate = regDate;
        this.notices = notices;
        this.dialogs = dialogs;
        this.savedDialogs = savedDialogs;
    }

    public Room() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Room{");
        sb.append("participations=").append(participations);
        sb.append(", regDate=").append(regDate);
        sb.append(", notices=").append(notices);
        sb.append(", dialogs=").append(dialogs);
        sb.append(", savedDialogs=").append(savedDialogs);
        sb.append('}');
        return sb.toString();
    }

    /* JSON */
    public String toJson(){
        return UtilGJSON.toJSON(this);
    }
    public static Room fromJson(String _strJSON){
        return (Room) UtilGJSON.fromJSON(_strJSON, Room.class);
    }
    /* Getter & Setter*/
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public ArrayList<Participant> getParticipations() {
        return participations;
    }
    public void setParticipations(ArrayList<Participant> participations) {
        this.participations = participations;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public ArrayList<Notice> getNotices() {
        return notices;
    }
    public void setNotices(ArrayList<Notice> notices) {
        this.notices = notices;
    }
    public ArrayList<Dialog> getDialogs() {
        return dialogs;
    }
    public void setDialogs(ArrayList<Dialog> dialogs) {
        this.dialogs = dialogs;
    }
    public ArrayList<Dialog> getSavedDialogs() {
        return savedDialogs;
    }
    public void setSavedDialogs(ArrayList<Dialog> savedDialogs) {
        this.savedDialogs = savedDialogs;
    }

    public void setSync(Room _cRoom){
        set_id(_cRoom.get_id());
        setRegDate(_cRoom.getRegDate());
        setParticipations(_cRoom.getParticipations());
        setNotices(_cRoom.getNotices());
    }

    class Participant {
        String userId;
        String pushKey;

        public Participant(String userId, String pushKey) {
            this.userId = userId;
            this.pushKey = pushKey;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Participant{");
            sb.append("userId='").append(userId).append('\'');
            sb.append(", pushKey='").append(pushKey).append('\'');
            sb.append('}');
            return sb.toString();
        }

        public String getUserId() {
            return userId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public String getPushKey() {
            return pushKey;
        }
        public void setPushKey(String pushKey) {
            this.pushKey = pushKey;
        }
    }// end of inner class
}// end of class
