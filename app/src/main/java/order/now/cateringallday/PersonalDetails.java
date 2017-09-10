package order.now.cateringallday;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import mehdi.sakout.fancybuttons.FancyButton;

public class PersonalDetails extends AppCompatActivity {

    Toolbar toolbar;
    CheckBox checkBox;
    TextInputLayout name, phno, mailid, lunchaddress, dinneraddress;
    FancyButton lunchBtn, dinnerBtn;
    FloatingActionButton fab;
    Boolean isChecked, valid;

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
        return true;
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
    }
}
