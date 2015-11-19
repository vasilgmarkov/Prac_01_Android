package es.www2tocas.prac_01_vasil_markov;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

private Button btn_nav, btn_cam, btn_call, btn_mail ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_nav =(Button) findViewById(R.id.nav);
        btn_cam = (Button) findViewById(R.id.cam);
        btn_call = (Button) findViewById(R.id.call);
        btn_mail = (Button) findViewById(R.id.mail);
        btn_nav.setOnClickListener(this);
        btn_cam.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_mail.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private final static int CAPTURE_FOTO = 2;

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

    @Override
    public void onClick(View v){
        if (v.equals(btn_cam)) takePhoto();
        else if(v.equals(btn_nav)) lounchNav();
        else if(v.equals(btn_call)) makeCall();
        else if(v.equals(btn_mail)) sendMail();

    }

    private Uri photoUri;

    public void takePhoto(){
        File dir = getExternalFilesDir(null);
        if (dir==null){
            Toast.makeText(this, "Unable to mount the fileSystem", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(dir, "photo.jpg");

        photoUri=Uri.fromFile(file);
        Intent i = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(i,CAPTURE_FOTO);



    }

    public void lounchNav(){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sportal.bg"));
        startActivity(i);

    }

    public void makeCall(){
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:933 01 56 96"));
        startActivity(i);


    }

    public void sendMail() {
       Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"vasilgmarokv@gmai.com" });
        startActivity(intent);
        }

    }


