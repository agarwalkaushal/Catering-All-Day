package order.now.cateringallday;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by minos.ai on 12/09/17.
 */

public class TabMonday extends Fragment {
    ImageView showmore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_monday, container, false);

        showmore=(ImageView)rootView.findViewById(R.id.showmore);
        showmore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                registerForContextMenu(v);
            }
        });
        return rootView;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Your Dish");
        menu.add(0, v.getId(), 0, "Dhaba dal");
        menu.add(0, v.getId(), 0, "Maa ki dal");
        menu.add(0, v.getId(), 0, "Gujrati dal");
        menu.add(0, v.getId(), 0, "Tadka dal");
        menu.add(0, v.getId(), 0, "Dal Makhani");
        menu.add(0, v.getId(), 0, "Pachmela dal");

    }

}
