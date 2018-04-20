package edu.metrostate.ics372.br16groupassignment3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class UpdatePatientStatusActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient_status);

        Spinner spinnerPatientList = (Spinner) findViewById(R.id.spinnerPatientList);

        ArrayList<String> data = new ArrayList<>();
        data.add("patient12");
        data.add("patient13");
        data.add("patient14");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, data);
        spinnerPatientList.setAdapter(adapter);
    }
}
