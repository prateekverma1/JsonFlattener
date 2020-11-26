package com.flatten.json;

import org.json.JSONObject;

class Node{
    Object key;
    Object val;
    Node[] links;
    public Node(Object key,Object value) {
        this.key = key;
        if(value instanceof JSONObject) {
            int numOfElements = ((JSONObject) value).keySet().size();
            links = new Node[numOfElements];
            int index =0;
            for(Object subkey :((JSONObject) value).keySet()) {
                this.links[index] = new Node(subkey,((JSONObject) value).get((String)subkey));
                index++;
            }
        }else {
            this.links = null;
            this.val = value;
        }
    }
}

