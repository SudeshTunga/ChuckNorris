package com.example.chucknorris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chucknorris.entities.QuoteLoreResponse;
import com.example.chucknorris.entities.QuoteService;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView Quote;
    private ImageButton Meme;

    //this application accomplishes 2 main things:

    //- whenever a meme is clicked a new quote ir retrieved from the chuck norris api under the 'dev' category.
    //- this is done in the getQuote method whereby retrofit is used to create a call for this quote based on the 'dev' category.
    //- an enqueue is then used to ensure that the call is done asynchronously.
    //- whenever a meme is clicked a randomised number between 1-8 is created.
    //- based on this number a random generic chuck norris meme from the internet is generated.
    //- Sources for memes:
    //- meme 1: therichestimages. 2020. therichestimages. [ONLINE] Available at: https://static0.therichestimages.com/wordpress/wp-content/uploads/2019/12/feature-chuck-norris.jpg. [Accessed 4 April 2020].
    //- meme 2: kym-cdn. 2020. kym-cdn. [ONLINE] Available at: https://i.kym-cdn.com/entries/icons/facebook/000/000/244/nope.jpg. [Accessed 4 April 2020].
    //- meme 3: blazepress. 2020. blazepress. [ONLINE] Available at: https://blazepress.com/.image/t_share/MTI4OTk1Mjc0MzI3NzMwODE5/1.jpg. [Accessed 4 April 2020].
    //- meme 4: pinimg. 2020. pinimg. [ONLINE] Available at: https://i.pinimg.com/originals/82/b9/b9/82b9b9404b61e8ee86f2955dcb709e78.jpg. [Accessed 4 April 2020].
    //- meme 5: pinimg. 2020. pinimg. [ONLINE] Available at: https://i.pinimg.com/originals/0e/f5/d1/0ef5d1796e354fb4d1b8079e914bd00e.jpg. [Accessed 4 April 2020].
    //- meme 6: liveabout. 2020. liveabout. [ONLINE] Available at: https://www.liveabout.com/thmb/dp3FT0kShwtnfL6YJVUkozAbsJk=/1063x650/filters:no_upscale():max_bytes(150000):strip_icc()/chucknorrisgrandcanyon-5ac2b512a18d9e00372a7ac7.JPG. [Accessed 4 April 2020].
    //- meme 7: pics.me. 2020. pics.me. [ONLINE] Available at: https://pics.me.me/when-chuck-norris-turned-18-his-parents-moved-out-chuck-norris-facts-49317822.png. [Accessed 4 April 2020].
    //- meme 8: pics. 2020. pics. [ONLINE] Available at: https://pics.me.me/chuck-norris-likes-his-meat-so-rare-he-only-eats-32939815.png. [Accessed 4 April 2020].

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Meme = findViewById(R.id.Meme);
        Quote = findViewById(R.id.Quote);

        Meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quote.setText("Getting Quote, Please Wait...");
                getQuote();
                getMeme();

            }
        });
        Quote.setText("Getting Quote, Please Wait...");
        getQuote();
        getMeme();

    }

    public void getQuote() {
        //setting up retrofit to get the random quote
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        QuoteService service = retrofit.create(QuoteService.class);
        Call<QuoteLoreResponse> call = service.getQuote("dev");

        //setting up the enqueue so that we can run the api call on a separate thread.

        call.enqueue(new Callback<QuoteLoreResponse>() {
            @Override
            public void onResponse(Call<QuoteLoreResponse> call, Response<QuoteLoreResponse> response) {

                QuoteLoreResponse quote = response.body();
                Quote.setText(quote.getValue());
            }

            @Override
            public void onFailure(Call<QuoteLoreResponse> call, Throwable t) {

                Log.d("Main Activity", "Failed to get quote");

            }
        });


    }

    public void getMeme() {
        Random rand = new Random();
        int min = 1;
        int max = 8;

        int x = rand.nextInt((max - min) + min);

        if (x == 1) {
            Meme.setImageResource(R.drawable.meme_1);
        } else if (x == 2) {

            Meme.setImageResource(R.drawable.meme_2);
        } else if (x == 3) {

            Meme.setImageResource(R.drawable.meme_3);
        } else if (x == 4) {

            Meme.setImageResource(R.drawable.meme_4);
        } else if (x == 5) {

            Meme.setImageResource(R.drawable.meme_5);
        } else if (x == 6) {

            Meme.setImageResource(R.drawable.meme_6);
        } else if (x == 7) {

            Meme.setImageResource(R.drawable.meme_7);
        } else if (x == 8) {

            Meme.setImageResource(R.drawable.meme_8);
        }

        Log.d("Main Activity", "random number: " + x);

    }


}
