package com.example.trainreservation.jasper;

import com.example.trainreservation.entity.SeatOrCabin;
import com.example.trainreservation.repo.SeatOrCabinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private SeatOrCabinRepo seatOrCabinRepo;


    @Override
    public List<Map<String, Object>> seatreport() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (SeatOrCabin seatOrCabin : seatOrCabinRepo.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", seatOrCabin.getId());
            item.put("seatNo", seatOrCabin.getSeatNo());
            item.put("cabinNo", seatOrCabin.getCabinNo());
            //item.put("taskCriticalLevel", task.getTaskCriticalLevel().getLevel());
            // item.put("projectModule", task.getProjectModule().getTitle());
            // item.put("company", task.getCompany().getCompanyName());
//            item.put("dependentTaskStatus", task.isDependentTaskStatus());
            result.add(item);
        }
        return result;
    }
}

