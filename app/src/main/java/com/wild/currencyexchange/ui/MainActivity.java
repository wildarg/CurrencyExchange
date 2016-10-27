package com.wild.currencyexchange.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wild.currencyexchange.R;
import com.wild.currencyexchange.domain.Currency;
import com.wild.currencyexchange.services.RateRefreshService;
import com.wild.currencyexchange.utils.BroadcastUtils;

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

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private Currency currency = Currency.USD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        srcPager.setAdapter(new CurrencyPageAdapter(getSupportFragmentManager()));
        srcIndicator.setViewPager(srcPager);
        srcPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                setCurrentCurrency(Currency.values()[position]);
            }
        });
        dstPager.setAdapter(new CurrencyExchangePageAdapter(getSupportFragmentManager()));
        dstIndicator.setViewPager(dstPager);

        bindService(new Intent(this, RateRefreshService.class), conn, BIND_AUTO_CREATE);
    }

    private void setCurrentCurrency(Currency c) {
        this.currency = c;
        BroadcastUtils.notifyCurrencyChanged(this, currency);
    }

    public Currency getCurrentCurrency() {
        return currency;
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
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

    private class CurrencyExchangePageAdapter extends FragmentPagerAdapter {

        public CurrencyExchangePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CurrencyExchangeFragment.newInstance(Currency.values()[position]);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
