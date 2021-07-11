package hr.tvz.android.salehfragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ProductAdapter.ItemClicked{

    MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();


    FirebaseDatabase rootNode;
    DatabaseReference reference;
    MyDatabaseHelper  myDB;


    Button btnProductInfo, btnOwnerInfo;
    ImageView ivMake;
    TextView tvModel, tvName, tvTel;
    FragmentManager fragmentManager;
    Fragment buttonFrag, listFrag, productInfoFrag, ownerInfoFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        btnProductInfo = findViewById(R.id.btnProductInfo);
        btnOwnerInfo = findViewById(R.id.btnOwnerInfo);
        ivMake = findViewById(R.id.ivMake);
        tvName = findViewById(R.id.tvName);
        tvModel = findViewById(R.id.tvModel);
        tvTel = findViewById(R.id.tvTel);

        fragmentManager = getSupportFragmentManager();

        listFrag = fragmentManager.findFragmentById(R.id.listFrag);
        buttonFrag = fragmentManager.findFragmentById(R.id.buttonFrag);
        productInfoFrag = fragmentManager.findFragmentById(R.id.productInfoFrag);
        ownerInfoFrag = fragmentManager.findFragmentById(R.id.ownerInfoFrag);
        //the phone is in portrait mode
        if (findViewById(R.id.layout_land) != null)
        {
            fragmentManager.beginTransaction()
                    .show(buttonFrag)
                    .show(listFrag)
                    .show(productInfoFrag)
                    .hide(ownerInfoFrag)
                    .commit();
        }

        if (findViewById(R.id.layout_portrait) != null)
        {
            fragmentManager.beginTransaction()
                    .hide(buttonFrag)
                    .show(listFrag)
                    .hide(productInfoFrag)
                    .hide(ownerInfoFrag)
                    .commit();
        }

      /*  fragmentManager.beginTransaction()
                .show(buttonFrag)
                .show(listFrag)
                .show(productInfoFrag)
                .hide(ownerInfoFrag)
                .commit();


*/

        btnOwnerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager.beginTransaction()
                        .hide(productInfoFrag)
                        .show(ownerInfoFrag)
                        .commit();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Products");
                reference.setValue("First data stored");

            }
        });

        btnProductInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .show(productInfoFrag)
                        .hide(ownerInfoFrag)
                        .commit();
            }
        });

        if(findViewById(R.id.layout_land) !=null){
            onItemClicked(0);

        }
    }





    @Override
    public void onItemClicked(int index) {

    tvName.setText(ApplicationClass.products.get(index).getOwnerName());
    tvModel.setText(ApplicationClass.products.get(index).getModel());
    tvTel.setText(ApplicationClass.products.get(index).getOwnerTel());

        if(findViewById(R.id.layout_portrait) != null){
            fragmentManager = this.getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .show(buttonFrag)
                    .hide(listFrag)
                    .show(productInfoFrag)
                    .addToBackStack(null)
                    .commit();
        }

      if (ApplicationClass.products.get(index).getMake().equals("derma")){
          ivMake.setImageResource(R.drawable.derma);
      }
      else if (ApplicationClass.products.get(index).getMake().equals("lumin")){
          ivMake.setImageResource(R.drawable.lumin);
      }
        else if (ApplicationClass.products.get(index).getMake().equals("lipo")){
            ivMake.setImageResource(R.drawable.lipo);
        }
      else if (ApplicationClass.products.get(index).getMake().equals("soyfacecleanser")){
          ivMake.setImageResource(R.drawable.soyfacecleanser);
      }
      else if (ApplicationClass.products.get(index).getMake().equals("aroma")){
          ivMake.setImageResource(R.drawable.aroma);
      }
      else if (ApplicationClass.products.get(index).getMake().equals("facetransparent")){
          ivMake.setImageResource(R.drawable.facetransparent);
      }
      else if (ApplicationClass.products.get(index).getMake().equals("facehero")){
          ivMake.setImageResource(R.drawable.facehero);
      }
      else{

      }


    }


    @Override
    protected void onStart(){
        super.onStart();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myBroadcastReceiver,filter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);

    }

    /*-------FOR THE LANGUAGE---------------------*/
    private void showChangeLanguageDialog(){
        final String[] listItems = {"French","Turkish","Croatian","English"};

        //aray of languages to display in alert dialog
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("fr");
                    recreate();
                }
                if(i==1){
                    setLocale("tr");
                    recreate();
                }
                if(i==2){
                    setLocale("hr");
                    recreate();
                }
                if(i==3){
                    setLocale("en");
                    recreate();
                }
                //dismiss alert dialog when language selected
                dialogInterface.dismiss();
            }
        });
        android.app.AlertDialog mDialog = mBuilder.create();
        //show alert
        mDialog.show();

    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        //save date to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences prefs= getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String Language = prefs.getString("My_Lang","");
        setLocale(Language);
    }


    /*---FOR MENU OPTİON----------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_share:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you really want to share this content?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                sharingIntent.setType("text/plain");
                                String shareBody="Just trying to mess up with you";
                                String shareSubject="Hey there";

                                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

                                startActivity(Intent.createChooser(sharingIntent, "Share Using"));

                            }})
                        .setNegativeButton("Cancel",null);
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.action_language:
                showChangeLanguageDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}