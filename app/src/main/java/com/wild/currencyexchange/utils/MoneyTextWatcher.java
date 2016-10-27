package com.wild.currencyexchange.utils;

// Created by Wild on 25.10.2016.

import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;

public class MoneyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable e) {
        String s = e.toString();
        String[] parts = s.replace(".", ",").split(",");

        AbsoluteSizeSpan[] spans = e.getSpans(0, s.length(), AbsoluteSizeSpan.class);
        if (spans != null)
            for (AbsoluteSizeSpan sp: spans)
                e.removeSpan(sp);

        e.setSpan(new AbsoluteSizeSpan(32, true), parts[0].length(), s.length(), 0);
    }
}
