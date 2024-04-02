package ru.inno.x_clients.http;


import io.restassured.http.ContentType;
import ru.inno.x_clients.http.model.Company;
import ru.inno.x_clients.http.model.CreateCompany;

import static io.restassured.RestAssured.given;

public class WebClient {
    public static final String URL = "https://x-clients-be.onrender.com/company";
    public static final String URL_ID = "https://x-clients-be.onrender.com/company/{id}";
    public static final String URL_AUTH = "https://x-clients-be.onrender.com/auth/login";

    public String getToken(String login, String pass) {
        String creds = "{\"username\": \"" + login + "\",\"password\": \"" + pass + "\"}";

        // авторизоваться
        return given().log().all()
                .body(creds)
                .contentType(ContentType.JSON)
                .when().post(URL_AUTH)
                .then().log().all()
                .extract().path("userToken");
    }

    public int createCompany(String name, String desc, String token) {
        CreateCompany createCompany = new CreateCompany(name, desc);

        // создать компанию
        return given()
                .log().all()
                .body(createCompany)
                .header("x-client-token", token)
                .contentType(ContentType.JSON)
                .when()
                .post(URL)
                .then()
                .log().all()
                .extract().path("id");
    }

    public Company getCompanyInfo(int compId) {
        return given()
                .log().all()
                .pathParams("id", compId)
                .get(URL_ID)
                .then()
                .log().all()
                .extract().as(Company.class);
    }
}