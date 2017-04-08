package fundroid.ixicode.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.model.City;
import fundroid.ixicode.ui.CityListActivity;

public class CityRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<City> dataList;

    public CityRecAdapter(Context context, List<City> dataList) {
        this.context = context;
        this.dataList = dataList;

//        Place asset = new Place(true);
//        dataList.add(asset);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_ver, parent, false);
        return new DetailItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        setAssetData(((DetailItemViewHolder) holder), position);
    }

    private void setAssetData(DetailItemViewHolder holder, final int position) {
        final City city = dataList.get(position);
        holder.tv_place_name.setText(city.getText());
        holder.tv_place_loc.setText(city.getCt());
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CityListActivity)context).goInCity(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class DetailItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_place_name;
        TextView tv_place_loc;
        LinearLayout ll_main;

        private DetailItemViewHolder(View itemView) {
            super(itemView);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
            tv_place_name = (TextView) itemView.findViewById(R.id.tv_place_name);
            tv_place_loc = (TextView) itemView.findViewById(R.id.tv_place_loc);
        }
    }
}