package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base;

/**
 * Created by Emre on 01/10/2018.
 */

public interface FragmentManagerNavigator {
    /**
     * @param fragment eklenecek fragment
     */
    void addFragment(BaseFragment fragment);

    /**
     * @param fragment eklenecek fragment ve backstack e eklenip eklenmeyecegini belirler.
     */
    void addFragment(BaseFragment fragment, boolean withAnimation, boolean addToBackStack);

    /**
     * fragment gecislerini yoneten metod
     *
     * @param fragment gosterilecek fragment
     */
    void replaceFragment(BaseFragment fragment, boolean withAnimation);


    /**
     * @param fragment hides fragment
     */
    void hideFragment(BaseFragment fragment, boolean withAnimation);


    /**
     * shows fragment
     *
     * @param fragment gosterilecek fragment
     */

    void showFragment(BaseFragment fragment, boolean withAnimation);

    /**
     * removes top fragment from stack and shows behind it
     */
    void popFragment(boolean withAnimation);

    /**
     * @param fragment kaldirilacak fragmentremoveFragment
     */
    void removeFragment(BaseFragment fragment);

    /**
     * returns visibleFragment
     */
    BaseFragment visibleFragment();
}
