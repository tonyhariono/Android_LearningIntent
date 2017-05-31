package id.co.imastudio.latihaninput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class HasilActivity extends AppCompatActivity {
    private EditText etSisi,etHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        String dataSisi=getIntent().getStringExtra(Constanta.DATA_SISI);
        String dataHasil=getIntent().getStringExtra(Constanta.DATA_HASIL);
        String dataPilihan=getIntent().getStringExtra(Constanta.DATA_PILIHAN);

        Log.d("Hasil",""+dataHasil+dataPilihan+dataSisi);

        etSisi=(EditText) findViewById(R.id.etHasilSisi);
        etHasil=(EditText) findViewById(R.id.etHasilHitung);

        etSisi.setText(dataSisi);
        etHasil.setText(dataHasil);


    }
}
