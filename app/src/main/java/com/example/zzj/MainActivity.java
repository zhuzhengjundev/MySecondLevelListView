package com.example.zzj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Group> list = new ArrayList<>();
    GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        listView = (ListView) findViewById(R.id.listView);
        adapter = new GroupAdapter(this, list);
        listView.setAdapter(adapter);
        initData();
    }

    private void initData() {
        list.clear();
        adapter.notifyDataSetChanged();
        try {
            JSONArray jsonArray = new JSONArray("[{\"groupid\":\"100\",\"groupName\":\"物料A\",\"groupAllNum\":\"100\",\"items\":[{\"itemid\":\"1001\",\"itemName\":\"L1\",\"itemAllNum\":\"20\"},{\"itemid\":\"1002\",\"itemName\":\"L2\",\"itemAllNum\":\"30\"}]},{\"groupid\":\"200\",\"groupName\":\"物料B\",\"groupAllNum\":\"200\",\"items\":[{\"itemid\":\"2001\",\"itemName\":\"L3\",\"itemAllNum\":\"50\"},{\"itemid\":\"2002\",\"itemName\":\"L4\",\"itemAllNum\":\"30\"},{\"itemid\":\"2003\",\"itemName\":\"L7\",\"itemAllNum\":\"30\"},{\"itemid\":\"2004\",\"itemName\":\"L1\",\"itemAllNum\":\"30\"}]},{\"groupid\":\"300\",\"groupName\":\"物料C\",\"groupAllNum\":\"300\",\"items\":[{\"itemid\":\"3001\",\"itemName\":\"L5\",\"itemAllNum\":\"10\"},{\"itemid\":\"3002\",\"itemName\":\"L6\",\"itemAllNum\":\"70\"},{\"itemid\":\"3003\",\"itemName\":\"L3\",\"itemAllNum\":\"70\"}]},{\"groupid\":\"400\",\"groupName\":\"物料D\",\"groupAllNum\":\"200\",\"items\":[{\"itemid\":\"4001\",\"itemName\":\"L6\",\"itemAllNum\":\"90\"},{\"itemid\":\"4002\",\"itemName\":\"L3\",\"itemAllNum\":\"10\"},{\"itemid\":\"4003\",\"itemName\":\"L6\",\"itemAllNum\":\"90\"},{\"itemid\":\"4004\",\"itemName\":\"L3\",\"itemAllNum\":\"10\"},{\"itemid\":\"4005\",\"itemName\":\"L6\",\"itemAllNum\":\"90\"},{\"itemid\":\"4006\",\"itemName\":\"L3\",\"itemAllNum\":\"10\"}]}]");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Group group = new Group();
                group.setGroupid(jsonObject.getString("groupid"));
                group.setGroupName(jsonObject.getString("groupName"));
                group.setGroupAllNum(jsonObject.getString("groupAllNum"));
                JSONArray jsonArrayItem = jsonObject.getJSONArray("items");
                List<Items> itemList = new ArrayList<>();
                for (int j = 0; j < jsonArrayItem.length(); j++) {
                    JSONObject jsonObjectItem = jsonArrayItem.getJSONObject(j);
                    Items items = new Items();
                    items.setItemid(jsonObjectItem.getString("itemid"));
                    items.setItemName(jsonObjectItem.getString("itemName"));
                    items.setItemAllNum(jsonObjectItem.getString("itemAllNum"));
                    itemList.add(items);
                }
                group.setItems(itemList);
                list.add(group);
                Log.d("长度", list.size() + "");
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_F1://F1按钮

                break;
            case KeyEvent.KEYCODE_F2://F2按钮

                break;
            case KeyEvent.KEYCODE_BACK://后退按钮
            case KeyEvent.KEYCODE_ESCAPE:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
