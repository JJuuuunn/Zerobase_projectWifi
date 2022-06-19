package wifi_project;

import java.io.BufferedInputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//api를 데이터베이스에 저장
public class wifi_json {

    static String key = "717a49786b6469643832656d765066";

    public List<wifi_getset> wifi_read(int start) throws Exception {

        List<wifi_getset> wifigsList = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(readUrl(start));
        JSONObject json = (JSONObject) jsonObject.get("TbPublicWifiInfo");
        JSONArray array = (JSONArray) json.get("row");
        for (int i = 0; i < array.size(); i++) {
            JSONObject row = (JSONObject) array.get(i);
            String 관리번호 = (String) row.get("X_SWIFI_MGR_NO");
            String 자치구 = (String) row.get("X_SWIFI_WRDOFC");
            String 와이파이명 = (String) row.get("X_SWIFI_MAIN_NM");
            String 도로명주소 = (String) row.get("X_SWIFI_ADRES1");
            String 상세주소 = (String) row.get("X_SWIFI_ADRES2");
            String 설치위치 = (String) row.get("X_SWIFI_INSTL_FLOOR");
            String 설치유형 = (String) row.get("X_SWIFI_INSTL_TY");
            String 설치기관 = (String) row.get("X_SWIFI_INSTL_MBY");
            String 서비스구분 = (String) row.get("X_SWIFI_SVC_SE");
            String 망종류 = (String) row.get("X_SWIFI_CMCWR");
            String 설치년도 = (String) row.get("X_SWIFI_CNSTC_YEAR");
            String 실내외구분 = (String) row.get("X_SWIFI_INOUT_DOOR");
            String 접속환경 = (String) row.get("X_SWIFI_REMARS3");
            String X좌표= (String) row.get("LAT");
            String Y좌표= (String) row.get("LNT");
            String 작업일자= (String) row.get("WORK_DTTM");

            //            System.out.println("====================");
//            System.out.println("관리번호 = " + 관리번호);
//            System.out.println("자치구 = " + 자치구);
//            System.out.println("와이파이명 = " + 와이파이명);
//            System.out.println("도로명주소 = " + 도로명주소);
//            System.out.println("상세주소 = " + 상세주소);
//            System.out.println("설치위치 = " + 설치위치);
//            System.out.println("설치유형 = " + 설치유형);
//            System.out.println("설치기관 = " + 설치기관);
//            System.out.println("서비스구분 = " + 서비스구분);
//            System.out.println("망종류 = " + 망종류);
//            System.out.println("설치년도 = " + 설치년도);
//            System.out.println("실내외구분 = " + 실내외구분);
//            System.out.println("접속환경 = " + 접속환경);
//            System.out.println("X좌표 = " + X좌표);
//            System.out.println("Y좌표 = " + Y좌표);
//            System.out.println("작업일자 = " + 작업일자);
//            System.out.println("====================");


            wifi_getset wifigs = new wifi_getset();

            wifigs.setX_SWIFI_MGR_NO(관리번호);
            wifigs.setX_SWIFI_WRDOFC(자치구);
            wifigs.setX_SWIFI_MAIN_NM(와이파이명);
            wifigs.setX_SWIFI_ADRES1(도로명주소);
            wifigs.setX_SWIFI_ADRES2(상세주소);
            wifigs.setX_SWIFI_INSTL_FLOOR(설치위치);
            wifigs.setX_SWIFI_INSTL_TY(설치유형);
            wifigs.setX_SWIFI_INSTL_MBY(설치기관);
            wifigs.setX_SWIFI_SVC_SE(서비스구분);
            wifigs.setX_SWIFI_CMCWR(망종류);
            wifigs.setX_SWIFI_CNSTC_YEAR(설치년도);
            wifigs.setX_SWIFI_INOUT_DOOR(실내외구분);
            wifigs.setX_SWIFI_REMARS3(접속환경);
            wifigs.setLAT(X좌표);
            wifigs.setLNT(Y좌표);
            wifigs.setWORK_DTTM(작업일자);
            wifigsList.add(wifigs);
        }
        return wifigsList;
    }

    private static String readUrl(int start) throws Exception {
        BufferedInputStream reader = null;

        int end = start + 999;

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/"
                    + key + "/json/TbPublicWifiInfo/" + start + "/" + end);

            reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i = 0;
            byte[] b = new byte[4096];
            while ((i = reader.read(b)) != -1) {
                buffer.append(new String(b, 0, i));
            }
            return buffer.toString();

        } finally {
            if (reader != null) reader.close();

        }

    }

    public void register(wifi_getset wifigs) {

        String url = "jdbc:mariadb://localhost:3306/project_wifi";
        String dbUserId = "testuser1";
        String dbPassWord = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
//        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

            String sql = " insert into wifi_list (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2" +
                    "                                   , X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR" +
                    "                                   , X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    " values (?, ? ,?, ?, ?" +
                    "        ,?, ?, ? ,?, ?" +
                    "        ,? ,?, ?, ?, ?, ?) "
                    ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifigs.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2, wifigs.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3, wifigs.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4, wifigs.getX_SWIFI_ADRES1());
            preparedStatement.setString(5, wifigs.getX_SWIFI_ADRES2());
            preparedStatement.setString(6, wifigs.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7, wifigs.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8, wifigs.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9, wifigs.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10, wifigs.getX_SWIFI_CMCWR());
            preparedStatement.setString(11, wifigs.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12, wifigs.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13, wifigs.getX_SWIFI_REMARS3());
            preparedStatement.setString(14, wifigs.getLAT());
            preparedStatement.setString(15, wifigs.getLNT());
            preparedStatement.setString(16, wifigs.getWORK_DTTM());

            int affected = preparedStatement.executeUpdate();

            if ( affected > 0) {
                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

   
}

