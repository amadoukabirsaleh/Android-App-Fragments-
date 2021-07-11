package hr.tvz.android.salehfragment;

import android.app.Application;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

public class ApplicationClass extends Application {

      public static ArrayList<Product> products;
    ArrayList<String> product_id,make,model,ownerName,ownerTel;
       MyDatabaseHelper myDB;

    @Override
    public void onCreate() {

        super.onCreate();
        //---------SETTING DATABASE AND ARRAYS TO STORE DATA -----------------
        myDB = new MyDatabaseHelper(ApplicationClass.this);
        product_id = new ArrayList<>();
        make = new ArrayList<>();
        model = new ArrayList<>();
        ownerName = new ArrayList<>();
        ownerTel = new ArrayList<>();
        storeDataInArrays();

        products = new ArrayList<Product>();
        for(int i=0; i< product_id.size();i++){
            products.add(new Product(make.get(i), model.get(i), ownerName.get(i), ownerTel.get(i)));

        }

       /* products.add(new Product("aroma","Aroma","R. Ponnuswamy","info@aromaco.co.uk"));
        products.add(new Product("facehero","Face Hero","Zoë Foster Blake","info.facehero@gmail.com"));
        products.add(new Product("facetransparent","Face Transparent","Carnagei","info.facetransparent@gmail.com"));
        products.add(new Product("lumin","Lumin","Vladimir Oane","info.lumin@gmail.com"));
        products.add(new Product("soyfacecleanser","Soyface Cleanser","Lev Glazman and Alina Roytberg","info.soyfacecleanser@gmail.com"));
        products.add(new Product("derma","Derma","Linda Miles","info.derma@gmail.com"));
        products.add(new Product("lipo","Lipo","Patricia","info.dermalipo@gmail.com"));
*/



    }


    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                make.add(cursor.getString(1));
                model.add(cursor.getString(2));
                ownerName.add(cursor.getString(3));
                ownerTel.add(cursor.getString(4));

            }
        }
    }
}
