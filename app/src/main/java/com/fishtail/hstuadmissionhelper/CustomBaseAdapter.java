package com.fishtail.hstuadmissionhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String devList[];
    int devImages[];
    LayoutInflater inflater;


    public CustomBaseAdapter(Context context, String[] devList, int[] devImages) {
            this.context=context;
            this.devList=devList;
            this.devImages=devImages;
            inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return devList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.activity_custom_list_view, null);

        TextView textView=(TextView) view.findViewById(R.id.txt_dev_name);
        ImageView imageView=(ImageView) view.findViewById(R.id.dev_icon);

        textView.setText(devList[i]);
        imageView.setImageResource(devImages[i]);


        return view;
    }
}
