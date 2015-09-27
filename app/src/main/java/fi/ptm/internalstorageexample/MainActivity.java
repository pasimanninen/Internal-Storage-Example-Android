package fi.ptm.internalstorageexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by pasi on 27/09/15.
 */
public class MainActivity extends Activity {
    private final static String FILENAME = "test_file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // test writing
        String test = "Write text to Internal Storage.";
        try {
            FileOutputStream fileOut = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOut.write(test.getBytes());
            fileOut.close();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Cannot write text to Internal Storage!", Toast.LENGTH_SHORT).show();
        }

        // test reading
        StringBuffer text = new StringBuffer();
        final byte[] buffer = new byte[1024];
        try {
            FileInputStream fileIn = openFileInput(FILENAME);
            int n = 0;
            while ( (n = fileIn.read(buffer)) > 0 ) {
                text.append(new String(buffer, 0, n));
            }
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(text.toString());
            fileIn.close();
        } catch (Exception e)  {
            Toast.makeText(getBaseContext(), "Cannot read text from Internal Storage!", Toast.LENGTH_SHORT).show();
        }
    }

}
