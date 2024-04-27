package com.nomad01;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Trigonometry#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Trigonometry extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText editTextValue;
    private EditText editTextAngle;
    private Spinner spinnerOperations;
    private ImageButton buttonCalculate;
    private TextView textViewResult;

    private String mParam1;
    private String mParam2;

    public Trigonometry() {
    }


    public static Trigonometry newInstance(String param1, String param2) {
        Trigonometry fragment = new Trigonometry();
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

        View view = inflater.inflate(R.layout.fragment_trigonometry, container, false);
        editTextValue = view.findViewById(R.id.edt_trigonometry_value);
        editTextAngle = view.findViewById(R.id.edt_trigonometry_angle);
        spinnerOperations = view.findViewById(R.id.sp_operations);
        buttonCalculate = view.findViewById(R.id.img_btn_trigonometry_calculate);
        textViewResult = view.findViewById(R.id.tv_trigonometry_result);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.trigonometry_operations,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperations.setAdapter(adapter);

        buttonCalculate.setOnClickListener(v -> calculateResult());
        return view;
    }
    @SuppressLint("SetTextI18n")
    private void calculateResult() {
        String valueInput = editTextValue.getText().toString().trim();
        String angleInput = editTextAngle.getText().toString().trim();

        if (valueInput.isEmpty() || angleInput.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter both value and angle", Toast.LENGTH_SHORT).show();
            return;
        }

        double value = Double.parseDouble(valueInput);
        double angle = Double.parseDouble(angleInput);
        String selectedOperation = spinnerOperations.getSelectedItem().toString();
        double result = 0;

        DecimalFormat df = new DecimalFormat("#.##");

        switch (selectedOperation) {
            case "Sin":
                result = value * Math.sin(Math.toRadians(angle));
                break;
            case "Cos":
                result = value * Math.cos(Math.toRadians(angle));
                break;
            case "Tan":
                result = value * Math.tan(Math.toRadians(angle));
                break;
        }

        String formattedResult = df.format(result);

        textViewResult.setText(selectedOperation + "(" + value + ", " + angle + ") = " + formattedResult);
    }
}