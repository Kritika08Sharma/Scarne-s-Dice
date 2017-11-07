package myapplication.app1.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    //declaration of global variables!!
    int UserTurnScore = 0;
    int UserOverallScore = 0;
    int ComputerTurnScore = 0;
    int ComputerOverallScore = 0;
    int flag;
    Button Broll;
    Button Bhold;
    Button Breset;
    TextView tv;
    TextView tv1;
    ImageView iv;
    Context context;
    Toast toast;
    int CompNum;
    Intent intent;
    final int LENGTH_SHORT = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Broll = (Button) findViewById(R.id.Roll);
        Bhold = (Button) findViewById(R.id.Hold);
        Breset = (Button) findViewById(R.id.Reset);
        iv = (ImageView) findViewById(R.id.image);
        tv = (TextView) findViewById(R.id.textView);
        tv1 = (TextView) findViewById(R.id.textView1);
        final Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        //final Intent intent1 = new Intent(MainActivity.this,Main2Activity.class);
        context = getApplicationContext();
        toast = Toast.makeText(context,"User's turn",Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        },500);

        Broll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int Rno = RandomNum();
                if (Rno != 1) {
                    UserTurnScore += Rno;

                    //changing text in text view
                    tv.setText("Your Score : " + UserOverallScore + " Computer Score : " + ComputerOverallScore);
                    tv1.setText("Your Turn Score: " + UserTurnScore);

                } else {
                    UserTurnScore = 0;
                    tv.setText("Your Score : " + UserOverallScore + " Computer Score : " + ComputerOverallScore);
                    tv1.setText("Your Turn Score : " + UserTurnScore);
                    Bhold.setEnabled(false);  //disable the button "HOLD"
                    Broll.setEnabled(false);
                    context = getApplicationContext();
                    /*toast = Toast.makeText(context, "Computer's turn", Toast. LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    },500);*/



                    ComputerTurn();
                }

            }
        });
        Bhold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserOverallScore += UserTurnScore;
                UserTurnScore = 0;
                tv.setText("Your Score : "+UserOverallScore+" Computer Score : " +ComputerOverallScore );
                tv1.setText("Your Turn Score : "+UserTurnScore);
                if (UserOverallScore >= 100) {

                    Bhold.setEnabled(false);  //disable the button "HOLD"
                    Broll.setEnabled(false);  //disable the button "ROLL"
                    flag=1;
                    WinActivity(flag);
                    /*context = getApplicationContext();
                    toast = Toast.makeText(context,"U win",Toast.LENGTH_LONG);
                    toast.show();*/

                } else {
                    Bhold.setEnabled(false);  //disable the button "HOLD"
                    Broll.setEnabled(false);
                    context = getApplicationContext();
                    /*toast = Toast.makeText(context, "Computer's turn", Toast. LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    },500);*/



                    ComputerTurn();
                }  //calling functon to implement computer's turn
            }
        });
        Breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserTurnScore = 0;
                UserOverallScore = 0;
                ComputerTurnScore = 0;
                ComputerOverallScore = 0;
                tv.setText(" Your Score : 0 Computer Score : 0 ");
                tv1.setText("Your Total Score : 0");
                Bhold.setEnabled(true);  //disable the button "HOLD"
                Broll.setEnabled(true);  //disable the button "ROLL"
                context = getApplicationContext();
                toast = Toast.makeText(context,"User's Turn",Toast. LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                },500);


            }
        });

    }



    public int ComputerTurn() {
        int CompNum = 1 + (int) (Math.random() * 5);
        while (CompNum != 1) {
            ComputerTurnScore += CompNum;
            if (ComputerTurnScore >= 5) {
                ComputerOverallScore += ComputerTurnScore;
                ComputerTurnScore=0;
                tv.setText("Your Score : " + UserOverallScore + " Computer Score :" + ComputerOverallScore);
                tv1.setText("Your Turn Score: " + UserTurnScore);
                break;
            } else {
                CompNum = 1 + (int) (Math.random() * 5);
            }
        }
        if (CompNum == 1) {
            ComputerTurnScore = 0;
            tv.setText("Your Score : " + UserOverallScore + " Computer Score : " + ComputerOverallScore);
            tv1.setText("Your Turn Score : " + UserTurnScore);
        }
        if (ComputerOverallScore >=100) {
            Bhold.setEnabled(false);  //disable the button "HOLD"
            Broll.setEnabled(false);  //disable the button "ROLL"
            flag = 2;
            WinActivity(flag);
           /* context = getApplicationContext();
            toast = Toast.makeText(context, "U lose", Toast.LENGTH_LONG);
            toast.show();*/
            return 0;


        } else {

            context = getApplicationContext();
            toast = Toast.makeText(context, "User's turn", Toast. LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            },500);

            Bhold.setEnabled(true);  //disable the button "HOLD"
            Broll.setEnabled(true);  //disable the button "ROLL"
            return 0;

        }
    }

    public int RandomNum() {
        int NumOnRoll = 1 + (int) (Math.random() * 5);
        if (NumOnRoll == 1) {
            iv.setImageResource(R.drawable.dice1);
        } else if (NumOnRoll == 2) {
            iv.setImageResource(R.drawable.dice2);
        } else if (NumOnRoll == 3) {
            iv.setImageResource(R.drawable.dice3);
        } else if (NumOnRoll == 4) {
            iv.setImageResource(R.drawable.dice4);
        } else if (NumOnRoll == 5) {
            iv.setImageResource(R.drawable.dice5);
        } else {
            iv.setImageResource(R.drawable.dice6);
        }
        return NumOnRoll;

    }

    public void WinActivity(int value)
    {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        if (value==1)
        {

            intent.putExtra("Score","U");
            startActivity(intent);
        }
        else if(value==2)
        {
            intent.putExtra("Score","C");
            startActivity(intent);

        }
    }
}