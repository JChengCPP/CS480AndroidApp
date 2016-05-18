package cs480teamavatar.cs480androidapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import java.sql.*;

public class LoginPage extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://db.zer0-one.net/tutorWeb_test";
    private static final String USER = "TeamAvatar";
    private static final String PASS = "Ie5Jaxae";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void onClick(View view) {
        String username = ((EditText) findViewById(R.id.reg_email_text)).getText().toString();
        String password = ((EditText) findViewById(R.id.reg_password_text)).getText().toString();
        String[] storage = new String[2];
        storage[0] = username;
        storage[1] = password;
        new RegisterUser().execute(storage);

    }

    private class RegisterUser extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            Connection conn = null;
            Statement stmt = null;
            try {
                System.out.println("Registering driver");
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                System.out.println("Registered driver");
                System.out.println("Accessing database");
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Inside database!");
                stmt = conn.createStatement();
                String sql;
                System.out.println("Testing tutor button");
                System.out.println("Tutor button works!");
                System.out.println("Testing sql insert command");
                sql = "INSERT INTO tutor (tutorPassword, tutorEmail) " +
                        "VALUES ('" + params[1] + "', '" + params[0] + "')";
                stmt.executeUpdate(sql);
                System.out.println("YESSSSS INSERT COMMAND DIDNT FAIL MEEE");
                stmt = conn.createStatement();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}