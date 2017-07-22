package blooddonorsystem.nerddevstudio.blooddonorsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Profile_activity extends AppCompatActivity {

    TextView nameView;
    TextView ageView;
    TextView phoneView;
    TextView locationView;
    TextView bloodView;

    private String id;
    private String name;
    private String age;
    private String phone;
    private String bloodGroup;
    private String location;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);

        nameView = (TextView) findViewById(R.id.nameView);
        ageView = (TextView) findViewById(R.id.ageView);
        phoneView = (TextView) findViewById(R.id.phoneView);
        locationView = (TextView) findViewById(R.id.locationView);
        bloodView = (TextView) findViewById(R.id.bloodView);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();

        name = data.get("Name").toString();
        age = data.get("Age").toString();
        phone = data.get("Phone").toString();
        bloodGroup = data.get("BloodGroup").toString();
        location = data.get("Location").toString();

        nameView.setText("Name: " + name);
        ageView.setText("Age: " + age);
        phoneView.setText("Phone: " + phone);
        locationView.setText("Location: " + location);
        bloodView.setText("Blood Group: " + bloodGroup);



    }
}
