package cs480teamavatar.cs480androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainPage extends AppCompatActivity {
    private Student student;
    private Tutor tutor;
    private Intent intent;
    private String packType;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Bundle check = getIntent().getExtras();
        packType = check.getString("packtype");
        extras = new Bundle();
        if (packType.charAt(0) == 't') {
            tutor = check.getParcelable("tutorPackage");
            extras.putParcelable("tutorPackage", tutor);
            extras.putString("packtype", packType);
        }
        else {
            student = check.getParcelable("studentPackage");
            extras.putParcelable("studentPackage", student);
            extras.putString("packtype", packType);
        }
    }

    public void onClickProfile(View view) {
        intent = new Intent(MainPage.this, Profile.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void onSessionClick(View view) {
        intent = new Intent(MainPage.this, Session.class);
        extras = new Bundle();
        if (packType.charAt(0) == 't') {
            extras.putString("packtype", packType);
            extras.putParcelable("tutorPackage", tutor);
            intent.putExtras(extras);
            startActivity(intent);
        }
        else {
            extras.putString("packtype", packType);
            extras.putParcelable("studentPackage", student);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    public void onLogoutClick(View view) {
        intent = new Intent(MainPage.this, StartUp.class);
        startActivity(intent);
    }

}
