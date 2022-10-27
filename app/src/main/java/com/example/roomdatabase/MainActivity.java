package com.example.roomdatabase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.roomdatabase.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import database.Employee;
import database.MyViewModel;
import views.SalariesEmployeesRecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    //Employ{id,name,birthdate,email}
    //Salary{id,amount,date,empId}
    SalariesEmployeesRecyclerAdapter adapter;
    ActivityMainBinding binding;
MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);



        adapter = new SalariesEmployeesRecyclerAdapter(new ArrayList<>(),myViewModel);

        binding.mainRv.setAdapter(adapter);
        binding.mainRv.setHasFixedSize(true);
        binding.mainRv.setLayoutManager(new LinearLayoutManager(this));




        myViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });

        ActivityResultLauncher<Intent> arl = registerForActivityResult(new ActivityResultContracts.
                StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK && result.getData()!=null){

                    Employee employee = (Employee) result.getData().getSerializableExtra
                            (AddEmployeeActivity.EMPLOYEE_KEY);
                    myViewModel.insertEmployee(employee);
                }



            }
        });

        binding.fabAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), AddEmployeeActivity.class);
                arl.launch(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.add_salary:
               Intent intent = new Intent(this, AddSalaryActivity.class);
               startActivity(intent);
               return true;
       }
       return false;
    }
}