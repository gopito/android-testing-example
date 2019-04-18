package ru.android2019.testingexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import androidx.appcompat.app.AppCompatActivity;
import ru.android2019.testingexample.emailValidator.SettingsActivity;
import ru.android2019.testingexample.idlingResourceSample.IdlingActivity;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator;
    private Button settingsButton;
    private Button idlingButton;
    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;
    private EditText firstDigit;
    private EditText secondDigit;
    private TextView result;
    private AsyncTask<Void, Void, Void> sleepTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        sleepTask = new SleepAsyncTask();
//        sleepTask.execute();
        calculator = Calculator.getInstance();
        addButton = findViewById(R.id.add);
        firstDigit = findViewById(R.id.editText1);
        secondDigit = findViewById(R.id.editText2);



        result = findViewById(R.id.result);


        idlingButton = findViewById(R.id.idling);
        idlingButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IdlingActivity.class);
            startActivity(intent);
        });

        settingsButton = findViewById(R.id.settings);
        settingsButton.setOnClickListener(v -> {
//                sleepTask = new SleepAsyncTask();
//                sleepTask.execute();
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
        addButton.setOnClickListener(getOnCLickListener("+", firstDigit, secondDigit));
        subtractButton = findViewById(R.id.subtract);
        subtractButton.setOnClickListener(getOnCLickListener("-", firstDigit, secondDigit));
        multiplyButton = findViewById(R.id.multiply);
        multiplyButton.setOnClickListener(getOnCLickListener("*", firstDigit, secondDigit));
        divideButton = findViewById(R.id.divide);
        divideButton.setOnClickListener(getOnCLickListener("/", firstDigit, secondDigit));
    }


    private View.OnClickListener getOnCLickListener(final String operation, final TextView firstDigit, final TextView secondDigit) {
        return v -> {
            Integer first = calculator.convertDigit(firstDigit.getText().toString());
            Integer second = calculator.convertDigit(secondDigit.getText().toString());

            switch (operation) {
                case "+":
                    calculator.add(first, second);
                    break;
                case "-":
                    calculator.subtract(first, second);
                    break;
                case "*":
                    calculator.multiply(first, second);
                    break;
                case "/":
                    calculator.divide(first, second);
                    break;
            }

            if (calculator.getError().isEmpty()) {

                result.setText(String.valueOf(calculator.getResult()));
            } else {
                result.setText(calculator.getError());
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
//        new Handler().post(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });


//        new Handler().postDelayed(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, 400);


    }

    private class SleepAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }




}
