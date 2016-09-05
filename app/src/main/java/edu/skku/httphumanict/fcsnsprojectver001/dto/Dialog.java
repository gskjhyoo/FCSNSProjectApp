package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.util.ArrayList;
import java.util.Date;

import edu.skku.httphumanict.fcsnsprojectver001.util.UtilGJSON;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class Dialog implements FCSNSDTOable {
    String _id;
    String roomId;
    Date regDate;
    String content;
    String fromId;
    ArrayList<String> checked;

    public Dialog(String _id, String roomId, Date regDate, String content, String fromId, ArrayList<String> checkedIds) {
        this._id = _id;
        this.roomId = roomId;
        this.regDate = regDate;
        this.content = content;
        this.fromId = fromId;
        this.checked = checkedIds;
    }
    /**
     * 일반 방 대화 전달 객체
     * @param fromId 전달 사용자의 식별 값
     * @param roomId 방 식별 값
     * @param content 대화 내용
     */
    public Dialog(String fromId, String roomId, String content){
        this(null, roomId, null, content, fromId, null);
    }
    /**
     * default 생성자
     */
    public Dialog() {
        this(null, null, null, null, null, null);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Dialog{");
        sb.append("_id='").append(_id).append('\'');
        sb.append(", roomId='").append(roomId).append('\'');
        sb.append(", regDate=").append(regDate);
        sb.append(", content='").append(content).append('\'');
        sb.append(", fromId='").append(fromId).append('\'');
        sb.append(", checkedIds=").append(checked);
        sb.append('}');
        return sb.toString();
    }

    /* JSON */
    public String toJson(){
        return UtilGJSON.toJSON(this);
    }
    public static Dialog fromJson(String _strJSON){
        return (Dialog) UtilGJSON.fromJSON(_strJSON, Dialog.class);
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFromId() {
        return fromId;
    }
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }
    public ArrayList<String> getCheckedIds() {
        return checked;
    }
    public void setCheckedIds(ArrayList<String> checkedIds) {
        this.checked = checkedIds;
    }
}// end of class
