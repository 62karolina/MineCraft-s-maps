package com.sss.carolina.minecraftsmaps;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by carolina on 08.06.17.
 */

public class MyArrayAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] names;

    public MyArrayAdapter(MainActivity context, String[] names) {
        super(context, R.layout.rowlayout, names);
        this.context = context;
        this.names = names;
    }


    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder буферизирует оценку различных полей шаблона элемента

        ViewHolder holder;
        // Очищает сущетсвующий шаблон, если параметр задан
        // Работает только если базовый шаблон для всех классов один и тот же
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowlayout, null, true);
            holder = new ViewHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.label);
            holder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }




        holder.textView.setText(names[position]);
        //Изменение иконок
        String s = names[position];
        if (s.startsWith("Fly Dream")) {
            holder.imageView.setImageResource(R.mipmap.fly_dream);
        } else if (s.startsWith("Halloween Town")) {
            holder.imageView.setImageResource(R.mipmap.halloween_town);
        } else if (s.startsWith("Tower Defense")){
            holder.imageView.setImageResource(R.mipmap.tower_defense);
        }

        return rowView;
    }

}
