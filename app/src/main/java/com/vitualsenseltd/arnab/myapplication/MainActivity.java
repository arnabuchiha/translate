package com.vitualsenseltd.arnab.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText editText = (EditText)findViewById(R.id.simple_text);



        Button button =(Button) findViewById(R.id.translateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str  = editText.getText().toString();
                transl("AIzaSyCJg1Q22bNXvxB2Rhdi33sFLZG05C7x0PE",str,"en","hi");
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void transl(String key,String q,String source,String target){
        final translate tn = translate.retrofit.create(translate.class);
    Call<authPOJO> call = tn.performTranslate(key,q,source,target);
    call.enqueue(new Callback<authPOJO>() {
        @Override
        public void onResponse(Call<authPOJO> call, Response<authPOJO> response) {
            Data data=response.body().getData();
            //See the response
            System.out.println("HAHA: "+response.raw().toString());
            List<Translation> list = data.getTranslations();

            ListIterator<Translation> los=list.listIterator();
            String txt1="";
            do{
                String txt=los.next().getTranslatedText();
                txt1=txt1+txt;
            }while(los.hasNext());


//            ListIterator<Translation> listIterator=list.listIterator();
//            String txt1="";
////            do{
//                String txt=list.get(0).getTranslatedText();
//                txt1=txt1+txt;
//            }while(listIterator.hasNext());
            TextView textView=(TextView)findViewById(R.id.text);
            textView.setText(txt1);


        }


        @Override
        public void onFailure(Call<authPOJO> call, Throwable t) {
            TextView textView=(TextView)findViewById(R.id.text);
            textView.setText("Failed");

        }
    });
    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
