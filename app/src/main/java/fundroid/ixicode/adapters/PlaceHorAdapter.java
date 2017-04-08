package fundroid.ixicode.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.model.Place;
import fundroid.ixicode.utils.AppUtils;

public class PlaceHorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Place> dataList;
    private final int TYPE_NORMAL = 1;
    private final int TYPE_DUMMY = 2;

    public PlaceHorAdapter(Context context, List<Place> dataList) {
        this.context = context;
        this.dataList = dataList;

//        Place asset = new Place(true);
//        dataList.add(asset);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_DUMMY){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recom_rec_hor_dummy, parent, false);
            return new DummyItemViewHolder(itemView);
        }else{
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recom_rec_hor, parent, false);
            return new DetailItemViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(dataList.get(position).isDummy()){
            return TYPE_DUMMY;
        }else{
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
                setAssetData(holder, position);
                break;
        }
    }

    private void setAssetData(RecyclerView.ViewHolder holder, final int position){

        Place place = dataList.get(position);
        AppUtils.setImageUrl(((DetailItemViewHolder) holder).mItemImage, place.getImage(), R.drawable.box_white_trans);
    }

    private void setDummyData(RecyclerView.ViewHolder holder, int position){
        // no specific action
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private class DetailItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemImage;
        TextView tv_asset_name;
        RelativeLayout ll_main;

        private DetailItemViewHolder(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.iv_asset_dp);
            tv_asset_name = (TextView) itemView.findViewById(R.id.tv_asset_name);
            ll_main = (RelativeLayout) itemView.findViewById(R.id.ll_main);
        }
    }

    private class DummyItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_add;

        private DummyItemViewHolder(View itemView) {
            super(itemView);
//            iv_add = (ImageView) itemView.findViewById(R.id.iv_asset_dp);
//            iv_add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ((BaseActivity)context).gotoAddAsset();
//                }
//            });
        }
    }
}