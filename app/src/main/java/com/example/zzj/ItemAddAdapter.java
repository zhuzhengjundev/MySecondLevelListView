package com.example.zzj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class ItemAddAdapter extends ArrayAdapter<ItemAdd> {

    private Context mContext;
    private int resourceId;
    private List<ItemAdd> itemAddList;

    public ItemAddAdapter(Context context, int resource, List<ItemAdd> itemAddList) {
        super(context, resource, itemAddList);
        mContext = context;
        resourceId = resource;
        this.itemAddList = itemAddList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemAdd itemAdd = getItem(position);
        View view;
        final ItemAddAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ItemAddAdapter.ViewHolder();
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.CheckBox);
            viewHolder.textView_itemId = (TextView) view.findViewById(R.id.itemId);
            viewHolder.textView_itemName = (TextView) view.findViewById(R.id.itemName);
            viewHolder.textView_itemAllNum = (TextView) view.findViewById(R.id.itemAllNum);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ItemAddAdapter.ViewHolder) view.getTag();
        }

        viewHolder.textView_itemId.setText(itemAdd.getItemid());
        viewHolder.textView_itemName.setText(itemAdd.getItemName());
        viewHolder.textView_itemAllNum.setText(itemAdd.getItemAllNum());

        if (itemAdd.getCheck()) {
            viewHolder.checkBox.setChecked(true);
        }else {
            viewHolder.checkBox.setChecked(false);
        }
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemAddList.get(position).getCheck()) {
                    itemAddList.get(position).setCheck(false);
                }else {
                    itemAddList.get(position).setCheck(true);
                }
            }
        });

        return view;
    }

    public List<ItemAdd> getItemAddList() {
        return itemAddList;
    }

    private class ViewHolder {
        CheckBox checkBox;
        TextView textView_itemId;
        TextView textView_itemName;
        TextView textView_itemAllNum;
    }
}