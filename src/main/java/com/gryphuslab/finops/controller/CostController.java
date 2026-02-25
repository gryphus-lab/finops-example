package com.gryphuslab.finops.controller;

import com.gryphuslab.finops.service.CostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/costs")
public class CostController {

    private final CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCosts(@RequestParam(required = false) String provider,
                                                        @RequestParam(required = false) String period) {
        return ResponseEntity.ok(costService.getCostSummary(provider, period));
    }
}
