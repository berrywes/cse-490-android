package com.example.berry.helpcustomers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.models.Product;
import com.example.berry.helpcustomers.models.User;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{

    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_products, parent, false);
        return new ProductsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewName.setText(product.getName());
        holder.textViewCategory.setText(product.getCategory());
        holder.textViewPrice.setText(product.getPrice());
        holder.textViewStatus.setText(product.getStatus());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName, textViewCategory, textViewPrice, textViewStatus;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);

        }
    }


}
