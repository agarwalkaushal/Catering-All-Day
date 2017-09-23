package order.now.cateringallday;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by minos.ai on 12/09/17.
 */

public class TabMonday extends Fragment {
    ImageView showmore;
    TextView item1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_monday, container, false);

        showmore=(ImageView)rootView.findViewById(R.id.showmore);
        item1=(TextView)rootView.findViewById(R.id.textView4);
        showmore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    showmore.performLongClick();
                }
        });
        registerForContextMenu(showmore);

        return rootView;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Your Dish");
        menu.add(0, v.getId(), 0, "Dhaba dal");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Maa ki dal");
        menu.add(0, v.getId(), 0, "Gujrati dal");
        menu.add(0, v.getId(), 0, "Tadka dal");
        menu.add(0, v.getId(), 0, "Dal Makhani");
        menu.add(0, v.getId(), 0, "Pachmela dal");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        Log.e("Value is: ","item is selected");
        if(item.getTitle()=="Dhaba dal"){
                item1.setText("Dhaba dal");
        }
        else if(item.getTitle()=="Maa ki dal"){
            item1.setText("Maa ki dal");
        }
        else if(item.getTitle()=="Gujrati dal") {
            item1.setText("Gujrati dal");
        }
        else if(item.getTitle()=="Tadka dal") {
            item1.setText("Tadka dal");
        }
        else if(item.getTitle()=="Dal Makhani") {
            item1.setText("Dal Makhani");
        }
        else if(item.getTitle()=="Pachmela dal") {
            item1.setText("Pachmela dal");
        }else{
            return false;
        }
        return true;
    }

}
