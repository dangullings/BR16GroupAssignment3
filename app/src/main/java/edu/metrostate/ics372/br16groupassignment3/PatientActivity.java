package edu.metrostate.ics372.br16groupassignment3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientActivity extends MainActivity {

    String pattern = "[0-9a-zA-Z]{1,10}";
    String patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        final EditText txtPatientId = (EditText) findViewById(R.id.txtPatientId);

        Button btnSubmitPatient = (Button) findViewById(R.id.btnSubmitPatient);

        txtPatientId.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                patientId = txtPatientId.getText().toString();

                if (patientId.length() > 0 && isRegexMatch(pattern, patientId)){
                    txtPatientId.setTextColor(getResources().getColor(R.color.colorDefaultText));
                }else {
                    txtPatientId.setTextColor(getResources().getColor(R.color.colorErrorText));
                }
            }
        });

        btnSubmitPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientId = txtPatientId.getText().toString();
                    if(patientId.length() > 0 && patientId.length() < 10 && isRegexMatch(pattern, patientId)) {
                        Patient patient = new Patient(patientId);
                        if (getTrial().addPatient(patient)) {
                            reportSubmit("Patient " + patientId + " added");
                        } else {
                            reportSubmit("Patient " + patientId + " already exists");
                        }
                    }else
                        reportSubmit("Invalid patient id either nothing or longer than 10 characters or invalid characters (0-9, a-z, A-Z).");

                txtPatientId.setText("");
            }
        });

    }

    private void reportSubmit(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isRegexMatch(String pattern, String value) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(value);
        return matcher.matches();
    }
}
