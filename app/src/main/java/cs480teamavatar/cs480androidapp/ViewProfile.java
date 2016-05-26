package cs480teamavatar.cs480androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

public class ViewProfile extends AppCompatActivity {
    private TextView status;
    private TextView name;
    private TextView email;
    private TextView address;
    private TextView subject;
    private TextView description;
    private TextView rate;
    private TextView hours;
    private Tutor tutor;
    private Student student;
    private String packType;
    private Bundle extras;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        status = (TextView) findViewById(R.id.vp_status_text_view);
        name = (TextView) findViewById(R.id.vp_name_text_view);
        email = (TextView) findViewById(R.id.vp_email_text_view);
        address = (TextView) findViewById(R.id.vp_address_text_view);
        subject = (TextView) findViewById(R.id.vp_subject_text_view);
        description = (TextView) findViewById(R.id.vp_description_text_view);
        rate = (TextView) findViewById(R.id.vp_rate_text_view);
        hours = (TextView) findViewById(R.id.vp_hours_text_view);
        extras = new Bundle();
        extras = getIntent().getExtras();
        packType = extras.getString("packtype");
        if (packType.equals("tutor")) {
            tutor = extras.getParcelable("tutorPackage");
            status.setText("Tutor");
            name.setText(tutor.getName());
            email.setText(tutor.getEmail());
            address.setText(tutor.getAddress());
            subject.setText(tutor.getSubject());
            description.setText(tutor.getDescription());
            rate.setText(tutor.getRateString());
            hours.setText(tutor.getHoursString());
            extras = new Bundle();
            extras.putString("packtype", packType);
            extras.putParcelable("tutorPackage", tutor);
        }
        else {
            TextView dLabel = (TextView) findViewById(R.id.vpl_description_text_view);
            TextView rLabel = (TextView) findViewById(R.id.vpl_rate_text_view);
            TextView hLabel = (TextView) findViewById(R.id.vpl_hours_text_view);
            dLabel.setVisibility(View.GONE);
            rLabel.setVisibility(View.GONE);
            hLabel.setVisibility(View.GONE);
            description.setVisibility(View.GONE);
            rate.setVisibility(View.GONE);
            hours.setVisibility(View.GONE);
            student = extras.getParcelable("studentPackage");
            status.setText("Student");
            name.setText(student.getName());
            email.setText(student.getEmail());
            address.setText(student.getAddress());
            subject.setText(student.getSubject());
            extras = new Bundle();
            extras.putString("packtype", packType);
            extras.putParcelable("studentPackage", student);
        }
    }

    public void onMainMenuClick(View view) {
        intent = new Intent(ViewProfile.this, MainPage.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void onBackClick(View view) {
        intent = new Intent(ViewProfile.this, TutorList.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
