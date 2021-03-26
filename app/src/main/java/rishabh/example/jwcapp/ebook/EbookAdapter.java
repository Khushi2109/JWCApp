package rishabh.example.jwcapp.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import rishabh.example.jwcapp.R;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {

    private Context context;
    private List<EbookData> list;
    private InterstitialAd interstitialAd;

    public EbookAdapter(Context context, List<EbookData> list, InterstitialAd interstitialAd) {
        this.context = context;
        this.list = list;
        this.interstitialAd = interstitialAd;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout, parent, false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, final int position) {

        holder.ebookName.setText(list.get(position).getPDFTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        interstitialAd.loadAd(new AdRequest.Builder().build());
                        super.onAdClosed();
                        Intent intent = new Intent(context, pdfViewerActivity.class);
                        intent.putExtra("PDFUrl", list.get(position).getPDFUrl());
                        context.startActivity(intent);
                    }
                });

                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                    return;
                }

                Intent intent = new Intent(context, pdfViewerActivity.class);
                intent.putExtra("PDFUrl", list.get(position).getPDFUrl());
                context.startActivity(intent);
            }
        });

        holder.ebook_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        interstitialAd.loadAd(new AdRequest.Builder().build());
                        super.onAdClosed();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(list.get(position).getPDFUrl()));
                        context.startActivity(intent);
                    }
                });

                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPDFUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void FilteredList(ArrayList<EbookData> filterList) {
        list = filterList;
        notifyDataSetChanged();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView ebookName;
        private ImageView ebook_download;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);

            ebookName = itemView.findViewById(R.id.ebookName);
            ebook_download = itemView.findViewById(R.id.ebook_download);
        }
    }
}
