package fabienflorek.slip.uk.smartlock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fabienflorek on 11/10/15.
 */
public class LockListAdapter extends ArrayAdapter<Lock> {
    private final Context context;
    private final List<Lock> locks;

    public LockListAdapter(Context context, List<Lock> locks) {
        super(context, -1, locks);
        this.context = context;
        this.locks = locks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_lists_locks_list_row, parent, false);
        TextView textViewFirst = (TextView) rowView.findViewById(R.id.list_row_locks_firstLine);
        TextView textViewSecond = (TextView) rowView.findViewById(R.id.list_row_locks_secondLine);
        ImageView imageViewIcon = (ImageView) rowView.findViewById(R.id.list_row_locks_iconlock);
        ImageView imageViewPlace = (ImageView) rowView.findViewById(R.id.list_row_locks_iconplace);
        LinearLayout linearLayoutCircle = (LinearLayout) rowView.findViewById(R.id.list_row_locks_circle);
        textViewFirst.setText(locks.get(position).getName());

        if (locks.get(position).status) {
            imageViewIcon.setImageResource(R.drawable.ic_lock_open_black_24dp);
            textViewSecond.setText("Open" + locks.get(position).getId());
        }
        else {
            imageViewIcon.setImageResource(R.drawable.ic_lock_black_24dp);
            if (locks.get(position).isStatusRequested())
                textViewSecond.setText("Opening" + locks.get(position).getId());
            else
                textViewSecond.setText("Locked" + locks.get(position).getId());
        }

        if (locks.get(position).status!=locks.get(position).isStatusRequested())
            imageViewIcon.setImageResource(R.drawable.ic_sync_black_24dp);

        switch (locks.get(position).getPlace()) {
            case 0 : {imageViewPlace.setImageResource(R.drawable.ic_home_white_24dp);break;}
            case 1 : {imageViewPlace.setImageResource(R.drawable.ic_store_white_24dp);break;}
            case 2 : {imageViewPlace.setImageResource(R.drawable.ic_perm_identity_white_24dp);break;}
        }

        switch (position % 3) {
            case 0 :  {linearLayoutCircle.getBackground().setLevel(0);break;}
            case 1 :  {linearLayoutCircle.getBackground().setLevel(1);break;}
            case 2 :  {linearLayoutCircle.getBackground().setLevel(2);break;}
        }



        return rowView;
    }
}
