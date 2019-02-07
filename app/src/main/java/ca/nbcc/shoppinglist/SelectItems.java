package ca.nbcc.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectItems extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.nbcc.shoppinglist.selectitems.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items);
    }

    public void selectItem(View view) {
        String item = view.getTag().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, item);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
