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
    public ResponseEntity<Node> createRule(@RequestBody Map<String, String> payload) {
        String ruleString = payload.get("ruleString");
        Node ruleNode = ruleService.createRule(ruleString);

        if (ruleNode != null) {
            return ResponseEntity.ok(ruleNode);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/evaluate")
    public ResponseEntity<Boolean> evaluateRule(@RequestBody Map<String, Object> requestData) {
        try {
            Node ruleNode = ruleService.createRule("age > 30 AND salary > 50000");  // Example rule
            Map<String, Object> data = (Map<String, Object>) requestData.get("data");

            boolean result = ruleService.evaluateRule(ruleNode, data);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/combine")
    public ResponseEntity<Node> combineRules(@RequestBody List<String> ruleStrings) {
        Node combinedRule = ruleService.combineRules(ruleStrings);
        if (combinedRule != null) {
            return ResponseEntity.ok(combinedRule);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RuleEntity> saveRule(@RequestBody String ruleString) {
        RuleEntity ruleEntity = ruleService.saveRule(ruleString);
        return ResponseEntity.status(HttpStatus.CREATED).body(ruleEntity);
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
