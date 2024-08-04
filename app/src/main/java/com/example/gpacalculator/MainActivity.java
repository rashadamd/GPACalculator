package com.example.gpacalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    private Button gpaBtn;
    private TextView displayGpa, gpaName;
    private EditText course1, course2, course3, course4, course5, grade1, grade2, grade3, grade4, grade5, credit1, credit2, credit3, credit4, credit5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wiring
        gpaBtn = findViewById(R.id.btngpa);
        displayGpa = findViewById(R.id.displaygpa);
        gpaName = findViewById(R.id.gpaname);

        course1 = findViewById(R.id.course1);
        course2 = findViewById(R.id.course2);
        course3 = findViewById(R.id.course3);
        course4 = findViewById(R.id.course4);
        course5 = findViewById(R.id.course5);

        grade1 = findViewById(R.id.grade1);
        grade2 = findViewById(R.id.grade2);
        grade3 = findViewById(R.id.grade3);
        grade4 = findViewById(R.id.grade4);
        grade5 = findViewById(R.id.grade5);

        credit1 = findViewById(R.id.credit1);
        credit2 = findViewById(R.id.credit2);
        credit3 = findViewById(R.id.credit3);
        credit4 = findViewById(R.id.credit4);
        credit5 = findViewById(R.id.credit5);


        course1.setText("UCT32022");
        course2.setText("SWT32021");
        course3.setText("CIS32042");
        course4.setText("CMS32052");
        course5.setText("NST32021");
        credit1.setText("2");
        credit2.setText("1");
        credit3.setText("2");
        credit4.setText("2");
        credit5.setText("1");

        gpaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                double[] credits = new double[5];
                String[] grades = new String[5];

                //geting inputs
                credits[0] = getCreditValue(credit1);
                credits[1] = getCreditValue(credit2);
                credits[2] = getCreditValue(credit3);
                credits[3] = getCreditValue(credit4);
                credits[4] = getCreditValue(credit5);

                grades[0] = grade1.getText().toString().trim();
                grades[1] = grade2.getText().toString().trim();
                grades[2] = grade3.getText().toString().trim();
                grades[3] = grade4.getText().toString().trim();
                grades[4] = grade5.getText().toString().trim();

                double totalPoints = 0;
                int totalCredits = 0;


                //  total points, total credits
                for (int i = 0; i < 5; i++) {
                    double point = getPointForGrade(grades[i]);
                    totalPoints += point * credits[i];
                    totalCredits += credits[i];
                }

                // Calculate CGPA
                double cGPA = (totalCredits == 0) ? 0 : totalPoints / totalCredits;


                displayGpa.setText(String.format("CGPA: %.2f", cGPA));
                gpaName.setText("Your CGPA is:");
                displayGpa.setVisibility(View.VISIBLE);
                gpaName.setVisibility(View.VISIBLE);
            }
        });
    }

    private double getCreditValue(EditText creditEditText) {
        String creditText = creditEditText.getText().toString().trim();
        try {
            return Double.parseDouble(creditText);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double getPointForGrade(String grade) {
        switch (grade.toUpperCase()) {
            case "A+":
                return 4.0;
            case "A":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }
    }
}
