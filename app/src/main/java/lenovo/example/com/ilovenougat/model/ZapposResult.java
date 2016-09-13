package lenovo.example.com.ilovenougat.model;

/**
 * Created by Lenovo on 9/11/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ZapposResult {

    @SerializedName("originalTerm")
    @Expose
    private String originalTerm;
    @SerializedName("currentResultCount")
    @Expose
    private String currentResultCount;
    @SerializedName("totalResultCount")
    @Expose
    private String totalResultCount;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("statusCode")
    @Expose
    private String statusCode;

    /**
     *
     * @return
     * The originalTerm
     */
    public String getOriginalTerm() {
        return originalTerm;
    }

    /**
     *
     * @param originalTerm
     * The originalTerm
     */
    public void setOriginalTerm(String originalTerm) {
        this.originalTerm = originalTerm;
    }

    /**
     *
     * @return
     * The currentResultCount
     */
    public String getCurrentResultCount() {
        return currentResultCount;
    }

    /**
     *
     * @param currentResultCount
     * The currentResultCount
     */
    public void setCurrentResultCount(String currentResultCount) {
        this.currentResultCount = currentResultCount;
    }

    /**
     *
     * @return
     * The totalResultCount
     */
    public String getTotalResultCount() {
        return totalResultCount;
    }

    /**
     *
     * @param totalResultCount
     * The totalResultCount
     */
    public void setTotalResultCount(String totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

    /**
     *
     * @return
     * The term
     */
    public String getTerm() {
        return term;
    }

    /**
     *
     * @param term
     * The term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     * The statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}