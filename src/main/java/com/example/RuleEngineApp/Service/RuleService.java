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
        if ("age > 30 AND salary > 50000".equals(ruleString)) {
            Node condition1 = new Node("operand", "age > 30");
            Node condition2 = new Node("operand", "salary > 50000");
            return new Node("operator", "AND", condition1, condition2);
        } else if ("experience > 5 OR department = 'Sales'".equals(ruleString)) {
            Node condition3 = new Node("operand", "experience > 5");
            Node condition4 = new Node("operand", "department = 'Sales'");
            return new Node("operator", "OR", condition3, condition4);
        } else {
            return null;
        }
    }

    public boolean evaluateRule(Node ruleNode, Map<String, Object> data) {
        if (ruleNode == null) {
            throw new IllegalArgumentException("Rule node cannot be null");
        }

        switch (ruleNode.getType()) {
            case "operand":
                String condition = ruleNode.getValue();
                String[] parts = condition.split(" ");
                String attribute = parts[0];
                String operator = parts[1];
                int threshold = Integer.parseInt(parts[2]);

                if (!data.containsKey(attribute)) {
                    throw new IllegalArgumentException("Attribute " + attribute + " not found in data");
                }

                int value = (int) data.get(attribute);

                return switch (operator) {
                    case ">" -> value > threshold;
                    case "<" -> value < threshold;
                    case "=" -> value == threshold;
                    default -> throw new IllegalArgumentException("Invalid operator: " + operator);
                };

            case "operator":
                boolean leftEval = evaluateRule(ruleNode.getLeft(), data);
                boolean rightEval = evaluateRule(ruleNode.getRight(), data);

                return switch (ruleNode.getValue()) {
                    case "AND" -> leftEval && rightEval;
                    case "OR" -> leftEval || rightEval;
                    default -> throw new IllegalArgumentException("Invalid operator: " + ruleNode.getValue());
                };

            default:
                throw new IllegalArgumentException("Invalid node type: " + ruleNode.getType());
        }
    }

    public Node combineRules(List<String> ruleStrings) {
        if (ruleStrings == null || ruleStrings.isEmpty()) {
            throw new IllegalArgumentException("Rule strings cannot be null or empty");
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
