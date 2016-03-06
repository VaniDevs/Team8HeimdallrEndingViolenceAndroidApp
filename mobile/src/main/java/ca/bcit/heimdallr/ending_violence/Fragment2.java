package ca.bcit.heimdallr.ending_violence;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;


/**
 * Created by Owner on 3/5/16.
 */
public class Fragment2 extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment2.
     */

    LinearLayout childLayoutV;
    LinearLayout prevThreatsV;
    LinearLayout currentLL;

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_fragment2,
                container, false);

        /* Add Child */
        childLayoutV = (LinearLayout) view.findViewById(R.id.ChildrenLayoutV);

        Button childButton = (Button) view.findViewById(R.id.addChildButton);
        childButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childLayoutV.addView(createNewLinearLayoutH());
                currentLL.addView(createNewTextView("Name", false));
                currentLL.addView(createNewTextView("Phone Number", false));
            }
        });

        /* Add PrevThreats */
        prevThreatsV = (LinearLayout) view.findViewById(R.id.PreviousThreatsV);
        Button prevButton = (Button) view.findViewById(R.id.addPreviousThreats);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevThreatsV.addView(createNewTextView("Name", true));
                prevThreatsV.addView(createNewLinearLayoutH());
                currentLL.addView(createNewTextView("Licence Plate",false));
                currentLL.addView(createNewCheckBox());
            }
        });

        return view;
    }

    private LinearLayout createNewLinearLayoutH(){
        LinearLayout LL = new LinearLayout(getActivity());
        final LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LL.setLayoutParams(lparams);
        LL.setOrientation(LinearLayout.HORIZONTAL);
        LL.setId(View.generateViewId());
        currentLL = LL;
        return LL;
    }

    private EditText createNewTextView(String text, boolean full) {
        int x;
        if(!full){
            x = ((getActivity().getResources().getDisplayMetrics().widthPixels)/2 - 50);
        } else {
            x = (getActivity().getResources().getDisplayMetrics().widthPixels);
        }
        final LayoutParams lparams = new LayoutParams(x, LayoutParams.MATCH_PARENT);
        final EditText editText = new EditText(getActivity());
        editText.setText(text);
        editText.setLayoutParams(lparams);
        return editText;
    }

    private CheckBox createNewCheckBox(){
        CheckBox cb = new CheckBox(getActivity());
        final LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        cb.setLayoutParams(lparams);
        cb.setText("Access To Weapons?");
        return cb;
    }
}