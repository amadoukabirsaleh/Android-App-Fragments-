package hr.tvz.android.salehfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> products;

    ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public ProductAdapter (Context context, ArrayList<Product> list){
        products=list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivMake;
        TextView tvModel, tvOwner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMake = itemView.findViewById(R.id.ivMake);
            tvModel = itemView.findViewById(R.id.tvModel);
            tvOwner = itemView.findViewById(R.id.tvOwner);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(products.indexOf((Product) view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(products.get(position));


        viewHolder.tvOwner.setText(products.get(position).getOwnerName());
        viewHolder.tvModel.setText(products.get(position).getModel());

        if(products.get(position).getMake().equals("derma")){
            viewHolder.ivMake.setImageResource(R.drawable.derma);
        }
        else if(products.get(position).getMake().equals("aroma")){
            viewHolder.ivMake.setImageResource(R.drawable.aroma);
        }

        else if(products.get(position).getMake().equals("facehero")){
            viewHolder.ivMake.setImageResource(R.drawable.facehero);
        }
        else if(products.get(position).getMake().equals("lipo")){
            viewHolder.ivMake.setImageResource(R.drawable.lipo);
        }
        else if(products.get(position).getMake().equals("facetransparent")){
            viewHolder.ivMake.setImageResource(R.drawable.facetransparent);
        }

        else if(products.get(position).getMake().equals("lumin")){
            viewHolder.ivMake.setImageResource(R.drawable.lumin);
        }
        else if(products.get(position).getMake().equals("soyfacecleanser")){
            viewHolder.ivMake.setImageResource(R.drawable.soyfacecleanser);
        }
        else{

        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
