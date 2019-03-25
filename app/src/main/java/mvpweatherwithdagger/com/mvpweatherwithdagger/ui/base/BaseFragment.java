package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.ActivityComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.utils.CommonUtils;

/**
 * Created by Emre.Karatas on 7.11.2018.
 */

public abstract class BaseFragment extends Fragment implements MvpView, FragmentManagerNavigator {

    FragmentManagerNavigator mNavigator = null;

    BaseActivity mActivity;

    @Nullable
    private Unbinder viewUnbinder;

    private View rootView;

    protected abstract void inject(ActivityComponent activityComponent);

    protected abstract void initViews(View rootView);

    protected abstract void attachView();

    protected abstract void detachView();

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

    private ProgressDialog mProgressDialog;


    @Override
    public void onDestroyView() {
        if (viewUnbinder != null) {
            viewUnbinder.unbind();
        }
        super.onDestroyView();
    }

    /**
     * Fragment Lifecycle
     */


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentManagerNavigator) {
            mNavigator = (FragmentManagerNavigator) context;
        }

        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(layoutResourceID(), container, false);

        viewUnbinder = ButterKnife.bind(this, rootView);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            inject(component);
        }

        attachView();
        initViews(rootView);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }

    public void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * Mvp View Implementation
     */

    @Override
    public Resources resources() {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        return mActivity.resources();
    }

    @Override
    public ActivityComponent getActivityComponent() {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        return mActivity.getActivityComponent();
    }

    @Override
    public void showProgress() {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideProgress() {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(int resId) {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        mActivity.onError(resId);
    }

    @Override
    public void onError(String message) {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        mActivity.onError(message);
    }

    @Override
    public void showMessage(String message) {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        mActivity.showMessage(message);
    }

    @Override
    public void showMessage(int resId) {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        mActivity.showMessage(resId);
    }

    @Override
    public void hideKeyboard() {
        if (mActivity == null) {
            throw new RuntimeException("Parent activity must extend BaseActivity.");
        }

        mActivity.hideKeyboard();
    }

    /**
     * Fragment Manager Navigator
     */

    @Override
    public void addFragment(BaseFragment fragment) {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        mNavigator.addFragment(fragment);
    }

    @Override
    public void addFragment(BaseFragment fragment, boolean withAnimation, boolean addToBackStack) {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        mNavigator.addFragment(fragment, withAnimation, addToBackStack);
    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean withAnimation) {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        mNavigator.replaceFragment(fragment, withAnimation);
    }

    @Override
    public void hideFragment(BaseFragment fragment, boolean withAnimation) {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        mNavigator.hideFragment(fragment, withAnimation);
    }

    @Override
    public void showFragment(BaseFragment fragment, boolean withAnimation) {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        mNavigator.showFragment(fragment, withAnimation);
    }

    @Override
    public void popFragment(boolean withAnimation) {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        mNavigator.popFragment(withAnimation);
    }

    @Override
    public void removeFragment(BaseFragment fragment) {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        mNavigator.removeFragment(fragment);
    }

    @Override
    @Nullable
    public BaseFragment visibleFragment() {
        if (mNavigator == null) {
            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
        }

        return mNavigator.visibleFragment();
    }

    /**
     * Dashboard Tab Push
     */

    public void pushFragment(BaseFragment fragment) {
//        if(mActivity instanceof DashboardActivity) {
//            ((DashboardActivity)mActivity).pushFragment(fragment);
//        }
//        else {
//            throw new RuntimeException("Parent activity must implement FragmentManagerNavigator.");
//        }
    }
}
