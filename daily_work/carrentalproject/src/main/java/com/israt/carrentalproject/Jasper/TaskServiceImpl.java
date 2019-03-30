package com.israt.carrentalproject.Jasper;


import com.israt.carrentalproject.Entity.Car;
import com.israt.carrentalproject.Repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private CarRepo carRepo;


    @Override
    public List<Map<String, Object>> carreport() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Car car : carRepo.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", car.getId());
            item.put("carModel", car.getCarModel());
            item.put("color", car.getColor());
            item.put("noOfSeats", car.getNoOfSeats());
            //item.put("taskCriticalLevel", task.getTaskCriticalLevel().getLevel());
            // item.put("projectModule", task.getProjectModule().getTitle());
            // item.put("company", task.getCompany().getCompanyName());
//            item.put("dependentTaskStatus", task.isDependentTaskStatus());
            result.add(item);
        }
        return result;
    }


}

