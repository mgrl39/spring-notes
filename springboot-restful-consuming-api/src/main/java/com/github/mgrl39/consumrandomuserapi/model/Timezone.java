package com.github.mgrl39.consumrandomuserapi.model;

/*
        "timezone": {
          "offset": "+9:30",
          "description": "Adelaide, Darwin"
        }
 */
public class Timezone {
    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String offset;
    private String description;
}
