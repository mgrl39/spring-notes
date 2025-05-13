package com.github.mgrl39.consumrandomuserapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
"name": {
        "title": "Miss",
        "first": "Jennie",
        "last": "Nichols"
      }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    private String title;
    private String first;
    private String last;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFirst() { return first; }
    public void setFirst(String first) { this.first = first; }

    public String getLast() { return last; };
    public void setLast(String last) { this.last = last; }

    public String getFullName() { return first + " " + last; }

    public String getFullTitle() { return title + " " + getFullName(); }
}
