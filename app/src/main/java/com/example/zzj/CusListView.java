package com.example.zzj;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class CusListView extends LinearLayout {

    public CusListView(Context context) {
        this(context, null);
    }

    public CusListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CusListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    public void setList(final List<Items> list, final String groupName, final GroupAdapter groupAdapter, final CusListView listView) {
        if (list == null) {
            throw new RuntimeException("list is null");
        }
        int count = list.size();
        removeAllViews();
        for (int i = 0; i < count; i++) {
            final Items items = list.get(i);
            final View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_out_item , null);
            TextView tv_itemId = f(view, R.id.itemId);
            TextView tv_itemName = f(view, R.id.itemName);
            TextView tv_itemAllNum = f(view, R.id.itemAllNum);
            ImageView deleteItem = f(view, R.id.deleteItem);
            tv_itemId.setText(items.getItemid());
            tv_itemName.setText(items.getItemName());
            tv_itemAllNum.setText(items.getItemAllNum());
            final int finalI = i;
            deleteItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("删除项的索引", finalI + "，groupName：" + groupName);
                    groupAdapter.deleteStringListMap(groupName, finalI,groupName,listView);
                    removeView(view);
                }
            });
            addView(view);
        }
    }

    private <T extends View> T f(View view, int id) {
        return (T) view.findViewById(id);
    }
}
