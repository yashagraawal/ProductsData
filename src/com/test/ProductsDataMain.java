package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.test.model.Product;
import com.test.model.Product.Rating;

public class ProductsDataMain {
	public static void main(String[] args) {
		// Fetch JSON data from API
        String apiUrl = "https://fakestoreapi.com/products";
        String jsonData = fetchDataFromApi(apiUrl);
        
//        System.out.println(jsonData);

        // Map JSON data to Java objects
        List<Product> productList = mapJsonToObjects(jsonData);
        
//        System.out.println(productList);

        // Insert data into MySQL database
        insertDataIntoDatabase(productList);
	}
	
	private static String fetchDataFromApi(String apiUrl) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    private static List<Product> mapJsonToObjects(String jsonData) {
        Gson gson = new Gson();
        Product[] products = gson.fromJson(jsonData, Product[].class);
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(product);
        }
        return productList;
    }

    private static void insertDataIntoDatabase(List<Product> productList) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "**********";

        String query = "INSERT INTO products (id, title, price, description, category, image, rating_rate, rating_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Product p = new Product();
        p.setId(2);
        p.setTitle("sadadsdsad");
        p.setPrice((float)103.20);
        p.setDescription("adlkjdbAJDBLSJDBSADSDSD");
        p.setCatagory("clothing");
        p.setImage("https://asdajdad.com/dakjdbaksjd");
        p.setRating(p.new Rating((float)3.5, 20));
        
        productList.add(p);
        
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (Product product : productList) {
                statement.setInt(1, product.getId());
                statement.setString(2, product.getTitle());
                statement.setDouble(3, product.getPrice());
                statement.setString(4, product.getDescription());
                statement.setString(5, product.getCatagory());

                // Set image bytes
                String imageBytes = product.getImage();
                if (imageBytes != null) {
                    statement.setString(6, imageBytes);
                } else {
                    statement.setNull(6, Types.BLOB);
                }

                statement.setDouble(7, product.getRating().getRate());
                statement.setInt(8, product.getRating().getCount());
                statement.addBatch();
            }
            
            int[] result = statement.executeBatch();
            System.out.println("Records Inserted! \n");
            
//            List<Integer> res = Arrays.stream(result).boxed().collect(Collectors.toList());
//            List<Integer> res = new ArrayList<Integer>();
//            
//            for(int i=0;i<result.length;i++) {
//            	if(result[i] != 1) {
//            		res.add(i);
//            	}
//            }
//            
//            if(res.size() > 0) {
//            	System.out.println("Following records doesn't inserted properly: ");
//            	for(int n:res) {
//            		System.out.println(productList.get(n));
//            	}
//            }else {
//            	System.out.println("Records Inserted! \n");
//            }
            
        } catch (SQLException e) {
        	System.out.println("Something Went Wrong!\nFailure with Following SQL state:\n"+e.getSQLState()+"\nMessage:\n"+ e.getMessage());
//            e.printStackTrace();
        }
    }

}