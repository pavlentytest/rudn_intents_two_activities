package ru.samsung.itschool.mdev.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn, btn2;
    private EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://google.com";
                // Намерение неявное
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.button2);
        ed = findViewById(R.id.editText);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Явное намерение
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("ccc", ed.getText().toString());
                startActivity(intent); // если мы не ждем обратно ничего
              //  startActivityForResult(intent, ACTIVITY_RESULT_CODE);
                someActivityResultLauncher.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == MainActivity2.RESULT_CODE){
                        ed.setText(result.getData().getStringExtra("ppp"));
                    }
                }
    });

    // startActivityForResult(intent, 3);

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_RESULT_CODE) {
            // resultCode - либо ошибка, либо ОК.
            Log.d("RRRR", Integer.toString(resultCode));
            ed.setText(data.getStringExtra("ppp"));
        }
        if (requestCode == 3) {


        }
    }

     */
}