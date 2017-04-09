package fundroid.ixicode.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.ui.CityDetailsActivity;
import fundroid.ixicode.utils.AppUtils;

public class PointHorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_NORMAL = 1;
    private final int TYPE_DUMMY = 2;
    int typePos = 0;
    private Context context;
    private List<Point> dataList;

    public PointHorAdapter(Context context, List<Point> dataList, int pos) {
        this.context = context;
        this.dataList = dataList;
        this.typePos = pos;

//        Point point = new Point(true);
//        dataList.add(point);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_DUMMY) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point_hor_dummy, parent, false);
            return new DummyItemViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point_hor, parent, false);
            return new DetailItemViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).isDummy()) {
            return TYPE_DUMMY;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_DUMMY:
                setDummyData(holder, position);
                break;
            case TYPE_NORMAL:
                setPointData(((DetailItemViewHolder) holder), position);
                break;
        }
    }

    private void setPointData(DetailItemViewHolder holder, final int position) {
        final Point point = dataList.get(position);
        holder.tv_place_name.setText(point.getName());
        AppUtils.setImageUrl(holder.mItemImage, point.getKeyImageUrl(), R.drawable.def_back_w);
//        holder.tv_place_loc.setText(place.getLat() + ", " + place.getLng());
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity) context).showpointDetails(point);
            }
        });

    }

    private void setDummyData(RecyclerView.ViewHolder holder, int position) {
        // no specific action
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class DetailItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemImage;
        TextView tv_place_name;
        TextView tv_place_loc;
        RelativeLayout ll_main;

        private DetailItemViewHolder(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.iv_asset_dp);
            tv_place_name = (TextView) itemView.findViewById(R.id.tv_place_name);
            tv_place_loc = (TextView) itemView.findViewById(R.id.tv_place_loc);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_main);
        }
    }

    private class DummyItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_main;

        private DummyItemViewHolder(View itemView) {
            super(itemView);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
            ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((CityDetailsActivity) context).showAllPoints(typePos);
                }
            });
        }
    }

}