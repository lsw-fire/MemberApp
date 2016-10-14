package lsw.system.member.business;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import lsw.system.member.business.model.ModelContext;
import lsw.system.member.business.model.Product;
import lsw.system.member.business.model.ProductCategory;
import lsw.system.member.business.utility.FileHelper;
import lsw.system.member.business.utility.MySpinnerAdapter;

public class Product_Maintain extends AppCompatActivity {

    ArrayList<ProductCategory> listPC= new ArrayList<ProductCategory>();

    private Context mContext;
    private ProductCategory selectedPC;

    private Realm realm;

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_maintain);

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        mContext = this;

        Spinner spinner = (Spinner) findViewById(R.id.spProductCategory);
        Button btnSave = (Button)findViewById(R.id.btnSave);
        final EditText etProductName = (EditText)findViewById(R.id.etProductName);
        final EditText etProductPrice = (EditText)findViewById(R.id.etProductPrice);

        Type type = new TypeToken<ArrayList<ProductCategory>>() {}.getType();

        String json = FileHelper.openJsonFile(this,"ProductCategory.json");
        listPC = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create().fromJson(json,type);

        SpinnerAdapter myAdapter = new MySpinnerAdapter<ProductCategory>(mContext,listPC) {
            @Override
            public String getTValue(ProductCategory productCategory) {
                return productCategory.getName();
            }
        };
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPC = listPC.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Product product = new Product();
                //product.setProductName(etProductName.getText().toString());
                //product.setProductPrice(Double.parseDouble(etProductPrice.getText().toString()));
                //product.setProductCategory(selectedPC);

//                Gson gson = new Gson();
//                String result = gson.toJson(product);
//                Log.d("save product",result);
//
//                ModelContext modelContext = new ModelContext(mContext);
//                modelContext.saveToFile(result);


                //Realm realm = Realm.getInstance(mContext);
                realm.beginTransaction();
                Product product = realm.createObject(Product.class); // Create a new object
                product.setProductName(etProductName.getText().toString());
                product.setProductPrice(Double.parseDouble(etProductPrice.getText().toString()));
                //product.setProductCategory(selectedPC);
                realm.commitTransaction();


                Toast.makeText(mContext,"保存产品成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
