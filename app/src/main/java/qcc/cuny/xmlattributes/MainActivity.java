package qcc.cuny.xmlattributes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    double yearEntered, loanEntered, intEntered, calculationPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        // Create the objects
        final TextView titleApp = findViewById(R.id.txtTitle);
        final TextView monthlyPayment = findViewById(R.id.txtMonthlyPayment);
        final ImageView image = findViewById(R.id.imgYears);
        final EditText years = findViewById(R.id.txtYears);
        final EditText loan = findViewById(R.id.txtLoan);
        final EditText interest = findViewById(R.id.txtInterest);
        final Button payment = findViewById(R.id.btnPayment);
        final Button endBtn = findViewById(R.id.btnPayment);
        //
        // When I click the button, the program should calculate the monthly payment
        // display another image and the monthly payment, and make the EditText invisible
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // convert the input string into double
                yearEntered = Double.parseDouble(years.getText().toString());
                loanEntered = Double.parseDouble(loan.getText().toString());
                intEntered = Double.parseDouble(interest.getText().toString());
                if (yearEntered > 6 || yearEntered < 1) {
                    Toast.makeText(MainActivity.this, "Please enter a year between 1 and 6", Toast.LENGTH_LONG).show();
                }
                else if (loanEntered > 5000 || loanEntered < 1000) {
                    Toast.makeText(MainActivity.this, "Please enter a loan  amount between $1000 and $5000", Toast.LENGTH_LONG).show();
                }
                else if (intEntered > 9 || intEntered < 1) {
                    Toast.makeText(MainActivity.this, "Please enter an interest rate between 1% and 9%", Toast.LENGTH_LONG).show();
                }
                else {
                    //
                    // calculate the monthly fee
                    calculationPayment = (loanEntered * (1 + yearEntered * (intEntered/100))) / (12 * yearEntered);
                    DecimalFormat payment = new DecimalFormat("$###,###.##");
                    //
                    // display results
                    image.setImageResource(R.drawable.money);
                    monthlyPayment.setText("Your monthly car payment is: " + payment.format(calculationPayment));
                    titleApp.setVisibility(View.INVISIBLE);
                    years.setVisibility(View.INVISIBLE);
                    loan.setVisibility(View.INVISIBLE);
                    interest.setVisibility(View.INVISIBLE);
                    endBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
