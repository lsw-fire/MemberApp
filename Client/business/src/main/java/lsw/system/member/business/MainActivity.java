package lsw.system.member.business;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity  {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Gson gson = new Gson();
        User user = gson.fromJson("{name:\"lishiwei\",age:34}", User.class);
        String userName = user.getName();

        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.menuPay){

            Intent i = new Intent(this, Member_Pay.class);
            startActivity(i);
        }

        if (id == R.id.menuMemberAdd)
        {
            Intent i = new Intent(this, Member_Maintain.class);
            i.putExtra("isAdd",true);
            startActivity(i);
        }

        if (id==R.id.menuProductAdd)
        {
            Intent i = new Intent(this, Product_Maintain.class);
            i.putExtra("isAdd",true);
            startActivity(i);
        }

        if (id == R.id.menuMemberLevel)
        {
            Intent i = new Intent(this, Member_Level_Maintain.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }

    public class User
    {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}



