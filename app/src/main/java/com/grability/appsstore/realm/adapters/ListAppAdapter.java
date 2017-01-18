package com.grability.appsstore.realm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.grability.appsstore.R;
import com.grability.appsstore.activity.DetailAppActivity;
import com.grability.appsstore.realm.RealmController;
import com.grability.appsstore.realm.model.App;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

/**
 * Created by killerwilmer on 17/01/17.
 */

public class ListAppAdapter extends RealmRecyclerViewAdapter<App>  {

    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public ListAppAdapter(Context context) {

        this.context = context;
    }

    @Override
    public ListAppAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_apps, parent, false);
        return new ListAppAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        final App app = getItem(position);
        final ListAppAdapter.CardViewHolder holder = (ListAppAdapter.CardViewHolder) viewHolder;

        holder.textLabel.setText(app.getIm_name());
        Picasso.with(context).load(app.getImages().get(2).getLabel()).into(holder.imageApp);

        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, app.getIm_name() , Toast.LENGTH_SHORT).show();
                Intent myListAppsIntent = new Intent(context, DetailAppActivity.class);
                myListAppsIntent.putExtra("app_id", app.getId().toString());
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
        public ImageView imageApp;


        public CardViewHolder(View itemView) {
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_apps);
            textLabel = (TextView) itemView.findViewById(R.id.app_name);
            imageApp = (ImageView) itemView.findViewById(R.id.app_image);
        }
    }
}
