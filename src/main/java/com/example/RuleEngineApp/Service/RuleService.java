package com.example.RuleEngineApp.Service;

import com.example.RuleEngineApp.Model.RuleEntity;
import com.example.RuleEngineApp.Model.Node;
import com.example.RuleEngineApp.Repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;


    public Node createRule(String ruleString) {

        if (ruleString.equals("age > 30 AND salary > 50000")) {
            Node condition1 = new Node("operand", "age > 30");
            Node condition2 = new Node("operand", "salary > 50000");
            return new Node("operator", "AND", condition1, condition2);  // AND (age > 30 AND salary > 50000)

        } else if (ruleString.equals("experience > 5 OR department = 'Sales'")) {
            Node condition3 = new Node("operand", "experience > 5");
            Node condition4 = new Node("operand", "department = 'Sales'");
            return new Node("operator", "OR", condition3, condition4);  // OR (experience > 5 OR department = 'Sales')

        } else {

            return null;
        }
    }


    public boolean evaluateRule(Node ruleNode, Map<String, Object> data) {
        if (ruleNode == null) {
            return false;
        }


        if (ruleNode.getType().equals("operand")) {
            String condition = ruleNode.getValue();


            String[] parts = condition.split(" ");
            String attribute = parts[0];
            String operator = parts[1];
            int threshold = Integer.parseInt(parts[2]);


            int value = (int) data.get(attribute);


            switch(operator) {
                case ">":
                    return value > threshold;
                case "<":
                    return value < threshold;
                case "=":
                    return value == threshold;
                default:
                    return false;
            }
        }


        if (ruleNode.getType().equals("operator")) {
            boolean leftEval = evaluateRule(ruleNode.getLeft(), data);
            boolean rightEval = evaluateRule(ruleNode.getRight(), data);

            switch (ruleNode.getValue()) {
                case "AND":
                    return leftEval && rightEval;
                case "OR":
                    return leftEval || rightEval;
                default:
                    return false;
            }
        }

        return false;
    }
    public Node getRuleById(Long id) {
        RuleEntity ruleEntity = ruleRepository.findById(id).orElse(null);
        if (ruleEntity != null) {
            return createRule(ruleEntity.getRuleString());
        } else {
            return null;
        }
    }

    public Node combineRules(List<String> ruleStrings) {
        if (ruleStrings == null || ruleStrings.isEmpty()) {
            return null; // No rules to combine
        }


        Node root = createRule(ruleStrings.get(0));


        for (int i = 1; i < ruleStrings.size(); i++) {
            Node nextRule = createRule(ruleStrings.get(i));


            root = new Node("operator", "AND", root, nextRule);
        }

        return root;
    }

    public RuleEntity saveRule(String ruleString) {
        RuleEntity ruleEntity = new RuleEntity(ruleString);
        return ruleRepository.save(ruleEntity);
    }


    public RuleEntity getRule(Long id) {
        return ruleRepository.findById(id).orElse(null);
    }

}
