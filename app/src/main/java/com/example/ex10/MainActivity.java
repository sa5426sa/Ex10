package com.example.ex10;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The activity Main
 */
public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private HelperDB hlp;
    private EditText eTname, eTpass, eTage, eTsub, eTgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAll();
    }

    /**
     * initAll
     * <p>
     * This method init the views& database
     */
    private void initAll() {
        eTname = (EditText) findViewById(R.id.eTname);
        eTpass = (EditText) findViewById(R.id.eTpass);
        eTage = (EditText) findViewById(R.id.eTage);
        eTsub = (EditText) findViewById(R.id.eTsub);
        eTgrade = (EditText) findViewById(R.id.eTgrade);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    /**
     * Usersdatain
     * <p>
     * This method push data to Users table
     * @param view the view
     */
    public void usersdatain(View view) {
        String name, pass, strage;
        int age;

        name = eTname.getText().toString();
        pass = eTpass.getText().toString();
        strage = eTage.getText().toString();
        age = Integer.parseInt(strage);

        ContentValues cv = new ContentValues();
        cv.put(Users.NAME, name);
        cv.put(Users.PASSWORD, pass);
        cv.put(Users.AGE, age);

        db = hlp.getWritableDatabase();
        db.insert(Users.TABLE_USERS, null, cv);
        db.close();

        eTname.setText("");
        eTpass.setText("");
        eTage.setText("");
        Toast.makeText(this, "Data pushed to Users table", Toast.LENGTH_LONG).show();
    }

    /**
     * Gradesdatain
     * <p>
     * This method push data to Grades table
     * @param view the view
     */
    public void gradesdatain(View view) {
        String subject, strgrade;
        int grade;

        subject = eTsub.getText().toString();
        strgrade = eTgrade.getText().toString();
        grade = Integer.parseInt(strgrade);

        ContentValues cv = new ContentValues();
        cv.put(Grades.SUBJECT, subject);
        cv.put(Grades.GRADE, grade);

        db = hlp.getWritableDatabase();
        db.insert(Grades.TABLE_GRADES, null, cv);
        db.close();

        eTsub.setText("");
        eTgrade.setText("");
        Toast.makeText(this, "Data pushed to Grades table", Toast.LENGTH_LONG).show();
    }

    /**
     * onCreateOptionsMenu
     * <p>
     * This method create the menu options
     * @param menu the menu
     */
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * onOptionsItemSelected
     * <p>
     * This method react to the menu option selected
     * @param item the menu item selected
     */
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuWatch) {
            Intent t = new Intent(this, Watchtables.class);
            startActivity(t);
        } else if (id == R.id.menuUpdate) {
            Intent t = new Intent(this, Update.class);
            startActivity(t);
        } else if (id == R.id.menuSort) {
            Intent t = new Intent(this, Sort.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }
}