package ca.bcit.heimdallr.ending_violence;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Owner on 3/5/16.
 */
public class Fragment3 extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1.
     */

    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment3,
                container, false);
        System.out.println("STARTED");
        new UserLoginTask().execute((Void) null);

        return view;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Void> {
        String response;
        protected Void doInBackground(Void... params){
            try {
                System.out.println("STARTED 2");
                SharedPreferences settings;
                String text;
                settings = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE); //1
                text = settings.getString("tokenVal", null);
                System.out.println("https://quiet-falls-67309.herokuapp.com/api/profile" +
                        "?api_token=" + text);

                response = run("https://quiet-falls-67309.herokuapp.com/api/profile" +
                "?api_token=" + text);

            } catch (Exception e){
                System.out.println("ERROR");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            try {
                TextView name = (TextView)getActivity().findViewById(R.id.NameLabel);
                TextView address = (TextView)getActivity().findViewById(R.id.AddressLabel);
                TextView phone = (TextView)getActivity().findViewById(R.id.PhoneNumberLabel);

                JSONObject userData = (JSONObject) new JSONObject(response).get("user_data");
                System.out.println(userData);

                name.setText(userData.get("first_name") + " " + userData.get("last_name"));
                phone.setText((String)userData.get("phone"));
                address.setText((String) userData.get("address"));
            } catch(Exception e){

            }
        }

    }
}
