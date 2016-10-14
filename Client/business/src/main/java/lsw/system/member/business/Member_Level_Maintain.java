package lsw.system.member.business;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import lsw.system.member.business.model.MemberLevel;
import lsw.system.member.business.model.ModelContext;
import lsw.system.member.business.model.Product;
import lsw.system.member.business.model.ProductCategory;
import lsw.system.member.business.utility.FileHelper;
import lsw.system.member.business.utility.MySpinnerAdapter;

public class Member_Level_Maintain extends AppCompatActivity {

    ArrayList<MemberLevel> listML= new ArrayList<MemberLevel>();

    private Context mContext;
    private MemberLevel selectedMemberLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_level_maintain);

        this.mContext = this;

        Spinner spinner = (Spinner) findViewById(R.id.spMemberLevel);
        Button btnSave = (Button)findViewById(R.id.btnSave);
        final EditText etDiscountPercent = (EditText)findViewById(R.id.etDiscountPercent);

        Type type = new TypeToken<ArrayList<MemberLevel>>() {}.getType();

        String json = FileHelper.openJsonFile(this,"MemberLevel.json");
        listML = new Gson().fromJson(json,type);

        SpinnerAdapter myAdapter = new MySpinnerAdapter<MemberLevel>(mContext,listML) {
            @Override
            public String getTValue(MemberLevel memberLevel) {
                return memberLevel.getName();
            }
        };
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMemberLevel = listML.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MemberLevel product = new MemberLevel();
                product.setName(etDiscountPercent.getText().toString());
                product.setDiscount(Double.parseDouble(etDiscountPercent.getText().toString()));

                Gson gson = new Gson();
                String result = gson.toJson(product);
                Log.d("save product",result);

                ModelContext modelContext = new ModelContext(mContext);
                modelContext.saveToFile(result);

                Toast.makeText(mContext,"用户级别保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
