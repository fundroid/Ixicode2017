package fundroid.ixicode.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.model.PointContainer;
import fundroid.ixicode.ui.CityDetailsActivity;

/**
 * Created by Sagar Sagar Verma on 25-01-2017.
 */

public class CityPointVerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PointContainer> dataList;
    private Context context;

    public CityPointVerAdapter(Context context, List<PointContainer> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new DataHolder(inflater.inflate(R.layout.item_city_point_ver, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        updateData(holder, position);
        //No else part needed as load holder doesn't bind any data
    }

    private void updateData(final RecyclerView.ViewHolder holder, final int position) {
        ((DataHolder) holder).setName(dataList.get(position).getName());
        ((DataHolder) holder).stRecAdapter(dataList.get(position).getPoints(), position);
        ((DataHolder) holder).tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CityDetailsActivity) context).showAllPoints(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_all;
        private RecyclerView rv_points;

        public DataHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_point_name);
            tv_all = (TextView) itemView.findViewById(R.id.tv_all);
            rv_points = (RecyclerView) itemView.findViewById(R.id.rv_points);
            rv_points.setHasFixedSize(false);
            rv_points.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));// Setting the layout Manager
        }

        public void setName(String name) {
            tv_name.setText(name);
        }

        public void stRecAdapter(ArrayList<Point> points, int position) { // position defines type here
            rv_points.setAdapter(new PointHorAdapter(context, points, position));
        }
    }
}