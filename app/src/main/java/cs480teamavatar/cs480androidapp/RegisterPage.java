package cs480teamavatar.cs480androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.TextView;
import java.sql.*;


public class RegisterPage extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://db.zer0-one.net/tutorWeb_test";
    private static final String USER = "TeamAvatar";
    private static final String PASS = "Ie5Jaxae";
    private static RadioGroup radioGroup;
    private static RadioButton radioButton;
    private TextView textout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
    }

    public void onClick(View view) {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selected_id = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selected_id);
        String username = ((EditText) findViewById(R.id.reg_email_text)).getText().toString();
        String password = ((EditText) findViewById(R.id.reg_password_text)).getText().toString();
        String radio = radioButton.getText().toString();
        String[] storage = new String[3];
        storage[0] = username;
        storage[1] = password;
        storage[2] = radio;
        textout = (TextView) findViewById(R.id.register_text_view);
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
                ResultSet rs;
                String sql, queryCheck;
                if (params[2].equals("Tutor")) {
                    queryCheck = "SELECT * FROM tutor WHERE tutorEmail = '" +
                            params[0] + "'";
                    rs = stmt.executeQuery(queryCheck);
                    if (!rs.next()) {
                        rs.close();
                        sql = "INSERT INTO tutor (tutorPassword, tutorEmail) " +
                                "VALUES ('" + params[1] + "', '" + params[0] + "')";
                        stmt.executeUpdate(sql);
                    }
                    else {
                        rs.close();
                        return -1;
                    }
                }
                else if (params[2].equals("Student")) {
                    queryCheck = "SELECT * FROM student WHERE studentEmail = '" +
                            params[0] + "'";
                    rs = stmt.executeQuery(queryCheck);
                    if (!rs.next()) {
                        rs.close();
                        sql = "INSERT INTO student (studentPassword, studentEmail) " +
                                "VALUES ('" + params[1] + "', '" + params[0] + "')";
                        stmt.executeUpdate(sql);
                    }
                    else {
                        rs.close();
                        return -1;
                    }
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

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 0) {
                startActivity(new Intent(RegisterPage.this, StartUp.class));
            }
            else
                textout.setText("Username already exists.");
        }
    }
}

