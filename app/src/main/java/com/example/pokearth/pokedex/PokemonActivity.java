package com.example.pokearth.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pokearth.databinding.PokemonActivityBinding;

import me.sargunvohra.lib.pokekotlin.model.PokemonType;

// this activity populates the pokemon_activity.xml when a pokemon is clicked
// in the list view of pokemon_list.xml
public class PokemonActivity extends AppCompatActivity {

    PokemonActivityBinding ui;

    int position;
    String name = "";
    Bitmap image;
    int height;
    int weight;
    String pokemonType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = PokemonActivityBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        position = getIntent().getIntExtra("position",0);
        name = getIntent().getStringExtra("name");
        image = getIntent().getParcelableExtra("image");
        height = getIntent().getIntExtra("height", 0);
        weight = getIntent().getIntExtra("weight", 0);
        pokemonType = getIntent().getStringExtra("type");
        setTitle(name);
        ui.pokemonNameView2.setText(name.toUpperCase());
        ui.pokemonImage.setImageBitmap(image);
        ui.pokemonNumber.setText("No. " + (position+1));
        ui.pokemonType.setText("TYPE:        " + pokemonType);
        ui.pokemonHeight.setText("HEIGHT:     " + String.format("%d", height) + "  ft");
        ui.pokemonWeight.setText("WEIGHT:     " + String.format("%d", weight) + "  lb");

        switch(position){
            default: ui.pokemonDescription.setText("No Description");
            case 0:
                ui.pokemonDescription.setText("There is a plant seed on its back right from the day this Pokémon is born. The seed slowly grows larger.");
                break;
            case 1:
                ui.pokemonDescription.setText("When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs.");
                break;
            case 2:
                ui.pokemonDescription.setText("Its plant blooms when it is absorbing solar energy. It stays on the move to seek sunlight.");
                break;
            case 3:
                ui.pokemonDescription.setText("It has a preference for hot things. When it rains, steam is said to spout from the tip of its tail.");
                break;
            case 4:
                ui.pokemonDescription.setText("It has a barbaric nature. In battle, it whips its fiery tail around and slashes away with sharp claws.");
                break;
            case 5:
                ui.pokemonDescription.setText("It spits fire that is hot enough to melt boulders. It may cause forest fires by blowing flames.");
                break;
            case 6:
                ui.pokemonDescription.setText("When it retracts its long neck into its shell, it squirts out water with vigorous force.");
                break;
            case 7:
                ui.pokemonDescription.setText("It is recognized as a symbol of longevity. If its shell has algae on it, that Wartortle is very old.");
                break;
            case 8:
                ui.pokemonDescription.setText("It crushes its foe under its heavy body to cause fainting. In a pinch, it will withdraw inside its shell.");
                break;
            case 9:
                ui.pokemonDescription.setText("For protection, it releases a horrible stench from the antenna on its head to drive away enemies.");
                break;
            case 10:
                ui.pokemonDescription.setText("It is waiting for the moment to evolve. At this stage, it can only harden, so it remains motionless to avoid attack.");
                break;
            case 11:
                ui.pokemonDescription.setText("In battle, it flaps its wings at great speed to release highly toxic dust into the air.");
                break;
            case 12:
                ui.pokemonDescription.setText("Beware of the sharp stinger on its head. It hides in grass and bushes where it eats leaves.");
                break;
            case 13:
                ui.pokemonDescription.setText("Able to move only slightly. When endangered, it may stick out its stinger and poison its enemy.");
                break;
            case 14:
                ui.pokemonDescription.setText("It has three poisonous stingers on its forelegs and its tail. They are used to jab its enemy repeatedly.");
                break;
            case 15:
                ui.pokemonDescription.setText("Very docile. If attacked, it will often kick up sand to protect itself rather than fight back.");
                break;
            case 16:
                ui.pokemonDescription.setText("This Pokémon is full of vitality. It constantly flies around its large territory in search of prey.");
                break;
            case 17:
                ui.pokemonDescription.setText("This Pokémon flies at Mach 2 speed, seeking prey. Its large talons are feared as wicked weapons.");
                break;
            case 18:
                ui.pokemonDescription.setText("Will chew on anything with its fangs. If you see one, you can be certain that 40 more live in the area.");
                break;
            case 19:
                ui.pokemonDescription.setText("Its hind feet are webbed. They act as flippers, so it can swim in rivers and hunt for prey.");
                break;
            case 20:
                ui.pokemonDescription.setText("Inept at flying high. However, it can fly around very fast to protect its territory.");
                break;
            case 21:
                ui.pokemonDescription.setText("A Pokémon that dates back many years. If it senses danger, it flies high and away, instantly.");
                break;
            case 22:
                ui.pokemonDescription.setText("The older it gets, the longer it grows. At night, it wraps its long body around tree branches to rest.");
                break;
            case 23:
                ui.pokemonDescription.setText("The frightening patterns on its belly have been studied. Six variations have been confirmed.");
                break;
        }


    }

    public void onReturnClicked(View view){
        Intent i = new Intent();
        i.putExtra("position", position);
        i.putExtra("name", name);
        setResult(RESULT_OK,i);
        finish();
    }

}
