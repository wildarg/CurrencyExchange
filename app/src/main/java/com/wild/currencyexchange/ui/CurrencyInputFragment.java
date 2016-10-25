package com.wild.currencyexchange.ui;

// Created by Wild on 25.10.2016.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wild.currencyexchange.R;
import com.wild.currencyexchange.di.ModelFactory;
import com.wild.currencyexchange.domain.Currency;
import com.wild.currencyexchange.domain.CurrencyRepo;
import com.wild.currencyexchange.utils.SimpleTextWatcher;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyInputFragment extends Fragment {

    private static final String ARG_CURRENCY = "arg_currency";

    private CurrencyRepo repo;
    private Currency currency;

    @BindView(R.id.value_editor)
    EditText valueEditor;
    @BindView(R.id.currency_marker)
    TextView currencyMarker;

    public static CurrencyInputFragment newInstance(Currency currency) {
        CurrencyInputFragment f = new CurrencyInputFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CURRENCY, currency);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repo = ModelFactory.getCurrencyRepo();
        Bundle args = getArguments();
        currency = (Currency) args.getSerializable(ARG_CURRENCY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currency_input_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setCurrentValue(repo.get(currency));
        currencyMarker.setText(currency.name());
        valueEditor.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                repo.set(currency, getCurrentValue());
            }
        });
    }

    private void setCurrentValue(float v) {
        valueEditor.setText(String.format(Locale.getDefault(), "%.0f", v));
    }

    private float getCurrentValue() {
        try {
            float f = Float.valueOf(valueEditor.getText().toString());
            return f;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
