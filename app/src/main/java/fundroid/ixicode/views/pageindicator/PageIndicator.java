package fundroid.ixicode.views.pageindicator;

/**
 * Created by pragati on 16/09/15.
 */

import android.support.v4.view.ViewPager;

/**
 * A PageIndicator is responsible to show an visual indicator on the total views
 * number and the current visible view.
 */
public interface PageIndicator extends ViewPager.OnPageChangeListener {
    /**
     * Bind the indicator to a ViewPager.
     *
     * @param view
     */
    void setViewPager(ViewPager view);

    /**
     * Bind the indicator to a ViewPager.
     *
     * @param view
     * @param initialPosition
     */
    void setViewPager(ViewPager view, int initialPosition);

    /**
     * <p>Set the current page_tut of both the ViewPager and indicator.</p>
     * <p/>
     * <p>This <strong>must</strong> be used if you need to set the page_tut before
     * the views are drawn on screen (e.g., default start page_tut).</p>
     *
     * @param item
     */
    void setCurrentItem(int item);

    /**
     * Set a page_tut change listener which will receive forwarded events.
     *
     * @param listener
     */
    void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);

    /**
     * Notify the indicator that the fragment list has changed.
     */
    void notifyDataSetChanged();
}
