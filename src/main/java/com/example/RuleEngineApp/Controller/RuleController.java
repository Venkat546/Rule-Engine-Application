package com.example.RuleEngineApp.Controller;

import com.example.RuleEngineApp.Model.Node;
import com.example.RuleEngineApp.Model.RuleEntity;
import com.example.RuleEngineApp.Service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;



    @PostMapping("/create")
    public Node createRule(@RequestBody Map<String, String> payload) {
        String ruleString = payload.get("ruleString");
        return ruleService.createRule(ruleString);
    }


    @PostMapping("/evaluate")
    public boolean evaluateRule(@RequestBody Map<String, Object> requestData) {

        Node ruleNode = ruleService.createRule("age > 30 AND salary > 50000");  // Example rule
        Map<String, Object> data = (Map<String, Object>) requestData.get("data");


        return ruleService.evaluateRule(ruleNode, data);
    }

    @PostMapping("/combine")
    public Node combineRules(@RequestBody List<String> ruleStrings) {
        return ruleService.combineRules(ruleStrings);
    }

    @PostMapping("/save")
    public RuleEntity saveRule(@RequestBody String ruleString) {
        return ruleService.saveRule(ruleString);
    }


    public RuleEntity getRule(@PathVariable Long id) {
        return ruleService.getRule(id);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<RuleEntity> getRuleById(@PathVariable("id") Long id) {
        RuleEntity rule = ruleService.getRule(id);
        if (rule != null) {
            return ResponseEntity.ok(rule);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}