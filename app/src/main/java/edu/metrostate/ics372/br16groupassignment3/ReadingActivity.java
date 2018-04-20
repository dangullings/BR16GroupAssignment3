package edu.metrostate.ics372.br16groupassignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class ReadingActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        loadPatients();
        loadClinics();
        loadReadingTypes();
        loadUnits();
    }

    private void loadPatients(){
        Spinner sp = (Spinner) findViewById(R.id.ReadingddPatient);
        ArrayList<Patient> p = getTrial().getPatients();
        p.add(new Patient("1"));
        p.add(new Patient("2"));

        ArrayList<String> pText = new ArrayList<String>();
        Iterator<Patient> iterP = p.iterator();
        while(iterP.hasNext())
        {
            pText.add(iterP.next().getId());
        }
        loadSpinner(sp, pText);

    }

    private void loadClinics(){
        Spinner sp = (Spinner) findViewById(R.id.ReadingddClinic);
        ArrayList<Clinic> clinics = getTrial().getClinics();
        clinics.add(new Clinic(1, "my clinic"));
        clinics.add(new Clinic(2, "your clinic"));

        ArrayList<String> clinicText = new ArrayList<String>();
        Iterator<Clinic> iterClinic = clinics.iterator();
        while(iterClinic.hasNext())
        {
            clinicText.add(iterClinic.next().getClinicName());
        }
        loadSpinner(sp, clinicText);
    }
    private void loadUnits(){
        Spinner sp = (Spinner) findViewById(R.id.ReadingddReadingUnit);
        ArrayList<String> t = new ArrayList<String>();
        t.add("Fahrenheit");
        t.add("Celcius");
        t.add("Lbs");
        t.add("Kg");
        loadSpinner(sp, t);
    };
    private void loadReadingTypes(){
        Spinner sp = (Spinner) findViewById(R.id.ReadingddReadingType);
        ArrayList<String> t = new ArrayList<String>();
        t.add("weight");
        t.add("temp");
        t.add("steps");
        t.add("blood_press");
        loadSpinner(sp, t);
    }

    private void loadSpinner(Spinner target, ArrayList<String> data){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        target.setAdapter(arrayAdapter);

    }

    private String getValueFromSpinner(int sp){
        String returnValue = "";
        try{ returnValue = (String)((Spinner) findViewById(sp)).getSelectedItem();}
        catch(Exception ex){returnValue=(String)((Spinner) findViewById(sp)).getItemAtPosition(0);}
        return returnValue;
    }
    private String getValueFromText(int sp){
        return (String)((TextView) findViewById(sp)).getText();
    }

    private Long getvalueFromCalendar(int sp){
        return ((CalendarView)findViewById(sp)).getDate();
    }

    public void saveReading(View view){
        String patientID = getValueFromSpinner(R.id.ReadingddPatient);
        String readingType = getValueFromSpinner(R.id.ReadingddReadingType);
        String readingValue = getValueFromText(R.id.ReadinglblReadingValue);
        Long dateValue = getvalueFromCalendar(R.id.ReadingcalendarView);
        String readingUnit = getValueFromSpinner(R.id.ReadingddReadingUnit);
        String readingID = getValueFromText(R.id.ReadingtxtReadingID);
        Reading r = new Reading(patientID, readingType, readingID, readingValue, dateValue, readingUnit);
        Clinic clinic = getTrial().findClinicByName(getValueFromSpinner(R.id.ReadingddClinic));
        r.setClinicName(clinic.getClinicName());
        r.setClinicID(clinic.getClinicId());
        getTrial().addReadingToPatient(r);
    }
    public void cancelReading(View view){
        ((TextView) findViewById(R.id.ReadingtxtReadingID)).setText("");
        ((TextView) findViewById(R.id.ReadingtxtReadingValue)).setText("");
    }

}
