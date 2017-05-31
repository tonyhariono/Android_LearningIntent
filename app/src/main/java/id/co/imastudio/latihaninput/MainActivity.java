package id.co.imastudio.latihaninput;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private EditText etsisi, ethasil;
    private Button btnhitung;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnhitung = (Button) findViewById(R.id.btnHitung);
        etsisi = (EditText) findViewById(R.id.etSisi);
        ethasil = (EditText) findViewById(R.id.etHasil);
        spinner = (Spinner) findViewById(R.id.spinner);

        final String[] pilihanHitung = {
                "Volume",
                "Luas Permukaan",
                "Keliling"
        };

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, pilihanHitung);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Kamu memilih "+pilihanHitung[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnhitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (spinner.getSelectedItem().toString().equals(pilihanHitung[0])) {
                        hitungVolume();
                    } else if (spinner.getSelectedItem().toString().equals(pilihanHitung[1])) {
                        ethasil.setText(Double.toString(hitungLuas()));
                    } else if (spinner.getSelectedItem().toString().equals(pilihanHitung[2])) {
                        Double sisi = Double.parseDouble(etsisi.getText().toString());
                        hitungKeliling(sisi);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error :" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent pindah=new Intent(MainActivity.this,HasilActivity.class);
                //kirim data
                pindah.putExtra(Constanta.DATA_SISI,etsisi.getText().toString());
                pindah.putExtra(Constanta.DATA_HASIL,ethasil.getText().toString());
                pindah.putExtra(Constanta.DATA_PILIHAN,spinner.getSelectedItem().toString());
                startActivity(pindah);


            }
        });
    }

    ;


    private void hitungKeliling(Double sisi) {
        Double hasil = 12 * sisi * sisi;
        //ethasil.setText(Double.toString(hasil));
        ethasil.setText(String.format("%.2f",hasil));
    }

    private double hitungLuas() {
        Double sisi = Double.parseDouble(etsisi.getText().toString());
        Double hasil = 6 * sisi * sisi;
        return hasil;
    }

    private void hitungVolume() {
        Double sisi = Double.parseDouble(etsisi.getText().toString());
        Double hasil = sisi * sisi * sisi;
        ethasil.setText(Double.toString(hasil));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_settings2) {
            //Intent telpon=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:085740482440"));
            Intent telpon = new Intent(Intent.ACTION_CALL, Uri.parse("tel:085740482440"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
            startActivity(telpon);
        }

        return super.onOptionsItemSelected(item);
    }
}
