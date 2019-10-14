package com.hotelbeds.supplierintegrations.controller;

import com.hotelbeds.supplierintegrations.model.TimeComparatorDTO;
import com.hotelbeds.supplierintegrations.service.TimeComparatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.hotelbeds.supplierintegrations.constant.Constant.BASE_API;

@RestController
@RequestMapping(BASE_API + "/comparator")
public class TimeComparatorController {

    private final TimeComparatorService timeComparatorService;

    public TimeComparatorController(final TimeComparatorService timeComparatorService) {
        this.timeComparatorService = timeComparatorService;
    }

    @PostMapping(path = "/time-comparator")
    public Integer timeComparator(@RequestBody @Valid final TimeComparatorDTO timeComparator) {
        return timeComparatorService.compareTwoTimeStamp(timeComparator.getTime1(), timeComparator.getTime2());
    }
}
