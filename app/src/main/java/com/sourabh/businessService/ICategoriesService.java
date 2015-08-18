package com.sourabh.businessService;

import com.sourabh.entity.Category;
import com.sourabh.entity.Subcategory;
import com.sourabh.entity.Subcategory2;
import com.sourabh.entity.Subcategory3;

import java.util.ArrayList;

public interface ICategoriesService {

    ArrayList<Category> getCategories(String lastCategoryId);

    ArrayList<Subcategory> getSubcategories(String lastSubCategoryId);

    ArrayList<Subcategory2> getSubcategories2(String lastSubCategory2Id);

    ArrayList<Subcategory3> getSubcategories3(String lastSubcategory3Id);

}
