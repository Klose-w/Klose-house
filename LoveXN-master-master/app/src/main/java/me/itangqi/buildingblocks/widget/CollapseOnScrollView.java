package me.itangqi.buildingblocks.widget;

/**
 * Created by wade on 2016/5/2.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.OverScroller;
import android.widget.ScrollView;

import me.itangqi.buildingblocks.R;

/**
 * Created by kklassen on 21.11.2014.
 */
public class CollapseOnScrollView extends ScrollView {

    private ListView mLv;
    private float mLastY;
    private GestureDetector mGestureDetector;
    private Flinger mFlinger;
    private int mSlop;

    private View mExpandOnDragView;
    private int mExpandOnDragHeight;

    private View mCollapsibleView;

    private View mPinnedView;
    private int mPinnedViewHeight;

    public CollapseOnScrollView(Context context) {
        super(context);
        init(null);
    }

    public CollapseOnScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CollapseOnScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setVerticalScrollBarEnabled(false);

        mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mFlinger = new Flinger();
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                mFlinger.start((int) velocityY);
                return true;
            }
        });

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CollapseOnScrollView);
        final int pinnedViewId = typedArray.getResourceId(R.styleable.CollapseOnScrollView_stayVisibleId, -1);
        final int expandOnDragId = typedArray.getResourceId(R.styleable.CollapseOnScrollView_expandOnDragId, -1);
        typedArray.recycle();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                if (pinnedViewId >= 0) {
                    mPinnedView = findViewById(pinnedViewId);
                    mPinnedViewHeight = mPinnedView.getHeight();
                }

                if (expandOnDragId >= 0) {
                    mExpandOnDragView = findViewById(expandOnDragId);
                    mExpandOnDragHeight = mExpandOnDragView.getHeight();
                    mExpandOnDragView.getLayoutParams().height = 0;
                }

                mLv.getLayoutParams().height = getHeight() - mPinnedViewHeight;
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup root = (ViewGroup) getChildAt(0);
        mCollapsibleView = root.getChildAt(0);
        mLv = (ListView) root.getChildAt(1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                if (!mFlinger.isFinished()) {
                    mFlinger.stopFling();
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mLastY - y) > mSlop ) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mLastY = event.getY();
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float distance = mLastY - event.getY();
            int roundedDistance = Math.round(distance);
            scroll(roundedDistance, true);
            mLastY = event.getY();
        }
        return true;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        if (direction < 0) {
            return isCollapsed();
        } else {
            return isExpanded();
        }
    }

    private boolean isCollapsed() {
        if (mPinnedView == null) {
            return getScrollY() >= mCollapsibleView.getBottom();
        } else {
            return getScrollY() >= mPinnedView.getTop();
        }
    }

    private boolean isExpanded() {
        return getScrollY() <= 0;
    }

    private void scroll(int distance, boolean isDragging) {
        int remainingDistance = distance;

        if (mExpandOnDragView != null && isDragging) {
            ViewGroup.LayoutParams params = mExpandOnDragView.getLayoutParams();
            if (remainingDistance > 0 && params.height > 0) {
                params.height -= remainingDistance;
                if (params.height < 0) {
                    remainingDistance = -params.height;
                    params.height = 0;
                } else {
                    remainingDistance = 0;
                }
                mExpandOnDragView.setLayoutParams(params);
            } else if (distance < 0 && params.height < mExpandOnDragHeight && isExpanded() && isListReachedTop()) {
                params.height -= remainingDistance;
                if (params.height > mExpandOnDragHeight) {
                    remainingDistance = -(params.height - mExpandOnDragHeight);
                    params.height = mExpandOnDragHeight;
                } else {
                    remainingDistance = 0;
                }
                mExpandOnDragView.setLayoutParams(params);
            }
        }

        remainingDistance = scrollThis(remainingDistance);
        scrollList(remainingDistance);
    }

    private int scrollThis(int distance) {
        int lastScroll = getScrollY();
        scrollBy(0, distance);
        if (isCollapsed()) {
            if (mPinnedView == null) {
                scrollTo(0, mCollapsibleView.getBottom());
            } else {
                scrollTo(0, mPinnedView.getTop());
            }
            return distance ;
        } else if (isExpanded()) {
            return distance + lastScroll;
        }
        return 0;
    }

    private void scrollList(int dist) {
        mLv.smoothScrollBy(dist, 0);
    }

    private boolean isListReachedTop() {
        return mLv.getFirstVisiblePosition() == 0 && mLv.getChildAt(0).getTop() == 0;
    }

    private class Flinger implements Runnable {

        private OverScroller mScroller;
        private float mLastY;

        public Flinger() {
            mScroller = new OverScroller(getContext());
        }

        public void start(int initialVelocityY) {
            mLastY = 0;
            mScroller.fling(0, 0, 0, initialVelocityY, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation(this);
        }

        @Override
        public void run() {

            if (mScroller.isFinished()) {
                return;
            }

            mScroller.computeScrollOffset();
            float dist = mLastY - mScroller.getCurrY();
            scroll((int) dist, false);
            mLastY = mScroller.getCurrY();
            postOnAnimation(this);
        }

        private void stopFling() {
            mScroller.forceFinished(true);
        }

        public boolean isFinished() {
            return mScroller.isFinished();
        }
    }
}
