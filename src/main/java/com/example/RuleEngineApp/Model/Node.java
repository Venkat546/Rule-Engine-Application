package com.example.RuleEngineApp.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
    // Getters
    private String type; // "operand" or "operator"
    private String value; // Condition or operator (like "AND" or "OR")
    private Node left; // Left child
    private Node right; // Right child

    // Constructor for operand nodes
    public Node(String type, String value) {
        this.type = type;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Constructor for operator nodes
    public Node(String type, String value, Node left, Node right) {
        this.type = type;
        this.value = value;
        this.left = left;
        this.right = right;
    }

}
