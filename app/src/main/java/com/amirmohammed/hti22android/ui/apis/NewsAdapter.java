package com.amirmohammed.hti22android.ui.apis;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amirmohammed.hti22android.R;
import com.bumptech.glide.Glide;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    List<ArticlesItem> articles;

    public NewsAdapter(List<ArticlesItem> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        ArticlesItem articleItem = articles.get(position);

        holder.textViewTitle.setText(articleItem.getTitle());

        Glide.with(holder.itemView).load(articleItem.getImageUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ArticleDetailsActivity.class);
                intent.putExtra("articleUrl", articleItem.getUrl());
                view.getContext().startActivity(intent);

//                String url = articleItem.getUrl();
//
//                if (url.startsWith("https://") || url.startsWith("http://")) {
//                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                }
//                else {
//                    Toast.makeText(view.getContext(), "Invalid URL", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivNews);
            textViewTitle = itemView.findViewById(R.id.tvNewsTitle);
        }
    }

}
