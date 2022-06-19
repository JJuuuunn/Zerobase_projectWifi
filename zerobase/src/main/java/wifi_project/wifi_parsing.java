package wifi_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Member;

public class wifi_parsing {
	//위경도 거리에 따른 row출력
	public List<wifi_getset> list(String LAT1, String LNT1) {

        List<wifi_getset> wifiGetSetList = new ArrayList<>();

        wifi_record(LAT1, LNT1);
        
        String url = "jdbc:mariadb://localhost:3306/project_wifi";
        String dbUserId = "testuser1";
        String dbPassWord = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

            String sql = " select round((6371*acos(cos(radians(" + LAT1 + "))*cos(radians(LAT))*cos(radians(LNT) "
                    + "        -radians(" + LNT1 + "))+sin(radians(" + LAT1 + "))*sin(radians(LAT)))),3)	AS distance "
                    + "    , X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2 "
                    + "    , X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR "
                    + "    , X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM "
                    + " from wifi_list "
                    + " order by distance "
                    + " limit 0,20 "
                    ;

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                wifi_getset wifigs = new wifi_getset();

                wifigs.setDistance(rs.getString("distance"));
                wifigs.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
                wifigs.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
                wifigs.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
                wifigs.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
                wifigs.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
                wifigs.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
                wifigs.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
                wifigs.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
                wifigs.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
                wifigs.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
                wifigs.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
                wifigs.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
                wifigs.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
                wifigs.setLAT(rs.getString("LAT"));
                wifigs.setLNT(rs.getString("LNT"));
                wifigs.setWORK_DTTM(rs.getString("WORK_DTTM"));

                wifiGetSetList.add(wifigs);

                System.out.println(wifigs.getDistance());
                System.out.println(wifigs.getX_SWIFI_WRDOFC());
                System.out.println(wifigs.getX_SWIFI_MAIN_NM());
                System.out.println(wifigs.getX_SWIFI_ADRES1());
                System.out.println(wifigs.getX_SWIFI_ADRES2());
                System.out.println(wifigs.getX_SWIFI_INSTL_FLOOR());
                System.out.println(wifigs.getX_SWIFI_INSTL_TY());
                System.out.println(wifigs.getX_SWIFI_INSTL_MBY());
                System.out.println(wifigs.getX_SWIFI_SVC_SE());
                System.out.println(wifigs.getX_SWIFI_CMCWR());
                System.out.println(wifigs.getX_SWIFI_CNSTC_YEAR());
                System.out.println(wifigs.getX_SWIFI_INOUT_DOOR());
                System.out.println(wifigs.getX_SWIFI_REMARS3());
                System.out.println(wifigs.getLAT());
                System.out.println(wifigs.getLNT());
                System.out.println(wifigs.getWORK_DTTM());
                System.out.println("=====");

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

        return wifiGetSetList;

    }
	
	//입력한 위경도(LAT,LNT) 저장
	public void wifi_record(String LAT, String LNT) {

        String url = "jdbc:mariadb://localhost:3306/project_wifi";
        String dbUserId = "testuser1";
        String dbPassWord = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

            String sql = " insert into wifi_history (LAT, LNT, WORK_DTTM) "
                    + " values (?, ?, now()) "
                    ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, LAT);
            preparedStatement.setString(2, LNT);

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

	//wifi_history에 있는 데이터 출력
	public List<wifi_getset> record_list() {

        List<wifi_getset> wifiGetSetList = new ArrayList<>();

        String url = "jdbc:mariadb://localhost:3306/project_wifi";
        String dbUserId = "testuser1";
        String dbPassWord = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

            String sql = " select row_number() over(order by WORK_DTTM ) as row, LAT, LNT, WORK_DTTM "
                    + "from wifi_history "
                    ;

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                wifi_getset wifigs = new wifi_getset();

                wifigs.setRow(rs.getString("row"));
                wifigs.setLAT(rs.getString("LAT"));
                wifigs.setLNT(rs.getString("LNT"));
                wifigs.setWORK_DTTM(rs.getString("WORK_DTTM"));

                wifiGetSetList.add(wifigs);

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

        return wifiGetSetList;

    }

	//wifi_history에서 해당 열 삭제

    public void withdraw(String date) {

    	System.out.println(date);
        String url = "jdbc:mariadb://localhost:3306/project_wifi";
        String dbUserId = "testuser1";
        String dbPassWord = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassWord);

            String sql = "delete " +
                    "from wifi_history " +
                    "where WORK_DTTM = ? "
                    ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date);

            int affected = preparedStatement.executeUpdate();

            if ( affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
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

        

