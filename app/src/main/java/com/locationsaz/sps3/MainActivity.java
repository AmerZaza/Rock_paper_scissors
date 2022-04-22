package com.locationsaz.sps3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Integer roundSelectedCount = 0;
    public   String player1Name = "Player";
    Player player1  ;
    Player player2 ;
    int p1Result = 0;
    int p2Result = 0;
    Game game;
    int gameId = 0;
    float durat = 0.5f;
    ArrayList<Round> rounds = new ArrayList<Round>();
    ArrayList<Element> elements = new ArrayList<Element>();

    private ImageView imgVpapierP1 ;
    private  ImageView imgVpapierP2 ;
    private  ImageView imgVschareP1;
    private  ImageView imgVschareP2 ;
    private  ImageView imgVsteinP1 ;
    private  ImageView imgVsteinP2 ;
    private  ImageView imgVmain ;
    private TextView lablelP1squre ;
    private  TextView lablelP2squre;
    private  TextView labelRaund ;
    private  TextView lablelP1Name ;
    private TextView lablelP2Name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Get the transferred data from NewGameActivity.
        Intent intent = getIntent();
        player1Name = intent.getStringExtra("PlayerName");
        String roundSelectedCountString = intent.getStringExtra("RoundsCount");
        //System.out.println(roundSelectedCountString);
        if(roundSelectedCountString != null){
          roundSelectedCount = Integer.valueOf(roundSelectedCountString);
        }

        // Creat the Elements
        Element papier = new Element("Papier");
        Element schere = new Element("Schere");
        Element stein = new Element("Stein");

        papier.setStronger(schere);
        papier.setWeaker(stein);

        schere.setStronger(stein);
        schere.setWeaker(papier);

        stein.setStronger(papier);
        stein.setWeaker(schere);

        elements.add(papier);
        elements.add(schere);
        elements.add(stein);

        imgVpapierP1 = findViewById(R.id.imgVpapierP1);
        imgVpapierP2 = findViewById(R.id.imgVpapierP2);
        imgVschareP1 = findViewById(R.id.imgVschareP1);
        imgVschareP2 = findViewById(R.id.imgVschareP2);
        imgVsteinP1 = findViewById(R.id.imgVsteinP1);
        imgVsteinP2 = findViewById(R.id.imgVsteinP2);
        imgVmain = findViewById(R.id.imgVmain);
        lablelP1squre = findViewById(R.id.lablelP1squre);
        lablelP2squre = findViewById(R.id.lablelP2squre);
        labelRaund = findViewById(R.id.labelRaund);
        lablelP1Name = findViewById(R.id.lablelP1Name);
        lablelP2Name = findViewById(R.id.lablelP2Name);

        // Start Without Element in first use
        disableAll();

        // If the Start com from Strtnew in Menu get elements
        if(roundSelectedCount !=0){
            getElements();
            startNew();
        }

        // Get Lables first Start
        lablelP1squre.setText("0");
        lablelP2squre.setText("0");

        // Papier
        imgVpapierP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element player1SelectElement = new Element("Papier");

                // Get Player 2 (Computer select)
                int random =  (int)(Math.random() * 3) ;
                Element player2SelectElement = elements.get(random);

                // to Move Player1 Element
                imgEffect(imgVpapierP1,200,200,durat);

                // To move Computer selected img
                if(player2SelectElement.getElementName().equalsIgnoreCase("Papier")){
                    imgEffect(imgVpapierP2,180,-150,durat);
                }else if(player2SelectElement.getElementName().equalsIgnoreCase("Schere")){
                    imgEffect(imgVschareP2,0,-150,durat);
                }else if(player2SelectElement.getElementName().equalsIgnoreCase("Stein")){
                    imgEffect(imgVsteinP2,-180,-150,durat);
                }


                Round round = new Round();
                // Get Winner
                Player winner = round.getTheWinner(player1,player2,player1SelectElement,player2SelectElement);
                rounds.add(new Round(game, player1SelectElement, player2SelectElement,winner));

                // Update the result
                updateresultView(rounds,player1,player2);

                // End Game
                if(rounds.size() >= roundSelectedCount){
                    endGame();
                }
            }
        });

        // Schere
        imgVschareP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element player1SelectElement = new Element("Schere");

                // Get Player 2 (Computer select)
                int random =  (int)(Math.random() * 3) ;
                Element player2SelectElement = elements.get(random);

                // to Move Player1 Element
                imgEffect(imgVschareP1,180,180,durat);

                // To move Computer selected img
                if(player2SelectElement.getElementName().equalsIgnoreCase("Papier")){
                    imgEffect(imgVpapierP2,180,-150,durat);
                }else if(player2SelectElement.getElementName().equalsIgnoreCase("Schere")){
                    imgEffect(imgVschareP2,0,-150,durat);
                }else if(player2SelectElement.getElementName().equalsIgnoreCase("Stein")){
                    imgEffect(imgVsteinP2,-180,-150,durat);
                }


                Round round = new Round();
                // Get Winner
                Player winner = round.getTheWinner(player1,player2,player1SelectElement,player2SelectElement);
                rounds.add(new Round(game, player1SelectElement, player2SelectElement,winner));

                // Update the result
                updateresultView(rounds,player1,player2);

                // End Game
                if(rounds.size() >= roundSelectedCount){
                    endGame();
                }
            }
        });

        // Stein
        imgVsteinP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element player1SelectElement = new Element("Stein");

                // Get Player 2 (Computer select)
                int random =  (int)(Math.random() * 3) ;
                Element player2SelectElement = elements.get(random);

                // to Move Player1 Element

                imgEffect(imgVsteinP1,180,180,durat);
                // To move Computer selected img
                if(player2SelectElement.getElementName().equalsIgnoreCase("Papier")){
                    imgEffect(imgVpapierP2,180,-150,durat);
                }else if(player2SelectElement.getElementName().equalsIgnoreCase("Schere")){
                    imgEffect(imgVschareP2,0,-150,durat);
                }else if(player2SelectElement.getElementName().equalsIgnoreCase("Stein")){
                    imgEffect(imgVsteinP2,-180,-150,durat);
                }


                Round round = new Round();
                // Get Winner
                Player winner = round.getTheWinner(player1,player2,player1SelectElement,player2SelectElement);
                rounds.add(new Round(game, player1SelectElement, player2SelectElement,winner));

                // Update the result
                updateresultView(rounds,player1,player2);

                // End Game
                if(rounds.size() >= roundSelectedCount){
                    endGame();
                }
            }
        });


    }// End onCreat method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement    action_new_game
        if(id == R.id.action_new_game){
            Intent newGame = new Intent(MainActivity.this, NewGameActivity.class);
           startActivity(newGame);
            return true;
        }else if(id == R.id.action_about){
            return true;
        }else if(id == R.id.action_exit ){
            finish();
            moveTaskToBack(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */


    public void endGame(){

        imgVpapierP1.setVisibility(View.INVISIBLE);
        imgVschareP1.setVisibility(View.INVISIBLE);
        imgVsteinP1.setVisibility(View.INVISIBLE);

        imgVpapierP2.setVisibility(View.INVISIBLE);
        imgVschareP2.setVisibility(View.INVISIBLE);
        imgVsteinP2.setVisibility(View.INVISIBLE);
        rounds.clear();

    }

    public void updateresultView(ArrayList<Round> rounds, Player p1 , Player p2){
        p1Result = 0;
        p2Result = 0;
        for (int r = 0 ; r < rounds.size(); r++ ){

            if(rounds.get(r).getWinner().equals(p1)){
                p1Result +=1;
                lablelP1squre.setText(String.valueOf(p1Result));
            }else if(rounds.get(r).getWinner().equals(p2)){
                p2Result +=1;
                lablelP2squre.setText(String.valueOf(p2Result));
            }

            labelRaund.setText(String.valueOf(rounds.size())+" / " + String.valueOf(roundSelectedCount));
        }

    }

    public void disableAll(){
        imgVpapierP1.setVisibility(View.INVISIBLE);
        imgVschareP1.setVisibility(View.INVISIBLE);
        imgVsteinP1.setVisibility(View.INVISIBLE);

        imgVpapierP2.setVisibility(View.INVISIBLE);
        imgVschareP2.setVisibility(View.INVISIBLE);
        imgVsteinP2.setVisibility(View.INVISIBLE);

        labelRaund.setVisibility(View.INVISIBLE);
        lablelP1Name.setVisibility(View.INVISIBLE);
        lablelP2Name.setVisibility(View.INVISIBLE);
        lablelP1squre.setVisibility(View.INVISIBLE);
        lablelP2squre.setVisibility(View.INVISIBLE);
        labelRaund.setVisibility(View.INVISIBLE);
    }



    public void startNew(){
        p1Result = 0;
        p2Result = 0;

        labelRaund.setText(String.valueOf(rounds.size())+" / " + String.valueOf(roundSelectedCount));

        lablelP1squre.setText("0");
        lablelP2squre.setText("0");


        imgVpapierP1.setVisibility(View.VISIBLE);
        imgVschareP1.setVisibility(View.VISIBLE);
        imgVsteinP1.setVisibility(View.VISIBLE);

        imgVpapierP2.setVisibility(View.VISIBLE);
        imgVschareP2.setVisibility(View.VISIBLE);
        imgVsteinP2.setVisibility(View.VISIBLE);

        labelRaund.setVisibility(View.VISIBLE);
        lablelP1Name.setVisibility(View.VISIBLE);
        lablelP2Name.setVisibility(View.VISIBLE);
        lablelP1squre.setVisibility(View.VISIBLE);
        lablelP2squre.setVisibility(View.VISIBLE);
        labelRaund.setVisibility(View.VISIBLE);
    }

    public void getElements(){
        player1 = new Player(player1Name);
        player2 = new Player("Computer");

        lablelP1Name.setText(player1.getPlayerName());
        lablelP2Name.setText(player2.getPlayerName());

        game= new Game(gameId,player1,player2);
        gameId = game.getGameID() +1;
    }

    public void imgEffect(ImageView imgView, float x, float y,float durat){

        /*
        float elmentX = imgView.getX();
        float elmentY = imgView.getY();

        Animation transElement = new TranslateAnimation(elmentX, x,elmentY, y);
        transElement.setDuration(500);
        imgView.startAnimation(transElement);
        */
        imgView.animate().rotationBy(360).setDuration(500).setInterpolator(new LinearInterpolator()).start();

        imgVmain.animate().rotationBy(360).setDuration(500).setInterpolator(new LinearInterpolator()).start();

    }
}
