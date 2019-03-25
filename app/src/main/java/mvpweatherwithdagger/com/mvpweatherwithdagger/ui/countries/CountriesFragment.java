package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.countries;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mvpweatherwithdagger.com.mvpweatherwithdagger.R;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.newCountry.CountryModel;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.ActivityComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.MainActivity;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.adapter.CountriesAdapter;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BaseFragment;

public class CountriesFragment extends BaseFragment implements CountriesContract.Outputs {

    @Inject
    CountriesContract.Inputs<CountriesContract.Outputs> presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    CountriesAdapter countriesAdapter;
    private List<CountryModel> resultList = new ArrayList<>();

    public static CountriesFragment newInstance() {
        CountriesFragment countriesFragment = new CountriesFragment();

        return countriesFragment;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews(View rootView) {
        setRecyclerView();

        showProgress();
        if (!MainActivity.instance.isSettingsToCountry) {
            presenter.getCountries();
        } else {
            presenter.getLocalCountries();
        }

    }

    public void setRecyclerView() {
        countriesAdapter = new CountriesAdapter(resultList, (MainActivity) getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(countriesAdapter);
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }

    @Override
    protected int layoutResourceID() {
        return R.layout.fragment_countries;
    }

    @Override
    protected int navigationTitleText() {
        return 0;
    }

    @Override
    public void setCountries(List<CountryModel> countries) {
        countriesAdapter = new CountriesAdapter(countries, (MainActivity) getActivity());
        recyclerView.setAdapter(countriesAdapter);
        countriesAdapter.notifyDataSetChanged();
        hideProgress();
    }

    @Override
    public void countriesError(String errorMessage) {
        hideProgress();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void filteredCounties(String filterValue) {
        presenter.getFilteredValue(filterValue);
    }

    public void getSession(){
        presenter.getLocalCountries();
    }
}
