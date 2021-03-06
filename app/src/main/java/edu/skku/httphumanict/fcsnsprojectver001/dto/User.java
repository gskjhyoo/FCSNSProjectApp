package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.util.Date;

import edu.skku.httphumanict.fcsnsprojectver001.util.UtilGJSON;

/**
 *
 * Created by ProLab on 2016-07-26.
 */
public class User implements FCSNSDTOable {
    String _id;
    String phone;
    String name;
    Date birthDate;
    int sex;
    String pushKey;
    String role;
    Relation relation;

    public User(String _id, String phone, String name, Date birthDate, int sex, String pushKey, String role, Relation relation) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.pushKey = pushKey;
        this.role = role;
        this.relation = relation;
    }

    public User(String _id, String phone, String name, Date birthDate, int sex, String pushKey, String role) {
        this._id = _id;
        this.phone = phone;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.pushKey = pushKey;
        this.role = role;
        this.relation = null;
    }

    public User(){
        this(null,null,null,null,-1,null,null, null);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("_id='").append(_id).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", sex=").append(sex);
        sb.append(", pushKey='").append(pushKey).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", relation=").append(relation);
        sb.append('}');
        return sb.toString();
    }
    /* JSON */
    public String toJson(){
     return UtilGJSON.toJSON(this);
    }
    public static User fromJson(String _strJSON){
        return (User) UtilGJSON.fromJSON(_strJSON, User.class);
    }

    /*getter setter*/
    public Relation getRelation() {
        return relation;
    }
    public void setRelation(Relation relation) {
        this.relation = relation;
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getPushKey() {
        return pushKey;
    }
    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


}// end of class
