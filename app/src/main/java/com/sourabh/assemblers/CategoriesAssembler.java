package com.sourabh.assemblers;

import com.sourabh.entity.Category;
import com.sourabh.entity.Subcategory;
import com.sourabh.entity.Subcategory2;
import com.sourabh.entity.Subcategory3;
import com.sourabh.utility.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesAssembler {

    public ArrayList<Category> responseToCategoriesAll(String jsonString) {

        ArrayList<Category> categoryList = new ArrayList<Category>();


        JSONArray jarray;
        try {
            jarray = new JSONArray(jsonString);

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);
                //Add Category
                Category category = new Category();
                category.setId(jsonObject.getString("id"));
                category.setCategory(jsonObject.getString("category"));
                category.setDetail(jsonObject.getString("detail"));
                categoryList.add(category);

            }
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in checkIfUserSignedIn." + ex.getMessage());
        }
        return categoryList;


    }

    public ArrayList<Subcategory> responseToSubCategoriesAll(String jsonString) {

        ArrayList<Subcategory> subcategoryList = new ArrayList<Subcategory>();


        JSONArray jarray;
        try {
            jarray = new JSONArray(jsonString);

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);
                //Add Category
                Subcategory subcategory = new Subcategory();
                subcategory.setId(jsonObject.getString("id"));
                subcategory.setCategoryId(jsonObject.getString("category"));
                subcategory.setSubcategory(jsonObject.getString("subcategory"));
                subcategoryList.add(subcategory);

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subcategoryList;


    }

    public ArrayList<Subcategory2> responseToSubCategories2All(String jsonString) {

        ArrayList<Subcategory2> subcategory2List = new ArrayList<Subcategory2>();


        JSONArray jarray;
        try {
            jarray = new JSONArray(jsonString);

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);
                //Add Category
                Subcategory2 subcategory2 = new Subcategory2();
                subcategory2.setId(jsonObject.getString("id"));
                subcategory2.setSubcategoryId(jsonObject.getString("subcategoryid"));
                subcategory2.setSubcategory2(jsonObject.getString("subcategory2"));
                subcategory2List.add(subcategory2);

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subcategory2List;


    }

    public ArrayList<Subcategory3> responseToSubCategories3All(String jsonString) {

        ArrayList<Subcategory3> subcategory3List = new ArrayList<Subcategory3>();


        JSONArray jarray;
        try {
            jarray = new JSONArray(jsonString);

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);
                //Add Category
                Subcategory3 subcategory3 = new Subcategory3();
                subcategory3.setId(jsonObject.getString("id"));
                subcategory3.setSubcategory2Id(jsonObject.getString("subcategory2id"));
                subcategory3.setSubcategory3(jsonObject.getString("subcategory3"));
                subcategory3List.add(subcategory3);

            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subcategory3List;


    }

}
