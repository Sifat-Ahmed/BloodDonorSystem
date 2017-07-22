package blooddonorsystem.nerddevstudio.blooddonorsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    Button register;
    Button login;
    Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        register = (Button) findViewById(R.id.registerBtn);
        login = (Button) findViewById(R.id.loginBtn);
        search = (Button) findViewById(R.id.searchBtn);

        NavigateTo();
    }

    public void NavigateTo()
    {
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentRegister = new Intent(MainActivity.this , Register_Activity.class);
                startActivity(intentRegister);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentLogin = new Intent(MainActivity.this , Login_Activity.class);
                startActivity(intentLogin);
            }
        });

        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentSearch = new Intent(MainActivity.this , Search_Activity.class);
                startActivity(intentSearch);
            }
        });
    }
}
