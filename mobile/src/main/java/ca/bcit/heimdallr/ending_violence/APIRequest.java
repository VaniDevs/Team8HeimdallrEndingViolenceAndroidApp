package ca.bcit.heimdallr.ending_violence;

import android.support.v7.app.AppCompatActivity;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by janycasas on 2016-03-06.
 */
public class APIRequest extends AppCompatActivity{


    static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static OkHttpClient okHttpClient = new OkHttpClient();

    static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /* JSON RETURNS */
    static String updateProfileJSON(String firstName, String lastName, String addres, String phoneNumber) {

        return "{\"first_name\":\"" + firstName + "\","
                + "\"last_name\":\"" + lastName + "\","
                + "\"address\":\"" + addres + "\","
                + "\"phone\":\"" + phoneNumber + "\""
                + "}";
    }

    static String jsonIncidents(double locationX, double locationY) {

        return "{\"location\":\"" + locationY + " " +  locationX + "\""
                + "}";
    }
}
