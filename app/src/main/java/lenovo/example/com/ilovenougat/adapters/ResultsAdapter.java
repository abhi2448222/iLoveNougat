package lenovo.example.com.ilovenougat.adapters;

/**
 * Created by Lenovo on 9/11/2016.
 */
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lenovo.example.com.ilovenougat.R;
import lenovo.example.com.ilovenougat.model.Result;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {
    List<Result> resultList;
    Context context;
    ItemOnClickListener itemOnClickListener;

    public static class ResultsViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnailImage;
        public TextView price, percentOff, brand,productName;
        public CardView container;

        public ResultsViewHolder(View v) {
            super(v);

            thumbnailImage = (ImageView) v.findViewById(R.id.item_thumbnail);
            productName = (TextView) v.findViewById(R.id.product_name);
            price = (TextView) v.findViewById(R.id.price);
            percentOff = (TextView) v.findViewById(R.id.percent_off);
            brand = (TextView) v.findViewById(R.id.brand_name);
            container = (CardView) v.findViewById(R.id.container);
        }
    }

    public ResultsAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ResultsAdapter.ResultsViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results_item, parent, false);

        ResultsViewHolder vh = new ResultsViewHolder(v);
        return vh;
    }

    //Displaying Product name, price, brand, percentage discount and thumbnail of the image in a cardView
    @Override
    public void onBindViewHolder(ResultsViewHolder holder, final int position) {


        holder.productName.setText(Html.fromHtml(resultList.get(position).getProductName()));
        holder.price.setText(String.valueOf(resultList.get(position).getPrice()));
        holder.brand.setText(Html.fromHtml(String.valueOf(resultList.get(position).getBrandName())));

        if(!resultList.get(position).getPercentOff().equals("0%")){
            holder.percentOff.setText(resultList.get(position).getPercentOff() + " off");
        }else{
            holder.percentOff.setVisibility(View.GONE);
        }

        Picasso.with(context).load(resultList.get(position).getThumbnailImageUrl()).into(holder.thumbnailImage);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClickListener.onItemClick(resultList.get(position));
            }
        });
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public interface ItemOnClickListener{
        void onItemClick(Result res);
    }
}
