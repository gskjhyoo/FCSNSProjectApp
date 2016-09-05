package edu.skku.httphumanict.fcsnsprojectver001.dto;

import java.util.ArrayList;
import java.util.Date;

import edu.skku.httphumanict.fcsnsprojectver001.util.UtilGJSON;

/**
 *
 * Created by sk on 2016-08-31.
 */
public class Relation implements FCSNSDTOable {
    String _id;
    ArrayList<FamilyShip> familyShips;

    /* JSON */
    public String toJson(){
        return UtilGJSON.toJSON(this);
    }
    public static Relation fromJson(String _strJSON){
        return (Relation) UtilGJSON.fromJSON(_strJSON, Relation.class);
    }

    class FamilyShip {
        //! 사용자 아이디로 변경 해야 함.
        // 사용자 측에서 population 을 이용하여 내부 정보를 끼워 송신할 예정
        // 동기화 시점은 LogoAcitivity에서 관계를 가져오는 것으로 시작
        String userId;
        String userPhone;
        Date regDate;

        public FamilyShip(String userId, String userPhone, Date regDate) {
            this.userId = userId;
            this.userPhone = userPhone;
            this.regDate = regDate;
        }

        public FamilyShip() {
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("FamilyShip{");
            sb.append("userId='").append(userId).append('\'');
            sb.append(", userPhone='").append(userPhone).append('\'');
            sb.append(", regDate=").append(regDate);
            sb.append('}');
            return sb.toString();
        }



        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public Date getRegDate() {
            return regDate;
        }

        public void setRegDate(Date regDate) {
            this.regDate = regDate;
        }
    }
}// end of class
