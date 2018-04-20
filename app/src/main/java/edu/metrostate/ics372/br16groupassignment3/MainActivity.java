package edu.metrostate.ics372.br16groupassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Trial trial;

    public MainActivity() {
        trial = new Trial();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLoadFile = (Button) findViewById(R.id.btnLoadFile);
        btnLoadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btnSaveFile = (Button) findViewById(R.id.btnSaveFile);
        btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.commonmenuss, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuShowReadings){
            Toast.makeText(this, "Patient Readings", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ShowReadingsActivity.class));
        }else if (id == R.id.menuUpdatePatientStatus){
            Toast.makeText(this, "Patient Status", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, UpdatePatientStatusActivity.class));
        }else if (id == R.id.menuAddPatient){
            Toast.makeText(this, "Add Patient", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, PatientActivity.class));
        }else if (id == R.id.menuAddClinic){
            Toast.makeText(this, "Add Clinic", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ClinicActivity.class));
        }else if (id == R.id.menuAddReading){
            Toast.makeText(this, "Add Reading", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ReadingActivity.class));
        }else if (id == R.id.menuHome){
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public Trial getTrial() {
        return trial;
    }
}
