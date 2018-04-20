package edu.metrostate.ics372.br16groupassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import ru.bartwell.exfilepicker.ExFilePicker;
import ru.bartwell.exfilepicker.data.ExFilePickerResult;

public class MainActivity extends AppCompatActivity {

    private Trial trial;

    public MainActivity() {
        trial = new Trial();
    }

    private static final int LOAD_FILE_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    public void chooseFile(View view) {
        ExFilePicker exFilePicker = new ExFilePicker();
        exFilePicker.setCanChooseOnlyOneItem(true);
        exFilePicker.setShowOnlyExtensions("json", "xml");
        exFilePicker.setChoiceType(ExFilePicker.ChoiceType.FILES);
        exFilePicker.start(this, LOAD_FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOAD_FILE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
                try {
                    ArrayList<Reading> readings = PatientReadingsParser.loadFile(result.getPath() + result.getNames().get(0));
                    trial.processReadingsList(readings);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Trial getTrial() {
        return trial;
    }
}
