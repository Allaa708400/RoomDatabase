package database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.jar.Attributes;

@Entity(tableName = "Employee_table")

public class Employee implements Serializable {

    //Employ{id,name,birthdate,email}
    @PrimaryKey
    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    private Date brithDate;


    public Employee(long id, String name, String email, Date brithDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.brithDate = brithDate;
    }

    public Employee() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(Date brithDate) {
        this.brithDate = brithDate;
    }
}
