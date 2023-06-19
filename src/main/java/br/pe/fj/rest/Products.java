package br.pe.fj.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;

public class Products {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Test
    public void validateProductList() {
        String responseBody  =    given()
                .log().all()
                .contentType(ContentType.JSON)
       .when()
                .get("/productsList")
       .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString()
                ;

        JSONObject json = new JSONObject(responseBody);
        int responseCode = json.getInt("responseCode");
        JSONArray productsArray = json.getJSONArray("products");

        // Validate response and products
        assert responseCode == 200;
        assert productsArray != null;

        // Iterate over products
        for (int i = 0; i < productsArray.length(); i++) {
            JSONObject product = productsArray.getJSONObject(i);
            int productId = product.getInt("id");

            // Check if the current product has desired ID
            if (productId == 11) {
                String productName = product.getString("name");
                String productPrice = product.getString("price");
                String productBrand = product.getString("brand");
                String productCategory = product.getJSONObject("category").getString("category");

                // Validate the product attributes
                assert productName.equals("Sleeves Printed Top - White");
                assert productPrice.equals("Rs. 499");
                assert productBrand.equals("Babyhug");
                assert productCategory.equals("Tops & Shirts");

                // Print product information
                System.out.println("ID: " + productId);
                System.out.println("Nome: " + productName);
                System.out.println("Preço: " + productPrice);
                System.out.println("Marca: " + productBrand);
                System.out.println("Categoria: " + productCategory);
                System.out.println();

                // Break the loop as we found the desired product
                break;

            }
        }
    }
}