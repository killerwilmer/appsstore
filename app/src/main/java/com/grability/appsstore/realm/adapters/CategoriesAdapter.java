package com.grability.appsstore.realm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grability.appsstore.R;
import com.grability.appsstore.activity.ListAppsActivity;
import com.grability.appsstore.realm.RealmController;
import com.grability.appsstore.realm.model.Category;

import io.realm.Realm;

/**
 * Created by killerwilmer on 13/01/17.
 */

public class CategoriesAdapter extends RealmRecyclerViewAdapter<Category> {

    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public CategoriesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_categories, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        final Category category = getItem(position);
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        holder.textLabel.setText(category.getLabel());

        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myListAppsIntent = new Intent(context, ListAppsActivity.class);
                myListAppsIntent.putExtra("category_id", category.getId().toString());
                context.startActivity(myListAppsIntent);
            }
        });
    }

    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView textLabel;

        public CardViewHolder(View itemView) {
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_categories);
            textLabel = (TextView) itemView.findViewById(R.id.text_categories_label);
        }
    }
}
