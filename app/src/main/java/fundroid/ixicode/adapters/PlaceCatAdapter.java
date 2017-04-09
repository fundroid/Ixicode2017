package fundroid.ixicode.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.model.City;
import fundroid.ixicode.ui.CityListActivity;

public class PlaceCatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> dataList;

    public PlaceCatAdapter(Context context, ArrayList<String> dataList) {
        this.context = context;
        this.dataList = dataList;

//        Place asset = new Place(true);
//        dataList.add(asset);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_cat, parent, false);
        return new DetailItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        setAssetData(((DetailItemViewHolder) holder), position);
    }

    private void setAssetData(DetailItemViewHolder holder, final int position) {
        holder.tv_place_name.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class DetailItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_place_name;

        private DetailItemViewHolder(View itemView) {
            super(itemView);
            tv_place_name = (TextView) itemView.findViewById(R.id.tv_place_name);
        }
    }
}