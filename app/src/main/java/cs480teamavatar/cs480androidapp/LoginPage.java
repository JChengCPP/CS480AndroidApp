package cs480teamavatar.cs480androidapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
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
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(URL, USER, PASS);
                stmt = conn.createStatement();
                String sql;
                sql = "Select  FROM tutor, student WHERE ";
                stmt.executeUpdate(sql);
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

        @Override
        protected void onPostExecute(Integer rawr) {
            startActivity(new Intent(LoginPage.this, StartUp.class));
        }
    }
}