package com.example.berry.helpcustomers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.interfaces.FragmentCommunication;
import com.example.berry.helpcustomers.models.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{

    private Context mCtx;
    private List<Product> productList;
    private FragmentCommunication mCommunicator;


    public ProductsAdapter(Context mCtx, List<Product> productList, FragmentCommunication communication) {

        this.mCtx = mCtx;
        this.productList = productList;
        this.mCommunicator = communication;


    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_products, parent, false);
        ProductsViewHolder holder = new ProductsViewHolder(view, mCommunicator);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewNameProduct.setText(product.getName());
        holder.textViewCategory.setText(product.getCategory());
        holder.textViewPrice.setText(product.getPrice());
        holder.textViewStatus.setText(product.getStatus());

        Log.i("ProductAdapterName", product.getName());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewNameProduct, textViewCategory, textViewPrice, textViewStatus;
        Button editProductButton;
        FragmentCommunication mCommunication;

        public ProductsViewHolder(@NonNull View itemView, FragmentCommunication Communicator) {
            super(itemView);

            textViewNameProduct = itemView.findViewById(R.id.textViewNameProduct);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            mCommunication=Communicator;
            editProductButton = itemView.findViewById(R.id.editProductButton);
            editProductButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            Log.i("ProductAdapterOnClick", String.valueOf(productList.get(getAdapterPosition()).getCategory()));

            Log.i("ProductAdapterOnClick", Integer.toString((productList.get(getAdapterPosition()).getProduct_id())));
            mCommunication.respond( productList.get(getAdapterPosition()).getProduct_id());
        }
    }
}
