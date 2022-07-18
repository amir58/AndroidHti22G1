package com.amirmohammed.hti22android.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;

public class MenusActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        textView = findViewById(R.id.tvHelloWorld);

        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_copy_text) {
            Toast.makeText(this, "Text copied!", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optional_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_search) {
            Toast.makeText(this, R.string.search, Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.item_notifications) {
            Toast.makeText(this, getString(R.string.notifications), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.item_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeLanguagePopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(MenusActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_ar){
                    Toast.makeText(MenusActivity.this, "Ar", Toast.LENGTH_SHORT).show();
                }
                else if(menuItem.getItemId() == R.id.item_en){
                    Toast.makeText(MenusActivity.this, "En", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }
}