package myapplication.app1.scarnesdice;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView text1;
    String score1;
    View v1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        text1 = (TextView) findViewById(R.id.WinScreen);
        score1 =(bundle.getString("Score"));
        View v1 = (View) findViewById(R.id.AppScreen);
        Button button = (Button) findViewById(R.id.Button1);
        Intent intent;
        if (score1.equals("C"))
        {
            text1.setText("OOPS.. YOU LOSE..!! BETTER LUCK NEXT TIME..!!");
            v1.setBackgroundColor(Color.rgb(0,0,0));
        }
        else
        {
            text1.setText("CONGRATULATIONS.. YOU WIN..!!");
            v1.setBackgroundResource(R.drawable.cracker);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Func();
            }
        });
    }
    public void Func()
    {
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }


}
