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

public class ClinicActivity extends MainActivity {

    String namePattern = "[0-9a-zA-Z \\-]{1,100}";
    String idPattern = "[0-9]{1,13}";
    String clinicIdText;
    String clinicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic);

        final EditText txtClinicId = (EditText) findViewById(R.id.txtClinicId);
        final EditText txtClinicName = (EditText) findViewById(R.id.txtClinicName);

        Button btnSubmitClinic = (Button) findViewById(R.id.btnSubmitClinic);

        txtClinicId.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                clinicIdText = txtClinicId.getText().toString();

                if (clinicIdText.length() > 0 && isRegexMatch(idPattern, clinicIdText)){
                    txtClinicId.setTextColor(getResources().getColor(R.color.colorDefaultText));
                }else {
                    txtClinicId.setTextColor(getResources().getColor(R.color.colorErrorText));
                }
            }
        });

        txtClinicName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                clinicName = txtClinicName.getText().toString();

                if (clinicName.length() > 0 && isRegexMatch(namePattern, clinicName)){
                    txtClinicName.setTextColor(getResources().getColor(R.color.colorDefaultText));
                }else {
                    txtClinicName.setTextColor(getResources().getColor(R.color.colorErrorText));
                }
            }
        });

        btnSubmitClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clinicIdText = txtClinicId.getText().toString();
                clinicName = txtClinicName.getText().toString();

                if (clinicName.length() > 0 && isRegexMatch(namePattern, clinicName) && clinicIdText.length() > 0 && isRegexMatch(idPattern, clinicIdText)) {
                    try {
                        if (getTrial().addClinic(Integer.parseInt(clinicIdText), clinicName)){
                            reportSubmit("Clinic " + clinicIdText + " - " + clinicName + " added");
                        }else{
                            reportSubmit("Clinic " + clinicIdText + " - " + clinicName + " already exists");
                        }
                    }
                    catch(Exception e) {
                        reportSubmit("Invalid clinic information: must be integer for ID");
                    }
                    txtClinicName.setText("");
                    txtClinicId.setText("");
                }
                else
                    reportSubmit("Invalid clinic information: must be integer value for ID and an alpha-numeric string with only spaces or dashes for the name.");
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
