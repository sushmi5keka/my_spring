package com.israt.carrentalproject.Jasper;


import com.israt.carrentalproject.Entity.BookingSummary;
import com.israt.carrentalproject.Entity.Car;
import com.israt.carrentalproject.Repo.BookingSummaryRepo;
import com.israt.carrentalproject.Repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("summaryService")
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    private BookingSummaryRepo bookingSummaryRepo;


    @Override
    public List<Map<String, Object>> summaryreport() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (BookingSummary bookingSummary : bookingSummaryRepo.findAll()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", bookingSummary.getId());
            item.put("totalFareAmount", bookingSummary.getTotalFareAmount());
            item.put("advanceFareAmount", bookingSummary.getAdvanceFareAmount());
            item.put("dueFareAmount", bookingSummary.getDueFareAmount());
            item.put("collectedAmount", bookingSummary.getCollectedAmount());
            //item.put("taskCriticalLevel", task.getTaskCriticalLevel().getLevel());
            // item.put("projectModule", task.getProjectModule().getTitle());
            // item.put("company", task.getCompany().getCompanyName());
//            item.put("dependentTaskStatus", task.isDependentTaskStatus());
            result.add(item);
        }
        return result;
    }



}

