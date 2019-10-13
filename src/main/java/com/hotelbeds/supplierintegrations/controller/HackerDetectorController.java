package com.hotelbeds.supplierintegrations.controller;

import com.hotelbeds.supplierintegrations.service.HackerDetectorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/hacker/detector")
public class HackerDetectorController {

    private final HackerDetectorService hackerDetectorService;

    public HackerDetectorController(final HackerDetectorService hackerDetectorService) {
        this.hackerDetectorService = hackerDetectorService;
    }

    @PostMapping(path = "/process-line")
    public String processLineHackerDetector(@RequestBody @NotNull final String processLine) {
        return hackerDetectorService.parseLine(processLine);
    }
}
