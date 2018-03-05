package com.massive.voicetext;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;

/**
 * Created by minafaw on 2/24/2018.
 */

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {

    private ArrayList<TextModel> mtext;
    private Context mContext;

    public LinearAdapter(Context mcontext, ArrayList<TextModel> arrayList) {
        this.mContext = mcontext;
        this.mtext = arrayList;
    }

    @Override
    public LinearAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LinearAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(mtext.get(position).getText());

        holder.RemoveBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = mtext.get(position).getID();
                    if (!id.isEmpty()) {
                        Uri uri = Constant.Entry.FULL_URI;
                        uri = uri.buildUpon().appendPath(id).build();
                        mContext.getContentResolver().delete(uri, "Id =" + id, null);
                        holder.linearLayout.setVisibility(View.GONE);
                    } else
                        Toast.makeText(mContext, R.string.no_element_exit, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(mContext, R.string.refresh_the_page, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mtext.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button RemoveBttn;
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.FavTextView);
            RemoveBttn = itemView.findViewById(R.id.RemoveFromFav);
            linearLayout = itemView.findViewById(R.id.LinearContent);
        }
    }


}