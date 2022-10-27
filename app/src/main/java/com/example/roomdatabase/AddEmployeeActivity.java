package com.example.roomdatabase;

import static android.app.DatePickerDialog.*;

import static java.lang.reflect.Array.newInstance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.roomdatabase.databinding.ActivityAddEmployeeBinding;

import java.util.Calendar;

import database.Employee;

public class AddEmployeeActivity extends AppCompatActivity {
    ActivityAddEmployeeBinding binding;
    Calendar selectedDate;
    public static final String EMPLOYEE_KEY = "employee";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etEmpName2.getText().toString();
                String email = binding.etEmpEmail.getText().toString();
                String strEmpId = binding.etEmpId.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(strEmpId) || selectedDate==null){

                    Toast.makeText(AddEmployeeActivity.this,
                            "please enter a valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
              long id = Long.parseLong(strEmpId);
                Employee employee = new Employee(id,name,email,selectedDate.getTime());
                Intent intent = new Intent();
                intent.putExtra(EMPLOYEE_KEY,employee);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        binding.btnPickBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog = com.wdullaer.
                        materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                binding.btnPickBirthdate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                                selectedDate = Calendar.getInstance();
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, monthOfYear);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                            }
                        }, Calendar.getInstance());
                dialog.show(getSupportFragmentManager(),null);
            }

                });

    }
}