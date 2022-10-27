package views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.databinding.CustomSalaryEmployeeItemBinding;

import java.util.List;

import database.DoubleValueListener;
import database.Employee;
import database.MyViewModel;

public class SalariesEmployeesRecyclerAdapter extends RecyclerView.
        Adapter<SalariesEmployeesRecyclerAdapter.SalEmpHolder> {
    private List<Employee> employees;
    private MyViewModel mvm;



    public SalariesEmployeesRecyclerAdapter(List<Employee> employees, MyViewModel mvm) {
        this.employees = employees;
        this.mvm = mvm;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }



    @NonNull
    @Override
    public SalariesEmployeesRecyclerAdapter.SalEmpHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        return new SalEmpHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.custom_salary_employee_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SalariesEmployeesRecyclerAdapter.
            SalEmpHolder holder, int position) {
        Employee e = employees.get(position);
        holder.bind(e,mvm);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

     class SalEmpHolder extends RecyclerView.ViewHolder {
        CustomSalaryEmployeeItemBinding binding;
        Employee employee;
        public SalEmpHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomSalaryEmployeeItemBinding.bind(itemView);
        }

        public void bind(Employee employee, MyViewModel mvm) {
            this.employee = employee;
            binding.tvName.setText(employee.getName());
            mvm.getSalarySum(employee.getId(), new DoubleValueListener() {
                @Override
                public void onValueSubmit(Double value) {
                    binding.tvSalary.setText(String.valueOf(value));
                }
            });
        }
    }
}
