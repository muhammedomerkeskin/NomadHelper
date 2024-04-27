package com.nomad01;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Destination#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Destination extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText currentLatitude, currentLongitude, currentHeading,
            currentAirDistance;
    private TextView destinationLatitude, destinationLongitude;

    ImageButton calculateButton, clearButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Destination() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Destination.
     */
    // TODO: Rename and change types and number of parameters
    public static Destination newInstance(String param1, String param2) {
        Destination fragment = new Destination();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination, container, false);
        currentLatitude = view.findViewById(R.id.edt_destination_inputs_current_latitude);
        currentLongitude = view.findViewById(R.id.edt_destination_inputs_current_longitude);
        currentHeading = view.findViewById(R.id.edt_destination_inputs_current_heading);
        currentAirDistance = view.findViewById(R.id.edt_destination_inputs_air_distance);
        calculateButton=view.findViewById(R.id.btn_destination_calculate);
        clearButton=view.findViewById(R.id.btn_destination_clear);
        destinationLatitude=view.findViewById(R.id.tv_destination_latitude);
        destinationLongitude=view.findViewById(R.id.tv_destination_longitude);
        currentLatitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (isValidInput(input)) {
                    currentLatitude.setError("Invalid Value!");
                } else {
                    currentLatitude.setError(null);
                }
            }
        });
        currentLongitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (isValidInput(input)) {
                    currentLongitude.setError("Invalid Value!");
                } else {
                    currentLongitude.setError(null);
                }
            }
        });
        currentHeading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (isValidInput(input)) {
                    currentHeading.setError("Invalid Value!");
                } else {
                    currentHeading.setError(null);
                }
            }
        });
        currentAirDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (isValidInput(input)) {
                    currentAirDistance.setError("Invalid Value!");
                } else {
                    currentAirDistance.setError(null);
                }
            }
        });
        calculateButton.setOnClickListener(v -> {

            if (TextUtils.isEmpty(currentLatitude.getText()) ||
                    TextUtils.isEmpty(currentLongitude.getText()) ||
                    TextUtils.isEmpty(currentHeading.getText()) ||
                    TextUtils.isEmpty(currentAirDistance.getText())) {
                Toast.makeText(getActivity(), "Please fill in the blanks",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (isValidInput((currentLatitude.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Current Latitude", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isValidInput((currentLongitude.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Current Longitude", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isValidInput((currentHeading.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Current Heading", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isValidInput((currentAirDistance.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Current Horizontal Distance 2 Target", Toast.LENGTH_SHORT).show();
                return;
            }
            calculateOutputs();
        });
        clearButton.setOnClickListener(v -> clearOutputs());

        // Inflate the layout for this fragment
        return view;
    }
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void calculateOutputs() {
        double Latitude = Double.parseDouble(currentLatitude.getText().toString());
        double Longitude = Double.parseDouble(currentLongitude.getText().toString());
        double Heading = Double.parseDouble(currentHeading.getText().toString());
        double airDistance = Double.parseDouble(currentAirDistance.getText().toString());

        LocationCalculation locationCalculation = new LocationCalculation();

        double targetLatitude = locationCalculation.getTargetLocation(Latitude, Longitude, Heading, airDistance).get(0);
        double targetLongitude = locationCalculation.getTargetLocation(Latitude, Longitude, Heading, airDistance).get(1);


        destinationLatitude.setText("Destination Latitude " + String.format("%.5f", targetLatitude) + " °");
        destinationLongitude.setText("Destination Longitude " + String.format("%.5f", targetLongitude) + " °");
    }

    @SuppressLint("SetTextI18n")
    private void clearOutputs() {

        destinationLatitude.setText("Destination Latitude ");
        destinationLongitude.setText("Destination Longitude ");
    }

    private boolean isValidInput(String input) {

        try {
            Double.parseDouble(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}