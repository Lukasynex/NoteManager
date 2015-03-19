package com.lukasyno.useful.notemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



/**
 * 
 * @author Lukasz
 *TODO:
 *make SQLCOmmander schema as Hashmap:
 *now is: 	public final String COL1 = "id INT PRIMARY KEY"; ... COL4
 *future: 	public Map<String, String> COLS = new HashMap<String, String>();
 *
 *re-make the SQLUtils, to provide saving Notes, deleting them and so on
 *
 *
 *make them visible as textviews, draggable and with pastel background colours
 *
 *apply all on fragments!!!!
 *
 *make service(like timetable where you can see the most important memo
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
