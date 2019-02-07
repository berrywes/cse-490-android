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

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

    private Context mCtx;
    private List<Product> productList;
    private FragmentCommunication mCommunicator;


    public ResultsAdapter(Context mCtx, List<Product> productList, FragmentCommunication communication) {

        this.mCtx = mCtx;
        this.productList = productList;
        this.mCommunicator = communication;


    }

    @NonNull
    @Override
    public ResultsAdapter.ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_search, parent, false);
        ResultsAdapter.ResultsViewHolder holder = new ResultsAdapter.ResultsViewHolder(view, mCommunicator);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.ResultsViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewSearchName.setText(product.getName());
        holder.textviewSearchDescription.setText(product.getDescription());
        holder.textViewSearchPrice.setText(product.getPrice());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewSearchName, textviewSearchDescription, textViewSearchPrice;
        Button selectProductButton;
        FragmentCommunication mCommunication;

        public ResultsViewHolder(@NonNull View itemView, FragmentCommunication Communicator) {
            super(itemView);

            textViewSearchName = itemView.findViewById(R.id.textViewSearchName);
            textviewSearchDescription = itemView.findViewById(R.id.textviewSearchDescription);
            textViewSearchPrice = itemView.findViewById(R.id.textViewSearchPrice);
            mCommunication = Communicator;
            selectProductButton = itemView.findViewById(R.id.selectProductButton);
            selectProductButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i("resultsAdapterOnClick", String.valueOf(productList.get(getAdapterPosition()).getCategory()));

            Log.i("resultsAdapterOnClick", Integer.toString((productList.get(getAdapterPosition()).getProduct_id())));
            mCommunication.respond(productList.get(getAdapterPosition()).getProduct_id());
        }
    }
}


