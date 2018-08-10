package com.example.jude.inventoryapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.jude.inventoryapp.R;
import com.example.jude.inventoryapp.fragment.ItemFragment;

public class CategoryAdapter extends FragmentPagerAdapter {

   // private Context context; // TODO - removed
    private final String[] CATEGORIES ;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        //this.context = context;  //TODO - removed
        CATEGORIES = context.getResources().getStringArray(R.array.categories_array);

    }

    @Override
    public Fragment getItem(int position) {
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.setPosition(position);
        return itemFragment;
        // TODO - Edit
        /*switch (position) {
            case 0:
                return new TechnologyFragment();
            case 1:
                return new FoodFragment();
            case 2:
                return new BookFragment();
            case 3:
                return new FurnishingFragment();
            default:
                throw new IllegalArgumentException("Fragment position " + position + " is not accepted");
        }*/
    }

    @Override
    public int getCount() {
        return CATEGORIES.length; // TODO - EDIT you need 5 fragments not 4 !
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return CATEGORIES[position];
        // TODO - remove
        /*switch (position) {

            case 0:
                return "Technology";

            case 1:
                return "Food";

            case 2:
                return "Book";

            case 3:
                return "Furnishing";

            default:
                throw new IllegalArgumentException("Fragment position " + position + " is not accepted");
        }*/
    }
}
