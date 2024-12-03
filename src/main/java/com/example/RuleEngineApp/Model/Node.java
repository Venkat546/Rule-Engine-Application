package com.example.RuleEngineApp.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
    private String type;
    private String value;
    private Node left;
    private Node right;

    public Node(String type, String value) {
        this.type = type;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node(String type, String value, Node left, Node right) {
        this.type = type;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // Getter methods (explicitly defining them, but @Getter from Lombok will automatically generate them as well)
    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
