package ca.nbcc.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private ShoppingList shoppingList = new ShoppingList();
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "on create");

        if (savedInstanceState != null) {
            Log.d(LOG_TAG, "saved Instance");
            shoppingList = (ShoppingList)savedInstanceState.getSerializable("shoppingList");
        } else {
            Log.d(LOG_TAG, "no saved Instance");
        }
        displayList();
    }

    public void launchItemActivity(View view) {
        Intent intent = new Intent(this, SelectItems.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayList();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            shoppingList = (ShoppingList) savedInstanceState.getSerializable("shoppingList");
        }

        displayList();
    }

    private void displayList() {
        for (int i = 0; i < 10; i++) {
            String field = "txtItem" + i;
            TextView tmpView = (TextView) findViewById((int) getResources()
                    .getIdentifier(field, "id", getPackageName()));
            if (tmpView != null) {
                tmpView.setText("");
                tmpView.setVisibility(View.INVISIBLE);
            }
        }
        Iterator<Map.Entry<String, Integer>> iterator = shoppingList.getShoppingList()
                .entrySet().iterator();

        int i = 1;
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();
            String field = "txtItem" + i;
            TextView tmpView = (TextView) findViewById((int) getResources()
                    .getIdentifier(field, "id", getPackageName()));
            if (tmpView != null) {
                tmpView.setText(pair.getValue() + ": " + pair.getKey());
                tmpView.setVisibility(View.VISIBLE);
            }
            i++;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String item = data.getStringExtra(SelectItems.EXTRA_REPLY);
                if (item != null) {
                    shoppingList.addItem(item);
                }
            }
        }

        displayList();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("shoppingList", shoppingList);
    }
}
