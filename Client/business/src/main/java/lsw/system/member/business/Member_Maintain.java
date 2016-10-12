package lsw.system.member.business;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Member_Maintain extends AppCompatActivity {

    EditText etProductName;
    Spinner spProductCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_maintain);

        etProductName = (EditText)findViewById(R.id.etProductName);
        spProductCategory = (Spinner)findViewById(R.id.spProductCategory);
    }
}
