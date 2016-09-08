package de.erixpage.teuto_kb;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                displayAbout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Html.fromHtml((String) getText(R.string.about)))
               .setTitle(R.string.about_title)
               .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                   }
               })
               .setPositiveButton(R.string.goto_github, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       Intent i = new Intent(Intent.ACTION_VIEW);
                       i.setData(Uri.parse("https://github.com/pille1842/teuto-kb"));
                       startActivity(i);
                   }
               });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}