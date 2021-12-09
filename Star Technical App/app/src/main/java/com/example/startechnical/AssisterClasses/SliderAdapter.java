package com.example.startechnical.AssisterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.startechnical.R;

import java.util.zip.Inflater;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {


    public SliderAdapter(Context context)
    {
        this.context = context;
    }

    Context context;


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView images;
        TextView  titles;
        TextView  description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

              images      = itemView.findViewById(R.id.slider_img); //findViewById(R.id.slider_img);
              titles      = itemView.findViewById(R.id.slider_title);
              description = itemView.findViewById(R.id.slider_desc);
        }
    }

    int slides_images[] =
            {
                    R.drawable.profile_info_illust,
                    R.drawable.addtask_illust,
                    R.drawable.employee_performance_illust,
                  //  R.drawable.search_in_app_illust,
                    R.drawable.finance_illust
            };

    int slides_titles[] =
            {
                    R.string.profile_info_slide_title,
                    R.string.add_task_slide_title,
                    R.string.employee_performance_slide_title,
                    R.string.search_in_app_slide_title,
                    R.string.finance_slide_title

            };
    int slides_descriptions[] =
            {
                    R.string.profile_info_slide_desc,
                    R.string.add_task_slide_desc,
                    R.string.employee_performance_slide_desc,
                    R.string.search_in_app_slide_desc,
                    R.string.finance_slide_desc

            };

    @NonNull
    @Override
    public SliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slides_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.ViewHolder holder, int position) {


        holder.images.setImageResource(slides_images[position]);
        holder.titles.setText(slides_titles[position]);
        holder.description.setText(slides_descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return slides_titles.length;
    }
}