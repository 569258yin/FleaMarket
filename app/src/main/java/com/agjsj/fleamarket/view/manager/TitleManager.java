package com.agjsj.fleamarket.view.manager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agjsj.fleamarket.R;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.view.HomeUI;
import com.agjsj.fleamarket.view.MeUI;
import com.agjsj.fleamarket.view.goods.GoodsCategoryActivity;
import com.agjsj.fleamarket.view.school.SendLostFoundActivity;
import com.agjsj.fleamarket.view.search.SearchUI;

import com.agjsj.fleamarket.view.send.SendGoodActivity;
import org.apache.commons.lang3.StringUtils;

import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 管理标题容器的工具
 *
 * @author yh
 */
public class TitleManager implements Observer {
    private EditText searchEdit;
    private TextView back;
    private TextView title;
    private TextView category;
    private TextView tvRight;

    private static TitleManager titleManager = new TitleManager();
    private static final String TAG = "TilteManager";

    private RelativeLayout commomContainer;
    private RelativeLayout searchContainer;
    private Activity activity;

    private TitleManager() {
    }

    /**
     * 单列模式获取对象
     *
     * @return
     */
    public static TitleManager getInstance() {
        return titleManager;
    }
    //	public TitleManager(Activity activity){
    //		this.activity = activity;
    //		init(activity);
    //	}

    public void init(final Activity activity) {
        this.activity = activity;
        commomContainer = (RelativeLayout) activity
                .findViewById(R.id.ii_common_container);
        searchContainer = (RelativeLayout) activity
                .findViewById(R.id.ii_search_title);

        searchEdit = (EditText) searchContainer.findViewById(R.id.et_title_search_content);
        category = (TextView) searchContainer.findViewById(R.id.tv_title_category);
        back = (TextView) commomContainer.findViewById(R.id.tv_title_back);
        title = (TextView) commomContainer.findViewById(R.id.tv_title_text);
        tvRight = (TextView) commomContainer.findViewById(R.id.tv_title_right);
        setListener();
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, GoodsCategoryActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    public interface RightTvOnClickListen {
        public void OnClick();
    }

    private RightTvOnClickListen rightTvOnClickListen;

    public void setRightTvOnClickListen(RightTvOnClickListen rightTvOnClickListen) {
        this.rightTvOnClickListen = rightTvOnClickListen;
    }

    private void setListener() {
        searchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiddleManager.getInstance().changeUI(SearchUI.class);
            }
        });

    }

    //初始化
    private void initTitle() {
        tvRight.setVisibility(View.INVISIBLE);
        commomContainer.setVisibility(View.GONE);
        searchContainer.setVisibility(View.GONE);
    }

    //显示和隐藏
    public void showCommonTitle() {
        initTitle();
        if (rightTvOnClickListen != null) {
            tvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightTvOnClickListen.OnClick();
                }
            });
        }
        commomContainer.setVisibility(View.VISIBLE);
    }

    public void showSearchTitle() {
        initTitle();
        searchContainer.setVisibility(View.VISIBLE);
    }

    /**
     * 设置title的标题名
     *
     * @param title
     */
    public void changeTitle(String title) {
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data != null && StringUtils.isNumeric(data.toString())) {
            int id = Integer.parseInt(data.toString());
            switch (id) {
                case GlobalParams.VIEW_SEARCH:
                    initTitle();
                    break;
                case GlobalParams.VIEW_GOODSDETAIL:
                    title.setText("商品详情");
                    showCommonTitle();
                    back.setVisibility(View.VISIBLE);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MiddleManager.getInstance().goBack();
                        }
                    });
                    break;
                case GlobalParams.VIEW_HOME:
                    showSearchTitle();
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MiddleManager.getInstance().changeUI(HomeUI.class);
                        }
                    });
                    break;
                case GlobalParams.VIEW_SCHOOL:
                    showCommonTitle();
                    back.setVisibility(View.GONE);
                    title.setText("失物招领");
                    tvRight.setVisibility(View.VISIBLE);
                    tvRight.setText("发布");
                    tvRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity, SendLostFoundActivity.class);
                            activity.startActivity(intent);
                        }
                    });
                    break;
                case GlobalParams.VIEW_MESSSAGE:
                    showCommonTitle();
                    title.setText("消息");
                    tvRight.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.INVISIBLE);
                    break;
                case GlobalParams.VIEW_ME:
                    initTitle();
                    back.setVisibility(View.INVISIBLE);
                    break;
                case GlobalParams.VIEW_MY_SEND_GOODS:
                    showCommonTitle();
                    title.setText("我发布的宝贝");
                    tvRight.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.VISIBLE);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MiddleManager.getInstance().changeUI(MeUI.class);
                        }
                    });
                    break;
                case GlobalParams.VIEW_MY_SEND_FOUNDCASE:
                    showCommonTitle();
                    title.setText("我发布的失物招领");
                    back.setVisibility(View.VISIBLE);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MiddleManager.getInstance().changeUI(MeUI.class);
                        }
                    });
                    break;
                default:
                    initTitle();
                    break;
//			case ConstantValue.VIEW_HOME:
//				showUnLoginTitle();
//				break;
//			case ConstantValue.VIEW_MESSSAGE:
//			case ConstantValue.VIEW_SSQ:
//			case ConstantValue.VIEW_SHOPPING:
//			case ConstantValue.VIEW_LOGIN:
//			case ConstantValue.VIEW_PREBET:
//				showCommonTitle();
//				break;
//
//			case ConstantValue.VIEW_SCHOOL:
//				if (GlobalParams.isLogin) {
//					showLoginTitle();
//					String info = "用户名：" + GlobalParams.USERNAME + "\r\n" + "余额:" + GlobalParams.MONEY;
//					userInfo.setText(info);
//				} else {
//					showUnLoginTitle();
//				}
//				break;
            }
        }

    }
}
