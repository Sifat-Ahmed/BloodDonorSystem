package blooddonorsystem.nerddevstudio.blooddonorsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Database.DatabaseHandler;
import Donor.CheckRegistrationData;
import Donor.Donor;

public class Register_Activity extends AppCompatActivity {

    Button submitBtn;
    EditText nameText;
    EditText ageText;
    EditText phoneText;
    EditText passwordText;
    EditText locationText;

    Spinner bloodGrpSpinner;

    private String name;
    private String age;
    private String phoneNumber;
    private String password;
    private String bloodGroup;
    private String location;

    // this variable will check if data is inserted successfully into the database
    private boolean registrationStatus;
    // this variable will check if user has given any invalid input
    private boolean dataCheckStatus;

    public Donor donor; // creating a Donor type variable
    public DatabaseHandler db; // creating a DatabaseHandler type variable

    private static int Error = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        db = new DatabaseHandler(this);

        // taking access from xml to Java
        submitBtn = (Button) findViewById(R.id.submitBtn);
        nameText = (EditText) findViewById(R.id.nameTxt);
        ageText = (EditText) findViewById(R.id.ageTxt);
        phoneText = (EditText) findViewById(R.id.phoneNumberTxt);
        passwordText = (EditText) findViewById(R.id.passwordTxt);
        locationText = (EditText) findViewById(R.id.locationText);
        bloodGrpSpinner = (Spinner) findViewById(R.id.bloodGroupSpinner);

        processData();
    }

    private void getData() // gets data input from user
    {
        name = nameText.getText().toString();
        age =  ageText.getText().toString() ;
        phoneNumber = phoneText.getText().toString();
        password = passwordText.getText().toString();
        location = locationText.getText().toString();
        bloodGroup = bloodGrpSpinner.getSelectedItem().toString();
    }

    private boolean checkData() // check data after taking user input for invalid input. Returns True if data is valid.
    {
        // checking if input data is valid
        dataCheckStatus = CheckRegistrationData.dataValidity(name , age , phoneNumber , password , bloodGroup , location);
        // if input data iis not valid then getting the Error number
        if(dataCheckStatus == false)
            Error = CheckRegistrationData.ERROR;
            // getting the error value so that we can specify the error
            // to the user. This is value will trace the filed where user has made an error
            // and give instructions to correct it.

        // creating a new donor if Data is valid
        else
            donor = new Donor(name , Integer.parseInt(age) , phoneNumber , password , bloodGroup, location );
        // dataCheckStatus var is used to check if data is valid or not
        // as per the value, next instruction will be executed
        // for false value, User will be prompted to insert data again
        // for true value, next instruction sendData() will be executed
        // sendData sends data to Database
        return dataCheckStatus;
    }
    // sends data to database. Returns true if data is added in database successfully
    private void sendData()
    {
        try
        {
            // tries to write data in database
            // if fails then registrationStatus is false, else true
            db.addDonor(donor);
            registrationStatus = true;
            Toast.makeText(Register_Activity.this, "Registration successful", Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e)
        {
            registrationStatus = false;
        }

        if(registrationStatus)
        {
            Intent intent = new Intent(Register_Activity.this , Profile_activity.class);
            intent.putExtra("Name" , name);
            intent.putExtra("Age" , age);
            intent.putExtra("Phone" , phoneNumber);
            intent.putExtra("BloodGroup" , bloodGroup);
            intent.putExtra("Location" , location);
            startActivity(intent);
        }
    }

    private void processData() // does everything in background
    {
        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getData(); // get inputs
                checkData(); // check inputs
                if(dataCheckStatus)
                    sendData();
                else
                    Log.d("Error: " , String.valueOf( CheckRegistrationData.ERROR ) );// ask user to give valid input or try again
            }
        });

    }
}
