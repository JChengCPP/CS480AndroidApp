package cs480teamavatar.cs480androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        String username = ((EditText) findViewById(R.id.login_text)).toString();
        String password = ((EditText) findViewById(R.id.login_password_text)).toString();

    }
}
