package com.example.zzj;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAdapter extends BaseAdapter {

    private Context context;
    private List<Group> list;
    private Map<String, List<Items>> stringListMap = new HashMap<>();

    public GroupAdapter(Context context, List<Group> list ) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Group getItem(int i) {
        return list == null ? null : list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_out_group, viewGroup, false);
        }
        TextView tv_groupId = ViewHolder.get(view, R.id.groupId);
        TextView tv_groupName = ViewHolder.get(view, R.id.groupName);
        TextView tv_groupAllNum = ViewHolder.get(view, R.id.groupAllNum);
        ImageView addItem = ViewHolder.get(view, R.id.addItem);
        final CusListView listView = ViewHolder.get(view, R.id.cus_listView);

        final Group group = getItem(i);
        tv_groupId.setText(group.getGroupid());
        tv_groupName.setText(group.getGroupName());
        tv_groupAllNum.setText(group.getGroupAllNum());

        listView.setList(group.getItems(),group.getGroupName(),GroupAdapter.this,listView);
        stringListMap.put(group.getGroupName(), group.getItems());
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(group.getGroupName(),listView);
            }
        });
        return view;
    }

    public void deleteStringListMap(String groupNmae,int i,String groupName,CusListView listView) {
        stringListMap.get(groupNmae).remove(i);
        listView.setList(stringListMap.get(groupName),groupName,GroupAdapter.this,listView);
    }

    private ItemAdd itemAdd;
    private List<ItemAdd> itemAddList;
    private ItemAddAdapter itemAddAdapter;
    private void showDialog(final String groupName, final CusListView cusListView ) {
        itemAddList = new ArrayList<>();
        itemAddList.add(new ItemAdd(false, "1", "", ""));
        itemAddList.add(new ItemAdd(false, "2", "", ""));
        itemAddList.add(new ItemAdd(true, "3", "", ""));
        itemAddList.add(new ItemAdd(false, "4", "", ""));
        itemAddList.add(new ItemAdd(false, "5", "", ""));
        itemAddList.add(new ItemAdd(true, "6", "", ""));
        itemAddList.add(new ItemAdd(false, "7", "", ""));
        itemAddList.add(new ItemAdd(false, "8", "", ""));
        itemAddList.add(new ItemAdd(false, "9", "", ""));
        itemAddAdapter = new ItemAddAdapter(context, R.layout.adapter_out_item_add, itemAddList);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(context, R.layout.dialog, null);
        //设置对话框布局
        dialog.setView(dialogView);
        dialog.show();
        TextView textViewOk = (TextView) dialogView.findViewById(R.id.ok);
        TextView textViewOut = (TextView) dialogView.findViewById(R.id.out);
        final ListView listView = (ListView) dialogView.findViewById(R.id.ListView);
        listView.setAdapter(itemAddAdapter);
        textViewOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder str = new StringBuilder();
                str.append("-\n");
                for (int i = 0; i < itemAddAdapter.getItemAddList().size(); i++) {
                    str.append("序号：" + itemAddAdapter.getItemAddList().get(i).getItemid() + "，是否勾选：" + itemAddAdapter.getItemAddList().get(i).getCheck() + "\n");
                    if (itemAddAdapter.getItemAddList().get(i).getCheck()) {
                        Items items = new Items();
                        items.setItemid(itemAddAdapter.getItemAddList().get(i).getItemid());
                        items.setItemName(itemAddAdapter.getItemAddList().get(i).getItemid());
                        items.setItemAllNum(itemAddAdapter.getItemAddList().get(i).getItemid());
                        stringListMap.get(groupName).add(items);
                        cusListView.setList(stringListMap.get(groupName),groupName,GroupAdapter.this,cusListView);
                    }
                }
                Log.d("弹窗数据", str.toString());
                dialog.dismiss();
//                context.startActivity(new Intent(context, MainActivity.class));
//                MainActivity activity = (MainActivity) context;
//                activity.finish();
            }
        });
        textViewOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdd = null;
                itemAddList = null;
                itemAddAdapter = null;
                dialog.dismiss();
            }
        });
    }
}
