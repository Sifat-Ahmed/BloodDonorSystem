package blooddonorsystem.nerddevstudio.blooddonorsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DatabaseHandler;

public class Login_Activity extends AppCompatActivity
{

    private Button submitBtn;
    private EditText phoneText;
    private EditText passwordText;

    private String phone;
    private String password;


    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        db = new DatabaseHandler(this);

        phoneText = (EditText) findViewById(R.id.LoginMobileText);
        passwordText = (EditText) findViewById(R.id.LoginPasswordText);
        submitBtn = (Button) findViewById(R.id.LoginSubmitbtn);

        SubmitButton();
    }

    private void getLoginData()
    {
        phone = phoneText.getText().toString();
        password = passwordText.getText().toString();
    }

    private void checkLoginData()
    {
        // connecting to the database to get data
        SQLiteDatabase database = db.getReadableDatabase();

        // Taking cursor position to matched Phone and Password to match login.
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_NAME + " WHERE "
                + DatabaseHandler.KEY_PHONE + "=? AND " + DatabaseHandler.KEY_PASSWORD + "=?" , new String[]{phone , password});

        // cursor.getCount() will count how many results are there in the database
        if(cursor.getCount() > 0 && cursor != null)
        {
            cursor.moveToFirst();

            // reading the value from database
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.KEY_AGE));
            String phone = cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_PHONE));
            String bloodGroup = cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_BLOODGROUP));
            String location = cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_LOCATION));
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHandler.KEY_ID));

            // Showing a Toast notification
            Toast.makeText(Login_Activity.this, "Login Success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Login_Activity.this , Profile_activity.class);
            // putting all the information the intent which will be caught
            // in the profile class. Format - "Variable name" , value
            intent.putExtra("Name" , name);
            intent.putExtra("Age" , age);
            intent.putExtra("Phone" , phone);
            intent.putExtra("BloodGroup" , bloodGroup);
            intent.putExtra("Location" , location);
            intent.putExtra("ID" , id);
            startActivity(intent);

            // stopping back button press to no to let go back to login page again
            finish();

        }
        else
        {
            Toast.makeText(Login_Activity.this, "Login Failed , Try Again", Toast.LENGTH_SHORT).show();
        }

    }

    private void SubmitButton()
    {
        submitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getLoginData();
                // if we keep Mobile and password blank and press next then nothing should happen.
                // user must put something in these text box.
                try
                {
                    checkLoginData();
                }
                catch (SQLiteException e)
                {
                    Log.d("Error : " , e.getStackTrace().toString());
                }
            }
        });
    }

}
