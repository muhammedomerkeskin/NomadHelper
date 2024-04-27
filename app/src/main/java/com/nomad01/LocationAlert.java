package com.nomad01;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationAlert#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationAlert extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText alarmLatitude,alarmLongitude,alarmDistance;
    ImageButton setButton,cancelButton;
    private Vibrator vibrator;
    private boolean isSet=false;
    private TextView alarmDestinationLocation, alarmCurrentLocation, alarmAirDistance;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LocationAlert() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationAlert.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationAlert newInstance(String param1, String param2) {
        LocationAlert fragment = new LocationAlert();
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
        View view = inflater.inflate(R.layout.fragment_location_alert, container, false);
        alarmLatitude = view.findViewById(R.id.edt_alert_inputs_destination_latitude);
        alarmLongitude =  view.findViewById(R.id.edt_alert_inputs_destination_longitude);
        alarmDistance =  view.findViewById(R.id.edt_alert_inputs_destination_distance);
        setButton = view.findViewById(R.id.img_btn_alert_set);
        cancelButton = view.findViewById(R.id.img_btn_alert_cancel);
        alarmDestinationLocation = view.findViewById(R.id.tv_alert_destination_location);
        alarmCurrentLocation = view.findViewById(R.id.tv_alert_current_location);
        alarmAirDistance = view.findViewById(R.id.tv_alert_air_distance);
        alarmLatitude.addTextChangedListener(new TextWatcher() {
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
                    alarmLatitude.setError("Invalid Value!");
                    isSet=false;
                } else {
                    alarmLatitude.setError(null);
                }
            }
        });
        alarmLongitude.addTextChangedListener(new TextWatcher() {
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
                    alarmLongitude.setError("Invalid Value!");
                    isSet=false;
                } else {
                    alarmLongitude.setError(null);
                }
            }
        });
        alarmDistance.addTextChangedListener(new TextWatcher() {
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
                    alarmDistance.setError("Invalid Value!");
                    isSet=false;
                } else {
                    alarmDistance.setError(null);
                }
            }
        });

        setButton.setOnClickListener(v -> {

            if (TextUtils.isEmpty(alarmLatitude.getText()) ||
                    TextUtils.isEmpty(alarmLongitude.getText()) ||
                    TextUtils.isEmpty(alarmDistance.getText())) {
                Toast.makeText(getActivity(), "Please fill in the blanks",
                        Toast.LENGTH_SHORT).show();
                isSet=false;
                return;
            }
            if (isValidInput((alarmLatitude.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Alarm Latitude", Toast.LENGTH_SHORT).show();
                isSet=false;
                return;
            }
            if (isValidInput((alarmLongitude.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Alarm Longitude", Toast.LENGTH_SHORT).show();
                isSet=false;
                return;
            }
            if (isValidInput((alarmDistance.getText().toString()))) {
                Toast.makeText(getActivity(), "Please fill correct Value in " +
                        "Alarm Distance", Toast.LENGTH_SHORT).show();
                isSet=false;
                return;
            }
            isSet=true;

        });
        cancelButton.setOnClickListener(v -> {
            alarmDestinationLocation.setText("Destination Location");
            alarmAirDistance.setText("Air Distance");
            isSet=false;
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            getLocation();
        }

        vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);


        return view;
    }
    private void getLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            startLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    alarmCurrentLocation.setText("Current Location " + String.format("%.5f", latitude) + "," + String.format("%.5f", longitude));
                    if (isSet) {

                        alarmDestinationLocation.setText("Destination Location "+ alarmLatitude.getText().toString() + "," + alarmLongitude.getText().toString());
                        LocationCalculation locationCalculation = new LocationCalculation();
                        double distance = locationCalculation.getHorizontalDistance(Double.parseDouble(alarmLatitude.getText().toString()), Double.parseDouble(alarmLongitude.getText().toString()), latitude, longitude);
                        alarmAirDistance.setText("Air Distance "+String.format("%.3f", distance));
                        if (distance < Double.parseDouble(alarmDistance.getText().toString())) {
                            vibratePhone();
                            Toast.makeText(getActivity(), "You are near of your destination ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }, null);
    }

    private void vibratePhone() {
        vibrator.vibrate(1500);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startLocationUpdates();
            } else {
                Toast.makeText(getActivity(), "Location permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
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