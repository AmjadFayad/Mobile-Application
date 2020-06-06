package amjad.fayad.dev.assessment4.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import amjad.fayad.dev.assessment4.DetailsActivity;
import amjad.fayad.dev.assessment4.R;
import amjad.fayad.dev.assessment4.models.Currency;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.CurrencyHolder> {

    private List<Currency> currencies;

    public CurrencyRVAdapter(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        return new CurrencyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder holder, int position) {
        final Currency currency = currencies.get(position);
        holder.bind(currency);
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class CurrencyHolder extends RecyclerView.ViewHolder {

        private ImageView currencyImgView;
        private TextView currencyTxtView;
        private TextView bdlValTxtView;
        private TextView realValTxtView;
        private TextView gapTxtView;

        public CurrencyHolder(@NonNull View itemView) {
            super(itemView);

            currencyTxtView = itemView.findViewById(R.id.tv_currency);
            bdlValTxtView = itemView.findViewById(R.id.tv_bdl);
            realValTxtView = itemView.findViewById(R.id.tv_real);
            gapTxtView = itemView.findViewById(R.id.tv_gap);

            currencyImgView = itemView.findViewById(R.id.iv_currency);
        }

        /**
         * Binds values of a Currency obj to its corresponding xml widgets
         * Set onClickListener to the itemView
         * @param c Currency to get vals
         */
        void bind(Currency c) {
            currencyTxtView.setText(c.getTitle());
            bdlValTxtView.setText(itemView.getContext().getResources().getString(R.string.price_according_to_bdl) + " " + c.getVbdl());
            realValTxtView.setText(itemView.getContext().getResources().getString(R.string.real_price) + " " + c.getRealvalue());
            gapTxtView.setText(itemView.getContext().getResources().getString(R.string.gap) + " " + c.getGap());

            Picasso.get()
                    .load(c.getImage())
                    .fit()
                    .into(currencyImgView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    Intent i = new Intent(itemView.getContext(), DetailsActivity.class);
                    i.putExtra("name", c.getTitle());
                    i.putExtra("vbdl", c.getVbdl());
                    i.putExtra("real", c.getRealvalue());
                    i.putExtra("image", c.getImage());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }

    /**
     * After a refresh adds new currencies fetched from the API, if any, to the current list
     * in the adapter and notifies that the dataset has changed.
     * @param newCurrencies list of currencies to append
     */
    public void appendCurrencies(List<Currency> newCurrencies) {
        currencies.addAll(newCurrencies);
        notifyDataSetChanged();
    }
}
