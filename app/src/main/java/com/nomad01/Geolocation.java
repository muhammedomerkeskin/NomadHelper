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
 * Use the {@link Geolocation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Geolocation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText currentLatitude, currentLongitude, currentMSL,
            destinationLatitude, destinationLongitude, destinationMSL;
    private TextView verticalDistance, horizontalDistance, yawAngle, PitchAngle;
    ImageButton geolocationCalculateButton,geolocationClearButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Geolocation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Geolocation.
     */
    // TODO: Rename and change types and number of parameters
    public static Geolocation newInstance(String param1, String param2) {
        Geolocation fragment = new Geolocation();
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

        View view = inflater.inflate(R.layout.fragment_geolocation, container, false);
        currentLatitude = view.findViewById(R.id.edt_geolocation_inputs_current_latitude);
        currentLongitude = view.findViewById(R.id.edt_geolocation_inputs_current_longitude);
        currentMSL = view.findViewById(R.id.edt_geolocation_inputs_current_msl);
        destinationLatitude = view.findViewById(R.id.edt_geolocation_inputs_destination_latitude);
        destinationLongitude = view.findViewById(R.id.edt_geolocation_inputs_destination_longitude);
        destinationMSL = view.findViewById(R.id.edt_geolocation_inputs_destination_msl);
        geolocationCalculateButton = view.findViewById(R.id.btn_geolocation_calculate);
        geolocationClearButton=view.findViewById(R.id.btn_geolocation_clear);
        verticalDistance = view.findViewById(R.id.tv_geolocation_height);
        horizontalDistance =  view.findViewById(R.id.tv_geolocation_air_distance);
        yawAngle =  view.findViewById(R.id.tv_geolocation_yaw_angle);
        PitchAngle =  view.findViewById(R.id.tv_geolocation_pitch_angle);
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
        currentMSL.addTextChangedListener(new TextWatcher() {
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
                    currentMSL.setError("Invalid Value!");
                } else {
                    currentMSL.setError(null);
                }
            }
        });
        destinationLatitude.addTextChangedListener(new TextWatcher() {
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
                    destinationLatitude.setError("Invalid Value!");
                } else {
                    destinationLatitude.setError(null);
                }
            }
        });
        destinationLongitude.addTextChangedListener(new TextWatcher() {
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
                    destinationLongitude.setError("Invalid Value!");
                } else {
                    destinationLongitude.setError(null);
                }
            }
        });
        destinationMSL.addTextChangedListener(new TextWatcher() {
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
                    destinationMSL.setError("Invalid Value!");
                } else {
                    destinationMSL.setError(null);
                }
            }
        });
        geolocationCalculateButton.setOnClickListener(v -> {

            if (TextUtils.isEmpty(currentLatitude.getText()) ||
                    TextUtils.isEmpty(currentLongitude.getText()) ||
                    TextUtils.isEmpty(currentMSL.getText()) ||
                    TextUtils.isEmpty(destinationLatitude.getText()) ||
                    TextUtils.isEmpty(destinationLongitude.getText()) ||
                    TextUtils.isEmpty(destinationMSL.getText())) {

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
            if (isValidInput((currentMSL.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Current MSL", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isValidInput((destinationLatitude.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Destination Latitude", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isValidInput((destinationLongitude.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Destination Longitude", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isValidInput((destinationMSL.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Destination MSL", Toast.LENGTH_SHORT).show();
                return;
            }
            calculateOutputs();
        });
        geolocationClearButton.setOnClickListener(v -> clearOutputs());

        // Inflate the layout for this fragment
        return view;
    }
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void calculateOutputs() {
        double Latitude_1 = Double.parseDouble(currentLatitude.getText().toString());
        double Longitude_1 = Double.parseDouble(currentLongitude.getText().toString());
        double MSL_1 = Double.parseDouble(currentMSL.getText().toString());
        double Latitude_2 = Double.parseDouble(destinationLatitude.getText().toString());
        double Longitude_2 = Double.parseDouble(destinationLongitude.getText().toString());
        double MSL_2 = Double.parseDouble(destinationMSL.getText().toString());
        LocationCalculation locationCalculation = new LocationCalculation();

        double height = locationCalculation.getVerticalDistance(MSL_1, MSL_2);
        double distance = locationCalculation.getHorizontalDistance(Latitude_1, Longitude_1, Latitude_2, Longitude_2);
        double yaw = locationCalculation.getBearing(Latitude_1, Longitude_1, Latitude_2, Longitude_2);
        double pitch = Math.toDegrees(Math.atan(height / distance));

        verticalDistance.setText("Height " + String.format("%.2f", height) + " M"
                + " | " + String.format("%.2f", height / 1000) + " KM");
        horizontalDistance.setText("Air Distance " + String.format("%.2f", distance) + " M"
                + " | " + String.format("%.2f", distance / 1000) + " KM");
        yawAngle.setText("Yaw Angle " + String.format("%.2f", yaw) + " °");
        PitchAngle.setText("Pitch Angle " + String.format("%.2f", pitch) + " °");
    }
    @SuppressLint("SetTextI18n")
    private void clearOutputs() {
        verticalDistance.setText("Height ");
        horizontalDistance.setText("Air Distance ");
        yawAngle.setText("Yaw Angle ");
        PitchAngle.setText("Pitch Angle ");
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