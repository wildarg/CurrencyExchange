package com.wild.currencyexchange.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wild.currencyexchange.R;
import com.wild.currencyexchange.domain.Currency;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.src_currency_pager)
    ViewPager srcPager;
    @BindView(R.id.src_pager_indicator)
    CircleIndicator srcIndicator;
    @BindView(R.id.dst_currency_pager)
    ViewPager dstPager;
    @BindView(R.id.dst_pager_indicator)
    CircleIndicator dstIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        srcPager.setAdapter(new CurrencyPageAdapter(getSupportFragmentManager()));
        srcIndicator.setViewPager(srcPager);
    }

    private class CurrencyPageAdapter extends FragmentPagerAdapter {

        public CurrencyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CurrencyInputFragment.newInstance(Currency.values()[position]);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


    public static class CurrencyFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.currency_layout, container, false);
        }
    }
}
