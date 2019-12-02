package com.ramireddy.ramwikitask.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramireddy.ramwikitask.DetailsActivity;
import com.ramireddy.ramwikitask.MainActivity;
import com.ramireddy.ramwikitask.R;
import com.ramireddy.ramwikitask.model.Page;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WikiAdapter  extends RecyclerView.Adapter<WikiAdapter.ViewData> {
    private Context ctx;
    private List<Page> pages;
    public WikiAdapter(MainActivity mainActivity, List<Page> pages) {
        this.ctx=mainActivity;
        this.pages=pages;
    }

    @NonNull
    @Override
    public WikiAdapter.ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(ctx).inflate(R.layout.wikidesign,parent,false);
        return new ViewData(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final WikiAdapter.ViewData holder, final int position)
    {
        holder.tTitle.setText("Title : "+pages.get(position).getTitle());
        holder.tDesc.setText("Description : "+pages.get(position).getDescription());
        if(pages.get(position).getThumbnail()!=null) {
            Picasso.get().load(pages.get(position).getThumbnail().getSource()).into(holder.wikiImage);
        }else {
            Picasso.get().load(R.drawable.wikimg).into(holder.wikiImage);
        }
        holder.wikiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgUrl="";
                String data="";
                int po=holder.getAdapterPosition();
                Intent detailsIntent=new Intent(ctx, DetailsActivity.class);
                if(pages.get(po).getThumbnail()!=null) {
                    imgUrl=pages.get(po).getThumbnail().getSource();
                }
                data="Title : "+pages.get(po).getTitle()+"\n"+"Touched : "+pages.get(po).getTouched()+"\n"+"Description : "+pages.get(po).getDescription();
                detailsIntent.putExtra("url",imgUrl);
                detailsIntent.putExtra("data",data);
                detailsIntent.putExtra("fullurl",pages.get(po).getFullurl());
                ctx.startActivity(detailsIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public void setPages(List<Page> words) {
        pages=words;
        notifyDataSetChanged();
    }

    public class ViewData extends RecyclerView.ViewHolder {

        private TextView tTitle,tDesc;
        private ImageView wikiImage;

        public ViewData(@NonNull View itemView) {
            super(itemView);
            tTitle=itemView.findViewById(R.id.title);
            tDesc=itemView.findViewById(R.id.desciption);
            wikiImage=itemView.findViewById(R.id.image);


        }
    }



}
