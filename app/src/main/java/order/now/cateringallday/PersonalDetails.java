package order.now.cateringallday;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import mehdi.sakout.fancybuttons.FancyButton;

public class PersonalDetails extends AppCompatActivity {

    Toolbar toolbar;
    CheckBox checkBox;
    TextInputLayout name, phno, mailid, lunchaddress, dinneraddress;
    FancyButton lunchBtn, dinnerBtn;
    FloatingActionButton fab;
    Boolean isChecked;

    int LUNCH_PLACE_PICKER_REQUEST = 1;
    int DINNER_PLACE_PICKER_REQUEST = 2;

    public class Details{
        String userName, phoneNumber, mailID, lunchAddress, dinnerAddress;

        public Details(){
            userName = "";
            phoneNumber = "";
            mailID = "";
            lunchAddress = "";
            dinnerAddress = "";
        }

        public Details(String name, String phno, String mailid, String lunch, String dinner){
            userName = name;
            phoneNumber = phno;
            mailID = mailid;
            lunchAddress = lunch;
            dinnerAddress = dinner;
        }
    }

    Details details;

    public boolean validate(){
        boolean valid = true;

        if(name.getEditText().getText().toString().trim().equals("")){
            name.getEditText().setError("Can't be empty");
            valid = false;
        }else{
            name.getEditText().setError(null);
            valid = true;
        }

        if(phno.getEditText().getText().toString().trim().equals("")){
            phno.getEditText().setError("Can't be empty");
            valid = false;
        }else if(!Patterns.PHONE.matcher(phno.getEditText().getText()).matches()){
            phno.getEditText().setError("Invalid");
            valid = false;
        }else {
            phno.getEditText().setError(null);
            valid = true;
        }

        if(!mailid.getEditText().getText().toString().trim().equals("")){
            if(!Patterns.EMAIL_ADDRESS.matcher(mailid.getEditText().getText()).matches()){
                mailid.getEditText().setError("Invalid");
                valid = false;
            }else{
                mailid.getEditText().setError(null);
                valid = true;
            }
        }

        if(lunchaddress.getEditText().getText().toString().trim().equals("")){
            lunchaddress.setError("Can't be empty");
            valid = false;
        }else{
            lunchaddress.setError(null);
            lunchaddress.setErrorEnabled(false);
            valid = true;
        }

        if(isChecked){
            if(dinneraddress.getEditText().getText().toString().trim().equals("")){
                dinneraddress.setError("Can't be empty");
                valid = false;
            }else{
                dinneraddress.setError(null);
                dinneraddress.setErrorEnabled(false);
                valid = true;
            }
        }

        return valid;
    }

    public void pickPlace(int requestCode){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(PersonalDetails.this), requestCode);
        }catch (GooglePlayServicesRepairableException e){
            e.printStackTrace();
        }catch (GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Personal Details");

        checkBox = (CheckBox)findViewById(R.id.checkBox);
        name = (TextInputLayout)findViewById(R.id.nameinput);
        phno = (TextInputLayout)findViewById((R.id.phnoinput));
        mailid = (TextInputLayout)findViewById(R.id.mailidinput);
        lunchaddress = (TextInputLayout)findViewById(R.id.lunchaddress);
        dinneraddress = (TextInputLayout)findViewById(R.id.dinneraddress);
        lunchBtn = (FancyButton)findViewById(R.id.lunch_button);
        dinnerBtn = (FancyButton)findViewById(R.id.dinner_button);
        fab = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        isChecked = false;

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isChecked = true;
                    dinneraddress.setVisibility(View.GONE);
                    dinnerBtn.setVisibility(View.GONE);
                }
                else{
                    isChecked = false;
                    dinneraddress.setVisibility(View.VISIBLE);
                    dinnerBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        lunchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickPlace(LUNCH_PLACE_PICKER_REQUEST);
            }
        });

        dinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickPlace(DINNER_PLACE_PICKER_REQUEST);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate() || true){
                    if(!isChecked) {
                        Toast.makeText(PersonalDetails.this, "test", Toast.LENGTH_SHORT).show();
                        details = new Details(name.getEditText().getText().toString(),phno.getEditText().getText().toString(),mailid.getEditText().getText().toString(),lunchaddress.getEditText().getText().toString(),dinneraddress.getEditText().getText().toString());
                        Intent intentt = new Intent(PersonalDetails.this,FoodMenu.class);
                        startActivity(intentt);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LUNCH_PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String lunchAddress = place.getAddress().toString();
                lunchaddress.getEditText().setText(lunchAddress);
            }
        }else if(requestCode == DINNER_PLACE_PICKER_REQUEST){
//            if(requestCode == RESULT_OK){
            Place place = PlacePicker.getPlace(data, this);
            String dinnerAddress = place.getAddress().toString();
            dinneraddress.getEditText().setText(dinnerAddress);
//            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
