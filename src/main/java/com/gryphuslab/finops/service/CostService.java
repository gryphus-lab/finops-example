package com.gryphuslab.finops.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CostService {

    public Map<String, Object> getCostSummary(String provider, String period) {
        Map<String, Object> m = new HashMap<>();
        m.put("provider", provider == null ? "aws" : provider);
        m.put("period", period == null ? "current" : period);
        m.put("total", 1234.56);
        m.put("currency", "USD");
        return m;
    }
}
