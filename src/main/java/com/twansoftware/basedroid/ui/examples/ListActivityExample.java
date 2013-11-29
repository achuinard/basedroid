package com.twansoftware.basedroid.ui.examples;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.twansoftware.basedroid.entity.Animal;

/**
 * This should probably have been done with a ListFragment instead but keeping it simple for now.
 */
public class ListActivityExample extends ListActivity implements AdapterView.OnItemClickListener {
    private static final Animal[] ANIMALS = {
            new Animal("Cat", "Meowwww!"),
            new Animal("Dog", "Woof woof!"),
            new Animal("Cow", "Mooooooo!"),
    };


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The array adapter will use the toString() of the Animal class for the contents of each row
        getListView().setAdapter(new ArrayAdapter<Animal>(this, android.R.layout.simple_list_item_1, ANIMALS));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        final Animal chosenAnimal = (Animal) adapterView.getItemAtPosition(position);
        Toast.makeText(this, chosenAnimal.getSound(), Toast.LENGTH_LONG).show();
    }
}
