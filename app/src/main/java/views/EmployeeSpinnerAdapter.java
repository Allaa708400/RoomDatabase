package views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.roomdatabase.R;

import java.util.List;

import database.Employee;

public class EmployeeSpinnerAdapter extends BaseAdapter {
    private List<Employee> employees;

    public EmployeeSpinnerAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return employees.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v==null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner_item
            , null, false);
        }
        TextView tv = v.findViewById(R.id.tv_emp_name);
        Employee e = (Employee) getItem(position);
        tv.setText(e.getName());
        return v;
    }
}
