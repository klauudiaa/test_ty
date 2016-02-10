package pl.amistad.tychy.adapter;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.amistad.tychy.R;
import pl.amistad.tychy.api.model.NominatimObject;

/**
 * Created by klaud on 2016-02-10.
 */
public class ShowPlaceSuggestionAdapter extends SimpleCursorAdapter {
    private NominatimObject[] mSuggestions;
    private LayoutInflater mInflater;

    public ShowPlaceSuggestionAdapter(Context context, NominatimObject[] suggestions) {
        super(context, R.layout.row_suggestions, null,null, null, 0);
        this.mSuggestions = suggestions;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuggestionHolder holder = null;
        NominatimObject suggestion = getItem(position);
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.row_suggestions, parent, false);
            holder = new SuggestionHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (SuggestionHolder) convertView.getTag();
        }

        holder.suggestion_name.setText(suggestion.getDisplayName());

        return convertView;
    }

    @Override
    public int getCount() {
        return mSuggestions.length;
    }

    @Override
    public NominatimObject getItem(int position) throws IndexOutOfBoundsException{
        return mSuggestions[position];
    }

    public void setData(NominatimObject[] data) {
        this.mSuggestions = data;
    }

    private static class SuggestionHolder {
        @Bind(R.id.suggestion_name)
        TextView suggestion_name;

        @Bind(R.id.suggestion_address)
        TextView suggestion_address;

        public SuggestionHolder(View view){
            ButterKnife.bind(this, view);
        }
    }


}
