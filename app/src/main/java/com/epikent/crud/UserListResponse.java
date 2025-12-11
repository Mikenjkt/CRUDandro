package com.epikent.crud;

import java.util.List;

public class UserListResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data; // The list of users

    public List<User> getData() {
        return data;
    }
}

