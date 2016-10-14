package lsw.system.member.business.utility;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by swli on 10/13/2016.
 */
public abstract class MySpinnerAdapter<T> implements SpinnerAdapter {

    private Context context;
    private ArrayList<T> array;

    public abstract String getTValue(T t);

    public MySpinnerAdapter(Context context, ArrayList<T> array)
    {
        this.context = context;
        this.array = array;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setTextSize(20);
        textView.setText(getTValue(array.get(position)));
        return textView;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setTextSize(20);
        textView.setText(getTValue(array.get(position)));
        return textView;
    }
}
