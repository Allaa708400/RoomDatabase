package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.databinding.ActivityAddSalaryBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import database.Employee;
import database.MyViewModel;
import database.Salary;
import views.EmployeeSpinnerAdapter;

public class AddSalaryActivity extends AppCompatActivity {
ActivityAddSalaryBinding binding;
Calendar selectedDate;
MyViewModel myViewModel;
EmployeeSpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSalaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.btnPickSalaryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog = com.wdullaer.
                        materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                binding.btnPickSalaryDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                                selectedDate = Calendar.getInstance();
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, monthOfYear);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                            }
                        }, Calendar.getInstance());
                dialog.show(getSupportFragmentManager(),null);
            }

        });

        adapter = new EmployeeSpinnerAdapter(new ArrayList<>());
        binding.spEmployees.setAdapter(adapter);
        myViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);

                binding.btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strAmount = binding.etSalaryAmount.getText().toString();
                        long empId = binding.spEmployees.getSelectedItemId();
                        if (TextUtils.isEmpty(strAmount) || selectedDate==null){
                            Toast.makeText(AddSalaryActivity.this,
                                    "please enter a valid data", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        double amount = Double.parseDouble(strAmount);
                        Salary salary = new Salary(amount,selectedDate.getTime(),empId);
                        myViewModel.insertSalary(salary);
                        finish();
                    }
                });
            }
        });


    }


    }
