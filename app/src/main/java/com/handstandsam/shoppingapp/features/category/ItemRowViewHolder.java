package com.handstandsam.shoppingapp.features.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.handstandsam.shoppingapp.R;
import com.handstandsam.shoppingapp.features.home.ColorInts;
import com.handstandsam.shoppingapp.features.itemdetail.ItemDetailActivity;
import com.handstandsam.shoppingapp.features.itemdetail.ItemDetailPresenter;
import com.handstandsam.shoppingapp.models.Item;

import butterknife.BindView;
import butterknife.ButterKnife;


class ItemRowViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text)
    TextView textView;

    @BindView(R.id.image)
    AppCompatImageView imageView;

    private Item item;

    public ItemRowViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = itemView.getContext();
                Intent intent = new Intent(view.getContext(), ItemDetailActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable(ItemDetailPresenter.BUNDLE_PARAM_ITEM, item);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
    }

    public void bindData(Item item, int position) {
        this.item = item;

        String imageUrl = item.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(imageView.getContext()).load(item.getImage()).into(imageView);
        } else {
            itemView.setBackgroundResource(ColorInts.getColor(position));
        }

        textView.setText(this.item.getLabel());
    }
}
