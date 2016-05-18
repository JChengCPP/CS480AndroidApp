package cs480teamavatar.cs480androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.EditText;
import android.os.AsyncTask;
import java.sql.*;


public class RegisterPage extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://db.zer0-one.net/tutorWeb_test";
    private static final String USER = "TeamAvatar";
    private static final String PASS = "Ie5Jaxae";
    private static RadioGroup radioGroup;
    private static RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
    }

    public void onClick(View view) {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selected_id = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selected_id);
        System.out.println(radioButton.getText());
        String username = ((EditText) findViewById(R.id.reg_email_text)).getText().toString();
        String password = ((EditText) findViewById(R.id.reg_password_text)).getText().toString();
        String radio = radioButton.getText().toString();
        System.out.println(radio);
        String[] storage = new String[3];
        storage[0] = username;
        storage[1] = password;
        storage[2] = radio;
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
                if (params[2].equals("Tutor")) {
                    System.out.println("Tutor button works!");
                    System.out.println("Testing sql insert command");
                    sql = "INSERT INTO tutor (tutorPassword, tutorEmail) " +
                            "VALUES ('" + params[1] + "', '" + params[0] + "')";
                    stmt.executeUpdate(sql);
                    System.out.println("YESSSSS INSERT COMMAND DIDNT FAIL MEEE");
                }
                else if (params[2].equals("Student")) {
                    System.out.println("Tutor button works!");
                    System.out.println("Testing sql insert command");
                    sql = "INSERT INTO student (studentPassword, studentEmail) " +
                            "VALUES ('" + params[1] + "', '" + params[0] + "')";
                    stmt.executeUpdate(sql);
                    System.out.println("YESSSSS INSERT COMMAND DIDNT FAIL MEEE");
                }
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

