package lenovo.example.com.ilovenougat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import lenovo.example.com.ilovenougat.R;
import lenovo.example.com.ilovenougat.model.Result;
import lenovo.example.com.ilovenougat.model.ZapposResult;
import lenovo.example.com.ilovenougat.utils.RequestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchEditText;
    Button goButton;
    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        goButton = (Button) findViewById(R.id.search_btn);
        infoTextView = (TextView) findViewById(R.id.info_text_view);

        goButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        dismissKeyboard();

        String searchQuery = searchEditText.getText().toString();

        if(!validateSearchString(searchQuery)){
            makeSearchQuery(searchQuery);
        }else{
            infoTextView.setText("Enter a valid search term!");
        }
    }

    //Calling Zappos REST API
    private void makeSearchQuery(String searchString) {

        Call<ZapposResult> call = RequestService.getService(getResources().getString(R.string.base_url_zappos))
                .getSearchResults(searchString, getResources().getString(R.string.APIKeyZappos));
        call.enqueue(new Callback<ZapposResult>() {
            @Override
            public void onResponse(Call<ZapposResult> call, Response<ZapposResult> response) {
                if(response.body().getResults().size() == 0){
                    infoTextView.setText("No results found, try another term!");
                }else{
                    showSearchResults(response.body());
                }

            }

            @Override
            public void onFailure(Call<ZapposResult> call, Throwable t) {
                infoTextView.setText("Something went wrong. Try again!");
            }
        });
    }

    // This method calls ResultsActivity class where the response obtained from the webservice is dispalyed in a RecyclerView
    private void showSearchResults(ZapposResult response){
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putParcelableArrayListExtra(ResultsActivity.RESULTS_INTENT_KEY,(ArrayList<Result>) response.getResults());
        intent.putExtra(ResultsActivity.RESULTS_INTENT_SEARCH_KEY, response.getOriginalTerm());
        startActivity(intent);
    }

    private boolean validateSearchString(String searchString){
        return searchString.isEmpty();
    }

    private void dismissKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
    }
}
