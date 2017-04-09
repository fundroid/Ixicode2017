package fundroid.ixicode.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.utils.AppUtils;

public class PointListVerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_DATA = 0;
    private final int TYPE_LOAD = 1;
    private OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;
    private List<Point> dataList;
    private Context context;

    public PointListVerAdapter(Context context, List<Point> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_DATA) {
            return new DataViewHoler(inflater.inflate(R.layout.item_point_ver, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.row_loading, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_DATA) {
            setPointData(((DataViewHoler)holder), position);
        }
        //No else part needed as load holder doesn't bind any data
    }

    private void setPointData(DataViewHoler holder, final int position){
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


    @Override
    public int getItemViewType(int position) {
//        Slog.d("item type : " + dataList.get(position).getType());
        if (dataList.get(position).isLoading()) {
            return TYPE_LOAD;
        } else {
            return TYPE_DATA;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /* VIEW HOLDERS */

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    private class DataViewHoler extends RecyclerView.ViewHolder {
        ImageView mItemImage;
        TextView tv_place_name;
        TextView tv_place_loc;
        RelativeLayout ll_main;

        public DataViewHoler(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.iv_asset_dp);
            tv_place_name = (TextView) itemView.findViewById(R.id.tv_place_name);
            tv_place_loc = (TextView) itemView.findViewById(R.id.tv_place_loc);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_main);
        }
    }

}