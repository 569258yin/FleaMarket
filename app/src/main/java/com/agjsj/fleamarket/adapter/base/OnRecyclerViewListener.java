package com.agjsj.fleamarket.adapter.base;

/**
 * 为RecycleView添加点击事件
 * @project OnRecyclerViewListener
 * @date 2016-03-03-16:39
 */
public interface OnRecyclerViewListener {
    void onItemClick(int position);

    /**
     * 点击item里面的某个空间，第二个参数是该控件的id
     * @param position
     * @param id
     */
    void onItemClick(int position, int id);

    boolean onItemLongClick(int position);
}
