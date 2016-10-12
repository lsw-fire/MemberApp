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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lsw.system.member.business.model.ModelContext;
import lsw.system.member.business.model.Product;
import lsw.system.member.business.model.ProductCategory;

public class Product_Maintain extends AppCompatActivity {

    List<ProductCategory> listPC= new ArrayList<ProductCategory>();

    private Context mContext;
    private ProductCategory selectedPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_maintain);

        mContext = this;

        Spinner spinner = (Spinner) findViewById(R.id.spProductCategory);
        Button btnSave = (Button)findViewById(R.id.btnSave);
        final EditText etProductName = (EditText)findViewById(R.id.etProductName);
        final EditText etProductPrice = (EditText)findViewById(R.id.etProductPrice);

        Type type = new TypeToken<ArrayList<ProductCategory>>() {}.getType();

        String json = loadProductsFromAssets();
        listPC = new Gson().fromJson(json,type);

        SpinnerAdapter myAdapter = new MyAdapter();
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

                Product product = new Product();
                product.setProductName(etProductName.getText().toString());
                product.setProductPrice(Double.parseDouble(etProductPrice.getText().toString()));
                product.setProductCategory(selectedPC);

                Gson gson = new Gson();
                String result = gson.toJson(product);
                Log.d("save product",result);

                ModelContext modelContext = new ModelContext(mContext);
                modelContext.saveToFile(result);

                Toast.makeText(mContext,"保存产品成功",Toast.LENGTH_SHORT);
            }
        });
    }

    private String loadProductsFromAssets() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("ProductCategory.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception e) {
                Log.e("LP", "Load product", e);
        }
        return json;
    }

    private class MyAdapter implements SpinnerAdapter {
        private ThemedSpinnerAdapter.Helper helper;


        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return listPC.size();
        }

        @Override
        public Object getItem(int position) {
            return listPC.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(mContext);
            textView.setTextSize(20);
            textView.setText(listPC.get(position).getName());
            return textView;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(mContext);
            textView.setTextSize(20);
            textView.setText(listPC.get(position).getName());
            return textView;
        }
    }
}
