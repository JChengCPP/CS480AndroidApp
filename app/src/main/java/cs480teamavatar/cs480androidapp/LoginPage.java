package cs480teamavatar.cs480androidapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import java.sql.*;

public class LoginPage extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://db.zer0-one.net/tutorWeb_test";
    private static final String USER = "TeamAvatar";
    private static final String PASS = "Ie5Jaxae";
    private EditText email;
    private EditText pass;
    private TextView textOut;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void onClick(View view) {
        email = (EditText) findViewById(R.id.login_email_text);
        pass = (EditText) findViewById(R.id.login_password_text);
        String username = email.getText().toString();
        String password = pass.getText().toString();
        String[] storage = new String[2];
        storage[0] = username;
        storage[1] = password;
        textOut = (TextView) findViewById(R.id.login_text_view);
        new LoginUser().execute(storage);
    }

    private class LoginUser extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            Connection conn = null;
            Statement stmt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(URL, USER, PASS);
                stmt = conn.createStatement();
                String queryCheck = "SELECT * FROM student WHERE (studentEmail = '" + params[0] +
                        "' AND studentPassword = '" + params[1] + "')";
                ResultSet rs = stmt.executeQuery(queryCheck);
                while (rs.next()){
                    System.out.println(rs.getInt("studentID") + " " + params[0] + " " + params[1]);
                }
                /*String queryCheck;
                ResultSet rs1, rs2;
                queryCheck = "SELECT * FROM student WHERE studentEmail = '" +
                        params[0] + "' AND studentPassWord = '" + params[1] + "'";
                rs1 = stmt.executeQuery(queryCheck);
                if (!rs1.next()) {
                    System.out.println("In rs statement for some reason");
                    /*
                    Student student = new Student(rs.getInt("studentID"),
                            rs.getString("studentName"), rs.getString("studentEmail"),
                            rs.getString("studentAddress"), rs.getString("studentSubject"));
                    rs.close();
                    intent = new Intent(LoginPage.this, MainPage.class);
                    intent.putExtra("studentParcelable", student);

                    rs1.close();
                    intent = new Intent(LoginPage.this, MainPage.class);
                    String s_email = rs1.getString("studentEmail");
                    intent.putExtra("studentEmail", s_email);
                }/*
                queryCheck = "SELECT * FROM tutor WHERE tutorEmail = '" + params[0] +
                        "' AND tutorPassword = '" + params[1] + "'";
                stmt = null;
                rs1.close();
                rs2 = stmt.executeQuery(queryCheck);
                if(!rs2.next()) {
                    rs2.close();
                    Tutor tutor = new Tutor(rs2.getInt("tutorID"), rs2.getString("tutorName"),
                            rs2.getString("tutorEmail"), rs2.getString("tutorAddress"),
                            rs2.getString("tutorSubject"), rs2.getString("tutorDescription"),
                            rs2.getDouble("tutorRatePerHour"), rs2.getInt("tutortotalHours"));
                    rs2.close();
                    intent = new Intent(LoginPage.this, MainPage.class);
                    intent.putExtra("tutorParcelable", tutor);
                }

                else {
                    rs1.close();
                    return -1;
                }*/
                stmt.close();
                conn.close();
                return -1;
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer rawr) {
            if (rawr == 0)
                startActivity(intent);
            else {
                email.setText("");
                pass.setText("");
                textOut.setText("Incorrect email or password");
            }
        }
    }
}