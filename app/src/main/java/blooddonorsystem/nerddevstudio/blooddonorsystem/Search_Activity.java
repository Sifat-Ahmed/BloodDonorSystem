package blooddonorsystem.nerddevstudio.blooddonorsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import Donor.invalidDataCheck;

public class Search_Activity extends AppCompatActivity
{

    private EditText SearchLocationTxt;
    private Spinner SearchBloodGroupSpinner;
    private Button SearchSubmitBtn;

    private String location;
    private String bloodGroup;
    private boolean inputIsInValid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        SearchLocationTxt = (EditText) findViewById(R.id.searchLocationText);
        SearchBloodGroupSpinner = (Spinner) findViewById(R.id.searchBloodGroupSpinner);
        SearchSubmitBtn = (Button) findViewById(R.id.SearchSubmitBtn);

        eventHandler();
    }

    private void eventHandler()
    {
        SearchSubmitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getData();
            }
        });
    }

    private void getData()
    {
        location = SearchLocationTxt.getText().toString();
        bloodGroup = SearchBloodGroupSpinner.getSelectedItem().toString();
        inputIsInValid = invalidDataCheck.hasNums(location);

        if( inputIsInValid )
        {
            //SearchLocationTxt.setText("");
            SearchLocationTxt.setError("Location Invalid");
            //SearchLocationTxt.setHintTextColor(255);
        }
        else
        {
            Intent intent = new Intent(this, ShowSearchResults_Activity.class);
            intent.putExtra("Location" , location);
            intent.putExtra("Blood" , bloodGroup);
            startActivity(intent);
        }
    }

}
