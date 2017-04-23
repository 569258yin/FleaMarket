package com.agjsj.fleamarket.view.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.agjsj.fleamarket.util.LogUtil;

/**
 * Created by YH on 2017/4/23.
 */
public abstract class BaseFragment extends Fragment {

    protected FragmentActivity mActivity;
    /**
     * 根view
     */
    protected View mRootView;

    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;

    @Override
    public void onAttach(Context context) {
        LogUtil.info("BaseFragment onAttach");
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.info("BaseFragment onCreate");
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.info("BaseFragment onCreateView");
        View view = inflater.inflate(setLayoutResouceId(), container, false);
        ButterKnife.bind(this, view);
        initData(getArguments());
        initView();
        setListener();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (mRootView == null) {
            mRootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? mRootView : view, savedInstanceState);
    }


    /**
     * 初始化数据
     *
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected void initData(Bundle arguments) {

    }

    /**
     * 初始化View
     */
    protected void initView() {

    }

    /**
     * 设置监听事件
     */
    protected void setListener() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (mRootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }
    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {

    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        mRootView = null;
        isReuseView = true;
    }
    /**
     * 执行刷新方法
     */
    protected void refreshView() {

    }
    /**
     * 设置根布局资源id
     *
     * @return
     */
    protected abstract int setLayoutResouceId();

    /**
     * 启动指定Activity
     *
     * @param target
     * @param bundle
     */
    public void startActivity(Class<? extends Activity> target, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, target);
        if (bundle != null)
            intent.putExtra(getActivity().getPackageName(), bundle);
        getActivity().startActivity(intent);
    }

    protected void runOnMain(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }

    protected final static String NULL = "";
    private Toast toast;

    public void toast(final Object obj) {
        try {
            runOnMain(new Runnable() {

                @Override
                public void run() {
                    if (toast == null)
                        toast = Toast.makeText(getActivity(), NULL, Toast.LENGTH_SHORT);
                    toast.setText(obj.toString());
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        LogUtil.info("BaseFragment onDestroyView");
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        LogUtil.info("BaseFrame onDestroy");
        super.onDestroy();
    }
}
