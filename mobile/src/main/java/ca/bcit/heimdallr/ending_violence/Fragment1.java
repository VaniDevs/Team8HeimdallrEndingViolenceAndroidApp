package ca.bcit.heimdallr.ending_violence;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.content.Context;

/**
 * Created by Owner on 3/5/16.
 */
public class Fragment1 extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1.
     */
    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        SharedPreferences settings;
        String text;
        settings = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE); //1
        text = settings.getString("tokenVal", null);
        System.out.println(text + " HI");


        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

}
