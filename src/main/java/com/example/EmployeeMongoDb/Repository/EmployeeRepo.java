package com.example.EmployeeMongoDb.Repository;

import com.example.EmployeeMongoDb.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepo  extends MongoRepository <Employee , Long> {
}
