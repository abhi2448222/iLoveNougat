package lenovo.example.com.ilovenougat.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lenovo.example.com.ilovenougat.R;
import lenovo.example.com.ilovenougat.adapters.ResultsAdapter;
import lenovo.example.com.ilovenougat.model.Result;
import lenovo.example.com.ilovenougat.model.ZapposResult;
import lenovo.example.com.ilovenougat.utils.RequestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsActivity extends AppCompatActivity implements ResultsAdapter.ItemOnClickListener {

    public static final String RESULTS_INTENT_KEY = "resultsListIntentKey";
    public static final String RESULTS_INTENT_SEARCH_KEY = "resultsSearchQuery";

    private Toolbar resultsToolbar;
    private RecyclerView resultsRecyclerView;

    List<Result> resultList;
    private ResultsAdapter resultsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultsToolbar = (Toolbar) findViewById(R.id.results_toolbar);
        resultsRecyclerView = (RecyclerView) findViewById(R.id.search_results_recycler_view);

        setSupportActionBar(resultsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchString = getIntent().getStringExtra(RESULTS_INTENT_SEARCH_KEY);
        resultList = getIntent().getParcelableArrayListExtra(RESULTS_INTENT_KEY);

        getSupportActionBar().setTitle("Showing results for: \"" + searchString + "\"");

        resultsRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        resultsRecyclerView.setLayoutManager(layoutManager);

        resultsAdapter = new ResultsAdapter(resultList, this);
        resultsAdapter.setItemOnClickListener(this);
        resultsRecyclerView.setAdapter(resultsAdapter);

    }

    @Override
    public void onItemClick(Result res) {
        String productId = res.getProductId();
        String price = res.getPrice().substring(1);
        String productUrl = res.getProductUrl();


        call6PmEndpoint(productId, Double.parseDouble(price), productUrl);
    }

    //Calling 6PM REST API
    private void call6PmEndpoint(final String productId, final double price, final String productUrl) {
        Call<ZapposResult> call = RequestService.getService(getResources().getString(R.string.base_url_6pm))
                .getSearchResults(searchString, getResources().getString(R.string.APIKey6pm));
        call.enqueue(new Callback<ZapposResult>() {
            @Override
            public void onResponse(Call<ZapposResult> call, Response<ZapposResult> response) {
                if (response.body().getResults().size() == 0) {
                    Toast.makeText(getApplicationContext(), "No results found in 6pm.com", Toast.LENGTH_LONG).show();
                } else {

                    checkIfItemExists(response.body(), productId, price, productUrl);
                }

            }

            @Override
            public void onFailure(Call<ZapposResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No results found in 6pm.com", Toast.LENGTH_LONG).show();
            }
        });
    }

    //This function notifies the user whether the product is cheaper on 6pm or not
    //It also provides a share button with the product url
    private void showCompareDialog(boolean flag, double resultPrice, final String productUrl) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.compare_dialog, null);

        TextView compareAlert = (TextView) dialogView.findViewById(R.id.compare_alert);
        Button shareBut = (Button) dialogView.findViewById(R.id.share_alert);
        shareBut.setText("Share this product");
        dialogBuilder.setView(dialogView);

        if (flag == true)
            compareAlert.setText("The corresponding product is available at $" + String.valueOf(resultPrice) + " at 6pm.com");
        else {


            compareAlert.setText("The corresponding product is not found in 6PM or the product is cheaper in Zappos");
        }


        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        shareBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, productUrl);

                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share via"));
            }
        });
    }

    //Check whether the item exists in the JSON response obtained from 6PM
    private void checkIfItemExists(ZapposResult response, String productId, double originalPrice, String productUrl) {

        boolean checkWhetherDialogIsAlreadyCalled = false;
        List<Result> obtainedResults = response.getResults();


        for (int i = 0; i < obtainedResults.size(); i++) {


            //check whether the product exists and is cheaper on 6pm
            if (productId.equals(obtainedResults.get(i).getProductId())) {

                String price = obtainedResults.get(i).getPrice().substring(1);


                if (originalPrice > Double.parseDouble(price)) {

                    showCompareDialog(true, Double.parseDouble(price), productUrl);
                    checkWhetherDialogIsAlreadyCalled = true;
                    break;
                }
            } else {

            }

        }
        //The product doesnt exist in 6pm
        if (checkWhetherDialogIsAlreadyCalled == false) {
            showCompareDialog(false, 0.0, productUrl);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
