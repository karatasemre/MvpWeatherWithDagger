package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import mvpweatherwithdagger.com.mvpweatherwithdagger.MvpApp;
import mvpweatherwithdagger.com.mvpweatherwithdagger.R;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.ActivityComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.DaggerActivityComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.ActivityModule;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.PresenterModule;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.SharedPreferencesModule;


/**
 * Created by Emre.Karatas on 10.10.2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;

    @Inject
    ProgressDialog mProgressDialog;

    @Inject
    Resources mResources;

    protected abstract void inject(ActivityComponent activityComponent);

    protected abstract void initViews(View rootView, Bundle savedInstanceState);

    @LayoutRes
    protected abstract int layoutResourceID();

    @StringRes
    protected abstract int navigationTitleText();

    @MenuRes
    protected int optionsMenu() {
        return 0;
    }

    protected boolean isBackEnabled() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contentView = getLayoutInflater().inflate(layoutResourceID(), null);
        setContentView(contentView);

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .presenterModule(new PresenterModule())
                .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
                .applicationComponent(MvpApp.getApplicationComponent())
                .build();

        inject(mActivityComponent);

        initViews(contentView, savedInstanceState);
    }


    @Override
    public Resources resources() {
        return mResources;
    }

    @Override
    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    @Override
    public void showProgress() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setCanceledOnTouchOutside(false);
            if (!isFinishing()) {
                mProgressDialog.show();
            }
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog.isShowing()) {
            if (!isFinishing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    public void onError(int resId) {
        showAlertDialog(getString(resId));
    }

    @Override
    public void onError(String message) {
        showAlertDialog(message);
    }

    @Override
    public void showMessage(String message) {
        showAlertDialog(message);
    }

    @Override
    public void showMessage(int resId) {
        showAlertDialog(mResources.getString(resId));
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showAlertDialog(final String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        if (message != null) {
            builder.setMessage(Html.fromHtml(message));
        } else {
            builder.setMessage("");
        }
        builder.setPositiveButton(android.R.string.ok, null);

        if (!isFinishing()) {
            builder.show();
        }
    }
}
