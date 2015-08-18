package com.sourabh.DTO;

import com.sourabh.entity.Category;
import com.sourabh.entity.Subcategory;
import com.sourabh.entity.Subcategory2;
import com.sourabh.entity.Subcategory3;

import java.util.ArrayList;

public class CategoriesAllDTO {

    ArrayList<Category> category;
    ArrayList<Subcategory> subcategory;
    ArrayList<Subcategory2> subcategory2;
    ArrayList<Subcategory3> subcategory3;

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public ArrayList<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(ArrayList<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }

    public ArrayList<Subcategory2> getSubcategory2() {
        return subcategory2;
    }

    public void setSubcategory2(ArrayList<Subcategory2> subcategory2) {
        this.subcategory2 = subcategory2;
    }

    public ArrayList<Subcategory3> getSubcategory3() {
        return subcategory3;
    }

    public void setSubcategory3(ArrayList<Subcategory3> subcategory3) {
        this.subcategory3 = subcategory3;
    }
}

