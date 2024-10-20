package com.example.RuleEngineApp.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
    // Getters
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

}
