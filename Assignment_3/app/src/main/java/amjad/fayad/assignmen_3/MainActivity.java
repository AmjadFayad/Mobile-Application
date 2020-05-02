package amjad.fayad.assignmen_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText95 = findViewById(R.id.edOctan95);
        final EditText editText98 = findViewById(R.id.edOctan98);

        final TextView textView95 = findViewById(R.id.octan95Val);
        final TextView textView98 = findViewById(R.id.octan98Val);

        Button btnShow = findViewById(R.id.btnShow);
        final TableLayout tableLayout = findViewById(R.id.table);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableLayout.setVisibility(View.VISIBLE);

                textView95.setText(editText95.getText());
                textView98.setText(editText98.getText());
            }
        });
    }
}
