package ir.ac.kntu.patogh.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.gson.Gson;

import java.util.ArrayList;

import ir.ac.kntu.patogh.ApiDataTypes.TypeFavDorehamiAdd;
import ir.ac.kntu.patogh.Interfaces.PatoghApi;
import ir.ac.kntu.patogh.R;
import ir.ac.kntu.patogh.Utils.Event;
import ir.ac.kntu.patogh.Utils.FavoriteEvent;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterViewHolder> {

    private ArrayList<FavoriteEvent> eventsData;
    private long mLastClickTime = System.currentTimeMillis();
    private static final long CLICK_TIME_INTERVAL = 1000;
    private final FavoriteAdapter.FavoriteAdapterOnClickHandler mClickHandler;
    long now;
    private SharedPreferences sharedPreferences;
    private Context context;

    public void clear() {
        eventsData.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<FavoriteEvent> list) {
        eventsData.addAll(list);
        notifyDataSetChanged();
    }

    public interface FavoriteAdapterOnClickHandler {
        void onClick(FavoriteEvent weatherForDay);
    }

    public FavoriteAdapter(FavoriteAdapter.FavoriteAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
        eventsData = new ArrayList<>();
    }

    public class FavoriteAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView eventNameTextView;
        TextView eventDateTextView;
        TextView eventCapacityTextView;
        ImageView eventImage;
        ConstraintLayout constraintLayout;


        public FavoriteAdapterViewHolder(View view) {
            super(view);
            eventNameTextView = view.findViewById(R.id.tv_favorite_card_event_name);
            eventDateTextView = view.findViewById(R.id.tv_favorite_card_date);
            eventCapacityTextView = view.findViewById(R.id.tv_favorite_card_capacity);
            eventImage = view.findViewById(R.id.img_favorite_card_event_image);
            constraintLayout = view.findViewById(R.id.constraintLayout_favorite_card);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            now = System.currentTimeMillis();
            if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
                return;
            }
            mLastClickTime = now;
            int adapterPosition = getAdapterPosition();
            FavoriteEvent selectedEvent = eventsData.get(adapterPosition);
            mClickHandler.onClick(selectedEvent);
        }
    }


    @Override
    public FavoriteAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        sharedPreferences = context
                .getSharedPreferences("TokenPref", 0);
        int layoutIdForListItem = R.layout.favorite_card;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new FavoriteAdapter.FavoriteAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.FavoriteAdapterViewHolder eventAdapterViewHolder, int position) {
        FavoriteEvent selectedEvent = eventsData.get(position);
        eventAdapterViewHolder.eventNameTextView.setText(selectedEvent.getName());
        eventAdapterViewHolder.eventDateTextView.setText(String.format("%s"
                , selectedEvent.getDate()));
        eventAdapterViewHolder.eventCapacityTextView.setText(String.format("%s"
                ,selectedEvent.getCapacity()));
    }

    private void downloadThumbnail(String id, ImageView eventImage) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eg.potatogamers.ir:7701/api/")
                .build();
        Gson gson = new Gson();
        PatoghApi patoghApi = retrofit.create(PatoghApi.class);
        String token = sharedPreferences.getString("Token", "none");
        if (token.equals("none")) {
            return;
        }

        TypeFavDorehamiAdd te;
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json")
                , gson.toJson(te = new TypeFavDorehamiAdd(id)
                ));

        Log.d("@@@@@@@@@", te.toString());
        patoghApi.downloadThumbnail("Bearer " + token, requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    System.out.println("downloaded");
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    Glide.with(context)
                            .load(bmp)
                            .transform(new RoundedCorners(6))
                            .into(eventImage);
                } else {
                    System.out.println("failed to download");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("no responseeeee");
            }
        });
    }


    @Override
    public int getItemCount() {
        if (null == eventsData) return 0;
        return eventsData.size();
    }


    public void setEventData(ArrayList<FavoriteEvent> eventData) {
        eventsData = eventData;
        notifyDataSetChanged();
    }



}
